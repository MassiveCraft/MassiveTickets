package com.massivecraft.massivetickets.cmd;

import java.util.List;

import com.massivecraft.massivetickets.Perm;
import com.massivecraft.massivetickets.entity.MPlayerColl;
import com.massivecraft.mcore.cmd.arg.ARInteger;
import com.massivecraft.mcore.cmd.req.ReqHasPerm;
import com.massivecraft.mcore.util.Txt;

public class CmdTicketsList extends MassiveTicketsCommand
{
	public CmdTicketsList()
	{
		this.addOptionalArg("page", "1");
		
		this.addRequirements(ReqHasPerm.get(Perm.LIST.node));
	}
	
	@Override
	public void perform()
	{
		// Args
		Integer pageHumanBased = this.arg(0, ARInteger.get(), 1);
		if (pageHumanBased == null) return;
		
		// Create Lines
		List<String> lines = MPlayerColl.get().getAllTicketListLines();
		
		// Send them
		this.sendMessage(Txt.getPage(lines, pageHumanBased, "Ticket List", sender));		
	}
	
}
