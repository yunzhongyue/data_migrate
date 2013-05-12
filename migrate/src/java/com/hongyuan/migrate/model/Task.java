package com.hongyuan.migrate.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Timer;

public abstract class Task {
	
	private String id;
	private String createUser;
	private String createDept;
	private Date createTime;
	private String modifyUser;
	private String modifyDept;
	private String modifyTime;
	private String name;
	private ExeCycleType cycleType;
	private String taskParams;
	private Task parentTask;
	private long timeOut;
	private TimeOutHandleType timeOutHandleType;
	private String timeOutHandle;
	private ExceptionHandleType exceptionHandleType;
	private String exceptionHandle;
	private TaskStateType state;
	private int timeOut_count;
	private Timer timer;
	private HashMap<String, String> colMapping=new HashMap<String, String>();
	
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
	public String getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ExeCycleType getCycleType() {
		return cycleType;
	}
	public void setCycleType(ExeCycleType cycleType) {
		this.cycleType = cycleType;
	}
	public Task getParentTask() {
		return parentTask;
	}
	public void setParentTask(Task parentTask) {
		this.parentTask = parentTask;
	}
	public long getTimeOut() {
		return timeOut;
	}
	public void setTimeOut(long timeOut) {
		this.timeOut = timeOut;
	}
	public TaskStateType getState() {
		return state;
	}
	public void setState(TaskStateType state) {
		this.state = state;
	}
	public int getTimeOut_count() {
		return timeOut_count;
	}
	public void setTimeOut_count(int timeOut_count) {
		this.timeOut_count = timeOut_count;
	}
	public Timer getTimer() {
		return timer;
	}
	public void setTimer(Timer timer) {
		this.timer = timer;
	}
	public HashMap<String, String> getColMapping() {
		return colMapping;
	}
	public void addColMapping(HashMap<String, String> colMapping) {
		this.colMapping = colMapping;
	}
	public void dispatch()
	{
		
	}
	public abstract boolean doTask(Plan cxt);
	public String getTaskParams() {
		return taskParams;
	}
	public void setTaskParams(String taskParams) {
		this.taskParams = taskParams;
	}
	public TimeOutHandleType getTimeOutHandleType() {
		return timeOutHandleType;
	}
	public void setTimeOutHandleType(TimeOutHandleType timeOutHandleType) {
		this.timeOutHandleType = timeOutHandleType;
	}
	public String getTimeOutHandle() {
		return timeOutHandle;
	}
	public void setTimeOutHandle(String timeOutHandle) {
		this.timeOutHandle = timeOutHandle;
	}
	public ExceptionHandleType getExceptionHandleType() {
		return exceptionHandleType;
	}
	public void setExceptionHandleType(ExceptionHandleType exceptionHandleType) {
		this.exceptionHandleType = exceptionHandleType;
	}
	public String getExceptionHandle() {
		return exceptionHandle;
	}
	public void setExceptionHandle(String exceptionHandle) {
		this.exceptionHandle = exceptionHandle;
	}
}
