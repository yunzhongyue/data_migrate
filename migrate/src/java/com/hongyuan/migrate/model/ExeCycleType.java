package com.hongyuan.migrate.model;

public enum ExeCycleType {
	
	ONLY_ONCE,PER_MINUTE,PER_HOUR,EVERY_DAY,PER_WEEK,PER_MONTH,OTHER,NONE;

	public static ExeCycleType get(String type)
	{
		if(type!=null)
		{
			if(type.equalsIgnoreCase("once"))
			{
				return ExeCycleType.ONLY_ONCE;
			}
			else if(type.equalsIgnoreCase("minute"))
			{
				return ExeCycleType.PER_MINUTE;
			}
			else if(type.equalsIgnoreCase("hour"))
			{
				return ExeCycleType.PER_HOUR;
			}
			else if(type.equalsIgnoreCase("day"))
			{
				return ExeCycleType.EVERY_DAY;
			}
			else if(type.equalsIgnoreCase("week"))
			{
				return ExeCycleType.PER_WEEK;
			}
			else if(type.equalsIgnoreCase("month"))
			{
				return ExeCycleType.PER_MONTH;
			}
			else
			{
				return ExeCycleType.OTHER;
			}
		}
		else
		{
			return ExeCycleType.NONE;
		}
	}
 
}
