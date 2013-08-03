package com.massivecraft.massivetickets.cmd;

import java.util.List;

import com.massivecraft.massivetickets.entity.MPlayer;
import com.massivecraft.mcore.cmd.MCommand;

public abstract class MassiveTicketsCommand extends MCommand
{
	public MPlayer mme;
	
	@Override
	public void fixSenderVars()
	{
		this.mme = MPlayer.get(sender);
	}
	
	public MassiveTicketsCommand(List<String> aliases)
	{
		this.setAliases(aliases);
	}
	
}
