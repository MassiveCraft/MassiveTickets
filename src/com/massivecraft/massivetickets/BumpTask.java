package com.massivecraft.massivetickets;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.massivecraft.massivetickets.entity.MConf;
import com.massivecraft.massivetickets.entity.MPlayerColl;
import com.massivecraft.massivetickets.predictate.IsModeratorPredictate;
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
		// If there are any tickets ...
		if (MPlayerColl.get().getAllTickets().size() == 0) return;
		
		// ... send message to people on this server.
		String message = MassiveTickets.createBumpMessage();
		for (Player player : Bukkit.getOnlinePlayers())
		{
			if (!IsModeratorPredictate.get().apply(player)) continue;
			MassiveTickets.alertOneMessage(player, message);
		}
	}
	
}
