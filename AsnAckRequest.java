package com.dell.golf.golfsnservice.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class AsnAckRequest {
	
	    private AsnAckVO snACKNACKRequest;

		public AsnAckVO getSnACKNACKRequest() {
			return snACKNACKRequest;
		}

		public void setSnACKNACKRequest(AsnAckVO snACKNACKRequest) {
			this.snACKNACKRequest = snACKNACKRequest;
		}

	   
}