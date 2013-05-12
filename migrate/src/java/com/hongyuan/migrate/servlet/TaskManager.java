package com.hongyuan.migrate.servlet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dhtmlx.connector.ConnectorServlet;
import com.dhtmlx.connector.GridConnector;
import com.hongyuan.migrate.common.PropertiesLoader;
import com.hongyuan.migrate.dbAccess.DBType;
import com.hongyuan.migrate.dbAccess.DSFactory;

public class TaskManager extends ConnectorServlet {

	private static DBType dbtype;
	private static String serverName;
	private static int port;
	private static String dbName;
	private static String username;
	private static String password;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		 	Properties env = PropertiesLoader.env;
			dbtype=DBType.get(env.getProperty("db.type"));
			serverName=env.getProperty("db.ip");
			port=Integer.parseInt(env.getProperty("db.port"));
			dbName=env.getProperty("db.sid");
			username=env.getProperty("db.username");
			password=env.getProperty("db.password");
		
	}
	
	@Override
	public void configure(HttpServletRequest req, HttpServletResponse res) {
		
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			String method=req.getParameter("method");
			conn=DSFactory.getDS(dbtype, serverName, port, dbName, username, password).getConnection();
			
			GridConnector gc=new GridConnector(req, res, conn, com.dhtmlx.connector.DBType.Oracle);
			
			if("add".equals(method))
			{
				
			}
			else if("update".equals(method))
			{
				
			}
			else if("delete".equals(method))
			{
				
			}
			else if(gc.is_select_mode())
			{
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
