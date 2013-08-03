package com.massivecraft.massivetickets;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import org.bukkit.event.player.PlayerJoinEvent;

import com.massivecraft.massivetickets.entity.MPlayer;


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
	
}
