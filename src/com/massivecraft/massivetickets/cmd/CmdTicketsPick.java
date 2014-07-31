package com.massivecraft.massivetickets.cmd;

import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivetickets.MassiveTickets;
import com.massivecraft.massivetickets.Perm;
import com.massivecraft.massivetickets.entity.ARMPlayer;
import com.massivecraft.massivetickets.entity.MConf;
import com.massivecraft.massivetickets.entity.MPlayer;

public class CmdTicketsPick extends MassiveTicketsCommand
{
	public CmdTicketsPick()
	{
		this.addRequiredArg("player");
		
		this.addRequirements(ReqHasPerm.get(Perm.PICK.node));
	}
	
	@Override
	public void perform()
	{
		// Args
		MPlayer ticket = this.arg(0, ARMPlayer.getOnline());
		if (ticket == null) return;
		
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
			if (moderator == msender)
			{
				msg("<i>You have already picked this ticket.");
			}
			else
			{
				msg("<white>%s <b>has already picked this ticket.", moderator.getDisplayName(sender));
			}
			return;
		}
		
		// Apply
		ticket.setModerator(msender);
		
		// Inform
		MassiveTickets.alertModeratorsMsg("<white>%s <pink>picked <white>%s<pink>'s ticket.", msender.getDisplayName(), ticket.getDisplayName());
		MassiveTickets.alertOneMsg(msender.getId(), ticket.getMessage());
		
		MassiveTickets.alertOneMsg(ticket.getId(), "<white>%s <pink>just picked your ticket.", msender.getDisplayName());
		MassiveTickets.alertOneMsg(ticket.getId(), "Please go ahead and explain the situation.");
		
		// React
		MConf.get().getPickReaction().run(msender.getId(), ticket.getId());
	}
	
}
