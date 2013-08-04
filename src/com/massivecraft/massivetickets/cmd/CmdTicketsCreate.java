package com.massivecraft.massivetickets.cmd;

import java.util.List;

import org.bukkit.ChatColor;

import com.massivecraft.massivetickets.MassiveTickets;
import com.massivecraft.massivetickets.Perm;
import com.massivecraft.massivetickets.entity.MPlayerColl;
import com.massivecraft.mcore.cmd.MCommand;
import com.massivecraft.mcore.cmd.arg.ARString;
import com.massivecraft.mcore.cmd.req.ReqHasPerm;

public class CmdTicketsCreate extends MassiveTicketsCommand
{
	public CmdTicketsCreate(List<String> aliases)
	{
		super(aliases);
		
		this.addRequiredArg("message");
		this.setErrorOnToManyArgs(false);
		
		this.addRequirements(ReqHasPerm.get(Perm.CREATE.node));
	}
	
	@Override
	public void perform()
	{
		// Args
		String message = this.argConcatFrom(0, ARString.get());
		if (message == null) return;
		message = ChatColor.stripColor(message);
		
		// Apply
		boolean update = msender.hasMessage();
		String verb = update ? "updated" : "created";
		
		msender.setMessage(message);
		if (!update) msender.setMillis(System.currentTimeMillis());
		
		// Inform Moderators
		MassiveTickets.alertModeratorsMsg("<white>%s <pink>%s ticket: %s", msender.getDisplayName(), verb, message);
		
		// Inform Creator
		MassiveTickets.alertOneMsg(sender, "Your ticket was %s. We will help you asap.", verb);
		MassiveTickets.alertOneMsg(sender, "There is currently <aqua>%d <pink>working moderators.", MPlayerColl.get().getAllCurrentlyWorking().size());
		
		MCommand cmd = null;
		
		cmd = MassiveTickets.get().getOuterCmdTickets().cmdTicketsShow;
		MassiveTickets.alertOneMsg(sender, "Use " + cmd.getUseageTemplate(cmd.getCommandChain(), false, true, sender) + " <pink>to show your ticket");
		
		cmd = MassiveTickets.get().getOuterCmdTickets().cmdTicketsDone;
		MassiveTickets.alertOneMsg(sender, "Use " + cmd.getUseageTemplate(cmd.getCommandChain(), false, true, sender) + " <pink>to mark it as done");
		
		cmd = MassiveTickets.get().getOuterCmdTickets().cmdTicketsModlist;
		MassiveTickets.alertOneMsg(sender, "Use " + cmd.getUseageTemplate(cmd.getCommandChain(), false, true, sender) + " <pink>to list the moderators");
		
		cmd = MassiveTickets.get().getOuterCmdTickets().cmdTicketsCreate;
		MassiveTickets.alertOneMsg(sender, "Use " + cmd.getUseageTemplate(cmd.getCommandChain(), false, true, sender) + " <pink>to update the message");
	}
	
}
