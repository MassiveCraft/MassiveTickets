package com.massivecraft.massivetickets.cmd;

import java.util.List;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.cmd.arg.ARInteger;
import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivecore.util.Txt;
import com.massivecraft.massivetickets.Perm;
import com.massivecraft.massivetickets.entity.MPlayerColl;

public class CmdTicketsList extends MassiveTicketsCommand
{
	public CmdTicketsList()
	{
		this.addOptionalArg("page", "1");
		
		this.addRequirements(ReqHasPerm.get(Perm.LIST.node));
	}
	
	@Override
	public void perform() throws MassiveException
	{
		// Args
		Integer pageHumanBased = this.arg(0, ARInteger.get(), 1);
		
		// Create Lines
		List<String> lines = MPlayerColl.get().getAllTicketListLines(sender);
		
		// Send them
		this.sendMessage(Txt.getPage(lines, pageHumanBased, "Ticket List", sender));		
	}
	
}
