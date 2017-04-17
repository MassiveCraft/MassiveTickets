package com.massivecraft.massivetickets.cmd.type;

import com.massivecraft.massivecore.SenderPresence;
import com.massivecraft.massivecore.command.type.Type;
import com.massivecraft.massivetickets.entity.MPlayer;
import com.massivecraft.massivetickets.entity.MPlayerColl;

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
