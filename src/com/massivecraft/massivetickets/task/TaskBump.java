package com.massivecraft.massivetickets.task;

import com.massivecraft.massivecore.ModuloRepeatTask;
import com.massivecraft.massivecore.mson.Mson;
import com.massivecraft.massivecore.util.MUtil;
import com.massivecraft.massivecore.util.TimeUnit;
import com.massivecraft.massivetickets.MassiveTickets;
import com.massivecraft.massivetickets.entity.MConf;
import com.massivecraft.massivetickets.entity.MPlayerColl;
import com.massivecraft.massivetickets.predicate.PredicateIsModerator;
import org.bukkit.entity.Player;

public class TaskBump extends ModuloRepeatTask
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static TaskBump i = new TaskBump();
	public static TaskBump get() { return i; }
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public long getDelayMillis()
	{
		return (long) (MConf.get().getBumpEachMinutes() * TimeUnit.MILLIS_PER_MINUTE);
	}
	
	@Override
	public void invoke(long now)
	{
		// If there are any tickets ...
		if (MPlayerColl.get().getAllTickets().size() == 0) return;
		
		// ... send message to people on this server.
		Mson message = MassiveTickets.createBumpMessage();
		for (Player player : MUtil.getOnlinePlayers())
		{
			if (!PredicateIsModerator.get().apply(player)) continue;
			MassiveTickets.alertOneMessage(player, message);
		}
	}
	
}
