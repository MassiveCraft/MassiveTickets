package com.massivecraft.massivetickets.cmd;

import java.util.List;

import com.massivecraft.massivetickets.Perm;
import com.massivecraft.mcore.cmd.req.ReqHasPerm;

public class CmdTicketsShow extends MassiveTicketsCommand
{
	public CmdTicketsShow(List<String> aliases)
	{
		super(aliases);
		
		this.addOptionalArg("player", "you");
		
		this.addRequirements(ReqHasPerm.get(Perm.SHOW.node));
	}
	
	@Override
	public void perform()
	{
		// Args
		
	}
}
