package com.massivecraft.massivetickets.cmd;

import com.massivecraft.massivecore.cmd.MassiveCommand;
import com.massivecraft.massivecore.cmd.VisibilityMode;
import com.massivecraft.massivetickets.entity.MPlayer;

public abstract class MassiveTicketsCommand extends MassiveCommand
{
	public MPlayer msender;
	
	@Override
	public void fixSenderVars()
	{
		this.msender = MPlayer.get(sender);
	}
	
	public MassiveTicketsCommand()
	{
		this.setVisibilityMode(VisibilityMode.SECRET);
	}
	
}
