package com.massivecraft.massivetickets.predictate;

import java.io.Serializable;

import org.bukkit.command.CommandSender;

import com.massivecraft.massivetickets.Perm;
import com.massivecraft.mcore.Predictate;

public class IsModeratorPredictate implements Predictate<CommandSender>, Serializable
{
	private static final long serialVersionUID = 1L;
	
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
