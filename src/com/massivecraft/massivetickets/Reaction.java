package com.massivecraft.massivetickets;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.massivecraft.mcore.mixin.Mixin;
import com.massivecraft.mcore.util.SenderUtil;
import com.massivecraft.mcore.util.Txt;

public final class Reaction
{
	// -------------------------------------------- //
	// INSTANCES
	// -------------------------------------------- //
	
	public final static transient Reaction EMPTY = Reaction.valueOf(null, null, null);
	
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	private final List<String> moderatorCommands;
	public List<String> getModeratorCommands() { return new ArrayList<String>(this.moderatorCommands); }
	
	private final List<String> playerCommands;
	public List<String> getPlayerCommands() { return new ArrayList<String>(this.playerCommands); }
	
	private final List<String> consoleCommands;
	public List<String> getConsoleCommands() { return new ArrayList<String>(this.consoleCommands); }
	
	// -------------------------------------------- //
	// CONSTUCT
	// -------------------------------------------- //
	
	private Reaction(Collection<String> moderatorCommands, Collection<String> playerCommands, Collection<String> consoleCommands)
	{
		this.moderatorCommands = (moderatorCommands == null ? new ArrayList<String>() : new ArrayList<String>(moderatorCommands));
		this.playerCommands = (playerCommands == null ? new ArrayList<String>() : new ArrayList<String>(playerCommands));
		this.consoleCommands = (consoleCommands == null ? new ArrayList<String>() : new ArrayList<String>(consoleCommands));
	}
	
	private Reaction()
	{
		// No Arg Constructor for GSON
		this(null, null, null);
	}
	
	// -------------------------------------------- //
	// VALUE OF
	// -------------------------------------------- //
	
	public static Reaction valueOf(Collection<String> moderatorCommands, Collection<String> playerCommands, Collection<String> consoleCommands)
	{
		return new Reaction(moderatorCommands, playerCommands, consoleCommands);
	}
	
	// -------------------------------------------- //
	// RUN
	// -------------------------------------------- //
	
	public void run(String moderatorId, String playerId)
	{		
		if (moderatorId != null)
		{
			for (String cmd : prepareCmds(this.getModeratorCommands(), moderatorId, playerId))
			{
				Mixin.dispatchCommand(moderatorId, cmd);
			}
		}
		
		if (playerId != null)
		{
			for (String cmd : prepareCmds(this.getPlayerCommands(), moderatorId, playerId))
			{
				Mixin.dispatchCommand(playerId, cmd);
			}
		}
		
		for (String cmd : prepareCmds(this.getConsoleCommands(), moderatorId, playerId))
		{
			Mixin.dispatchCommand(moderatorId, SenderUtil.ID_CONSOLE, cmd);
		}
	}
	
	public static String prepareCmd(String cmd, String moderatorId, String playerId)
	{
		cmd = Txt.removeLeadingCommandDust(cmd);
		
		cmd = cmd.replace("{w}", String.valueOf(MassiveTickets.getCurrentWeek()));
		cmd = cmd.replace("{y}", String.valueOf(MassiveTickets.getCurrentYear()));
		
		if (moderatorId != null)
		{
			cmd = cmd.replace("{m}", Mixin.tryFix(moderatorId));
		}
		if (playerId != null)
		{
			cmd = cmd.replace("{p}", Mixin.tryFix(playerId));
		}
		
		return cmd;
	}
	
	public static List<String> prepareCmds(Collection<String> cmds, String moderatorId, String playerId)
	{
		List<String> ret = new ArrayList<String>();
		for (String cmd : cmds)
		{
			ret.add(prepareCmd(cmd, moderatorId, playerId));
		}
		return ret;
	}

	// -------------------------------------------- //
	// EQUALS & HASHCODE
	// -------------------------------------------- //
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((consoleCommands == null) ? 0 : consoleCommands.hashCode());
		result = prime
				* result
				+ ((moderatorCommands == null) ? 0 : moderatorCommands
						.hashCode());
		result = prime * result
				+ ((playerCommands == null) ? 0 : playerCommands.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj) return true;
		if (obj == null) return false;
		if (!(obj instanceof Reaction)) return false;
		Reaction other = (Reaction) obj;
		if (consoleCommands == null)
		{
			if (other.consoleCommands != null) return false;
		}
		else if (!consoleCommands.equals(other.consoleCommands)) return false;
		if (moderatorCommands == null)
		{
			if (other.moderatorCommands != null) return false;
		}
		else if (!moderatorCommands.equals(other.moderatorCommands)) return false;
		if (playerCommands == null)
		{
			if (other.playerCommands != null) return false;
		}
		else if (!playerCommands.equals(other.playerCommands)) return false;
		return true;
	}
	
}
