package com.massivecraft.massivetickets.predictate;

import com.massivecraft.massivetickets.entity.MPlayer;
import com.massivecraft.mcore.Predictate;

public class IsTicketPredictate implements Predictate<MPlayer>
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static IsTicketPredictate i = new IsTicketPredictate();
	public static IsTicketPredictate get() { return i; }
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public boolean apply(MPlayer mplayer)
	{
		return mplayer.hasMessage();		
	}
	
}
