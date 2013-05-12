package com.hongyuan.migrate.dbAccess;

import java.sql.SQLException;

import javax.sql.DataSource;

import oracle.jdbc.pool.OracleDataSource;

import org.apache.derby.jdbc.ClientDataSource;
import org.postgresql.ds.PGSimpleDataSource;
import org.sqlite.SQLiteDataSource;

import com.ibm.db2.jcc.DB2DataSource;
import com.informix.jdbcx.IfxDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.sybase.jdbc3.jdbc.SybDataSource;

public class DSFactory {

	public static DataSource getDS(DBType type,
								String serverName,
								int port,
								String dbName,
								String username,
								String password) throws SQLException
	{
		if(DBType.ORACLE.equals(type))
		{
			OracleDataSource ds=new OracleDataSource();
			ds.setURL("jdbc:oracle:thin:@"+serverName+":"+port+":"+dbName);
			ds.setUser(username);
			ds.setPassword(password);
			return ds;
		}
		else if(DBType.MYSQL.equals(type))
		{
			MysqlDataSource ds=new MysqlDataSource();
			ds.setServerName(serverName);
			ds.setPort(port);
			ds.setDatabaseName(dbName);
			ds.setUser(username);
			ds.setPassword(password);
			return ds;
		}
		else if(DBType.SQLSERVER.equals(type))
		{
			SQLServerDataSource ds=new SQLServerDataSource();
			ds.setServerName(serverName);
			ds.setPortNumber(port);
			ds.setDatabaseName(dbName);
			ds.setUser(username);
			ds.setPassword(password);
			return ds;
		}
		else if(DBType.POSTGRESQL.equals(type))
		{
			PGSimpleDataSource ds=new PGSimpleDataSource();
			ds.setServerName(serverName);
			ds.setPortNumber(port);
			ds.setDatabaseName(dbName);
			ds.setUser(username);
			ds.setPassword(password);
			return ds;
		}
		else if(DBType.DERBY.equals(type))
		{
			ClientDataSource ds=new ClientDataSource();
			ds.setServerName(serverName);
			ds.setPortNumber(port);
			ds.setDatabaseName(dbName);
			ds.setUser(username);
			ds.setPassword(password);
			return ds;
		}
		else if(DBType.SQLITE.equals(type))
		{
			SQLiteDataSource ds=new SQLiteDataSource();
			String url="jdbc:sqlite:"+dbName;
			ds.setUrl(url);
			return ds;
		}
		else if(DBType.DB2.equals(type))
		{
			DB2DataSource ds=new DB2DataSource();
			ds.setServerName(serverName);
			ds.setPortNumber(port);
			ds.setDatabaseName(dbName);
			ds.setUser(username);
			ds.setPassword(password);
			return ds;
		}
		else if(DBType.SYBASE.equals(type))
		{
			SybDataSource ds=new SybDataSource();
			ds.setServerName(serverName);
			ds.setPortNumber(port);
			ds.setDatabaseName(dbName);
			ds.setUser(username);
			ds.setPassword(password);
			return ds;
		}
		else if(DBType.INFORMIX.equals(type))
		{
			IfxDataSource ds=new IfxDataSource();
			ds.setServerName(serverName);
			ds.setPortNumber(port);
			ds.setDatabaseName(dbName);
			ds.setUser(username);
			ds.setPassword(password);
			return ds;
		}
		else
		{
			return null;
		}
	}
	
	public static DataSource getOracleDS(String url,String username,String password) throws SQLException
	{
		OracleDataSource ods=new OracleDataSource();
		ods.setURL(url);
		ods.setUser(username);
		ods.setPassword(password);
		return ods;
	}
	
	public static DataSource getMysqlDS(String url,String username,String password)
	{
		MysqlDataSource mds=new MysqlDataSource();
		mds.setURL(url);
		mds.setUser(username);
		mds.setPassword(password);
		return mds;
	}
	
	public static DataSource getSqlServerDs(String url,String username,String password)
	{
		SQLServerDataSource ssds=new SQLServerDataSource();
		ssds.setURL(url);
		ssds.setUser(username);
		ssds.setPassword(password);
		return ssds;
	}
	
	public static DataSource getDS(DBType type,String url,String username,String password) throws SQLException
	{
		if(DBType.ORACLE.equals(type))
		{
			OracleDataSource ods=new OracleDataSource();
			ods.setURL(url);
			ods.setUser(username);
			ods.setPassword(password);
			return ods;
		}
		else if(DBType.MYSQL.equals(type))
		{
			MysqlDataSource mds=new MysqlDataSource();
			mds.setURL(url);
			mds.setUser(username);
			mds.setPassword(password);
			return mds;
		}
		else if(DBType.SQLSERVER.equals(type))
		{
			SQLServerDataSource ssds=new SQLServerDataSource();
			ssds.setURL(url);
			ssds.setUser(username);
			ssds.setPassword(password);
			return ssds;
		}
		else
		{
			return null;
		}
	}
	
}
