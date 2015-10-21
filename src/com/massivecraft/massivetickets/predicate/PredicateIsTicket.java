package com.massivecraft.massivetickets.predicate;

import java.io.Serializable;

import com.massivecraft.massivecore.Predicate;
import com.massivecraft.massivetickets.entity.MPlayer;

public class PredicateIsTicket implements Predicate<MPlayer>, Serializable
{
	private static final long serialVersionUID = 1L;
	
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static PredicateIsTicket i = new PredicateIsTicket();
	public static PredicateIsTicket get() { return i; }
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public boolean apply(MPlayer mplayer)
	{
		return mplayer.hasMessage();		
	}
	
}
