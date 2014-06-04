package com.massivecraft.massivetickets.predictate;

import java.io.Serializable;

import com.massivecraft.massivecore.Predictate;
import com.massivecraft.massivetickets.entity.MPlayer;

public class IsCurrentlyWorkingPredictate implements Predictate<MPlayer>, Serializable
{
	private static final long serialVersionUID = 1L;
	
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
		
	private static IsCurrentlyWorkingPredictate i = new IsCurrentlyWorkingPredictate();
	public static IsCurrentlyWorkingPredictate get() { return i; }
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public boolean apply(MPlayer mplayer)
	{
		return mplayer.isOnline() && mplayer.isWorking();		
	}
	
}
