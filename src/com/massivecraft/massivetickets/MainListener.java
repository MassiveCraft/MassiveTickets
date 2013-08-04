package com.massivecraft.massivetickets;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import org.bukkit.event.player.PlayerJoinEvent;

import com.massivecraft.massivetickets.entity.MConf;
import com.massivecraft.massivetickets.entity.MPlayer;
import com.massivecraft.mcore.event.MCorePlayerLeaveEvent;
import com.massivecraft.mcore.mixin.Mixin;


public class MainListener implements Listener
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static MainListener i = new MainListener();
	public static MainListener get() { return i; }
	public MainListener() {}
	
	// -------------------------------------------- //
	// SETUP
	// -------------------------------------------- //
	
	public void setup()
	{
		Bukkit.getPluginManager().registerEvents(this, MassiveTickets.get());
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
		// If the bump on join is activated ...
		if (!MConf.get().isBumpOnJoinActive()) return;
		
		// ... and this is the right priority ...
		if (MConf.get().getBumpOnJoinPriority() != priority) return;
		
		// ... and this is an actuall join ...
		if (!Mixin.isActualJoin(event)) return;
		
		// ... then bump the player.
		MassiveTickets.alertMessage(event.getPlayer(), MassiveTickets.createBumpMessage());
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
	public void doneMarkOnLeave(MCorePlayerLeaveEvent event)
	{
		final Player player = event.getPlayer();
		final MPlayer mplayer = MPlayer.get(player);
		if (!mplayer.hasMessage()) return;
		mplayer.markAsDone(null);
	}
	
}
