package com.massivecraft.massivetickets.predictate;

import com.massivecraft.massivetickets.entity.MPlayer;
import com.massivecraft.mcore.Predictate;

public class CurrentlyWorkingPredictate implements Predictate<MPlayer>
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static CurrentlyWorkingPredictate i = new CurrentlyWorkingPredictate();
	public static CurrentlyWorkingPredictate get() { return i; }
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public boolean apply(MPlayer mplayer)
	{
		return mplayer.isOnline() && mplayer.isWorking();		
	}
	
}
