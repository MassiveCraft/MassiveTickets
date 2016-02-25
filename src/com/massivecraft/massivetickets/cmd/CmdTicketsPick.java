package com.massivecraft.massivetickets.cmd;

import java.util.List;

import org.bukkit.ChatColor;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.MassiveCommand;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.mson.Mson;
import com.massivecraft.massivetickets.MassiveTickets;
import com.massivecraft.massivetickets.Perm;
import com.massivecraft.massivetickets.entity.TypeMPlayer;
import com.massivecraft.massivetickets.entity.MConf;
import com.massivecraft.massivetickets.entity.MPlayer;

public class CmdTicketsPick extends MassiveTicketsCommand
{
	// -------------------------------------------- //
	// INSTANCE
	// -------------------------------------------- //
	
	private static CmdTicketsPick i = new CmdTicketsPick() { @Override public List<String> getAliases() { return MConf.get().aliasesOuterTicketsPick; } };
	public static CmdTicketsPick get() { return i; }
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdTicketsPick()
	{
		// Parameters
		this.addParameter(TypeMPlayer.getOnline(), "player");
		
		// Requirements
		this.addRequirements(RequirementHasPerm.get(Perm.PICK.node));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public List<String> getAliases()
	{
		return MConf.get().aliasesInnerTicketsPick;
	}
	
	@Override
	public void perform() throws MassiveException
	{
		// Args
		MPlayer ticket = this.readArg();		
		
		// Force Sync
		ticket.sync();
		
		// Is there a ticket?
		if (!ticket.hasMessage())
		{
			msg("<white>%s <b>has not created a ticket.", ticket.getDisplayName(sender));
			return;
		}
		
		// Already picked?
		MPlayer moderator = ticket.getModerator();
		if (moderator != null)
		{
			Mson message = null;
			String commandLine = null;
			MassiveCommand command = CmdTickets.get().cmdTicketsYield;
			
			if (moderator == msender)
			{
				message = mson("You have already picked this ticket.").color(ChatColor.YELLOW);
				
				if (Perm.YIELD.has(sender)) commandLine = command.getCommandLine(msender.getName());
			}
			else
			{
				message = mson(
					ChatColor.stripColor(moderator.getDisplayName(sender)),
					mson(" has already picked this ticket.").color(ChatColor.RED)
				);
				
				if (Perm.YIELD_OTHER.has(sender)) commandLine = command.getCommandLine(moderator.getName());
			}
			
			if (commandLine != null)
			{
				message = mson(message, Mson.SPACE, CmdTicketsShow.BUTTON_YIELD.command(commandLine));
			}
			message(message);
			return;
		}
		
		// Apply
		ticket.setModerator(msender);
		
		// Inform
		MassiveTickets.alertModeratorsMsg("<white>%s <pink>picked <white>%s<pink>'s ticket.", msender.getDisplayName(null), ticket.getDisplayName(null));
		MassiveTickets.alertOneMsg(msender.getId(), ticket.getMessage());
		
		MassiveTickets.alertOneMsg(ticket.getId(), "<white>%s <pink>just picked your ticket.", msender.getDisplayName(ticket.getId()));
		MassiveTickets.alertOneMsg(ticket.getId(), "Please go ahead and explain the situation.");
		
		// React
		MConf.get().getPickReaction().run(msender.getId(), ticket.getId());
	}
	
}
