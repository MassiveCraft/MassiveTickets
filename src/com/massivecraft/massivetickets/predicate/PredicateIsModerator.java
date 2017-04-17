package com.massivecraft.massivetickets.predicate;

import com.massivecraft.massivecore.predicate.Predicate;
import com.massivecraft.massivetickets.Perm;
import org.bukkit.command.CommandSender;

import java.io.Serializable;

public class PredicateIsModerator implements Predicate<CommandSender>, Serializable
{
	// -------------------------------------------- //
	// SERIALIZABLE
	// -------------------------------------------- //
	
	private static final long serialVersionUID = 1L;
	
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static PredicateIsModerator i = new PredicateIsModerator();
	public static PredicateIsModerator get() { return i; }
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public boolean apply(CommandSender sender)
	{
		return Perm.PICK.has(sender, false);
	}

}
