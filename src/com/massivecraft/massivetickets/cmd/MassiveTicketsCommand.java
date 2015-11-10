package com.massivecraft.massivetickets.cmd;

import org.bukkit.ChatColor;

import com.massivecraft.massivecore.command.MassiveCommand;
import com.massivecraft.massivecore.command.Visibility;
import com.massivecraft.massivecore.mson.Mson;
import com.massivecraft.massivetickets.entity.MPlayer;

public abstract class MassiveTicketsCommand extends MassiveCommand
{
	// -------------------------------------------- //
	// CONSTANTS: REUSABLE MSONS
	// -------------------------------------------- //
	
	public static final Mson BUTTON_CREATE = getButton("Create");
	public static final Mson BUTTON_UPDATE = getButton("Update");
	public static final Mson BUTTON_PICK = getButton("Pick");
	public static final Mson BUTTON_YIELD = getButton("Yield");
	public static final Mson BUTTON_DONE = getButton("Done");
	public static final Mson BUTTON_START = getButton("Start");
	public static final Mson BUTTON_SHOW = getButton("Show");
	public static final Mson BUTTON_LIST = getButton("List");
	public static final Mson BUTTON_STOP = getButton("Stop");
	public static final Mson BUTTON_TELEPORT = getButton("Teleport");
	
	public static Mson getButton(String name) { return Mson.mson("[" + name + "]").color(ChatColor.AQUA); }
	
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	public MPlayer msender;
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void senderFields(boolean set)
	{
		this.msender = set ? MPlayer.get(sender) : null;
	}
	
	public MassiveTicketsCommand()
	{
		this.setVisibility(Visibility.SECRET);
	}
	
}
