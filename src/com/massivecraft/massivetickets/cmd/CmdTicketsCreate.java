package com.massivecraft.massivetickets.cmd;

import java.util.List;

import org.bukkit.ChatColor;

import com.massivecraft.massivetickets.Perm;
import com.massivecraft.mcore.cmd.arg.ARString;
import com.massivecraft.mcore.cmd.req.ReqHasPerm;

public class CmdTicketsCreate extends MassiveTicketsCommand
{
	public CmdTicketsCreate(List<String> aliases)
	{
		super(aliases);
		
		this.addRequiredArg("message");
		this.setErrorOnToManyArgs(false);
		
		this.addRequirements(ReqHasPerm.get(Perm.CREATE.node));
	}
	
	@Override
	public void perform()
	{
		// Args
		String message = this.argConcatFrom(0, ARString.get());
		if (message == null) return;
		message = ChatColor.stripColor(message);
		
		
		
	}
}
