package com.massivecraft.massivetickets.cmd;

import org.bukkit.ChatColor;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.cmd.MassiveCommand;
import com.massivecraft.massivecore.cmd.arg.ARString;
import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivetickets.MassiveTickets;
import com.massivecraft.massivetickets.Perm;
import com.massivecraft.massivetickets.entity.MConf;
import com.massivecraft.massivetickets.entity.MPlayerColl;

public class CmdTicketsCreate extends MassiveTicketsCommand
{
	public CmdTicketsCreate()
	{
		this.addRequiredArg("message");
		this.setErrorOnToManyArgs(false);
		
		this.addRequirements(ReqHasPerm.get(Perm.CREATE.node));
	}
	
	@Override
	public void perform() throws MassiveException
	{
		// Args
		String message = this.argConcatFrom(0, ARString.get());
		message = ChatColor.stripColor(message);
		
		// Apply
		boolean update = msender.hasMessage();
		String verb = update ? "updated" : "created";
		
		msender.setMessage(message);
		if (!update) msender.setMillis(System.currentTimeMillis());
		
		// Inform Moderators
		if (msender.hasModeratorId())
		{
			MassiveTickets.alertOneMsg(msender.getModeratorId(), "<white>%s <pink>%s ticket: %s", msender.getDisplayName(msender.getModeratorId()), verb, message);
		}
		else
		{
			MassiveTickets.alertModeratorsMsg("<white>%s <pink>%s ticket: %s", msender.getDisplayName(), verb, message);
		}
		
		// Inform Creator
		MassiveTickets.alertOneMsg(sender, "Your ticket was %s. We will help you soon.", verb);
		MassiveTickets.alertOneMsg(sender, "There is currently <aqua>%d <pink>working moderators.", MPlayerColl.get().getAllCurrentlyWorking().size());
		
		MassiveCommand cmd = null;
		
		cmd = MassiveTickets.get().getOuterCmdTickets().cmdTicketsShow;
		MassiveTickets.alertOneMsg(sender, "Use " + cmd.getUseageTemplate(cmd.getCommandChain(), false, true, sender) + " <pink>to show your ticket");
		
		cmd = MassiveTickets.get().getOuterCmdTickets().cmdTicketsDone;
		MassiveTickets.alertOneMsg(sender, "Use " + cmd.getUseageTemplate(cmd.getCommandChain(), false, true, sender) + " <pink>to mark it as done");
		
		cmd = MassiveTickets.get().getOuterCmdTickets().cmdTicketsModlist;
		MassiveTickets.alertOneMsg(sender, "Use " + cmd.getUseageTemplate(cmd.getCommandChain(), false, true, sender) + " <pink>to list the moderators");
		
		cmd = MassiveTickets.get().getOuterCmdTickets().cmdTicketsCreate;
		MassiveTickets.alertOneMsg(sender, "Use " + cmd.getUseageTemplate(cmd.getCommandChain(), false, true, sender) + " <pink>to update the message");
		
		// React
		if (update)
		{
			MConf.get().getUpdateReaction().run(msender.getModeratorId(), msender.getId());
		}
		else
		{
			MConf.get().getCreateReaction().run(msender.getModeratorId(), msender.getId());
		}
				
	}
	
}
