package com.massivecraft.massivetickets.cmd;

import java.util.List;

import com.massivecraft.massivetickets.Perm;
import com.massivecraft.mcore.cmd.req.ReqHasPerm;

public class CmdTicketsModlist extends MassiveTicketsCommand
{
	public CmdTicketsModlist(List<String> aliases)
	{
		super(aliases);
		
		this.addRequirements(ReqHasPerm.get(Perm.MODLIST.node));
	}
	
	@Override
	public void perform()
	{
		// TODO
	}
}
