package com.massivecraft.massivetickets.cmd;

import java.util.List;

import com.massivecraft.massivetickets.MassiveTickets;
import com.massivecraft.massivetickets.Perm;
import com.massivecraft.massivetickets.entity.ARMPlayer;
import com.massivecraft.massivetickets.entity.MPlayer;
import com.massivecraft.mcore.cmd.req.ReqHasPerm;

public class CmdTicketsPick extends MassiveTicketsCommand
{
	public CmdTicketsPick(List<String> aliases)
	{
		super(aliases);
		
		this.addRequiredArg("player");
		
		this.addRequirements(ReqHasPerm.get(Perm.PICK.node));
	}
	
	@Override
	public void perform()
	{
		// Args
		MPlayer ticket = this.arg(0, ARMPlayer.getStartOnline());
		if (ticket == null) return;
		
		// Force Sync
		ticket.sync();
		
		// Is there a ticket?
		if (!ticket.hasMessage())
		{
			msg("<white>%s <b>has not created a ticket.", ticket.getDisplayName());
			return;
		}
		
		// Already picked?
		MPlayer moderator = ticket.getModerator();
		if (moderator != null)
		{
			if (moderator == msender)
			{
				msg("<i>You have already picked this ticket.");
			}
			else
			{
				msg("<white>%s <b>has already picked this ticket.", moderator.getDisplayName());
			}
			return;
		}
		
		// Apply
		ticket.setModerator(msender);
		
		// Inform
		MassiveTickets.alertMsg("<white>%s <pink>picked <white>%s<pink>'s ticket:", msender.getDisplayName(), ticket.getDisplayName());
		MassiveTickets.alertMsg(ticket.getMessage());
		
		MassiveTickets.alertMsg(ticket.getId(), "<white>%s <pink>just picked your ticket.", msender.getDisplayName());
		// TODO: How about some message here encouraging the player to do something.
		
	}
	
}
