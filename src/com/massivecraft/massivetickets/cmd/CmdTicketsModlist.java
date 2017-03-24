package com.massivecraft.massivetickets.cmd;

import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.util.Txt;
import com.massivecraft.massivetickets.Perm;
import com.massivecraft.massivetickets.entity.MConf;
import com.massivecraft.massivetickets.entity.MPlayer;
import com.massivecraft.massivetickets.entity.MPlayerColl;

import java.util.ArrayList;
import java.util.List;

public class CmdTicketsModlist extends MassiveTicketsCommand
{
	// -------------------------------------------- //
	// INSTANCE
	// -------------------------------------------- //
	
	private static CmdTicketsModlist i = new CmdTicketsModlist() { @Override public List<String> getAliases() { return MConf.get().aliasesOuterTicketsModlist; } };
	public static CmdTicketsModlist get() { return i; }
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdTicketsModlist()
	{
		// Requirements
		this.addRequirements(RequirementHasPerm.get(Perm.MODLIST));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public List<String> getAliases()
	{
		return MConf.get().aliasesInnerTicketsModlist;
	}
	
	@Override
	public void perform()
	{
		message(Txt.titleize("Moderators Working Right Now"));
		
		List<String> names = new ArrayList<String>();
		for (MPlayer mplayer : MPlayerColl.get().getAllCurrentlyWorking())
		{
			if ( ! mplayer.isVisible(sender)) continue;
			names.add(mplayer.getDisplayName(sender));
		}
		
		if (names.size() > 0)
		{
			message(Txt.implodeCommaAndDot(names, Txt.parse("<i>")));
		}
		else
		{
			msg("<i><em>There are no moderators right now. Sorry.");
		}
		
	}
	
}
