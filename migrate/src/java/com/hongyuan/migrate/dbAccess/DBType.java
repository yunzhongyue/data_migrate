package com.hongyuan.migrate.dbAccess;

public enum DBType {

	ORACLE,MYSQL,SQLSERVER,POSTGRESQL,SQLITE,DERBY,DB2,SYBASE,INFORMIX,OTHER;
	
	public static DBType get(String type)
	{
		if(type!=null)
		{
			if(type.equalsIgnoreCase("ORACLE"))
			{
				return DBType.ORACLE;
			}
			else if(type.equalsIgnoreCase("MYSQL"))
			{
				return DBType.MYSQL;
			}
			else if(type.equalsIgnoreCase("SQLSERVER"))
			{
				return DBType.SQLSERVER;
			}
			else if(type.equalsIgnoreCase("POSTGRESQL"))
			{
				return DBType.POSTGRESQL;
			}
			else if(type.equalsIgnoreCase("SQLITE"))
			{
				return DBType.SQLITE;
			}
			else if(type.equalsIgnoreCase("DERBY"))
			{
				return DBType.DERBY;
			}
			else if(type.equalsIgnoreCase("DB2"))
			{
				return DBType.DB2;
			}
			else if(type.equalsIgnoreCase("SYBASE"))
			{
				return DBType.SYBASE;
			}
			else if(type.equalsIgnoreCase("INFORMIX"))
			{
				return DBType.INFORMIX;
			}
			else
			{
				return DBType.OTHER;
			}
		}
		else
		{
			return DBType.OTHER;
		}
	}
}
