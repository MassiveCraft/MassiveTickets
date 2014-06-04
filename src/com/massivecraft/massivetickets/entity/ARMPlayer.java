package com.massivecraft.massivetickets.entity;

import com.massivecraft.massivecore.cmd.arg.ARSenderEntity;
import com.massivecraft.massivecore.cmd.arg.ArgReader;

public class ARMPlayer
{
	// -------------------------------------------- //
	// INSTANCE
	// -------------------------------------------- //
	
	private static ArgReader<MPlayer> fullAny = ARSenderEntity.getFullAny(MPlayerColl.get());
	public static ArgReader<MPlayer> getFullAny() { return fullAny; }
	
	private static ArgReader<MPlayer> startAny = ARSenderEntity.getStartAny(MPlayerColl.get());
	public static ArgReader<MPlayer> getStartAny() { return startAny; }
	
	private static ArgReader<MPlayer> fullOnline = ARSenderEntity.getFullOnline(MPlayerColl.get());
	public static ArgReader<MPlayer> getFullOnline() { return fullOnline; }
	
	private static ArgReader<MPlayer> startOnline = ARSenderEntity.getStartOnline(MPlayerColl.get());
	public static ArgReader<MPlayer> getStartOnline() { return startOnline; }
	
}
