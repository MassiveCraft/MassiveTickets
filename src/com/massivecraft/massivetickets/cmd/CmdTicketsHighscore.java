package com.massivecraft.massivetickets.cmd;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.cmd.arg.ARInteger;
import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivecore.util.MUtil;
import com.massivecraft.massivecore.util.Txt;
import com.massivecraft.massivetickets.Level;
import com.massivecraft.massivetickets.MassiveTickets;
import com.massivecraft.massivetickets.Perm;
import com.massivecraft.massivetickets.entity.MConf;
import com.massivecraft.massivetickets.entity.MPlayer;
import com.massivecraft.massivetickets.entity.MPlayerColl;

public class CmdTicketsHighscore extends MassiveTicketsCommand
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdTicketsHighscore()
	{
		// Args
		this.addArg(ARInteger.get(), "week", "now");
		this.addArg(ARInteger.get(), "year", "now");
		
		// Requirements
		this.addRequirements(ReqHasPerm.get(Perm.HIGHSCORE.node));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void perform() throws MassiveException
	{
		// Args
		int week = this.readArg(MassiveTickets.getCurrentWeek());
		int year = this.readArg(MassiveTickets.getCurrentYear());
		
		// Compile highscore data
		Map<MPlayer, Integer> mplayer2count = new HashMap<MPlayer, Integer>();
		for (MPlayer mplayer : MPlayerColl.get().getAll())
		{
			int count = mplayer.getCount(year, week);
			if (count == 0) continue;
			mplayer2count.put(mplayer, count);
		}
		
		// Send Message
		sendMessage(Txt.titleize("Highscore Year " + year + " Week " + week));
		
		if (mplayer2count.size() > 0)
		{
			for (Entry<MPlayer, Integer> entry : MUtil.entriesSortedByValues(mplayer2count, false))
			{
				MPlayer mplayer = entry.getKey();
				Integer count = entry.getValue();
				Level level = MConf.get().getLevelForCount(count);
				String levelDesc = "";
				if (level != null) levelDesc = level.getName();
				msg("<pink>%d <white>%s <pink><em>%s", count, mplayer.getDisplayName(sender), levelDesc);
			}
		}
		else
		{
			msg("<i><em>no activity this week");
		}
		
	}
	
}
