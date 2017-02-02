package com.massivecraft.massivetickets.cmd;

import java.util.List;

import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivetickets.Perm;
import com.massivecraft.massivetickets.entity.MConf;

public class CmdTickets extends MassiveTicketsCommand
{
	// -------------------------------------------- //
	// INSTANCE
	// -------------------------------------------- //
	
	private static CmdTickets i = new CmdTickets();
	public static CmdTickets get() { return i; }
	
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	public CmdTicketsList cmdTicketsList = new CmdTicketsList();
	public CmdTicketsShow cmdTicketsShow = new CmdTicketsShow();
	public CmdTicketsCreate cmdTicketsCreate = new CmdTicketsCreate();
	public CmdTicketsDone cmdTicketsDone = new CmdTicketsDone();
	public CmdTicketsPick cmdTicketsPick = new CmdTicketsPick();
	public CmdTicketsYield cmdTicketsYield = new CmdTicketsYield();
	public CmdTicketsHighscore cmdTicketsHighscore = new CmdTicketsHighscore();
	public CmdTicketsModlist cmdTicketsModlist = new CmdTicketsModlist();
	public CmdTicketsWorking cmdTicketsWorking = new CmdTicketsWorking();
	public CmdTicketsTeleport cmdTicketsTeleport = new CmdTicketsTeleport();
	public CmdTicketsCheat cmdTicketsCheat = new CmdTicketsCheat();
	public CmdTicketsVersion cmdTicketsVersion = new CmdTicketsVersion();
	public CmdTicketsConfig cmdMassiveTicketsConfig = new CmdTicketsConfig();
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdTickets()
	{
		// Children
		this.addChild(this.cmdTicketsList);
		this.addChild(this.cmdTicketsShow);
		this.addChild(this.cmdTicketsCreate);
		this.addChild(this.cmdTicketsDone);
		this.addChild(this.cmdTicketsPick);
		this.addChild(this.cmdTicketsYield);
		this.addChild(this.cmdTicketsHighscore);
		this.addChild(this.cmdTicketsModlist);
		this.addChild(this.cmdTicketsWorking);
		this.addChild(this.cmdTicketsTeleport);
		this.addChild(this.cmdTicketsCheat);
		this.addChild(this.cmdTicketsVersion);
		this.addChild(this.cmdMassiveTicketsConfig);
		
		// Requirements
		this.addRequirements(RequirementHasPerm.get(Perm.BASECOMMAND));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public List<String> getAliases()
	{
		 return MConf.get().aliasesOuterTickets;
	}
	
}
