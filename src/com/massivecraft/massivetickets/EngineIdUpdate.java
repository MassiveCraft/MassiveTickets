package com.massivecraft.massivetickets;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.plugin.Plugin;

import com.massivecraft.massivecore.EngineAbstract;
import com.massivecraft.massivecore.event.EventMassiveCoreUuidUpdate;
import com.massivecraft.massivecore.util.IdUpdateUtil;
import com.massivecraft.massivetickets.entity.MPlayerColl;

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
	public void onUuidUpdate(EventMassiveCoreUuidUpdate event)
	{
		IdUpdateUtil.update(MPlayerColl.get());
	}

}
