package com.massivecraft.massivetickets.predicate;

import java.io.Serializable;

import com.massivecraft.massivecore.Predicate;
import com.massivecraft.massivetickets.entity.MPlayer;

public class PredicateIsCurrentlyWorking implements Predicate<MPlayer>, Serializable
{
	private static final long serialVersionUID = 1L;
	
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
		
	private static PredicateIsCurrentlyWorking i = new PredicateIsCurrentlyWorking();
	public static PredicateIsCurrentlyWorking get() { return i; }
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public boolean apply(MPlayer mplayer)
	{
		return mplayer.isOnline() && mplayer.isWorking();		
	}
	
}
