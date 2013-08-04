package com.massivecraft.massivetickets.cmd;

import java.util.ArrayList;
import java.util.List;

import com.massivecraft.massivetickets.Perm;
import com.massivecraft.massivetickets.entity.MPlayer;
import com.massivecraft.massivetickets.entity.MPlayerColl;
import com.massivecraft.mcore.cmd.req.ReqHasPerm;
import com.massivecraft.mcore.util.Txt;

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
		sendMessage(Txt.titleize("Moderators Working Right Now"));
		
		List<String> names = new ArrayList<String>();
		for (MPlayer mplayer : MPlayerColl.get().getAllCurrentlyWorking())
		{
			names.add(mplayer.getDisplayName());
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
