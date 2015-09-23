package com.massivecraft.massivetickets.cmd;

import java.util.LinkedHashMap;

import org.bukkit.ChatColor;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.cmd.MassiveCommand;
import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivecore.mson.Mson;
import com.massivecraft.massivecore.mson.MsonEvent;
import com.massivecraft.massivecore.util.PermUtil;
import com.massivecraft.massivecore.util.TimeDiffUtil;
import com.massivecraft.massivecore.util.TimeUnit;
import com.massivecraft.massivecore.util.Txt;
import com.massivecraft.massivetickets.MassiveTickets;
import com.massivecraft.massivetickets.Perm;
import com.massivecraft.massivetickets.entity.ARMPlayer;
import com.massivecraft.massivetickets.entity.MConf;
import com.massivecraft.massivetickets.entity.MPlayer;

public class CmdTicketsShow extends MassiveTicketsCommand
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdTicketsShow()
	{
		// Args
		this.addArg(ARMPlayer.getOnline(), "player", "you");
		
		// Requirements
		this.addRequirements(ReqHasPerm.get(Perm.SHOW.node));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void perform() throws MassiveException
	{
		// Args
		MPlayer mplayer = this.readArg(msender);
		
		// Other Perm?
		if (mplayer != msender && !Perm.SHOW_OTHER.has(sender, true)) return;
		
		// Send them messages!
		msg(Txt.titleize(mplayer.getDisplayName(sender)+"<l>'s ticket"));
		if (!mplayer.hasMessage())
		{
			msg("<silver><em>has not created a ticket");
			message(getButtonFunctional(BUTTON_CREATE, Perm.CREATE, null, mplayer, MassiveTickets.get().getOuterCmdTickets().cmdTicketsCreate, false, true));
	
			return;
		}
		msg("<k>Message: <v>%s", mplayer.getMessage());
		
		LinkedHashMap<TimeUnit, Long> unitcounts = TimeDiffUtil.unitcounts(System.currentTimeMillis() - mplayer.getMillis(), TimeUnit.getAllButMillis());
		String formated = TimeDiffUtil.formatedVerboose(unitcounts, "<v>");
		msg("<k>Created: <v>%s <v>ago", formated);
		
		// Create buttons
		Mson buttonUpdate = mson();
		Mson buttonPick = BUTTON_PICK;
		Mson buttonDone = getButtonFunctional(BUTTON_DONE, Perm.DONE, Perm.DONE_OTHER, mplayer, MassiveTickets.get().getOuterCmdTickets().cmdTicketsDone, true, false);
		Mson buttonYield = getButtonFunctional(BUTTON_YIELD, Perm.YIELD, Perm.YIELD_OTHER, mplayer, MassiveTickets.get().getOuterCmdTickets().cmdTicketsYield, true, false);
		Mson buttonTeleport = getButtonFunctional(BUTTON_TELEPORT, Perm.TELEPORT, null, mplayer, MassiveTickets.get().getOuterCmdTickets().cmdTicketsTeleport, true, false);
		
		// Check if moderated and change desc & buttons
		String pickedByDesc = Txt.parse("<silver><em>noone yet");
		if (mplayer.hasModeratorId())
		{
			pickedByDesc = mplayer.getModerator().getDisplayName(sender);
			buttonPick = buttonPick.color(ChatColor.GRAY).tooltipParse("<b>The ticket is already assigned.");
		}
		else
		{
			buttonPick = getButtonFunctional(buttonPick, Perm.PICK, null, mplayer, MassiveTickets.get().getOuterCmdTickets().cmdTicketsPick, true, false);
		}
		
		if (msender == mplayer)
		{
			MassiveCommand command = MassiveTickets.get().getOuterCmdTickets().cmdTicketsCreate;
			buttonUpdate = getButtonFunctional(BUTTON_UPDATE, Perm.CREATE, null, mplayer, command, false, true);
			buttonUpdate = mson(buttonUpdate.suggest(command, msender.getMessage()), Mson.SPACE);
			
			buttonTeleport = buttonTeleport.color(ChatColor.GRAY).tooltipParse("<b>You cannot teleport to yourself.").event(false, MsonEvent.suggest(""));
		}
		
		// Send moderator info and buttons
		msg("<k>Picked By: <v>%s", pickedByDesc);
		message(mson(buttonUpdate, buttonPick, Mson.SPACE, buttonYield, Mson.SPACE, buttonDone, Mson.SPACE, buttonTeleport));
		
		// React
		MConf.get().getShowReaction().run(msender.getId(), mplayer.getId());
	}
	
	private Mson getButtonFunctional(Mson button, Perm perm, Perm permOther, MPlayer other, MassiveCommand command, boolean addName, boolean suggest)
	{
		// Handle Permissions
		if (! perm.has(sender)) return getDenied(button, perm);
		
		// Handle Permissions other
		if (permOther != null && sender == other && permOther.has(sender))  return getDenied(button, permOther);
		
		String commandLine = addName ? command.getCommandLine(other.getName()) : command.getCommandLine();
		return suggest ? button.suggest(commandLine) : button.command(commandLine);
	}
	
	private Mson getDenied(Mson mson, Perm perm)
	{
		return mson.color(ChatColor.GRAY).tooltip(PermUtil.getDeniedMessage(perm.node));
	}
	
}
