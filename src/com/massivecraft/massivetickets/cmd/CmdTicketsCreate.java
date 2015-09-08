package com.massivecraft.massivetickets.cmd;

import org.bukkit.ChatColor;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.cmd.MassiveCommand;
import com.massivecraft.massivecore.cmd.arg.ARString;
import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivecore.mixin.Mixin;
import com.massivecraft.massivecore.mson.Mson;
import com.massivecraft.massivecore.util.Txt;
import com.massivecraft.massivetickets.MassiveTickets;
import com.massivecraft.massivetickets.Perm;
import com.massivecraft.massivetickets.entity.MConf;
import com.massivecraft.massivetickets.entity.MPlayerColl;

public class CmdTicketsCreate extends MassiveTicketsCommand
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdTicketsCreate()
	{
		// Args
		this.addArg(ARString.get(), "message", true);
		
		// Requirements
		this.addRequirements(ReqHasPerm.get(Perm.CREATE.node));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void perform() throws MassiveException
	{
		// Args
		String message = this.readArg();
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
			MassiveTickets.alertModeratorsMsg("<white>%s <pink>%s ticket: %s", msender.getDisplayName(null), verb, message);
		}
		
		// Inform Creator
		MassiveTickets.alertOneMsg(sender, "Your ticket was %s. We will help you soon.", verb);
		MassiveTickets.alertOneMsg(sender, "There is currently <aqua>%d <pink>working moderators.", MPlayerColl.get().getAllCurrentlyWorking().size());
		
		MassiveCommand cmd = null;
		
		cmd = MassiveTickets.get().getOuterCmdTickets().cmdTicketsShow;
		Mixin.messageOne(sender, getUseCommand(cmd, " to show your ticket"));
		
		cmd = MassiveTickets.get().getOuterCmdTickets().cmdTicketsDone;
		Mixin.messageOne(sender, getUseCommand(cmd, " to mark it as done"));
		
		cmd = MassiveTickets.get().getOuterCmdTickets().cmdTicketsModlist;
		Mixin.messageOne(sender, getUseCommand(cmd, " to list the moderators"));
		
		cmd = MassiveTickets.get().getOuterCmdTickets().cmdTicketsCreate;
		Mixin.messageOne(sender, getUseCommand(cmd, " to update the message"));
		
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
	
	private Mson getUseCommand(MassiveCommand command, String message)
	{
		String commandString = command.getCommandLine();
		String tooltip = Txt.parse("<g>Click to <c>%s<i>.", commandString);
		
		return Mson.mson(
				"Use ",
				command.getUseageTemplate(command.getCommandChain(), false, true, sender),
				mson(message).color(ChatColor.LIGHT_PURPLE).tooltip(tooltip)
		);
	}
	
}
