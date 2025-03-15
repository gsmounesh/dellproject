package com.dell.golf.golfsnservice.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.NoArgsConstructor;


	@Data
	@NoArgsConstructor
	@JsonInclude(Include.NON_NULL)
	public class SnAckResponse {
		
		SnAckVO snACKResponse;

		public SnAckVO getSnACKResponse() {
			return snACKResponse;
		}

		public void setSnACKResponse(SnAckVO snACKResponse) {
			this.snACKResponse = snACKResponse;
		}

	}

