package com.dell.golf.golfsnservice.dao;

public class BaseDBPackages {

	public static final String DB_CALL = " call ";
	public static final String QUESTION_MARK = "?";
	public static final String LEFT_PARENTHESIS = "(";
	public static final String RIGHT_PARENTHESIS = ")";
	public static final String COMMA = ",";

	/**
	 * This will create a a DB PROC call string. For eg:
	 * 
	 * <pre>
	 * Input                                             Result
	 * --------------------------------------------------------------------------
	 * createDBProcCallString("GET_ALL_CONFIG", 2);      call GET_ALL_CONFIG(?, ?)
	 * </pre>
	 * 
	 * @param procName   the name of the procedure
	 * @param paramCount the number of parameters your proc is taking
	 * @return The db proc call string
	 */
	protected static String createDBProcCallString(String procName, int paramCount) {
		return DB_CALL + procName + getParameter(paramCount);
	}

	/**
	 * This method create parameter for your DB proc. getParameter
	 * 
	 * @param paramaterCount
	 * @return
	 */
	private static String getParameter(int paramaterCount) {
		String paramaterString = null;
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(LEFT_PARENTHESIS);
		if (paramaterCount > 0) {
			for (int i = 0; i < paramaterCount; i++) {
				stringBuffer.append(QUESTION_MARK);
				stringBuffer.append(COMMA);
			}
			stringBuffer.deleteCharAt(stringBuffer.lastIndexOf(COMMA));
		}
		stringBuffer.append(RIGHT_PARENTHESIS);
		paramaterString = stringBuffer.toString();

		return paramaterString;
	}

}