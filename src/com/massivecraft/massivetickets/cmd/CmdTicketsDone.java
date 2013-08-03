package com.massivecraft.massivetickets.cmd;

import java.util.List;

import com.massivecraft.massivetickets.Perm;
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
		// TODO
		//Perm.DONE_OTHER
	}
}
