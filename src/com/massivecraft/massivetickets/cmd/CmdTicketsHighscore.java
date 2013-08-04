package com.massivecraft.massivetickets.cmd;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.massivecraft.massivetickets.MassiveTickets;
import com.massivecraft.massivetickets.Perm;
import com.massivecraft.massivetickets.entity.MPlayer;
import com.massivecraft.massivetickets.entity.MPlayerColl;
import com.massivecraft.mcore.cmd.arg.ARInteger;
import com.massivecraft.mcore.cmd.req.ReqHasPerm;
import com.massivecraft.mcore.util.MUtil;
import com.massivecraft.mcore.util.Txt;

public class CmdTicketsHighscore extends MassiveTicketsCommand
{
	public CmdTicketsHighscore(List<String> aliases)
	{
		super(aliases);
		
		this.addOptionalArg("week", "now");
		
		this.addOptionalArg("year", "now");
		
		this.addRequirements(ReqHasPerm.get(Perm.HIGHSCORE.node));
	}
	
	@Override
	public void perform()
	{
		// Args
		Integer week = this.arg(0, ARInteger.get(), MassiveTickets.getCurrentWeek());
		if (week == null) return;
		
		Integer year = this.arg(1, ARInteger.get(), MassiveTickets.getCurrentYear());
		if (year == null) return;
		
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
		for (Entry<MPlayer, Integer> entry : MUtil.entriesSortedByValues(mplayer2count, false))
		{
			MPlayer mplayer = entry.getKey();
			Integer count = entry.getValue();
			
			msg("<white>%s <pink>%d", mplayer.getDisplayName(), count);
		}
		
	}
}
