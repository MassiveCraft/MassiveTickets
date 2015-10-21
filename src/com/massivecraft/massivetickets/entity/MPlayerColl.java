package com.massivecraft.massivetickets.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.massivecraft.massivecore.mson.Mson;
import com.massivecraft.massivecore.store.MStore;
import com.massivecraft.massivecore.store.SenderColl;
import com.massivecraft.massivetickets.Const;
import com.massivecraft.massivetickets.MassiveTickets;
import com.massivecraft.massivetickets.predicate.PredicateIsCurrentlyWorking;
import com.massivecraft.massivetickets.predicate.PredicateIsTicket;

public class MPlayerColl extends SenderColl<MPlayer>
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static MPlayerColl i = new MPlayerColl();
	public static MPlayerColl get() { return i; }
	private MPlayerColl()
	{
		super(Const.COLLECTION_BASENAME_MPLAYER, MPlayer.class, MStore.getDb(), MassiveTickets.get());
	}

	// -------------------------------------------- //
	// STACK TRACEABILITY
	// -------------------------------------------- //
	
	@Override
	public void onTick()
	{
		super.onTick();
	}
	
	// -------------------------------------------- //
	// EXTRAS
	// -------------------------------------------- //
	
	public Collection<MPlayer> getAllCurrentlyWorking()
	{
		return this.getAll(PredicateIsCurrentlyWorking.get());
	}
	
	public Collection<MPlayer> getAllTickets()
	{
		return this.getAll(PredicateIsTicket.get());
	}
	
	// For the list command
	public List<Mson> getAllTicketListLines(Object watcherObject)
	{
		List<Mson> ret = new ArrayList<Mson>();
		for (MPlayer ticket : this.getAllTickets())
		{
			ret.add(ticket.getListLine(watcherObject));
		}
		return ret;
	}
	
}
