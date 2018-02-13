package com.massivecraft.massivetickets.cmd;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.mson.Mson;
import com.massivecraft.massivetickets.MassiveTickets;
import com.massivecraft.massivetickets.Perm;
import com.massivecraft.massivetickets.cmd.type.TypeMPlayer;
import com.massivecraft.massivetickets.entity.MConf;
import com.massivecraft.massivetickets.entity.MPlayer;
import org.bukkit.ChatColor;

import java.util.List;

import static com.massivecraft.massivecore.mson.Mson.SPACE;

public class CmdTicketsYield extends MassiveTicketsCommand
{
	// -------------------------------------------- //
	// INSTANCE
	// -------------------------------------------- //
	
	private static CmdTicketsYield i = new CmdTicketsYield() { @Override public List<String> getAliases() { return MConf.get().aliasesOuterTicketsYield; } };
	public static CmdTicketsYield get() { return i; }
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdTicketsYield()
	{
		// Parameters
		this.addParameter(TypeMPlayer.getAny(), "player");
		
		// Requirements
		this.addRequirements(RequirementHasPerm.get(Perm.YIELD));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public List<String> getAliases()
	{
		return MConf.get().aliasesInnerTicketsYield;
	}
	
	@Override
	public void perform() throws MassiveException
	{
		// Args
		MPlayer ticket = this.readArg();
		
		// Force Sync
		ticket.sync();
		
		// The person who picked this ticket is ...
		MPlayer moderator = ticket.getModerator();
		
		// ... noone?
		if (moderator == null)
		{
			msg("<i>Can't yield since noone has picked this ticket.");
			return;
		}
		
		// ... someone else?
		boolean other = (moderator != msender);
		if (other && !Perm.YIELD_OTHER.has(sender, true)) return;
		
		// Apply
		ticket.setModeratorId(null);
		
		// Inform
		MassiveTickets.alertModeratorsMessage(this.getYieldedMson(ticket));
		MassiveTickets.alertModeratorsMsg(ticket.getMessage());
		
		MassiveTickets.alertOneMsg(ticket.getId(), "<white>%s <pink>has yielded your ticket.", msender.getDisplayName(ticket.getId()));
		MassiveTickets.alertOneMsg(ticket.getId(), "It is now placed back in the ticket list. ");
		
		// React
		MConf.get().getYieldReaction().run(moderator.getId(), ticket.getId());
	}
	
	private static Mson YIELDED = mson("yielded");
	private static Mson TICKET = mson("'s ticket.");
	
	private Mson getYieldedMson(MPlayer ticket)
	{
		return mson(
			mson(msender.getDisplayName(null)).color(ChatColor.WHITE),
			SPACE,
			YIELDED,
			SPACE,
			mson(ticket.getDisplayName(null)).color(ChatColor.WHITE),
			SPACE,
			TICKET,
			SPACE,
			BUTTON_SHOW.command(CmdTickets.get().cmdTicketsShow, ticket.getName())
		).color(ChatColor.LIGHT_PURPLE);
	}
	
}
