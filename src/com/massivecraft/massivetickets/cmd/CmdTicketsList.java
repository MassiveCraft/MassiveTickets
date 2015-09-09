package com.massivecraft.massivetickets.cmd;

import java.util.Collection;

import org.bukkit.command.CommandSender;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.cmd.ArgSetting;
import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivecore.mson.Mson;
import com.massivecraft.massivecore.pager.Msonifier;
import com.massivecraft.massivecore.pager.Pager;
import com.massivecraft.massivetickets.Perm;
import com.massivecraft.massivetickets.entity.MPlayer;
import com.massivecraft.massivetickets.entity.MPlayerColl;

public class CmdTicketsList extends MassiveTicketsCommand
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdTicketsList()
	{
		// Args
		this.addArg(ArgSetting.getPage());
		
		// Requirements
		this.addRequirements(ReqHasPerm.get(Perm.LIST.node));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void perform() throws MassiveException
	{
		// Args
		int page = this.readArg();
		
		// Pager Create
		Collection<MPlayer> tickets = MPlayerColl.get().getAllTickets();
		final CommandSender sender = this.sender;
		final Pager<MPlayer> pager = new Pager<MPlayer>(this, "Tickets", page, tickets, new Msonifier<MPlayer>(){
			@Override
			public Mson toMson(MPlayer ticket, int index)
			{
				return ticket.getListLine(sender);
			}
		});
		
		// Pager Message
		pager.message();
	}
	
}
