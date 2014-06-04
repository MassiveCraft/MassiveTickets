package com.massivecraft.massivetickets.cmd;

import com.massivecraft.massivecore.cmd.arg.ARBoolean;
import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivetickets.MassiveTickets;
import com.massivecraft.massivetickets.Perm;
import com.massivecraft.massivetickets.entity.MConf;

public class CmdTicketsWorking extends MassiveTicketsCommand
{
	public CmdTicketsWorking()
	{
		this.addOptionalArg("yes/no", "*toggle*");
		
		this.addRequirements(ReqHasPerm.get(Perm.WORKING.node));
	}
	
	@Override
	public void perform()
	{
		// Args
		boolean old = msender.isWorking();
		Boolean target = this.arg(0, ARBoolean.get(), !old);
		if (target == null) return;
		
		// Detect Nochange
		if (old == target)
		{
			if (target)
			{
				msg("<i>You are already working.");
			}
			else
			{
				msg("<i>You are already not working.");
			}
			return;
		}
		
		// Apply
		msender.setWorking(target);
		
		// Inform
		String verb = target ? "started" : "stopped"; 
		MassiveTickets.alertOneMsg(msender.getId(), "You %s twerking!", verb);
		MassiveTickets.alertOneMessage(msender.getId(), MassiveTickets.createBumpMessage());
		
		// React
		if (target)
		{
			MConf.get().getWorkingOnReaction().run(msender.getId(), null);
		}
		else
		{
			MConf.get().getWorkingOffReaction().run(msender.getId(), null);
		}
		
	}
	
}
