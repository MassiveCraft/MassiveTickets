package com.massivecraft.massivetickets.cmd;

import java.util.List;

import com.massivecraft.massivetickets.Perm;
import com.massivecraft.mcore.cmd.req.ReqHasPerm;

public class CmdTicketsHighscore extends MassiveTicketsCommand
{
	public CmdTicketsHighscore(List<String> aliases)
	{
		super(aliases);
		
		this.addOptionalArg("week", "now");
		
		this.addOptionalArg("year", "now");
		
		this.addRequirements(ReqHasPerm.get(Perm.HIGHSCORE.node));
	}
	
	@Override
	public void perform()
	{
		// TODO	
	}
}
