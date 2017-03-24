package com.massivecraft.massivetickets.predicate;

import com.massivecraft.massivecore.predicate.Predicate;
import com.massivecraft.massivetickets.Perm;
import org.bukkit.command.CommandSender;

import java.io.Serializable;

public class IsModeratorPredicate implements Predicate<CommandSender>, Serializable
{
	private static final long serialVersionUID = 1L;
	
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static IsModeratorPredicate i = new IsModeratorPredicate();
	public static IsModeratorPredicate get() { return i; }
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public boolean apply(CommandSender sender)
	{
		return Perm.PICK.has(sender, false);
	}

}
