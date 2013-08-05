package com.massivecraft.massivetickets;

public final class Level
{
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	private final String name;
	public String getName() { return this.name; }
	
	private final Reaction reaction;
	public Reaction getReaction() { return this.reaction; }
	
	// -------------------------------------------- //
	// CONSTUCT
	// -------------------------------------------- //
	
	private Level(String name, Reaction reaction)
	{
		this.name = name;
		this.reaction = reaction;
	}
	
	private Level()
	{
		// No Arg Constructor for GSON
		this("UNTITLED", Reaction.EMPTY);
	}
	
	// -------------------------------------------- //
	// VALUE OF
	// -------------------------------------------- //
	
	public static Level valueOf(String name, Reaction reaction)
	{
		return new Level(name, reaction);
	}
	
	// -------------------------------------------- //
	// EQUALS & HASHCODE
	// -------------------------------------------- //

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((reaction == null) ? 0 : reaction.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj) return true;
		if (obj == null) return false;
		if (!(obj instanceof Level)) return false;
		Level other = (Level) obj;
		if (name == null)
		{
			if (other.name != null) return false;
		}
		else if (!name.equals(other.name)) return false;
		if (reaction == null)
		{
			if (other.reaction != null) return false;
		}
		else if (!reaction.equals(other.reaction)) return false;
		return true;
	}
	
}
