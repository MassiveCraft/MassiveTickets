package com.massivecraft.massivetickets.cmd;

import java.util.List;

import org.bukkit.ChatColor;

import com.massivecraft.massivetickets.MassiveTickets;
import com.massivecraft.massivetickets.Perm;
import com.massivecraft.massivetickets.entity.MPlayerColl;
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
		
		// Inform
		MassiveTickets.alertMsg("<white>%s <pink>%s ticket: %s", msender.getDisplayName(), verb, message);
		MassiveTickets.alertMsg(sender, "Your ticket was %s. We will help you asap.", verb);
		MassiveTickets.alertMsg(sender, "There is currently <aqua>%d <pink>working moderators.", MPlayerColl.get().getAllCurrentlyWorking().size());
		
		MassiveTickets.alertMsg(sender, "Use " + MassiveTickets.get().getOuterCmdTickets().cmdTicketsShow.getUseageTemplate() + " <pink>to show your ticket");
		MassiveTickets.alertMsg(sender, "Use " + MassiveTickets.get().getOuterCmdTickets().cmdTicketsDone.getUseageTemplate() + " <pink>to mark it as done");
		MassiveTickets.alertMsg(sender, "Use " + MassiveTickets.get().getOuterCmdTickets().cmdTicketsModlist.getUseageTemplate() + " <pink>to list the moderators");
		MassiveTickets.alertMsg(sender, "Use " + MassiveTickets.get().getOuterCmdTickets().cmdTicketsCreate.getUseageTemplate() + " <pink>to update your current ticket message");
	}
}
