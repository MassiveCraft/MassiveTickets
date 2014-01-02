package com.massivecraft.massivetickets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.List;

import org.bukkit.command.CommandSender;

import com.massivecraft.massivetickets.cmd.CmdTickets;
import com.massivecraft.massivetickets.cmd.CmdTicketsCheat;
import com.massivecraft.massivetickets.cmd.CmdTicketsCreate;
import com.massivecraft.massivetickets.cmd.CmdTicketsDone;
import com.massivecraft.massivetickets.cmd.CmdTicketsHearsound;
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
	
	private CmdTicketsCheat outerCmdTicketsCheat;
	public CmdTicketsCheat getOuterCmdTicketsCheat() { return this.outerCmdTicketsCheat; }
	
	private CmdTicketsHearsound outerCmdTicketsHearsound;
	public CmdTicketsHearsound getOuterCmdTicketsHearsound() { return this.outerCmdTicketsHearsound; }
	
	private VersionCommand outerCmdTicketsVersion;
	public VersionCommand getOuterCmdTicketsVersion() { return this.outerCmdTicketsVersion; }

	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void onEnable()
	{
		if ( ! preEnable()) return;
		
		// Initialize Database
		MConfColl.get().init();
		MPlayerColl.get().init();
		
		// Commands
		this.outerCmdTickets = new CmdTickets() { @Override public List<String> getAliases() { return MConf.get().aliasesOuterTickets; } };
		this.outerCmdTickets.register();
		
		this.outerCmdTicketsList = new CmdTicketsList() { @Override public List<String> getAliases() { return MConf.get().aliasesOuterTicketsList; } };
		this.outerCmdTicketsList.register();
		
		this.outerCmdTicketsShow = new CmdTicketsShow() { @Override public List<String> getAliases() { return MConf.get().aliasesOuterTicketsShow; } };
		this.outerCmdTicketsShow.register();
		
		this.outerCmdTicketsCreate = new CmdTicketsCreate() { @Override public List<String> getAliases() { return MConf.get().aliasesOuterTicketsCreate; } };
		this.outerCmdTicketsCreate.register();
		
		this.outerCmdTicketsDone = new CmdTicketsDone() { @Override public List<String> getAliases() { return MConf.get().aliasesOuterTicketsDone; } };
		this.outerCmdTicketsDone.register();
		
		this.outerCmdTicketsPick = new CmdTicketsPick() { @Override public List<String> getAliases() { return MConf.get().aliasesOuterTicketsPick; } };
		this.outerCmdTicketsPick.register();
		
		this.outerCmdTicketsYield = new CmdTicketsYield() { @Override public List<String> getAliases() { return MConf.get().aliasesOuterTicketsYield; } };
		this.outerCmdTicketsYield.register();
		
		this.outerCmdTicketsHighscore = new CmdTicketsHighscore() { @Override public List<String> getAliases() { return MConf.get().aliasesOuterTicketsHighscore; } };
		this.outerCmdTicketsHighscore.register();
		
		this.outerCmdTicketsModlist = new CmdTicketsModlist() { @Override public List<String> getAliases() { return MConf.get().aliasesOuterTicketsModlist; } };
		this.outerCmdTicketsModlist.register();
		
		this.outerCmdTicketsWorking = new CmdTicketsWorking() { @Override public List<String> getAliases() { return MConf.get().aliasesOuterTicketsWorking; } };
		this.outerCmdTicketsWorking.register();
		
		this.outerCmdTicketsCheat = new CmdTicketsCheat() { @Override public List<String> getAliases() { return MConf.get().aliasesOuterTicketsCheat; } };
		this.outerCmdTicketsCheat.register();
		
		this.outerCmdTicketsHearsound = new CmdTicketsHearsound() { @Override public List<String> getAliases() { return MConf.get().aliasesOuterTicketsHearsound; } };
		this.outerCmdTicketsHearsound.register();
		
		this.outerCmdTicketsVersion = new VersionCommand(MassiveTickets.get(), Perm.VERSION.node) { @Override public List<String> getAliases() { return MConf.get().aliasesOuterTicketsVersion; } };
		this.outerCmdTicketsVersion.register();

		// Engines
		EngineMain.get().activate();
		
		// Schedule recurring non-tps-dependent tasks
		BumpTask.get().activate(this);
		
		postEnable();
	}
	
	// -------------------------------------------- //
	// CURRENT TIME
	// -------------------------------------------- //
	
	public static int getCurrentYear()
	{
		return GregorianCalendar.getInstance().getWeekYear();
	}
	
	public static int getCurrentWeek()
	{
		return GregorianCalendar.getInstance().get(GregorianCalendar.WEEK_OF_YEAR);
	}
	
	// -------------------------------------------- //
	// BUMP
	// -------------------------------------------- //
	
	public static String createBumpMessage()
	{
		return Txt.parse("<aqua>%d <pink>tickets <aqua>%d <pink>moderators", MPlayerColl.get().getAllTickets().size(), MPlayerColl.get().getAllCurrentlyWorking().size());
	}
	
	// -------------------------------------------- //
	// ALERT MESSAGE HELPERS
	// -------------------------------------------- //
	
	// All Moderators
	public static boolean alertModeratorsMessage(String message)
	{
		return alertModeratorsMessage(MUtil.list(message));
	}
	public static boolean alertModeratorsMessage(String... messages)
	{
		return alertModeratorsMessage(Arrays.asList(messages));
	}
	public static boolean alertModeratorsMessage(Collection<String> messages)
	{
		List<String> target = new ArrayList<String>();
		for (String message : messages)
		{
			target.add(Txt.parse(MConf.get().getPrefix()) + message);
		}
		return Mixin.message(IsModeratorPredictate.get(), target);
	}
	
	// One
	public static boolean alertOneMessage(CommandSender sender, String message)
	{
		return alertOneMessage(sender, MUtil.list(message));
	}
	public static boolean alertOneMessage(CommandSender sender, String... messages)
	{
		return alertOneMessage(sender, Arrays.asList(messages));
	}
	public static boolean alertOneMessage(CommandSender sender, Collection<String> messages)
	{
		List<String> target = new ArrayList<String>();
		for (String message : messages)
		{
			target.add(Txt.parse(MConf.get().getPrefix()) + message);
		}
		return Mixin.message(sender, target);
	}
	
	// One by id
	public static boolean alertOneMessage(String senderId, String message)
	{
		return alertOneMessage(senderId, MUtil.list(message));
	}
	public static boolean alertOneMessage(String senderId, String... messages)
	{
		return alertOneMessage(senderId, Arrays.asList(messages));
	}
	public static boolean alertOneMessage(String senderId, Collection<String> messages)
	{
		List<String> target = new ArrayList<String>();
		for (String message : messages)
		{
			target.add(Txt.parse(MConf.get().getPrefix()) + message);
		}
		return Mixin.message(senderId, target);
	}

	// All Moderators
	public static boolean alertModeratorsMsg(String msg) // WORKS
	{
		return Mixin.msg(IsModeratorPredictate.get(), MConf.get().getPrefix() + msg);
	}
	public static boolean alertModeratorsMsg(String msg, Object... args)
	{
		return Mixin.msg(IsModeratorPredictate.get(), MConf.get().getPrefix() + msg, args);
	}
	public static boolean alertModeratorsMsg(Collection<String> msgs)
	{
		List<String> target = new ArrayList<String>();
		for (String msg : msgs)
		{
			target.add(MConf.get().getPrefix() + msg);
		}
		return Mixin.msg(IsModeratorPredictate.get(), target);
	}
	
	// One
	public static boolean alertOneMsg(CommandSender sender, String msg)
	{
		return Mixin.msg(sender, MConf.get().getPrefix() + msg);
	}
	public static boolean alertOneMsg(CommandSender sender, String msg, Object... args)
	{
		return Mixin.msg(sender, MConf.get().getPrefix() + msg, args);
	}
	public static boolean alertOneMsg(CommandSender sender, Collection<String> msgs)
	{
		List<String> target = new ArrayList<String>();
		for (String msg : msgs)
		{
			target.add(MConf.get().getPrefix() + msg);
		}
		return Mixin.msg(sender, target);
	}
	
	// One by id
	public static boolean alertOneMsg(String senderId, String msg)
	{
		return Mixin.msg(senderId, MConf.get().getPrefix() + msg);
	}
	public static boolean alertOneMsg(String senderId, String msg, Object... args)
	{
		return Mixin.msg(senderId, MConf.get().getPrefix() + msg, args);
	}
	public static boolean alertOneMsg(String senderId, Collection<String> msgs)
	{
		List<String> target = new ArrayList<String>();
		for (String msg : msgs)
		{
			target.add(MConf.get().getPrefix() + msg);
		}
		return Mixin.msg(senderId, target);
	}
	
}
