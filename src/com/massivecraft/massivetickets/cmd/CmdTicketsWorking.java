package com.massivecraft.massivetickets.cmd;

import java.util.List;

import com.massivecraft.massivetickets.Perm;
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
		// TODO
	}
}
