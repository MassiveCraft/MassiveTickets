package com.massivecraft.massivetickets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.bukkit.command.CommandSender;

import com.massivecraft.massivetickets.cmd.CmdTickets;
import com.massivecraft.massivetickets.cmd.CmdTicketsCreate;
import com.massivecraft.massivetickets.cmd.CmdTicketsDone;
import com.massivecraft.massivetickets.cmd.CmdTicketsHighscore;
import com.massivecraft.massivetickets.cmd.CmdTicketsList;
import com.massivecraft.massivetickets.cmd.CmdTicketsModlist;
import com.massivecraft.massivetickets.cmd.CmdTicketsPick;
import com.massivecraft.massivetickets.cmd.CmdTicketsShow;
import com.massivecraft.massivetickets.cmd.CmdTicketsWorking;
import com.massivecraft.massivetickets.cmd.CmdTicketsYield;
import com.massivecraft.massivetickets.entity.MConf;
import com.massivecraft.massivetickets.entity.MConfColl;
import com.massivecraft.massivetickets.entity.MPlayerColl;
import com.massivecraft.massivetickets.predictate.IsModeratorPredictate;
import com.massivecraft.mcore.MPlugin;
import com.massivecraft.mcore.cmd.VersionCommand;
import com.massivecraft.mcore.mixin.Mixin;
import com.massivecraft.mcore.util.MUtil;
import com.massivecraft.mcore.util.Txt;

