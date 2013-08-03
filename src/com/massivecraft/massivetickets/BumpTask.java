package com.massivecraft.massivetickets;

import com.massivecraft.massivetickets.entity.MConf;
import com.massivecraft.mcore.ModuloRepeatTask;
import com.massivecraft.mcore.util.TimeUnit;

public class BumpTask extends ModuloRepeatTask
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static BumpTask i = new BumpTask();
	public static BumpTask get() { return i; }
	
	// -------------------------------------------- //
	// OVERRIDE: MODULO REPEAT TASK
	// -------------------------------------------- //
	
	@Override
	public long getDelayMillis()
	{
		return (long) (MConf.get().getBumpEachMinutes() * TimeUnit.MILLIS_PER_MINUTE);
	}
	
	@Override
	public void setDelayMillis(long delayMillis)
	{
		MConf.get().setBumpEachMinutes(delayMillis / (double) TimeUnit.MILLIS_PER_MINUTE);
	}
	
	@Override
	public void invoke(long now)
	{
		MassiveTickets.alertMessage(MassiveTickets.createBumpMessage());
	}
	
}
