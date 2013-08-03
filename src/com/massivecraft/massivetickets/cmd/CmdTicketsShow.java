package com.massivecraft.massivetickets.cmd;

import java.util.LinkedHashMap;
import java.util.List;

import com.massivecraft.massivetickets.Perm;
import com.massivecraft.massivetickets.entity.ARMPlayer;
import com.massivecraft.massivetickets.entity.MPlayer;
import com.massivecraft.mcore.cmd.req.ReqHasPerm;
import com.massivecraft.mcore.util.TimeDiffUtil;
import com.massivecraft.mcore.util.TimeUnit;
import com.massivecraft.mcore.util.Txt;

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
		MPlayer mplayer = this.arg(0, ARMPlayer.getStartOnline(), msender);
		if (mplayer == null) return;
		
		// Send them messages!
		msg(Txt.titleize(mplayer.getDisplayName()+"<l>'s ticket"));
		if (!mplayer.hasMessage())
		{
			msg("<silver><em>has not created a ticket");
			return;
		}
		msg("<k>Message: <v>%s", mplayer.getMessage());
		
		LinkedHashMap<TimeUnit, Long> unitcounts = TimeDiffUtil.unitcounts(System.currentTimeMillis() - mplayer.getMillis(), TimeUnit.getAllButMillis());
		String formated = TimeDiffUtil.formatedVerboose(unitcounts, "<v>");
		msg("<k>Created: <v>%s <v>ago", formated);
		
		String pickedByDesc = Txt.parse("<silver><em>noone yet");
		if (mplayer.hasModeratorId())
		{
			pickedByDesc = mplayer.getModerator().getDisplayName();
		}
		
		msg("<k>Picked By: <v>%s", pickedByDesc);
	}
	
}
