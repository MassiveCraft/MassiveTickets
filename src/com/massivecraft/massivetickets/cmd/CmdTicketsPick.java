package com.massivecraft.massivetickets.cmd;

import java.util.List;

import com.massivecraft.massivetickets.Perm;
import com.massivecraft.mcore.cmd.req.ReqHasPerm;

public class CmdTicketsPick extends MassiveTicketsCommand
{
	public CmdTicketsPick(List<String> aliases)
	{
		super(aliases);
		
		this.addRequiredArg("player");
		
		this.addRequirements(ReqHasPerm.get(Perm.PICK.node));
	}
	
	@Override
	public void perform()
	{
		// TODO
	}
}
