package com.massivecraft.massivetickets.cmd;

import java.util.List;

import com.massivecraft.massivecore.command.VersionCommand;
import com.massivecraft.massivetickets.MassiveTickets;
import com.massivecraft.massivetickets.Perm;
import com.massivecraft.massivetickets.entity.MConf;

public class CmdTicketsVersion extends VersionCommand
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
		super(MassiveTickets.get(), Perm.VERSION.node);
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
