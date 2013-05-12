package com.hongyuan.migrate.task;

import com.hongyuan.migrate.model.Plan;
import com.hongyuan.migrate.model.Task;

public class TaskManager {
	
	private static TaskManager manager=new TaskManager();
	
	private TaskManager(){}
	
	public static TaskManager getManager()
	{
		return manager;
	}

	public boolean startPlan(Plan plan)
	{		
		for(Task task:plan.getTasks())
		{
			boolean isSuccess=startTask(plan,task);
			if(!isSuccess)
			{
				return isSuccess;
			}
		}
		
		return true;
	}
	public boolean startTask(Plan plan,Task task)
	{
		boolean isSuccess=task.doTask(plan);
		return isSuccess;
	}
}
