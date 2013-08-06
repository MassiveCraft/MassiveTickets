package com.massivecraft.massivetickets.cmd;

import java.util.List;

import com.massivecraft.massivetickets.ConfServer;
import com.massivecraft.massivetickets.MassiveTickets;
import com.massivecraft.massivetickets.Perm;
import com.massivecraft.mcore.cmd.HelpCommand;
import com.massivecraft.mcore.cmd.VersionCommand;
import com.massivecraft.mcore.cmd.req.ReqHasPerm;

public class CmdTickets extends MassiveTicketsCommand
{
	public CmdTicketsList cmdTicketsList = new CmdTicketsList(ConfServer.aliasesInnerTicketsList);
	public CmdTicketsShow cmdTicketsShow = new CmdTicketsShow(ConfServer.aliasesInnerTicketsShow);
	public CmdTicketsCreate cmdTicketsCreate = new CmdTicketsCreate(ConfServer.aliasesInnerTicketsCreate);
	public CmdTicketsDone cmdTicketsDone = new CmdTicketsDone(ConfServer.aliasesInnerTicketsDone);
	public CmdTicketsPick cmdTicketsPick = new CmdTicketsPick(ConfServer.aliasesInnerTicketsPick);
	public CmdTicketsYield cmdTicketsYield = new CmdTicketsYield(ConfServer.aliasesInnerTicketsYield);
	public CmdTicketsHighscore cmdTicketsHighscore = new CmdTicketsHighscore(ConfServer.aliasesInnerTicketsHighscore);
	public CmdTicketsModlist cmdTicketsModlist = new CmdTicketsModlist(ConfServer.aliasesInnerTicketsModlist);
	public CmdTicketsWorking cmdTicketsWorking = new CmdTicketsWorking(ConfServer.aliasesInnerTicketsWorking);
	public CmdTicketsCheat cmdTicketsCheat = new CmdTicketsCheat(ConfServer.aliasesInnerTicketsCheat);
	public VersionCommand cmdTicketsVersion = new VersionCommand(MassiveTickets.get(), Perm.VERSION.node, ConfServer.aliasesInnerTicketsVersion);
	
	public CmdTickets(List<String> aliases)
	{
		super(aliases);
		
		this.addSubCommand(HelpCommand.get());
		
		this.addSubCommand(this.cmdTicketsList);
		this.addSubCommand(this.cmdTicketsShow);
		this.addSubCommand(this.cmdTicketsCreate);
		this.addSubCommand(this.cmdTicketsDone);
		this.addSubCommand(this.cmdTicketsPick);
		this.addSubCommand(this.cmdTicketsYield);
		this.addSubCommand(this.cmdTicketsHighscore);
		this.addSubCommand(this.cmdTicketsModlist);
		this.addSubCommand(this.cmdTicketsWorking);
		this.addSubCommand(this.cmdTicketsCheat);
		this.addSubCommand(this.cmdTicketsVersion);
		
		this.addRequirements(ReqHasPerm.get(Perm.BASECOMMAND.node));
	}
	
	@Override
	public void perform()
	{
		this.getCommandChain().add(this);
		HelpCommand.getInstance().execute(this.sender, this.args, this.commandChain);
	}
}
