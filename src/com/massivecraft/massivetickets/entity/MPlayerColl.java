package com.massivecraft.massivetickets.entity;

import com.massivecraft.massivetickets.ConfServer;
import com.massivecraft.massivetickets.Const;
import com.massivecraft.massivetickets.MassiveTickets;
import com.massivecraft.mcore.store.MStore;
import com.massivecraft.mcore.store.SenderColl;

public class MPlayerColl extends SenderColl<MPlayer>
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static MPlayerColl i = new MPlayerColl();
	public static MPlayerColl get() { return i; }
	private MPlayerColl()
	{
		super(Const.COLLECTION_BASENAME_MPLAYER, MPlayer.class, MStore.getDb(ConfServer.dburi), MassiveTickets.get());
	}
	
}
