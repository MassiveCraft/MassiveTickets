package com.massivecraft.massivetickets;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.plugin.Plugin;

import com.massivecraft.massivetickets.entity.MPlayerColl;
import com.massivecraft.mcore.EngineAbstract;
import com.massivecraft.mcore.event.MCoreUuidUpdateEvent;
import com.massivecraft.mcore.util.IdUpdateUtil;

public class EngineIdUpdate extends EngineAbstract
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static EngineIdUpdate i = new EngineIdUpdate();
	public static EngineIdUpdate get() { return i; }
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public Plugin getPlugin()
	{
		return MassiveTickets.get();
	}
	
	// -------------------------------------------- //
	// LISTENER
	// -------------------------------------------- //

	@EventHandler(priority = EventPriority.MONITOR)
	public void onUuidUpdate(MCoreUuidUpdateEvent event)
	{
		IdUpdateUtil.update(MPlayerColl.get());
	}

}
