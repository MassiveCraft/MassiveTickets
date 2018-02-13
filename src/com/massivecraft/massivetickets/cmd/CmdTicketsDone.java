package com.massivecraft.massivetickets.cmd;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivetickets.Perm;
import com.massivecraft.massivetickets.cmd.type.TypeMPlayer;
import com.massivecraft.massivetickets.entity.MConf;
import com.massivecraft.massivetickets.entity.MPlayer;

import java.util.List;

public class CmdTicketsDone extends MassiveTicketsCommand
{
	// -------------------------------------------- //
	// INSTANCE
	// -------------------------------------------- //
	
	private static CmdTicketsDone i = new CmdTicketsDone() { @Override public List<String> getAliases() { return MConf.get().aliasesOuterTicketsDone; } };
	public static CmdTicketsDone get() { return i; }
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdTicketsDone()
	{
		// Parameters
		this.addParameter(TypeMPlayer.getAny(), "player", "you");
		
		// Requirements
		this.addRequirements(RequirementHasPerm.get(Perm.DONE));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public List<String> getAliases()
	{
		return MConf.get().aliasesInnerTicketsDone;
	}
	
	@Override
	public void perform() throws MassiveException
	{
		// Args
		MPlayer ticket = this.readArg(msender);
		
		// Force Sync
		ticket.sync();
		
		// Is there a ticket?
		if (!ticket.hasMessage())
		{
			msg("<white>%s <b>has not created a ticket.", ticket.getDisplayName(null));
			return;
		}
		
		// Is this ticket created by me or someone else?
		boolean other = (ticket != msender);
		if (other && !Perm.DONE_OTHER.has(sender, true)) return;
		
		// Now mark it as done
		ticket.markAsDone(msender);
		
		// React
		MConf.get().getDoneReaction().run(ticket.getModeratorId(), ticket.getId());
	}
	
}
