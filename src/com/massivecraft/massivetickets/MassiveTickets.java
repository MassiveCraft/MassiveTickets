package com.massivecraft.massivetickets;

import com.massivecraft.massivetickets.cmd.CmdTickets;
import com.massivecraft.massivetickets.entity.MConfColl;
import com.massivecraft.massivetickets.entity.MPlayerColl;
import com.massivecraft.mcore.MPlugin;

public class MassiveTickets extends MPlugin
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static MassiveTickets i;
	public static MassiveTickets get() { return i; }
	public MassiveTickets() { MassiveTickets.i = this; }
	
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	// Commands
	private CmdTickets outerCmdTickets;
	public CmdTickets getOuterCmdTickets() { return this.outerCmdTickets; }

	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void onEnable()
	{
		if ( ! preEnable()) return;
		
		// Load Server Config
		ConfServer.get().load();
		
		// Initialize Database
		MConfColl.get().init();
		MPlayerColl.get().init();
		
		// Commands
		this.outerCmdTickets = new CmdTickets(ConfServer.aliasesOuterTickets);
		this.outerCmdTickets.register(this);

		// Setup Listeners
		//FactionsListenerMain.get().setup();
		
		// Schedule recurring non-tps-dependent tasks
		//TaskPlayerPowerUpdate.get().schedule(this);
		
		postEnable();
	}
	
}