public class MassiveTickets extends MPlugin
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static MassiveTickets i;
	public static MassiveTickets get() { return i; }
	public MassiveTickets() { MassiveTickets.i = this; }
	
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	// Commands
	private CmdTickets outerCmdTickets;
	public CmdTickets getOuterCmdTickets() { return this.outerCmdTickets; }
	
	private CmdTicketsList outerCmdTicketsList;
	public CmdTicketsList getOuterCmdTicketsList() { return this.outerCmdTicketsList; }
	
	private CmdTicketsShow outerCmdTicketsShow;
	public CmdTicketsShow getOuterCmdTicketsShow() { return this.outerCmdTicketsShow; }
	
	private CmdTicketsCreate outerCmdTicketsCreate;
	public CmdTicketsCreate getOuterCmdTicketsCreate() { return this.outerCmdTicketsCreate; }
	
	private CmdTicketsDone outerCmdTicketsDone;
	public CmdTicketsDone getOuterCmdTicketsDone() { return this.outerCmdTicketsDone; }
	
	private CmdTicketsPick outerCmdTicketsPick;
	public CmdTicketsPick getOuterCmdTicketsPick() { return this.outerCmdTicketsPick; }
	
	private CmdTicketsYield outerCmdTicketsYield;
	public CmdTicketsYield getOuterCmdTicketsYield() { return this.outerCmdTicketsYield; }
	
	private CmdTicketsHighscore outerCmdTicketsHighscore;
	public CmdTicketsHighscore getOuterCmdTicketsHighscore() { return this.outerCmdTicketsHighscore; }
	
	private CmdTicketsModlist outerCmdTicketsModlist;
	public CmdTicketsModlist getOuterCmdTicketsModlist() { return this.outerCmdTicketsModlist; }
	
	private CmdTicketsWorking outerCmdTicketsWorking;
	public CmdTicketsWorking getOuterCmdTicketsWorking() { return this.outerCmdTicketsWorking; }
	
	private VersionCommand outerCmdTicketsVersion;
	public VersionCommand getOuterCmdTicketsVersion() { return this.outerCmdTicketsVersion; }

	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void onEnable()
	{
		if ( ! preEnable()) return;
		
		// Load Server Config
		ConfServer.get().load();
		
		// Initialize Database
		MConfColl.get().init();
		MPlayerColl.get().init();
		
		// Commands
		this.outerCmdTickets = new CmdTickets(ConfServer.aliasesOuterTickets);
		this.outerCmdTickets.register(this);
		
		this.outerCmdTicketsList = new CmdTicketsList(ConfServer.aliasesOuterTicketsList);
		this.outerCmdTicketsList.register(this);
		
		this.outerCmdTicketsShow = new CmdTicketsShow(ConfServer.aliasesOuterTicketsShow);
		this.outerCmdTicketsShow.register(this);
		
		this.outerCmdTicketsCreate = new CmdTicketsCreate(ConfServer.aliasesOuterTicketsCreate);
		this.outerCmdTicketsCreate.register(this);
		
		this.outerCmdTicketsDone = new CmdTicketsDone(ConfServer.aliasesOuterTicketsDone);
		this.outerCmdTicketsDone.register(this);
		
		this.outerCmdTicketsPick = new CmdTicketsPick(ConfServer.aliasesOuterTicketsPick);
		this.outerCmdTicketsPick.register(this);
		
		this.outerCmdTicketsYield = new CmdTicketsYield(ConfServer.aliasesOuterTicketsYield);
		this.outerCmdTicketsYield.register(this);
		
		this.outerCmdTicketsHighscore = new CmdTicketsHighscore(ConfServer.aliasesOuterTicketsHighscore);
		this.outerCmdTicketsHighscore.register(this);
		
		this.outerCmdTicketsModlist = new CmdTicketsModlist(ConfServer.aliasesOuterTicketsModlist);
		this.outerCmdTicketsModlist.register(this);
		
		this.outerCmdTicketsWorking = new CmdTicketsWorking(ConfServer.aliasesOuterTicketsWorking);
		this.outerCmdTicketsWorking.register(this);
		
		this.outerCmdTicketsVersion = new VersionCommand(MassiveTickets.get(), Perm.VERSION.node, ConfServer.aliasesOuterTicketsVersion);
		this.outerCmdTicketsVersion.register(this);

		// Setup Listeners
		MainListener.get().setup();
		
		// Schedule recurring non-tps-dependent tasks
		//TaskPlayerPowerUpdate.get().schedule(this);
		
		postEnable();
	}
	
	// -------------------------------------------- //
	// ALERT MESSAGE HELPERS
	// -------------------------------------------- //
	
	// All Moderators
	public static boolean alertMessage(String message)
	{
		return alertMessage(MUtil.list(message));
	}
	public static boolean alertMessage(String... messages)
	{
		return alertMessage(Arrays.asList(messages));
	}
	public static boolean alertMessage(Collection<String> messages)
	{
		List<String> target = new ArrayList<String>();
		for (String message : messages)
		{
			target.add(Txt.parse(MConf.get().getPrefix()) + message);
		}
		return Mixin.message(IsModeratorPredictate.get(), target);
	}
	
	// One
	public static boolean alertMessage(CommandSender sender, String message)
	{
		return alertMessage(sender, MUtil.list(message));
	}
	public static boolean alertMessage(CommandSender sender, String... messages)
	{
		return alertMessage(sender, Arrays.asList(messages));
	}
	public static boolean alertMessage(CommandSender sender, Collection<String> messages)
	{
		List<String> target = new ArrayList<String>();
		for (String message : messages)
		{
			target.add(Txt.parse(MConf.get().getPrefix()) + message);
		}
		return Mixin.message(sender, target);
	}

	// All Moderators
	public static boolean alertMsg(String msg)
	{
		return Mixin.msg(IsModeratorPredictate.get(), MConf.get().getPrefix() + msg);
	}
	public static boolean alertMsg(String msg, Object... args)
	{
		return Mixin.msg(IsModeratorPredictate.get(), MConf.get().getPrefix() + msg, args);
	}
	public static boolean alertMsg(Collection<String> msgs)
	{
		List<String> target = new ArrayList<String>();
		for (String msg : msgs)
		{
			target.add(MConf.get().getPrefix() + msg);
		}
		return Mixin.msg(IsModeratorPredictate.get(), target);
	}
	
	// One
	public static boolean alertMsg(CommandSender sender, String msg)
	{
		return Mixin.msg(sender, MConf.get().getPrefix() + msg);
	}
	public static boolean alertMsg(CommandSender sender, String msg, Object... args)
	{
		return Mixin.msg(sender, MConf.get().getPrefix() + msg, args);
	}
	public static boolean alertMsg(CommandSender sender, Collection<String> msgs)
	{
		List<String> target = new ArrayList<String>();
		for (String msg : msgs)
		{
			target.add(MConf.get().getPrefix() + msg);
		}
		return Mixin.msg(sender, target);
	}
	
}
