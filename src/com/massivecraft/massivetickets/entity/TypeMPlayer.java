package com.massivecraft.massivetickets.entity;

import com.massivecraft.massivecore.SenderPresence;
import com.massivecraft.massivecore.command.type.Type;

public class TypeMPlayer
{
	// -------------------------------------------- //
	// INSTANCE
	// -------------------------------------------- //
	
	private static Type<MPlayer> any = MPlayerColl.get().getTypeEntity();
	public static Type<MPlayer> getAny() { return any; }
	
	private static Type<MPlayer> online = MPlayerColl.get().getTypeEntity(SenderPresence.ONLINE);
	public static Type<MPlayer> getOnline() { return online; }
	
}
