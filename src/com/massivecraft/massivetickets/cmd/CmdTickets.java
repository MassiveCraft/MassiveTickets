package com.massivecraft.massivetickets.cmd;

import java.util.List;

import com.massivecraft.massivecore.cmd.VersionCommand;
import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivetickets.MassiveTickets;
import com.massivecraft.massivetickets.Perm;
import com.massivecraft.massivetickets.entity.MConf;

public class CmdTickets extends MassiveTicketsCommand
{
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	public CmdTicketsList cmdTicketsList = new CmdTicketsList() { @Override public List<String> getAliases() { return MConf.get().aliasesInnerTicketsList; } };
	public CmdTicketsShow cmdTicketsShow = new CmdTicketsShow() { @Override public List<String> getAliases() { return MConf.get().aliasesInnerTicketsShow; } };
	public CmdTicketsCreate cmdTicketsCreate = new CmdTicketsCreate() { @Override public List<String> getAliases() { return MConf.get().aliasesInnerTicketsCreate; } };
	public CmdTicketsDone cmdTicketsDone = new CmdTicketsDone() { @Override public List<String> getAliases() { return MConf.get().aliasesInnerTicketsDone; } };
	public CmdTicketsPick cmdTicketsPick = new CmdTicketsPick() { @Override public List<String> getAliases() { return MConf.get().aliasesInnerTicketsPick; } };
	public CmdTicketsYield cmdTicketsYield = new CmdTicketsYield() { @Override public List<String> getAliases() { return MConf.get().aliasesInnerTicketsYield; } };
	public CmdTicketsHighscore cmdTicketsHighscore = new CmdTicketsHighscore() { @Override public List<String> getAliases() { return MConf.get().aliasesInnerTicketsHighscore; } };
	public CmdTicketsModlist cmdTicketsModlist = new CmdTicketsModlist() { @Override public List<String> getAliases() { return MConf.get().aliasesInnerTicketsModlist; } };
	public CmdTicketsWorking cmdTicketsWorking = new CmdTicketsWorking() { @Override public List<String> getAliases() { return MConf.get().aliasesInnerTicketsWorking; } };
	public CmdTicketsTeleport cmdTicketsTeleport = new CmdTicketsTeleport() { @Override public List<String> getAliases() { return MConf.get().aliasesInnerTicketsTeleport; } };
	public CmdTicketsCheat cmdTicketsCheat = new CmdTicketsCheat() { @Override public List<String> getAliases() { return MConf.get().aliasesInnerTicketsCheat; } };
	public VersionCommand cmdTicketsVersion = new VersionCommand(MassiveTickets.get(), Perm.VERSION.node) { @Override public List<String> getAliases() { return MConf.get().aliasesInnerTicketsVersion; } };
	
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
		
		// Requirements
		this.addRequirements(ReqHasPerm.get(Perm.BASECOMMAND.node));
	}
	
}
