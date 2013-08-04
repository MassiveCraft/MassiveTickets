package com.massivecraft.massivetickets.cmd;

import java.util.List;

import com.massivecraft.massivetickets.entity.MPlayer;
import com.massivecraft.mcore.cmd.MCommand;
import com.massivecraft.mcore.cmd.VisibilityMode;

public abstract class MassiveTicketsCommand extends MCommand
{
	public MPlayer msender;
	
	@Override
	public void fixSenderVars()
	{
		this.msender = MPlayer.get(sender);
	}
	
	public MassiveTicketsCommand(List<String> aliases)
	{
		this.setAliases(aliases);
		this.setVisibilityMode(VisibilityMode.SECRET);
	}
	
}
