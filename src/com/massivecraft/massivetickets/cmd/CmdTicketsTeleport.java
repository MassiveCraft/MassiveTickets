package com.massivecraft.massivetickets.cmd;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivecore.cmd.req.ReqIsPlayer;
import com.massivecraft.massivecore.mixin.Mixin;
import com.massivecraft.massivecore.mixin.TeleporterException;
import com.massivecraft.massivecore.teleport.DestinationPlayer;
import com.massivecraft.massivetickets.Perm;
import com.massivecraft.massivetickets.entity.TypeMPlayer;
import com.massivecraft.massivetickets.entity.MPlayer;

public class CmdTicketsTeleport extends MassiveTicketsCommand
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdTicketsTeleport()
	{
		// Parameters
		this.addParameter(TypeMPlayer.getAny(), "player");
		
		// Requirements
		this.addRequirements(ReqHasPerm.get(Perm.TELEPORT.node));
		this.addRequirements(ReqIsPlayer.get());
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void perform() throws MassiveException
	{
		// Args
		MPlayer mplayer = this.readArg();
		
		// If not a player, return
		if ( ! mplayer.isPlayer()) return;
		
		// Try teleport
		try
		{
			Mixin.teleport(me, new DestinationPlayer(mplayer));
		}
		catch (TeleporterException e)
		{
			me.sendMessage(e.getMessage());
		}
	}
	
}
