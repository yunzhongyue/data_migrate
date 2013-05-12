package com.hongyuan.migrate.common;

import com.hongyuan.migrate.dbAccess.DBType;


public class DBUtil {

	public static String buildUrl(DBType dbType,String serverName,int port,String dbName)
	{
		if(DBType.ORACLE.equals(dbType))
		{
			return "jdbc:oracle:thin:@"+serverName+":"+port+":"+dbName;
		}
		else
		{
			return "";
		}
	}
}
