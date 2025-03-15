package com.dell.golf.golfsnservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class AsnAckVO {

	private String golfMessageId;
	private String sn_number;
    private String accept;
    
    public String getGolfMessageId() {
		return golfMessageId;
	}
	public void setGolfMessageId(String golfMessageId) {
		this.golfMessageId = golfMessageId;
	}
	public String getSn_number() {
		return sn_number;
	}
	public void setSn_number(String sn_number) {
		this.sn_number = sn_number;
	}
	public String getAccept() {
		return accept;
	}
	public void setAccept(String accept) {
		this.accept = accept;
	}
    
	
}
