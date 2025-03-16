package com.dell.golf.golfsnservice.handler;


import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dell.golf.golfsnservice.common.AppConstants;
import com.dell.golf.golfsnservice.dao.SnAckServiceDao;
import com.dell.golf.golfsnservice.model.AsnAckVO;
import com.dell.golf.golfsnservice.model.SnAckResponse;
import com.dell.golf.golfsnservice.model.SnAckVO;
import com.dell.golf.golfsnservice.util.Base64Util;
import com.dell.golf.golfsnservice.service.*;

@Service
public class SnAckHandler {
 
    private static final Logger logger = LoggerFactory.getLogger(SnAckHandler.class);
 
    @Autowired
    private SnAckServiceDao snackServiceDao;
 
    public SnAckResponse processSnAckData(AsnAckVO asnAckVO) {
        logger.info("SnAckHandler : processSnAckData Started...");
        SnAckResponse snAckResponse = new SnAckResponse();
 
        try {
            HashMap<String, String> map = snackServiceDao.processProcedureCall(asnAckVO);
 
            if (map != null && !map.isEmpty()) {
                String xmlString = generateXMLFromProcedureData(asnAckVO, map);
                SnAckVO snack = new SnAckVO();
                snack.setSn_number(map.get("PO_SN_NUMBER"));
                snack.setCorrelationID(map.get("PO_VENDOR_SN_MESSAGE_ID"));
                snack.setMessage(Base64Util.encodeToBase64(xmlString));
                snAckResponse.setSnACKResponse(snack);
            }
        } catch (Exception ex) {
            logger.error("SnAckHandler : processSnAckData Error occurred {}", ex.toString());
        }
 
        logger.info("SnAckHandler : processSnAckData Completed...");
        return snAckResponse;
    }
 
    private String generateXMLFromProcedureData(AsnAckVO asnAckVO, HashMap<String, String> map) throws Exception {
        ShipNotificationAck ack = new ShipNotificationAck();
 
        // ✅ MESSAGE_HEADER
        ShipNotificationAck.MessageHeader header = new ShipNotificationAck.MessageHeader();
        header.setSenderId("GOLF_DAO");
        header.setReceiverId("CEVADELL");
        header.setMessageId(map.get("PO_MESSAGE_ID"));
        header.setMessageType("SHIP_NOTIFICATION_ACK");
        header.setVersion("4.0");
        header.setCorrelationId(map.get("PO_VENDOR_SN_MESSAGE_ID"));
        header.setCreationTs(map.get("PO_CREATION_TS"));
        ack.setMessageHeader(header);
 
        // ✅ SN_HEADER
        ShipNotificationAck.SnHeader snHeader = new ShipNotificationAck.SnHeader();
        snHeader.setSnNumber(map.get("PO_SN_NUMBER"));
        snHeader.setStatus(map.get("PO_STATUS"));
 
        // ❗ Add SN_HEADER Errors Only if Status = "N"
        if ("N".equals(map.get("PO_STATUS"))) {
            ShipNotificationAck.Errors snErrors = new ShipNotificationAck.Errors();
            List<ShipNotificationAck.ErrorDetail> errorList = new ArrayList<>();
            if (map.containsKey("PO_ERROR_TYPE") && map.containsKey("PO_ERROR_REASON")) {
                errorList.add(new ShipNotificationAck.ErrorDetail(map.get("PO_ERROR_TYPE"), map.get("PO_ERROR_REASON")));
            }
            snErrors.setError(errorList);
            snHeader.setErrors(snErrors);
        }
 
        ack.setSnHeader(snHeader);
 
        // ✅ SHIP_NOTIFICATION_LINES
        ShipNotificationAck.ShipNotificationLines shipLines = new ShipNotificationAck.ShipNotificationLines();
        List<ShipNotificationAck.ShipNotificationLine> lineList = new ArrayList<>();
 
        // ❗ Fetch ASN Load IDs from DB (Stored Procedure Call)
        List<Map<String, String>> shipLinesData = snackServiceDao.getShipNotificationLines(asnAckVO);
 
        for (Map<String, String> lineData : shipLinesData) { // ✅ Using for-each loop
            ShipNotificationAck.ShipNotificationLine line = new ShipNotificationAck.ShipNotificationLine();
            line.setLoadId(lineData.get("LOADID"));
            line.setStatus(lineData.get("STATUS"));
 
            // ❗ Add ERROR if STATUS = "N"
            if ("N".equals(lineData.get("STATUS"))) {
                ShipNotificationAck.Errors lineErrors = new ShipNotificationAck.Errors();
                List<ShipNotificationAck.ErrorDetail> lineErrorList = new ArrayList<>();
 
                if (lineData.containsKey("ERROR_TYPE") && lineData.containsKey("ERROR_REASON")) {
                    lineErrorList.add(new ShipNotificationAck.ErrorDetail(lineData.get("ERROR_TYPE"), lineData.get("ERROR_REASON")));
                }
                lineErrors.setError(lineErrorList);
                line.setErrors(lineErrors);
            }
 
            lineList.add(line);
        }
 
        shipLines.setShipNotificationLine(lineList);
        ack.setShipNotificationLines(shipLines);
 
        // ✅ Convert Java to XML
        JAXBContext context = JAXBContext.newInstance(ShipNotificationAck.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
 
        StringWriter sw = new StringWriter();
        marshaller.marshal(ack, sw);
        return sw.toString();
    }
}
 
