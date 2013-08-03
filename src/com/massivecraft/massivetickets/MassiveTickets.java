package com.massivecraft.massivetickets;

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
	//private CmdFactions outerCmdFactions;
	//public CmdFactions getOuterCmdFactions() { return this.outerCmdFactions; }

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
		//MPlayerColl.get().init();
		
		// Commands
		//this.outerCmdFactions = new CmdFactions();
		//this.outerCmdFactions.register(this);

		// Setup Listeners
		//FactionsListenerMain.get().setup();
		
		// Schedule recurring non-tps-dependent tasks
		//TaskPlayerPowerUpdate.get().schedule(this);
		
		postEnable();
	}
	
}
