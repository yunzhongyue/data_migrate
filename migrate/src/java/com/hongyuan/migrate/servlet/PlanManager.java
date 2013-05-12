package com.hongyuan.migrate.servlet;

import java.io.IOException;
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
import com.hongyuan.migrate.common.DBUtil;
import com.hongyuan.migrate.common.PropertiesLoader;
import com.hongyuan.migrate.dbAccess.DBType;
import com.hongyuan.migrate.dbAccess.DSFactory;

public class PlanManager extends ConnectorServlet {

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
		try{
			String method=req.getParameter("method");
			conn=DSFactory.getDS(dbtype, serverName, port, dbName, username, password).getConnection();
			
			GridConnector gc=new GridConnector(req, res, conn, com.dhtmlx.connector.DBType.Oracle);
			
			if("add".equals(method))
			{
				String addType=req.getParameter("addType");
				if("db_db".equals(addType))
				{
					String planId,planName,src_DBType,src_DBIP,src_DBPort,src_DBName,src_username,src_password,target_DBType,target_DBIP,target_DBPort,target_DBName,target_username,target_password;
					planId=req.getParameter("planId");
					planName=req.getParameter("planName");
					src_DBType=req.getParameter("src_DBType");
					src_DBIP=req.getParameter("src_DBIP");
					src_DBPort=req.getParameter("src_DBPort");
					src_DBName=req.getParameter("src_DBName");
					src_username=req.getParameter("src_username");
					src_password=req.getParameter("src_password");
					target_DBType=req.getParameter("target_DBType");
					target_DBIP=req.getParameter("target_DBIP");
					target_DBPort=req.getParameter("target_DBPort");
					target_DBName=req.getParameter("target_DBName");
					target_username=req.getParameter("target_username");
					target_password=req.getParameter("target_password");
					
					String src_conn_str=DBUtil.buildUrl(DBType.get(src_DBType), src_DBIP, Integer.parseInt(src_DBPort), src_DBName);
					String target_conn_str=DBUtil.buildUrl(DBType.get(target_DBType), target_DBIP, Integer.parseInt(target_DBPort), target_DBName);
					
					String insertSql="insert into plan(id, create_user, create_dept, create_time, modify_user, modify_dept, modify_time, plan_name, " +
							"src_conn_type, src_conn_str, src_username, src_password, target_conn_type, target_conn_str, target_username, target_password, " +
							"plan_state, task_finishcount, deleted) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
					
					ps=conn.prepareStatement(insertSql);
					ps.setString(1, planId);
					ps.setString(2, "");
					ps.setString(3, "");
					ps.setString(4, "");
					ps.setString(5, "");
					ps.setString(6, "");
					ps.setString(7, "");
					ps.setString(8, planName);
					ps.setString(9, src_DBType);
					ps.setString(10, src_conn_str);
					ps.setString(11, src_username);
					ps.setString(12, src_password);
					ps.setString(13, target_DBType);
					ps.setString(14, target_conn_str);
					ps.setString(15, target_username);
					ps.setString(16, target_password);
					ps.setInt(17, 0);
					ps.setInt(18, 0);
					ps.setString(19, "0");
					
					int insertCount=ps.executeUpdate();
					
					res.getWriter().print(insertCount);
				}
			}
			else if("delete".equals(method))
			{
				String planId=req.getParameter("planId");
				String sql="update plan set deleted=1 where id=?";
				ps=conn.prepareStatement(sql);
				ps.setString(1, planId);
				
				int result=ps.executeUpdate();
				res.getWriter().print(result);
			}
			else if(gc.is_select_mode())
			{
				String sql="select ID,"+
							      "PLAN_NAME,"+
							      "CREATE_USER,"+
							      "SRC_CONN_TYPE,"+
							      "TARGET_CONN_TYPE,"+
							      "(case"+
							       " when PLAN_STATE = 1 then"+
							        " '已启动'"+
							        " else"+
							        "  '未启动'"+
							      " end) PLAN_STATE,"+
							      " TASK_FINISHCOUNT"+
							 " from plan"+
							 " where DELETED = 0";
				gc.render_sql(sql, "ID", "PLAN_NAME,CREATE_USER,SRC_CONN_TYPE,TARGET_CONN_TYPE,PLAN_STATE,TASK_FINISHCOUNT");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{

			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
