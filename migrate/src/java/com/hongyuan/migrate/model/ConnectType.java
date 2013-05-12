package com.hongyuan.migrate.model;

public enum ConnectType {
	
	DB_ORACLE,DB_MYSQL,DB_SQLSERVER,FTP,WEBSERVICE,FILE,NONE;
	
	public static ConnectType get(String type)
	{
		if(type!=null)
		{
			if(type.equalsIgnoreCase("oracle"))
			{
				return ConnectType.DB_ORACLE;
			}
			else if(type.equalsIgnoreCase("mysql"))
			{
				return ConnectType.DB_MYSQL;
			}
			else if(type.equalsIgnoreCase("sqlserver"))
			{
				return ConnectType.DB_SQLSERVER;
			}
			else if(type.equalsIgnoreCase("ftp"))
			{
				return ConnectType.FTP;
			}
			else if(type.equalsIgnoreCase("webservice"))
			{
				return ConnectType.WEBSERVICE;
			}
			else if(type.equalsIgnoreCase("file"))
			{
				return ConnectType.FILE;
			}
			else
			{
				return ConnectType.NONE;
			}
		}
		else
		{
			return ConnectType.NONE;
		}
	}
}
