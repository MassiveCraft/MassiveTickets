package com.massivecraft.massivetickets.entity;

import com.massivecraft.massivecore.cmd.arg.AR;

public class ARMPlayer
{
	// -------------------------------------------- //
	// INSTANCE
	// -------------------------------------------- //
	
	private static AR<MPlayer> any = MPlayerColl.get().getAREntity();
	public static AR<MPlayer> getAny() { return any; }
	
	private static AR<MPlayer> online = MPlayerColl.get().getAREntity(true);
	public static AR<MPlayer> getOnline() { return online; }
	
}
