package com.massivecraft.massivetickets.cmd;

import com.massivecraft.massivecore.command.MassiveCommandVersion;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivetickets.MassiveTickets;
import com.massivecraft.massivetickets.Perm;
import com.massivecraft.massivetickets.entity.MConf;

import java.util.List;

public class CmdTicketsVersion extends MassiveCommandVersion
{
	// -------------------------------------------- //
	// INSTANCE
	// -------------------------------------------- //
	
	private static CmdTicketsVersion i = new CmdTicketsVersion() { @Override public List<String> getAliases() { return MConf.get().aliasesOuterTicketsVersion; } };
	public static CmdTicketsVersion get() { return i; }
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdTicketsVersion()
	{
		super(MassiveTickets.get());
		
		// Requirements
		this.addRequirements(RequirementHasPerm.get(Perm.VERSION));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public List<String> getAliases()
	{
		return MConf.get().aliasesInnerTicketsVersion;
	}
	
}
