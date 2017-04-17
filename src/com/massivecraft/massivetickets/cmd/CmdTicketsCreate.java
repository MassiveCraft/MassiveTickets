package com.massivecraft.massivetickets.cmd;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.MassiveCommand;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.command.type.primitive.TypeString;
import com.massivecraft.massivecore.mixin.MixinMessage;
import com.massivecraft.massivecore.mson.Mson;
import com.massivecraft.massivecore.util.Txt;
import com.massivecraft.massivetickets.MassiveTickets;
import com.massivecraft.massivetickets.Perm;
import com.massivecraft.massivetickets.entity.MConf;
import com.massivecraft.massivetickets.entity.MPlayer;
import com.massivecraft.massivetickets.entity.MPlayerColl;
import org.bukkit.ChatColor;

import java.util.List;

import static com.massivecraft.massivecore.mson.Mson.SPACE;

public class CmdTicketsCreate extends MassiveTicketsCommand
{
	// -------------------------------------------- //
	// INSTANCE
	// -------------------------------------------- //
	
	private static CmdTicketsCreate i = new CmdTicketsCreate() { @Override public List<String> getAliases() { return MConf.get().aliasesOuterTicketsCreate; } };
	public static CmdTicketsCreate get() { return i; }
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdTicketsCreate()
	{
		// Parameters
		this.addParameter(TypeString.get(), "message", true);
		
		// Requirements
		this.addRequirements(RequirementHasPerm.get(Perm.CREATE));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public List<String> getAliases()
	{
		return MConf.get().aliasesInnerTicketsCreate;
	}
	
	@Override
	public void perform() throws MassiveException
	{
		// Args
		String message = this.readArg();
		message = ChatColor.stripColor(message);
		
		// Apply
		boolean update = msender.hasMessage();
		String verb = update ? "updated" : "created";
		
		msender.setMessage(message);
		if (!update) msender.setMillis(System.currentTimeMillis());
		
		// Inform Moderators
		if (msender.hasModeratorId())
		{
			MassiveTickets.alertOneMsg(msender.getModeratorId(), "<white>%s <pink>%s ticket: %s", msender.getDisplayName(msender.getModeratorId()), verb, message);
		}
		else
		{
			MassiveTickets.alertModeratorsMessage(this.getCreatedMson(msender, verb, message));
		}
		
		// Inform Creator
		MassiveTickets.alertOneMsg(sender, "Your ticket was %s. We will help you soon.", verb);
		MassiveTickets.alertOneMsg(sender, "There is currently <aqua>%d <pink>working moderators.", MPlayerColl.get().getAllCurrentlyWorking().size());
		
		MassiveCommand cmd = null;
		
		cmd = CmdTickets.get().cmdTicketsShow;
		MixinMessage.get().messageOne(sender, this.getUseCommand(cmd, " to show your ticket"));
		
		cmd = CmdTickets.get().cmdTicketsDone;
		MixinMessage.get().messageOne(sender, this.getUseCommand(cmd, " to mark it as done"));
		
		cmd = CmdTickets.get().cmdTicketsModlist;
		MixinMessage.get().messageOne(sender, this.getUseCommand(cmd, " to list the moderators"));
		
		cmd = CmdTickets.get().cmdTicketsCreate;
		MixinMessage.get().messageOne(sender, this.getUseCommand(cmd, " to update the message"));
		
		// React
		if (update)
		{
			MConf.get().getUpdateReaction().run(msender.getModeratorId(), msender.getId());
		}
		else
		{
			MConf.get().getCreateReaction().run(msender.getModeratorId(), msender.getId());
		}	
	}
	
	private Mson getUseCommand(MassiveCommand command, String message)
	{
		String commandString = command.getCommandLine();
		String tooltip = Txt.parse("<g>Click to <c>%s<i>.", commandString);
		
		return mson(
			"Use ",
			command.getTemplate(false, true, sender),
			mson(message).tooltip(tooltip)
		).color(ChatColor.LIGHT_PURPLE);
	}
	
	private Mson getCreatedMson(MPlayer mplayer, String verb, String message)
	{
		return mson(
			mson(mplayer.getDisplayName(null)).color(ChatColor.WHITE),
			SPACE,
			mson(verb),
			SPACE,
			mson("ticket: " + message),
			SPACE,
			BUTTON_SHOW.command(CmdTickets.get().cmdTicketsShow, mplayer.getName())
		).color(ChatColor.LIGHT_PURPLE);
	}
	
}
