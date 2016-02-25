package com.massivecraft.massivetickets.cmd;

import java.util.List;

import org.bukkit.ChatColor;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.mson.Mson;
import com.massivecraft.massivetickets.MassiveTickets;
import com.massivecraft.massivetickets.Perm;
import com.massivecraft.massivetickets.entity.MConf;
import com.massivecraft.massivetickets.entity.MPlayer;
import com.massivecraft.massivetickets.entity.TypeMPlayer;

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
		this.addParameter(TypeMPlayer.getOnline(), "player");
		
		// Requirements
		this.addRequirements(RequirementHasPerm.get(Perm.YIELD.node));
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
	
	private static Mson YIELDED = Mson.SPACE.add(mson("yielded").color(ChatColor.LIGHT_PURPLE)).add(Mson.SPACE);
	private static Mson TICKET = Mson.SPACE.add(mson("'s ticket.").color(ChatColor.LIGHT_PURPLE)).add(Mson.SPACE);
	
	private Mson getYieldedMson(MPlayer ticket)
	{
		Mson yielded = mson(
			mson(msender.getDisplayName(null)).color(ChatColor.WHITE),
			YIELDED,
			mson(ticket.getDisplayName(null)).color(ChatColor.WHITE),
			TICKET,
			BUTTON_SHOW.command(CmdTickets.get().cmdTicketsShow, ticket.getName())
		);
		
		return yielded;
	}
	
}
