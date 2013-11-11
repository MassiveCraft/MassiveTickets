package com.massivecraft.massivetickets.cmd;

import com.massivecraft.massivetickets.Perm;
import com.massivecraft.massivetickets.entity.ARMPlayer;
import com.massivecraft.massivetickets.entity.MPlayer;
import com.massivecraft.mcore.cmd.req.ReqHasPerm;

public class CmdTicketsCheat extends MassiveTicketsCommand
{
	public CmdTicketsCheat()
	{	
		this.addOptionalArg("player", "you");
		
		this.addRequirements(ReqHasPerm.get(Perm.CHEAT.node));
	}
	
	@Override
	public void perform()
	{
		// Args
		MPlayer mplayer = this.arg(0, ARMPlayer.getStartOnline(), msender);
		if (mplayer == null) return;
		
		// Force Sync
		mplayer.sync();
		
		// Apply
		mplayer.givePoint(null);
		
		// Inform
		msg("<g>You gave <white>%s <g>a point 4NORaisins!", mplayer.getDisplayName());
	}
	
}
