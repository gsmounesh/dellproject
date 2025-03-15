package com.dell.golf.golfsnservice.service;


import jakarta.xml.bind.annotation.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Data
@Getter
@Setter

@XmlRootElement(name = "SHIP_NOTIFICATION_ACK")
@XmlType(propOrder = { "MessageHeader", "SnHeader", "ShipNotificationLine", "Error","Errors"})
@XmlAccessorType(XmlAccessType.FIELD)
public class ShipNotificationAck {
    
    @XmlElement(name = "MESSAGE_HEADER")
    private MessageHeader messageHeader;
    
    @XmlElement(name = "SN_HEADER")
    private SnHeader snHeader;
    
    @XmlElement(name = "SHIP_NOTIFICATION_LINES")
    private ShipNotificationLines shipNotificationLines;

	public MessageHeader getMessageHeader() {
		return messageHeader;
	}

	public void setMessageHeader(MessageHeader messageHeader) {
		this.messageHeader = messageHeader;
	}

	public SnHeader getSnHeader() {
		return snHeader;
	}

	public void setSnHeader(SnHeader snHeader) {
		this.snHeader = snHeader;
	}

	public ShipNotificationLines getShipNotificationLines() {
		return shipNotificationLines;
	}

	public void setShipNotificationLines(ShipNotificationLines shipNotificationLines) {
		this.shipNotificationLines = shipNotificationLines;
	}


@Data
@XmlAccessorType(XmlAccessType.FIELD)
public static class MessageHeader {
    @XmlElement(name = "SENDER_ID")
    private String senderId;
    
    @XmlElement(name = "RECEIVER_ID")
    private String receiverId;
    
    @XmlElement(name = "MESSAGE_ID")
    private String messageId;
    
    @XmlElement(name = "MESSAGE_TYPE")
    private String messageType;
    
    @XmlElement(name = "VERSION")
    private String version;
    
    @XmlElement(name = "CORRELATION_ID")
    private String correlationId;
    
    @XmlElement(name = "CREATION_TS")
    private String creationTs;

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getCorrelationId() {
		return correlationId;
	}

	public void setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
	}

	public String getCreationTs() {
		return creationTs;
	}

	public void setCreationTs(String creationTs) {
		this.creationTs = creationTs;
	}

  
}

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public static class SnHeader {
    @XmlElement(name = "SN_NUMBER")
    private String snNumber;
    
    @XmlElement(name = "STATUS")
    private String status;
    
    @XmlElement(name = "ERRORS")
    private Errors errors;

	public String getSnNumber() {
		return snNumber;
	}

	public void setSnNumber(String snNumber) {
		this.snNumber = snNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Errors getErrors() {
		return errors;
	}

	public void setErrors(Errors errors) {
		this.errors = errors;
	}

  
}

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public static class ShipNotificationLines {
    @XmlElement(name = "SHIP_NOTIFICATION_LINE")
    private List<ShipNotificationLine> shipNotificationLineList;

	public List<ShipNotificationLine> getShipNotificationLineList() {
		return shipNotificationLineList;
	}

	public void setShipNotificationLineList(List<ShipNotificationLine> shipNotificationLineList) {
		this.shipNotificationLineList = shipNotificationLineList;
	}
 
  
}

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public static class ShipNotificationLine {
    @XmlElement(name = "LOADID")
    private String loadId;
    
    @XmlElement(name = "STATUS")
    private String status;
    
    @XmlElement(name = "ERRORS")
    private Errors errors;
    
    @XmlElement(name = "ASN_NUMBER")
    private String asnNumber;

	public String getLoadId() {
		return loadId;
	}

	public void setLoadId(String loadId) {
		this.loadId = loadId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Errors getErrors() {
		return errors;
	}

	public void setErrors(Errors errors) {
		this.errors = errors;
	}

	public String getAsnNumber() {
		return asnNumber;
	}

	public void setAsnNumber(String asnNumber) {
		this.asnNumber = asnNumber;
	}

    
    
}

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public static class Errors {
    @XmlElement(name = "ERROR")
    private List<Error> errorList;

	public List<Error> getErrorList() {
		return errorList;
	}

	public void setErrorList(List<Error> errorList) {
		this.errorList = errorList;
	}

    
}

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public static class Error {
    @XmlElement(name = "TYPE")
    private String type;
    
    @XmlElement(name = "REASON")
    private String reason;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

   
}
}






