package com.massivecraft.massivetickets.predictate;

import org.bukkit.command.CommandSender;

import com.massivecraft.massivetickets.Perm;
import com.massivecraft.mcore.Predictate;

public class ModeratorPredictate implements Predictate<CommandSender>
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static ModeratorPredictate i = new ModeratorPredictate();
	public static ModeratorPredictate get() { return i; }
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public boolean apply(CommandSender sender)
	{
		return Perm.PICK.has(sender, false);
	}

}
