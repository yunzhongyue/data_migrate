package com.hongyuan.migrate.model;

import java.util.ArrayList;
import java.util.Date;

public class Plan {
	
	private String id;
	private String createUser;
	private String createDept;
	private Date createTime;
	private String modifyUser;
	private String modifyDept;
	private Date modifyTime;
	private String name;
	private ConnectType srcConnType;
	private String srcConnStr;
	private String srcUserName;
	private String srcPassword;
	private ConnectType targetConnType;
	private String targetConnStr;
	private String targetUserName;
	private String targetPassword;
	private PlanStateType state;
	private ArrayList<Task> tasks;
	private int finishTaskCount;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getCreateDept() {
		return createDept;
	}
	public void setCreateDept(String createDept) {
		this.createDept = createDept;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getModifyUser() {
		return modifyUser;
	}
	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}
	public String getModifyDept() {
		return modifyDept;
	}
	public void setModifyDept(String modifyDept) {
		this.modifyDept = modifyDept;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ConnectType getSrcConnType() {
		return srcConnType;
	}
	public void setSrcConnType(ConnectType srcConnType) {
		this.srcConnType = srcConnType;
	}
	public String getSrcConnStr() {
		return srcConnStr;
	}
	public void setSrcConnStr(String srcConnStr) {
		this.srcConnStr = srcConnStr;
	}
	public String getSrcUserName() {
		return srcUserName;
	}
	public void setSrcUserName(String srcUserName) {
		this.srcUserName = srcUserName;
	}
	public String getSrcPassword() {
		return srcPassword;
	}
	public void setSrcPassword(String srcPassword) {
		this.srcPassword = srcPassword;
	}
	public ConnectType getTargetConnType() {
		return targetConnType;
	}
	public void setTargetConnType(ConnectType targetConnType) {
		this.targetConnType = targetConnType;
	}
	public String getTargetConnStr() {
		return targetConnStr;
	}
	public void setTargetConnStr(String targetConnStr) {
		this.targetConnStr = targetConnStr;
	}
	public String getTargetUserName() {
		return targetUserName;
	}
	public void setTargetUserName(String targetUserName) {
		this.targetUserName = targetUserName;
	}
	public String getTargetPassword() {
		return targetPassword;
	}
	public void setTargetPassword(String targetPassword) {
		this.targetPassword = targetPassword;
	}
	public PlanStateType getState() {
		return state;
	}
	public void setState(PlanStateType state) {
		this.state = state;
	}
	public ArrayList<Task> getTasks() {
		return tasks;
	}
	public void setTasks(ArrayList<Task> tasks) {
		this.tasks = tasks;
	}
	public int getFinishTaskCount() {
		return finishTaskCount;
	}
	public void setFinishTaskCount(int finishTaskCount) {
		this.finishTaskCount = finishTaskCount;
	}
	
	
}
