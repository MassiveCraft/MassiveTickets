package com.massivecraft.massivetickets.cmd.type;

import com.massivecraft.massivecore.command.type.TypeReflection;
import com.massivecraft.massivetickets.Reaction;

public class TypeReaction extends TypeReflection<Reaction>
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static TypeReaction i = new TypeReaction();
	public static TypeReaction get() { return i; }
	public TypeReaction()
	{
		super(Reaction.class);
	}

}
