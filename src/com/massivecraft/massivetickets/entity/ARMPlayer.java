package com.massivecraft.massivetickets.entity;

import com.massivecraft.massivecore.cmd.arg.ArgReader;

public class ARMPlayer
{
	// -------------------------------------------- //
	// INSTANCE
	// -------------------------------------------- //
	
	private static ArgReader<MPlayer> any = MPlayerColl.get().getAREntity();
	public static ArgReader<MPlayer> getAny() { return any; }
	
	private static ArgReader<MPlayer> online = MPlayerColl.get().getAREntity(true);
	public static ArgReader<MPlayer> getOnline() { return online; }
	
}
