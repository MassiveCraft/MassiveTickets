package com.massivecraft.massivetickets.cmd;

import java.util.List;

import com.massivecraft.massivetickets.Perm;
import com.massivecraft.massivetickets.entity.ARMPlayer;
import com.massivecraft.massivetickets.entity.MConf;
import com.massivecraft.massivetickets.entity.MPlayer;
import com.massivecraft.mcore.cmd.req.ReqHasPerm;

public class CmdTicketsDone extends MassiveTicketsCommand
{
	public CmdTicketsDone(List<String> aliases)
	{
		super(aliases);
		
		this.addOptionalArg("player", "you");
		
		this.addRequirements(ReqHasPerm.get(Perm.DONE.node));
	}
	
	@Override
	public void perform()
	{
		// Args
		MPlayer ticket = this.arg(0, ARMPlayer.getStartOnline(), msender);
		if (ticket == null) return;
		
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
