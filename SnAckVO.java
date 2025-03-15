package com.dell.golf.golfsnservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class SnAckVO {
	
	private String sn_number;
	@JsonProperty("CorrelationID")
	private String CorrelationID;
	private String message;
	public String getSn_number() {
		return sn_number;
	}
	public void setSn_number(String sn_number) {
		this.sn_number = sn_number;
	}
	public String getCorrelationID() {
		return CorrelationID;
	}
	public void setCorrelationID(String correlationID) {
		CorrelationID = correlationID;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
  
}