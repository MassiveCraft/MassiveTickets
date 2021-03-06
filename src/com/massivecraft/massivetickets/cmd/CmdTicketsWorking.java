package com.massivecraft.massivetickets.cmd;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.command.type.primitive.TypeBooleanYes;
import com.massivecraft.massivecore.mson.Mson;
import com.massivecraft.massivetickets.MassiveTickets;
import com.massivecraft.massivetickets.Perm;
import com.massivecraft.massivetickets.entity.MConf;
import org.bukkit.ChatColor;

import java.util.List;

public class CmdTicketsWorking extends MassiveTicketsCommand
{
	// -------------------------------------------- //
	// INSTANCE
	// -------------------------------------------- //
	
	private static CmdTicketsWorking i = new CmdTicketsWorking() { @Override public List<String> getAliases() { return MConf.get().aliasesOuterTicketsWorking; } };
	public static CmdTicketsWorking get() { return i; }
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdTicketsWorking()
	{
		// Parameters
		this.addParameter(TypeBooleanYes.get(), "yes/no", "*toggle*");
		
		// Requirements
		this.addRequirements(RequirementHasPerm.get(Perm.WORKING));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public List<String> getAliases()
	{
		return MConf.get().aliasesInnerTicketsWorking;
	}
	
	@Override
	public void perform() throws MassiveException
	{
		// Args
		boolean before = msender.isWorking();
		boolean after = this.readArg(!before);
		
		// Detect Nochange
		if (before == after)
		{
			String message;
			Mson button;
			String commandLine;
			
			if (after)
			{
				commandLine = this.getCommandLine("no");
				message = "You are already working. ";
				button = BUTTON_STOP;
			}
			else
			{
				commandLine = this.getCommandLine("yes");
				message = "You are already not working. ";
				button = BUTTON_START;
			}
			
			Mson mson = mson(
				message,
				button.command(commandLine)
			).color(ChatColor.YELLOW);
			
			message(mson);
			return;
		}
		
		// Apply
		msender.setWorking(after);
		
		// Inform
		String verb = after ? "started" : "stopped"; 
		MassiveTickets.alertOneMsg(msender.getId(), "You %s working!", verb);
		MassiveTickets.alertOneMessage(msender.getId(), MassiveTickets.createBumpMessage());
		
		// React
		if (after)
		{
			MConf.get().getWorkingOnReaction().run(msender.getId(), null);
		}
		else
		{
			MConf.get().getWorkingOffReaction().run(msender.getId(), null);
		}
		
	}
	
}
