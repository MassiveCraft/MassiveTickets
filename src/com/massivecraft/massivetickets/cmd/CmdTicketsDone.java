package com.massivecraft.massivetickets.cmd;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivetickets.Perm;
import com.massivecraft.massivetickets.entity.ARMPlayer;
import com.massivecraft.massivetickets.entity.MConf;
import com.massivecraft.massivetickets.entity.MPlayer;

public class CmdTicketsDone extends MassiveTicketsCommand
{
	public CmdTicketsDone()
	{
		this.addOptionalArg("player", "you");
		
		this.addRequirements(ReqHasPerm.get(Perm.DONE.node));
	}
	
	@Override
	public void perform() throws MassiveException
	{
		// Args
		MPlayer ticket = this.arg(0, ARMPlayer.getOnline(), msender);
		
		// Force Sync
		ticket.sync();
		
		// Is there a ticket?
		if (!ticket.hasMessage())
		{
			msg("<white>%s <b>has not created a ticket.", ticket.getDisplayName());
			return;
		}
		
		// Is this ticket created by me or someone else?
		boolean other = (ticket != msender);
		if (other && !Perm.DONE_OTHER.has(sender, true)) return;
		
		// Now mark it as done
		ticket.markAsDone(msender);
		
		// React
		MConf.get().getDoneReaction().run(ticket.getModeratorId(), ticket.getId());
	}
}
