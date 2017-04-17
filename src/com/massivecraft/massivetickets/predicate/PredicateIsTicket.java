package com.massivecraft.massivetickets.predicate;

import com.massivecraft.massivecore.predicate.Predicate;
import com.massivecraft.massivetickets.entity.MPlayer;

import java.io.Serializable;

public class PredicateIsTicket implements Predicate<MPlayer>, Serializable
{
	// -------------------------------------------- //
	// SERIALIZABLE
	// -------------------------------------------- //
	
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
