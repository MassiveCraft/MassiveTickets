package com.massivecraft.massivetickets.predictate;

import java.io.Serializable;

import com.massivecraft.massivetickets.entity.MPlayer;
import com.massivecraft.mcore.Predictate;

public class IsTicketPredictate implements Predictate<MPlayer>, Serializable
{
	private static final long serialVersionUID = 1L;
	
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
