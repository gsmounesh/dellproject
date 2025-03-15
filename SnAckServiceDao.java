package com.dell.golf.golfsnservice.dao;

import java.util.HashMap;

import com.dell.golf.golfsnservice.model.AsnAckVO;


public interface SnAckServiceDao {

	public HashMap<String, String> processProcedureCall(AsnAckVO asnAckVO);

}

