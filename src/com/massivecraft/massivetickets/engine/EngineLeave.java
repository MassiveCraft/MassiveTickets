package com.massivecraft.massivetickets.engine;

import com.massivecraft.massivecore.Engine;
import com.massivecraft.massivecore.event.EventMassiveCorePlayerLeave;
import com.massivecraft.massivecore.mixin.MixinActual;
import com.massivecraft.massivecore.util.MUtil;
import com.massivecraft.massivetickets.entity.MPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;

public class EngineLeave extends Engine
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static EngineLeave i = new EngineLeave();
	public static EngineLeave get() { return i; }
	
	// -------------------------------------------- //
	// DONE-MARK ON LEAVE
	// -------------------------------------------- //
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void doneMarkOnLeave(EventMassiveCorePlayerLeave event)
	{
		// If a player is leaving the server ...
		final Player player = event.getPlayer();
		if (MUtil.isntPlayer(player)) return;
		final MPlayer mplayer = MPlayer.get(player);
		
		// ... and it's actually a leave ...
		if (!MixinActual.get().isActualLeave(event)) return;
		
		// Force Sync
		mplayer.sync();
		
		// ... and this player has made a ticket ... 
		if (!mplayer.hasMessage()) return;
		
		// ... then mark it as done.
		mplayer.markAsDone(null);
	}
	
}
