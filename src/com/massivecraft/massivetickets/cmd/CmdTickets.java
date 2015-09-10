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
		// SubCommands
		this.addSubCommand(this.cmdTicketsList);
		this.addSubCommand(this.cmdTicketsShow);
		this.addSubCommand(this.cmdTicketsCreate);
		this.addSubCommand(this.cmdTicketsDone);
		this.addSubCommand(this.cmdTicketsPick);
		this.addSubCommand(this.cmdTicketsYield);
		this.addSubCommand(this.cmdTicketsHighscore);
		this.addSubCommand(this.cmdTicketsModlist);
		this.addSubCommand(this.cmdTicketsWorking);
		this.addSubCommand(this.cmdTicketsTeleport);
		this.addSubCommand(this.cmdTicketsCheat);
		this.addSubCommand(this.cmdTicketsVersion);
		
		// Requirements
		this.addRequirements(ReqHasPerm.get(Perm.BASECOMMAND.node));
	}
	
}
