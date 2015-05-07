package com.massivecraft.massivetickets.cmd;

import java.util.ArrayList;
import java.util.List;

import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivecore.util.Txt;
import com.massivecraft.massivetickets.Perm;
import com.massivecraft.massivetickets.entity.MPlayer;
import com.massivecraft.massivetickets.entity.MPlayerColl;

public class CmdTicketsModlist extends MassiveTicketsCommand
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdTicketsModlist()
	{
		// Requirements
		this.addRequirements(ReqHasPerm.get(Perm.MODLIST.node));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void perform()
	{
		sendMessage(Txt.titleize("Moderators Working Right Now"));
		
		List<String> names = new ArrayList<String>();
		for (MPlayer mplayer : MPlayerColl.get().getAllCurrentlyWorking())
		{
			names.add(mplayer.getDisplayName(sender));
		}
		
		if (names.size() > 0)
		{
			sendMessage(Txt.implodeCommaAndDot(names, Txt.parse("<i>")));
		}
		else
		{
			msg("<i><em>There are no moderators right now. Sorry.");
		}
		
	}
	
}
