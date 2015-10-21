package com.massivecraft.massivetickets;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

import com.massivecraft.massivecore.EngineAbstract;
import com.massivecraft.massivecore.event.EventMassiveCorePlayerLeave;
import com.massivecraft.massivecore.mixin.Mixin;
import com.massivecraft.massivecore.util.MUtil;
import com.massivecraft.massivetickets.entity.MConf;
import com.massivecraft.massivetickets.entity.MPlayer;
import com.massivecraft.massivetickets.predicate.IsModeratorPredicate;


public class EngineMain extends EngineAbstract
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static EngineMain i = new EngineMain();
	public static EngineMain get() { return i; }
	public EngineMain() {}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public Plugin getPlugin()
	{
		return MassiveTickets.get();
	}
	
	// -------------------------------------------- //
	// VERIFY WORKING ON JOIN
	// -------------------------------------------- //
	// Why do we do this? In order to keep the database clean!
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void verifyWorkingOnJoin(PlayerJoinEvent event)
	{
		// If a player is joining this server ...
		final Player player = event.getPlayer();
		if (MUtil.isntPlayer(player)) return;
		MPlayer mplayer = MPlayer.get(player);
		
		// ... and that player has "working" toggled on ...
		if (!mplayer.isWorking()) return;
		
		// ... and the player lacks the permission to work ...
		if (Perm.WORKING.has(player)) return;
		
		// ... then toggle working off.
		mplayer.setWorking(false);
	}
	
	// -------------------------------------------- //
	// BUMP ON JOIN
	// -------------------------------------------- //
	
	public static void bumpOnJoin(PlayerJoinEvent event, EventPriority priority)
	{
		// If a player is joining the server ...
		final Player player = event.getPlayer();
		
		// ... and this player is a moderator ...
		if (!IsModeratorPredicate.get().apply(player)) return;
		
		// If the bump on join is activated ...
		if (!MConf.get().isBumpOnJoinActive()) return;
		
		// ... and this is the right priority ...
		if (MConf.get().getBumpOnJoinPriority() != priority) return;
		
		// ... and this is an actual join ...
		if (!Mixin.isActualJoin(event)) return;
		
		// ... then bump the player.
		MassiveTickets.alertOneMessage(player, MassiveTickets.createBumpMessage());
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void bumpOnJoinLowest(PlayerJoinEvent event)
	{
		bumpOnJoin(event, EventPriority.LOWEST);
	}
	
	@EventHandler(priority = EventPriority.LOW)
	public void bumpOnJoinLow(PlayerJoinEvent event)
	{
		bumpOnJoin(event, EventPriority.LOW);
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void bumpOnJoinNormal(PlayerJoinEvent event)
	{
		bumpOnJoin(event, EventPriority.NORMAL);
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void bumpOnJoinHigh(PlayerJoinEvent event)
	{
		bumpOnJoin(event, EventPriority.HIGH);
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void bumpOnJoinHighest(PlayerJoinEvent event)
	{
		bumpOnJoin(event, EventPriority.HIGHEST);
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void bumpOnJoinMonitor(PlayerJoinEvent event)
	{
		bumpOnJoin(event, EventPriority.MONITOR);
	}
	
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
		if (!Mixin.isActualLeave(event)) return;
		
		// Force Sync
		mplayer.sync();
		
		// ... and this player has made a ticket ... 
		if (!mplayer.hasMessage()) return;
		
		// ... then mark it as done.
		mplayer.markAsDone(null);
	}
	
}
