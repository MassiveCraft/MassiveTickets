package com.massivecraft.massivetickets.cmd;

import java.util.LinkedHashMap;
import java.util.List;

import org.bukkit.ChatColor;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.MassiveCommand;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.mson.Mson;
import com.massivecraft.massivecore.mson.MsonEvent;
import com.massivecraft.massivecore.util.PermissionUtil;
import com.massivecraft.massivecore.util.TimeDiffUtil;
import com.massivecraft.massivecore.util.TimeUnit;
import com.massivecraft.massivecore.util.Txt;
import com.massivecraft.massivetickets.Perm;
import com.massivecraft.massivetickets.entity.TypeMPlayer;
import com.massivecraft.massivetickets.entity.MConf;
import com.massivecraft.massivetickets.entity.MPlayer;

public class CmdTicketsShow extends MassiveTicketsCommand
{
	// -------------------------------------------- //
	// INSTANCE
	// -------------------------------------------- //
	
	private static CmdTicketsShow i = new CmdTicketsShow() { @Override public List<String> getAliases() { return MConf.get().aliasesOuterTicketsShow; } };
	public static CmdTicketsShow get() { return i; }
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdTicketsShow()
	{
		// Parameters
		this.addParameter(TypeMPlayer.getOnline(), "player", "you");
		
		// Requirements
		this.addRequirements(RequirementHasPerm.get(Perm.SHOW));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public List<String> getAliases()
	{
		return MConf.get().aliasesInnerTicketsShow;
	}
	
	@Override
	public void perform() throws MassiveException
	{
		// Args
		MPlayer mplayer = this.readArg(msender);
		
		// Other Perm?
		if (mplayer != msender && !Perm.SHOW_OTHER.has(sender, true)) return;
		
		// Send them messages!
		message(Txt.titleize(mplayer.getDisplayName(sender)+"<l>'s ticket"));
		if (!mplayer.hasMessage())
		{
			msg("<silver><em>has not created a ticket");
			message(getButtonFunctional(BUTTON_CREATE, Perm.CREATE, null, mplayer, CmdTickets.get().cmdTicketsCreate, false, true));
	
			return;
		}
		msg("<k>Message: <v>%s", mplayer.getMessage());
		
		LinkedHashMap<TimeUnit, Long> unitcounts = TimeDiffUtil.unitcounts(System.currentTimeMillis() - mplayer.getMillis(), TimeUnit.getAllButMillis());
		String formated = TimeDiffUtil.formatedVerboose(unitcounts, "<v>");
		msg("<k>Created: <v>%s <v>ago", formated);
		
		// Create buttons
		Mson buttonUpdate = mson();
		Mson buttonPick = BUTTON_PICK;
		Mson buttonDone = getButtonFunctional(BUTTON_DONE, Perm.DONE, Perm.DONE_OTHER, mplayer, CmdTickets.get().cmdTicketsDone, true, false);
		Mson buttonYield = getButtonFunctional(BUTTON_YIELD, Perm.YIELD, Perm.YIELD_OTHER, mplayer, CmdTickets.get().cmdTicketsYield, true, false);
		Mson buttonTeleport = getButtonFunctional(BUTTON_TELEPORT, Perm.TELEPORT, null, mplayer, CmdTickets.get().cmdTicketsTeleport, true, false);
		
		// Check if moderated and change desc & buttons
		String pickedByDesc = Txt.parse("<silver><em>noone yet");
		if (mplayer.hasModeratorId())
		{
			pickedByDesc = mplayer.getModerator().getDisplayName(sender);
			buttonPick = buttonPick.color(ChatColor.GRAY).tooltipParse("<b>The ticket is already assigned.");
		}
		else
		{
			buttonPick = getButtonFunctional(buttonPick, Perm.PICK, null, mplayer, CmdTickets.get().cmdTicketsPick, true, false);
		}
		
		if (msender == mplayer)
		{
			MassiveCommand command = CmdTickets.get().cmdTicketsCreate;
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
		return mson.color(ChatColor.GRAY).tooltip(PermissionUtil.getPermissionDeniedMessage(perm));
	}
	
}
