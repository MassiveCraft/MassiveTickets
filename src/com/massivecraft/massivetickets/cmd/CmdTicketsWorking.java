package com.massivecraft.massivetickets.cmd;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.cmd.arg.ARBoolean;
import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivetickets.MassiveTickets;
import com.massivecraft.massivetickets.Perm;
import com.massivecraft.massivetickets.entity.MConf;

public class CmdTicketsWorking extends MassiveTicketsCommand
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdTicketsWorking()
	{
		// Args
		this.addArg(ARBoolean.get(), "yes/no", "*toggle*");
		
		// Requirements
		this.addRequirements(ReqHasPerm.get(Perm.WORKING.node));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void perform() throws MassiveException
	{
		// Args
		boolean before = msender.isWorking();
		boolean after = this.readArg(!before);
		
		// Detect Nochange
		if (before == after)
		{
			if (after)
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
		msender.setWorking(after);
		
		// Inform
		String verb = after ? "started" : "stopped"; 
		MassiveTickets.alertOneMsg(msender.getId(), "You %s twerking!", verb);
		MassiveTickets.alertOneMessage(msender.getId(), MassiveTickets.createBumpMessage());
		
		// React
		if (after)
		{
			MConf.get().getWorkingOnReaction().run(msender.getId(), null);
		}
		else
		{
			MConf.get().getWorkingOffReaction().run(msender.getId(), null);
		}
		
	}
	
}
