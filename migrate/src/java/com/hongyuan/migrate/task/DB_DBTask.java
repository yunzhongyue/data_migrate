package com.hongyuan.migrate.task;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.hongyuan.migrate.dbAccess.DBType;
import com.hongyuan.migrate.dbAccess.DSFactory;
import com.hongyuan.migrate.model.Plan;
import com.hongyuan.migrate.model.Task;


public class DB_DBTask extends Task{

	private String src_tableName;
	private String target_tableName;
	private String src_cols;
	private String target_cols;
	
	@Override
	public boolean doTask(Plan cxt) {
		
		try {
			DataSource src_ds=DSFactory.getDS(DBType.get(cxt.getSrcConnType().toString()), 
					cxt.getSrcConnStr(), cxt.getSrcUserName(), cxt.getSrcPassword());
			
			DataSource target_ds=DSFactory.getDS(DBType.get(cxt.getTargetConnType().toString()), 
					cxt.getTargetConnStr(), cxt.getTargetUserName(), cxt.getTargetPassword());
			
			ResultSet src_rs=src_ds.getConnection().createStatement().executeQuery("select "+src_cols+" from "+src_tableName);
			while(src_rs.next())
			{
				StringBuffer values=new StringBuffer();
				for(int i=1;i<=src_rs.getMetaData().getColumnCount();i++)
				{
					values.append(src_rs.getString(i)).append(',');
				}
				target_ds.getConnection().createStatement().executeUpdate("insert into "+target_tableName+"("+target_cols+")"+" values("+values+")");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

}
