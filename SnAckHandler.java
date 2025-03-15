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
	SnAckServiceDao snackServiceDao;

	public SnAckResponse processSnAckData(AsnAckVO asnAckVO) {

		logger.info("SnAckHandler : processAsnAckData Started...");
		HashMap<String, String> map = null;
		
		SnAckResponse snAckResponse = new SnAckResponse();
		try {
			map = snackServiceDao.processProcedureCall(asnAckVO);
			SnAckVO snack = new SnAckVO();
			if (map != null || !map.isEmpty()) {
				String xmlString = getXMLStringMessage(asnAckVO,map);
				snack.setSn_number("PO_SN_NUMBER");
				snack.setCorrelationID(map.get("PO_VENDOR_SN_MESSAGE_ID"));
				snack.setMessage(Base64Util.encodeToBase64(xmlString));
				snAckResponse.setSnACKResponse(snack);
			}else {
				snack.setSn_number(null);
				snack.setCorrelationID(null);
				snack.setMessage(null);
				snAckResponse.setSnACKResponse(snack);
			}
		} catch (Exception ex) {
			logger.info("SnAckHandler : processAsnAckData Error occured {}", ex.toString());
			return snAckResponse;
		}
		logger.info("SnAckHandler : processAsnAckData Completed...");
		return snAckResponse;
	}

	public String getXMLStringMessage(AsnAckVO asnAckVO,HashMap<String, String> map) {
		logger.info("AsnAckHandler : getXMLStringMessage Started...");
		StringBuilder xmlStringBuilder = new StringBuilder();
		try {
			OffsetDateTime now = OffsetDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
			String formattedDateTime = now.format(formatter);
			String senderId = AppConstants.SENDER_ID+AppConstants.UNDERSCORE_SYMBOL+map.get("PO_REGION");
			String sn_number = map.get("PO_SN_NUMBER"); 
			String correlationId = map.get("PO_VENDOR_SN_MESSAGE_ID");
			String status = map.get("PO_STATUS");
			String reasoncode = map.get("PO_REASON_CODE");
			String reasondescription = map.get("PO_REASON_DESCRIPTION");
			String 
			
			
			ShipNotificationAck ack = new ShipNotificationAck();
			
			List<ShipNotificationAck.ShipNotificationLine> shipnotificationlineobject = new ArrayList<ShipNotificationAck.ShipNotificationLine>();
			
			ShipNotificationAck.ShipNotificationLines shipnotificationlinesobj = new ShipNotificationAck.ShipNotificationLines();
	        for()
			shipnotificationlinesobj.setShipNotificationLineList(shipnotificationlineobject);

			xmlStringBuilder.append(
					"<v1:SNAck xmlns:v1=\"http://schemas.dell.com/fulfillment/logistics/messaging/SNAck/V1_0\">")
						.append("<MessageHeader>")
							.append("<MessageID>").append(asnAckVO.getGolfMessageId())
							.append("</MessageID>").append("<MessageTimeStamp>").append(formattedDateTime).append("</MessageTimeStamp>")
							.append("<SenderID>").append(senderId).append("</SenderID>")
					      //.append("<ReceiverID>").append().append("</ReceiverID>")
							.append("<MessageType>").append(AppConstants.MESSAGE_TYPE).append("</MessageType>")
							.append("<CorrelationID>").append(correlationId).append("</CorrelationID>")
						.append("</MessageHeader>")
					    .append("<SnHeader>")
						    .append("<Status>").append(status).append("</Status>")
						    .append("<Errors>")
						    .append("<Error>")
				                .append("<Type>").append(reasoncode).append("</Type>")
			      	            .append("<Reason>").append(reasondescription).append("</Reason>")
						    .append("</Error>")
					        .append("</Errors>")
						.append("</SnHeader>")
						.append("<Ship_Notification_Lines>")
						.append("<Ship_Notification_Line>")
					        .append("<Status>").append().append("</Status>")
					        .append("<Errors>")
						    .append("<Error>")
						        .append("<Type>").append().append("</Type>")
		      	                .append("<Reason>").append().append("</Reason>")
		      	            .append("</Errors>")
					        .append("</Error>")
					    .append("</Ship_Notification_Lines>")
						.append("</Ship_Notification_Line>")
					.append("</v1:SNAck>");
		} catch (Exception ex) {
			logger.error("AsnAckHandler : getXMLStringMessage Error occured...{}p", ex.toString());
			
		}
		logger.info("AsnAckHandler : getXMLStringMessage Completed...");
		return xmlStringBuilder.toString();
	}

}