package com.dell.golf.golfsnservice.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dell.golf.golfsnservice.dao.DBPackage;
import com.dell.golf.golfsnservice.dao.SnAckServiceDao;
import com.dell.golf.golfsnservice.model.AsnAckVO;
import com.zaxxer.hikari.HikariDataSource;

import oracle.jdbc.OracleTypes;

@Repository
public class SnAckServiceDaoImpl implements SnAckServiceDao {

	private static final Logger logger = LoggerFactory.getLogger(SnAckServiceDaoImpl.class);

	@Autowired
	HikariDataSource golfDatasource;

	@Override
	public HashMap<String, String> processProcedureCall(AsnAckVO asnAckVO) {

		logger.info("SnAckServiceDaoImpl : processProcedureCall Procedure call starts..");
		HashMap<String, String> map = new HashMap<>();
		try (Connection conn = golfDatasource.getConnection();
				CallableStatement stmt = conn.prepareCall(DBPackage.GET_SN_ACK_NACK_DE)) {
			stmt.setString(1, asnAckVO.getGolfMessageId());
			stmt.setString(2, asnAckVO.getSn_number());
			stmt.setString(3, asnAckVO.getAccept());
			stmt.registerOutParameter(4, OracleTypes.VARCHAR);
			stmt.registerOutParameter(5, OracleTypes.VARCHAR);
			stmt.registerOutParameter(6, OracleTypes.VARCHAR);
			stmt.registerOutParameter(7, OracleTypes.VARCHAR);
			stmt.registerOutParameter(8, OracleTypes.VARCHAR);
			stmt.registerOutParameter(9, OracleTypes.VARCHAR);
			stmt.execute();
			        
	        map.put("PO_SN_NUMBER", stmt.getString(4));
	        map.put("PO_REGION", stmt.getString(5));
	        map.put("PO_VENDOR_SN_MESSAGE_ID", stmt.getString(6));
	        map.put("PO_STATUS", stmt.getString(7));
	        map.put("PO_REASON_CODE ", stmt.getString(8));
	        map.put("PO_REASON_DESCRIPTION", stmt.getString(9));
			

			logger.info("SnAckServiceDaoImpl : processProcedureCall DB procedure call done");

		} catch (Exception ex) {

			logger.error("SnAckServiceDaoImpl : processProcedureCall | {}", ex.toString());
			return map;
		}

		logger.info("SnAckServiceDaoImpl : processProcedureCall procedure call end");

		return map;
	}
}


