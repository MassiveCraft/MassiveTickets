package com.massivecraft.massivetickets.cmd;

import java.util.List;

import com.massivecraft.massivetickets.MassiveTickets;
import com.massivecraft.massivetickets.Perm;
import com.massivecraft.massivetickets.entity.MPlayerColl;
import com.massivecraft.mcore.cmd.arg.ARBoolean;
import com.massivecraft.mcore.cmd.req.ReqHasPerm;

public class CmdTicketsWorking extends MassiveTicketsCommand
{
	public CmdTicketsWorking(List<String> aliases)
	{
		super(aliases);
		
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
		MassiveTickets.alertMsg("<white>%s <pink>%s working. There's now <aqua>%d <pink>moderators.", msender.getDisplayName(), verb, MPlayerColl.get().getAllCurrentlyWorking().size());
	}
	
}
