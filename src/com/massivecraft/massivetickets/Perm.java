package com.massivecraft.massivetickets;

import org.bukkit.permissions.Permissible;

import com.massivecraft.massivecore.Identified;
import com.massivecraft.massivecore.util.PermissionUtil;

public enum Perm implements Identified
{
	// -------------------------------------------- //
	// ENUM
	// -------------------------------------------- //
	
	BASECOMMAND,
	LIST,
	SHOW,
	SHOW_OTHER,
	CREATE,
	DONE,
	DONE_OTHER,
	PICK,
	YIELD,
	YIELD_OTHER,
	HIGHSCORE,
	MODLIST,
	WORKING,
	CHEAT,
	VERSION,
	TELEPORT,
	
	// END OF LIST
	;
	
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	private final String id;
	@Override public String getId() { return this.id; }
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	Perm()
	{
		this.id = PermissionUtil.createPermissionId(MassiveTickets.get(), this);
	}
	
	// -------------------------------------------- //
	// HAS
	// -------------------------------------------- //
	
	public boolean has(Permissible permissible, boolean informSenderIfNot)
	{
		return PermissionUtil.hasPermission(permissible, this.id, informSenderIfNot);
	}
	
	public boolean has(Permissible permissible)
	{
		return has(permissible, false);
	}
	
}
