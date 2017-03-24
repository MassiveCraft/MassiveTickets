package com.massivecraft.massivetickets.cmd;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.Parameter;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.mson.Mson;
import com.massivecraft.massivecore.pager.Msonifier;
import com.massivecraft.massivecore.pager.Pager;
import com.massivecraft.massivetickets.Perm;
import com.massivecraft.massivetickets.entity.MConf;
import com.massivecraft.massivetickets.entity.MPlayer;
import com.massivecraft.massivetickets.entity.MPlayerColl;
import org.bukkit.command.CommandSender;

import java.util.Collection;
import java.util.List;

public class CmdTicketsList extends MassiveTicketsCommand
{
	// -------------------------------------------- //
	// INSTANCE
	// -------------------------------------------- //
	
	private static CmdTicketsList i = new CmdTicketsList() { @Override public List<String> getAliases() { return MConf.get().aliasesOuterTicketsList; } };
	public static CmdTicketsList get() { return i; }
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdTicketsList()
	{
		// Parameters
		this.addParameter(Parameter.getPage());
		
		// Requirements
		this.addRequirements(RequirementHasPerm.get(Perm.LIST));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public List<String> getAliases()
	{
		return MConf.get().aliasesInnerTicketsList;
	}
	
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
