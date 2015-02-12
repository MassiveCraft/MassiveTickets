package com.massivecraft.massivetickets.cmd;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivetickets.MassiveTickets;
import com.massivecraft.massivetickets.Perm;
import com.massivecraft.massivetickets.entity.ARMPlayer;
import com.massivecraft.massivetickets.entity.MConf;
import com.massivecraft.massivetickets.entity.MPlayer;

public class CmdTicketsYield extends MassiveTicketsCommand
{
	public CmdTicketsYield()
	{
		this.addRequiredArg("player");
		
		this.addRequirements(ReqHasPerm.get(Perm.YIELD.node));
	}
	
	@Override
	public void perform() throws MassiveException
	{
		// Args
		MPlayer ticket = this.arg(0, ARMPlayer.getOnline());
		
		// Force Sync
		ticket.sync();
		
		// The person who picked this ticket is ...
		MPlayer moderator = ticket.getModerator();
		
		// ... noone?
		if (moderator == null)
		{
			msg("<i>Can't yield since noone has picked this ticket.");
			return;
		}
		
		// ... someone else?
		boolean other = (moderator != msender);
		if (other && !Perm.YIELD_OTHER.has(sender, true)) return;
		
		// Apply
		ticket.setModeratorId(null);
		
		// Inform
		MassiveTickets.alertModeratorsMsg("<white>%s <pink>yielded <white>%s<pink>'s ticket.", msender.getDisplayName(), ticket.getDisplayName());
		MassiveTickets.alertModeratorsMsg(ticket.getMessage());
		
		MassiveTickets.alertOneMsg(ticket.getId(), "<white>%s <pink>has yielded your ticket.", msender.getDisplayName());
		MassiveTickets.alertOneMsg(ticket.getId(), "It is now placed back in the ticket list. ");
		
		// React
		MConf.get().getYieldReaction().run(moderator.getId(), ticket.getId());
	}
	
}
