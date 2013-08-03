package com.massivecraft.massivetickets.cmd;

import java.util.List;

import com.massivecraft.massivetickets.MassiveTickets;
import com.massivecraft.massivetickets.Perm;
import com.massivecraft.mcore.cmd.HelpCommand;
import com.massivecraft.mcore.cmd.VersionCommand;
import com.massivecraft.mcore.cmd.req.ReqHasPerm;

public class CmdTickets extends MassiveTicketsCommand
{
	//public CmdChatList cmdChatList = new CmdChatList(ConfServer.aliasesInnerChatList);
	
	public VersionCommand cmdChatVersion = new VersionCommand(MassiveTickets.get(), Perm.VERSION.node, "v", "version");
	
	public CmdTickets(List<String> aliases)
	{
		super(aliases);
		
		this.addSubCommand(HelpCommand.get());
		//this.addSubCommand(this.cmdChatList);
		this.addSubCommand(this.cmdChatVersion);
		
		this.addRequirements(ReqHasPerm.get(Perm.BASECOMMAND.node));
	}
	
	@Override
	public void perform()
	{
		this.getCommandChain().add(this);
		HelpCommand.getInstance().execute(this.sender, this.args, this.commandChain);
	}
}
