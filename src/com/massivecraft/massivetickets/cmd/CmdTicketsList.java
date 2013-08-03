package com.massivecraft.massivetickets.cmd;

import java.util.List;

import com.massivecraft.massivetickets.Perm;
import com.massivecraft.mcore.cmd.arg.ARInteger;
import com.massivecraft.mcore.cmd.req.ReqHasPerm;

public class CmdTicketsList extends MassiveTicketsCommand
{
	public CmdTicketsList(List<String> aliases)
	{
		super(aliases);
		
		this.addOptionalArg("page", "1");
		
		this.addRequirements(ReqHasPerm.get(Perm.LIST.node));
	}
	
	@Override
	public void perform()
	{
		// Parse parameters
		Integer pageHumanBased = this.arg(0, ARInteger.get(), 1);
		if (pageHumanBased == null) return;
		
		// Create Messages
		// TODO
				
		// Send them
		// TODO	
	}
}
