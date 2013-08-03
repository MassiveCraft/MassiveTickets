package com.massivecraft.massivetickets.cmd;

import java.util.List;

import org.bukkit.ChatColor;

import com.massivecraft.massivetickets.MassiveTickets;
import com.massivecraft.massivetickets.Perm;
import com.massivecraft.massivetickets.entity.MPlayerColl;
import com.massivecraft.mcore.cmd.arg.ARString;
import com.massivecraft.mcore.cmd.req.ReqHasPerm;

public class CmdTicketsCreate extends MassiveTicketsCommand
{
	public CmdTicketsCreate(List<String> aliases)
	{
		super(aliases);
		
		this.addRequiredArg("message");
		this.setErrorOnToManyArgs(false);
		
		this.addRequirements(ReqHasPerm.get(Perm.CREATE.node));
	}
	
	@Override
	public void perform()
	{
		// Args
		String message = this.argConcatFrom(0, ARString.get());
		if (message == null) return;
		message = ChatColor.stripColor(message);
		
		// Apply
		boolean update = mme.hasMessage();
		String verb = update ? "updated" : "created";
		
		mme.setMessage(message);
		if (!update) mme.setMillis(System.currentTimeMillis());
		
		// Inform
		MassiveTickets.alertMsg("<white>%s <pink>%s ticket: %s", mme.getDisplayName(), verb, message);
		MassiveTickets.alertMsg(me, "Your ticket was %. We will help you asap.", verb);
		MassiveTickets.alertMsg(me, "There is currently <aqua>%d <pink>working moderators.", MPlayerColl.get().getAllCurrentlyWorking().size());
		
		// TODO Add these messages as well.
		// Use /t s to show your ticket
		// Use /t d to mark it as done
		// Use /t m to list the moderators
		// Use /t c <message> to update your current ticket message.
		
	}
}
