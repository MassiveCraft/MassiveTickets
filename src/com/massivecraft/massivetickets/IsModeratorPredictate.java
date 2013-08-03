package com.massivecraft.massivetickets;

import org.bukkit.command.CommandSender;

import com.massivecraft.mcore.Predictate;

public class IsModeratorPredictate implements Predictate<CommandSender>
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static IsModeratorPredictate i = new IsModeratorPredictate();
	public static IsModeratorPredictate get() { return i; }
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public boolean apply(CommandSender sender)
	{
		return Perm.PICK.has(sender, false);
	}

}
