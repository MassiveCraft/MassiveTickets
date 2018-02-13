package com.massivecraft.massivetickets.cmd;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivetickets.MassiveTickets;
import com.massivecraft.massivetickets.Perm;
import com.massivecraft.massivetickets.cmd.type.TypeMPlayer;
import com.massivecraft.massivetickets.entity.MConf;
import com.massivecraft.massivetickets.entity.MPlayer;

import java.util.List;

public class CmdTicketsClose extends MassiveTicketsCommand
{
	// -------------------------------------------- //
	// INSTANCE
	// -------------------------------------------- //
	
	private static CmdTicketsClose i = new CmdTicketsClose() { @Override public List<String> getAliases() { return MConf.get().aliasesOuterTicketsClose; } };
	public static CmdTicketsClose get() { return i; }
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdTicketsClose()
	{
		// Parameters
		this.addParameter(TypeMPlayer.getAny(), "player");
		
		// Requirements
		this.addRequirements(RequirementHasPerm.get(Perm.CLOSE));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public List<String> getAliases()
	{
		return MConf.get().aliasesInnerTicketsClose;
	}
	
	@Override
	public void perform() throws MassiveException
	{
		// Args
		MPlayer ticket = this.readArg();
		
		// Force Sync
		ticket.sync();
		
		// Is there a ticket?
		if (!ticket.hasMessage())
		{
			msg("<white>%s <b>has not created a ticket.", ticket.getDisplayName(null));
			return;
		}
		
		// Apply
		ticket.setModeratorId(null);
		ticket.markAsDone(null);
		
		// Inform
		MassiveTickets.alertModeratorsMsg("<white>%s <pink>closed <white>%s<pink>'s ticket.", msender.getDisplayName(null), ticket.getDisplayName(null));
	}
	
}
