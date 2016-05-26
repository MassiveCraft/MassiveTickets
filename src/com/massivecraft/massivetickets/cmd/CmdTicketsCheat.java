package com.massivecraft.massivetickets.cmd;

import java.util.List;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivetickets.Perm;
import com.massivecraft.massivetickets.entity.TypeMPlayer;
import com.massivecraft.massivetickets.entity.MConf;
import com.massivecraft.massivetickets.entity.MPlayer;

public class CmdTicketsCheat extends MassiveTicketsCommand
{
	// -------------------------------------------- //
	// INSTANCE
	// -------------------------------------------- //
	
	private static CmdTicketsCheat i = new CmdTicketsCheat() { @Override public List<String> getAliases() { return MConf.get().aliasesOuterTicketsCheat; } };
	public static CmdTicketsCheat get() { return i; }
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdTicketsCheat()
	{
		// Parameters
		this.addParameter(TypeMPlayer.getOnline(), "player", "you");
		
		// Requirements
		this.addRequirements(RequirementHasPerm.get(Perm.CHEAT));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public List<String> getAliases()
	{
		return MConf.get().aliasesInnerTicketsCheat;
	}
	
	@Override
	public void perform() throws MassiveException
	{
		// Args
		MPlayer mplayer = this.readArg(msender);
		
		// Force Sync
		mplayer.sync();
		
		// Apply
		mplayer.givePoint(null);
		
		// Inform
		msg("<g>You gave <white>%s <g>a point 4NORaisins!", mplayer.getDisplayName(sender));
	}
	
}
