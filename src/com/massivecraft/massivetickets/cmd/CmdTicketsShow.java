package com.massivecraft.massivetickets.cmd;

import java.util.LinkedHashMap;

import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivecore.util.TimeDiffUtil;
import com.massivecraft.massivecore.util.TimeUnit;
import com.massivecraft.massivecore.util.Txt;
import com.massivecraft.massivetickets.Perm;
import com.massivecraft.massivetickets.entity.ARMPlayer;
import com.massivecraft.massivetickets.entity.MConf;
import com.massivecraft.massivetickets.entity.MPlayer;

public class CmdTicketsShow extends MassiveTicketsCommand
{
	public CmdTicketsShow()
	{
		this.addOptionalArg("player", "you");
		
		this.addRequirements(ReqHasPerm.get(Perm.SHOW.node));
	}
	
	@Override
	public void perform()
	{
		// Args
		MPlayer mplayer = this.arg(0, ARMPlayer.getOnline(), msender);
		if (mplayer == null) return;
		
		// Send them messages!
		msg(Txt.titleize(mplayer.getDisplayName(sender)+"<l>'s ticket"));
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
			pickedByDesc = mplayer.getModerator().getDisplayName(sender);
		}
		
		msg("<k>Picked By: <v>%s", pickedByDesc);
		
		// React
		MConf.get().getShowReaction().run(msender.getId(), mplayer.getId());
	}
	
}
