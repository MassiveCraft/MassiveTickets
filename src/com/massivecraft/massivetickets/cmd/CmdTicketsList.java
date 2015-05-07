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
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdTicketsList()
	{
		// Args
		this.addArg(ARInteger.get(), "page", "1");
		
		// Requirements
		this.addRequirements(ReqHasPerm.get(Perm.LIST.node));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void perform() throws MassiveException
	{
		// Args
		int pageHumanBased = this.readArg(1);
		
		// Create Lines
		List<String> lines = MPlayerColl.get().getAllTicketListLines(sender);
		
		// Send them
		this.sendMessage(Txt.getPage(lines, pageHumanBased, "Ticket List", sender));		
	}
	
}
