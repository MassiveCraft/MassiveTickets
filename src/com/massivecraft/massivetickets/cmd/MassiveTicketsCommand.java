package com.massivecraft.massivetickets.cmd;

import org.bukkit.ChatColor;

import com.massivecraft.massivecore.cmd.MassiveCommand;
import com.massivecraft.massivecore.cmd.VisibilityMode;
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
	public static final Mson BUTTON_STOP = getButton("Stop");
	
	public static Mson getButton(String name) { return Mson.mson("[" + name + "]").color(ChatColor.AQUA); }
	
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	public MPlayer msender;
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void fixSenderVars()
	{
		this.msender = MPlayer.get(sender);
	}
	
	@Override
	public void unsetSenderVars()
	{
		this.msender = null;
	}
	
	public MassiveTicketsCommand()
	{
		this.setVisibilityMode(VisibilityMode.SECRET);
	}
	
}
