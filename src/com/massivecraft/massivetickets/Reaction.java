package com.massivecraft.massivetickets;

import com.massivecraft.massivecore.command.editor.annotation.EditorTypeInner;
import com.massivecraft.massivecore.command.type.TypeStringCommand;
import com.massivecraft.massivecore.mixin.MixinCommand;
import com.massivecraft.massivecore.util.IdUtil;
import com.massivecraft.massivecore.util.MUtil;
import com.massivecraft.massivecore.util.Txt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public final class Reaction
{
	// -------------------------------------------- //
	// INSTANCES
	// -------------------------------------------- //
	
	public final static transient Reaction EMPTY = Reaction.valueOf(null, null, null);
	
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	@EditorTypeInner(TypeStringCommand.class)
	private final List<String> moderatorCommands;
	public List<String> getModeratorCommands() { return new ArrayList<>(this.moderatorCommands); }
	
	@EditorTypeInner(TypeStringCommand.class)
	private final List<String> playerCommands;
	public List<String> getPlayerCommands() { return new ArrayList<>(this.playerCommands); }
	
	@EditorTypeInner(TypeStringCommand.class)
	private final List<String> consoleCommands;
	public List<String> getConsoleCommands() { return new ArrayList<>(this.consoleCommands); }
	
	// -------------------------------------------- //
	// CONSTUCT
	// -------------------------------------------- //
	
	private Reaction(Collection<String> moderatorCommands, Collection<String> playerCommands, Collection<String> consoleCommands)
	{
		this.moderatorCommands = (moderatorCommands == null ? new ArrayList<String>() : new ArrayList<>(moderatorCommands));
		this.playerCommands = (playerCommands == null ? new ArrayList<String>() : new ArrayList<>(playerCommands));
		this.consoleCommands = (consoleCommands == null ? new ArrayList<String>() : new ArrayList<>(consoleCommands));
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
				MixinCommand.get().dispatchCommand(moderatorId, cmd);
			}
		}
		
		if (playerId != null)
		{
			for (String cmd : prepareCmds(this.getPlayerCommands(), moderatorId, playerId))
			{
				MixinCommand.get().dispatchCommand(playerId, cmd);
			}
		}
		
		for (String cmd : prepareCmds(this.getConsoleCommands(), moderatorId, playerId))
		{
			MixinCommand.get().dispatchCommand(moderatorId, IdUtil.CONSOLE_ID, cmd);
		}
	}
	
	public static List<String> prepareCmds(Collection<String> cmds, String moderatorId, String playerId)
	{
		// Create return collection
		List<String> ret = new ArrayList<>();
		
		// Calculate values for variables and save the results for reuse.
		String currentWeek = String.valueOf(MassiveTickets.getCurrentWeek());
		String currentYear = String.valueOf(MassiveTickets.getCurrentYear());
		boolean moderatorIdNull = moderatorId == null;
		boolean playerIdNull = playerId == null;
		String moderatorName = IdUtil.getName(moderatorId);
		String playerName = IdUtil.getName(playerId);
		
		// For each command string
		for (String cmd : cmds)
		{
			// Apply basic command string trimming
			cmd = Txt.removeLeadingCommandDust(cmd);
			
			// Apply the week and year variables
			cmd = cmd.replace("{w}", currentWeek);
			cmd = cmd.replace("{y}", currentYear);
			
			// Apply moderator name variable, if possible
			if (!moderatorIdNull) cmd = cmd.replace("{m}", moderatorName);
			
			// Apply the player name variable, if possible
			if (!playerIdNull) cmd = cmd.replace("{p}", playerName);
			
			// Attach to the output
			ret.add(cmd);
		}
		
		// Return
		return ret;
	}

	// -------------------------------------------- //
	// EQUALS & HASHCODE
	// -------------------------------------------- //
	
	@Override
	public int hashCode()
	{
		return Objects.hash(
			this.getConsoleCommands(),
			this.getModeratorCommands(),
			this.getPlayerCommands()
		);
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj) return true;
		if (obj == null) return false;
		if (!(obj instanceof Reaction)) return false;
		Reaction other = (Reaction) obj;
		
		return MUtil.equals(
			this.getConsoleCommands(), other.getConsoleCommands(),
			this.getModeratorCommands(), other.getModeratorCommands(),
			this.getPlayerCommands(), other.getPlayerCommands()
		);
	}
	
}
