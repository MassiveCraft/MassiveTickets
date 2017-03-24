package com.massivecraft.massivetickets.cmd;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.command.type.primitive.TypeInteger;
import com.massivecraft.massivecore.util.MUtil;
import com.massivecraft.massivecore.util.Txt;
import com.massivecraft.massivetickets.MassiveTickets;
import com.massivecraft.massivetickets.Perm;
import com.massivecraft.massivetickets.entity.MConf;
import com.massivecraft.massivetickets.entity.MPlayer;
import com.massivecraft.massivetickets.entity.MPlayerColl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class CmdTicketsHighscore extends MassiveTicketsCommand
{
	// -------------------------------------------- //
	// INSTANCE
	// -------------------------------------------- //
	
	private static CmdTicketsHighscore i = new CmdTicketsHighscore() { @Override public List<String> getAliases() { return MConf.get().aliasesOuterTicketsHighscore; } };
	public static CmdTicketsHighscore get() { return i; }
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdTicketsHighscore()
	{
		// Parameters
		this.addParameter(TypeInteger.get(), "week", "now");
		this.addParameter(TypeInteger.get(), "year", "now");
		
		// Requirements
		this.addRequirements(RequirementHasPerm.get(Perm.HIGHSCORE));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public List<String> getAliases()
	{
		return MConf.get().aliasesInnerTicketsHighscore;
	}
	
	@Override
	public void perform() throws MassiveException
	{
		// Args
		int week = this.readArg(MassiveTickets.getCurrentWeek());
		int year = this.readArg(MassiveTickets.getCurrentYear());
		
		// Compile highscore data
		Map<MPlayer, Integer> mplayer2count = new HashMap<>();
		for (MPlayer mplayer : MPlayerColl.get().getAll())
		{
			int count = mplayer.getCount(year, week);
			if (count == 0) continue;
			mplayer2count.put(mplayer, count);
		}
		
		// Send Message
		message(Txt.titleize("Highscore Year " + year + " Week " + week));
		
		if (mplayer2count.size() > 0)
		{
			for (Entry<MPlayer, Integer> entry : MUtil.entriesSortedByValues(mplayer2count, false))
			{
				MPlayer mplayer = entry.getKey();
				Integer count = entry.getValue();
				msg("<pink>%d <white>%s", count, mplayer.getDisplayName(sender));
			}
		}
		else
		{
			msg("<i><em>no activity this week");
		}
		
	}
	
}
