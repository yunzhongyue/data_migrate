package com.hongyuan.migrate.model;

public enum PlanStateType {
	
	RUNABLE,RUNNING,FINISH,HAND_UP,UNKNOWN;
	
	public static PlanStateType get(String type)
	{
		if(type!=null)
		{
			if(type.equalsIgnoreCase("runable"))
			{
				return PlanStateType.RUNABLE;
			}
			else if(type.equalsIgnoreCase("running"))
			{
				return PlanStateType.RUNNING;
			}
			else if(type.equalsIgnoreCase("finish"))
			{
				return PlanStateType.FINISH;
			}
			else if(type.equalsIgnoreCase("hand_up"))
			{
				return PlanStateType.HAND_UP;
			}
			else 
			{
				return PlanStateType.UNKNOWN;
			}
		}
		else
		{
			return PlanStateType.UNKNOWN;
		}
	}
}
