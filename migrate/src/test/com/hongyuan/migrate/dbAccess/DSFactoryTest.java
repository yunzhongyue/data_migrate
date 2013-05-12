package com.hongyuan.migrate.dbAccess;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DSFactoryTest {

	private static DBType dbtype;
	private static String serverName;
	private static int port;
	private static String dbName;
	private static String username;
	private static String password;
	private static String url;
	
	@Before
	public void setUp() throws Exception {
		dbtype=DBType.ORACLE;
		serverName="localhost";
		port=1521;
		dbName="XE";
		username="zhang";
		password="zhang";
		url="jdbc:oracle:thin:@127.0.0.1:1521:XE";
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test1() {
		try {
			DataSource ds=DSFactory.getDS(dbtype, serverName, port, dbName, username, password);
			//DataSource ds=DSFactory.getDS(dbtype, url, username, password);
			Connection conn=ds.getConnection();
			Statement s=conn.createStatement();
			ResultSet rs=s.executeQuery("select 1 from dual");
			while(rs.next())
			{
				System.out.println("have a row");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
