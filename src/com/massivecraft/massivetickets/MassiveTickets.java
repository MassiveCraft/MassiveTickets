package com.massivecraft.massivetickets;

import static com.massivecraft.massivecore.mson.Mson.mson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.massivecraft.massivecore.MassivePlugin;
import com.massivecraft.massivecore.command.VersionCommand;
import com.massivecraft.massivecore.mixin.Mixin;
import com.massivecraft.massivecore.mson.Mson;
import com.massivecraft.massivecore.util.MUtil;
import com.massivecraft.massivecore.util.Txt;
import com.massivecraft.massivetickets.cmd.CmdTickets;
import com.massivecraft.massivetickets.cmd.CmdTicketsCheat;
import com.massivecraft.massivetickets.cmd.CmdTicketsCreate;
import com.massivecraft.massivetickets.cmd.CmdTicketsDone;
import com.massivecraft.massivetickets.cmd.CmdTicketsHighscore;
import com.massivecraft.massivetickets.cmd.CmdTicketsList;
import com.massivecraft.massivetickets.cmd.CmdTicketsModlist;
import com.massivecraft.massivetickets.cmd.CmdTicketsPick;
import com.massivecraft.massivetickets.cmd.CmdTicketsShow;
import com.massivecraft.massivetickets.cmd.CmdTicketsTeleport;
import com.massivecraft.massivetickets.cmd.CmdTicketsWorking;
import com.massivecraft.massivetickets.cmd.CmdTicketsYield;
import com.massivecraft.massivetickets.cmd.MassiveTicketsCommand;
import com.massivecraft.massivetickets.entity.MConf;
import com.massivecraft.massivetickets.entity.MConfColl;
import com.massivecraft.massivetickets.entity.MPlayerColl;
import com.massivecraft.massivetickets.predicate.IsModeratorPredicate;

public class MassiveTickets extends MassivePlugin
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
	
	private CmdTicketsTeleport outerCmdTicketsTeleport;
	public CmdTicketsTeleport getOuterCmdTicketsTeleport() { return this.outerCmdTicketsTeleport; }
	
	private CmdTicketsCheat outerCmdTicketsCheat;
	public CmdTicketsCheat getOuterCmdTicketsCheat() { return this.outerCmdTicketsCheat; }
	
	private VersionCommand outerCmdTicketsVersion;
	public VersionCommand getOuterCmdTicketsVersion() { return this.outerCmdTicketsVersion; }

	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void onEnable()
	{
		if ( ! preEnable()) return;
		
		// Version Synchronized
		this.setVersionSynchronized(true);
		
		// Initialize Database
		MConfColl.get().init();
		MPlayerColl.get().init();
		
		// Commands
		this.outerCmdTickets = new CmdTickets() { @Override public List<String> getAliases() { return MConf.get().aliasesOuterTickets; } };
		this.outerCmdTickets.register(this);
		
		this.outerCmdTicketsList = new CmdTicketsList() { @Override public List<String> getAliases() { return MConf.get().aliasesOuterTicketsList; } };
		this.outerCmdTicketsList.register(this);
		
		this.outerCmdTicketsShow = new CmdTicketsShow() { @Override public List<String> getAliases() { return MConf.get().aliasesOuterTicketsShow; } };
		this.outerCmdTicketsShow.register(this);
		
		this.outerCmdTicketsCreate = new CmdTicketsCreate() { @Override public List<String> getAliases() { return MConf.get().aliasesOuterTicketsCreate; } };
		this.outerCmdTicketsCreate.register(this);
		
		this.outerCmdTicketsDone = new CmdTicketsDone() { @Override public List<String> getAliases() { return MConf.get().aliasesOuterTicketsDone; } };
		this.outerCmdTicketsDone.register(this);
		
		this.outerCmdTicketsPick = new CmdTicketsPick() { @Override public List<String> getAliases() { return MConf.get().aliasesOuterTicketsPick; } };
		this.outerCmdTicketsPick.register(this);
		
		this.outerCmdTicketsYield = new CmdTicketsYield() { @Override public List<String> getAliases() { return MConf.get().aliasesOuterTicketsYield; } };
		this.outerCmdTicketsYield.register(this);
		
		this.outerCmdTicketsHighscore = new CmdTicketsHighscore() { @Override public List<String> getAliases() { return MConf.get().aliasesOuterTicketsHighscore; } };
		this.outerCmdTicketsHighscore.register(this);
		
		this.outerCmdTicketsModlist = new CmdTicketsModlist() { @Override public List<String> getAliases() { return MConf.get().aliasesOuterTicketsModlist; } };
		this.outerCmdTicketsModlist.register(this);
		
		this.outerCmdTicketsWorking = new CmdTicketsWorking() { @Override public List<String> getAliases() { return MConf.get().aliasesOuterTicketsWorking; } };
		this.outerCmdTicketsWorking.register(this);
		
		this.outerCmdTicketsTeleport = new CmdTicketsTeleport() { @Override public List<String> getAliases() { return MConf.get().aliasesOuterTicketsTeleport; } };
		this.outerCmdTicketsTeleport.register(this);
		
		this.outerCmdTicketsCheat = new CmdTicketsCheat() { @Override public List<String> getAliases() { return MConf.get().aliasesOuterTicketsCheat; } };
		this.outerCmdTicketsCheat.register(this);
		
		this.outerCmdTicketsVersion = new VersionCommand(MassiveTickets.get(), Perm.VERSION.node) { @Override public List<String> getAliases() { return MConf.get().aliasesOuterTicketsVersion; } };
		this.outerCmdTicketsVersion.register(this);

		// Engines
		EngineMain.get().activate();
		
		// Schedule recurring non-tps-dependent tasks
		BumpTask.get().activate();
		
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
	
	private static Mson TICKETS = Mson.SPACE.add(mson("tickets").color(ChatColor.LIGHT_PURPLE)).add(Mson.SPACE);
	private static Mson MODERATORS = Mson.SPACE.add(mson("moderators").color(ChatColor.LIGHT_PURPLE)).add(Mson.SPACE);
	
	public static Mson createBumpMessage()
	{
		int tickets = MPlayerColl.get().getAllTickets().size();
		Mson bumpMson = mson(
			mson(String.valueOf(tickets)).color(ChatColor.AQUA),
			TICKETS,
			mson(String.valueOf(MPlayerColl.get().getAllCurrentlyWorking().size())).color(ChatColor.AQUA),
			MODERATORS,
			MassiveTicketsCommand.BUTTON_LIST.command(MassiveTickets.get().getOuterCmdTickets().cmdTicketsList)
		);

		return bumpMson;
	}
	
	// -------------------------------------------- //
	// ALERT MESSAGE HELPERS
	// -------------------------------------------- //
	
	// All Moderators
	public static boolean alertModeratorsMessage(Object message)
	{
		return alertModeratorsMessage(MUtil.list(message));
	}
	public static boolean alertModeratorsMessage(Object... messages)
	{
		return alertModeratorsMessage(Arrays.asList(messages));
	}
	public static boolean alertModeratorsMessage(Collection<Object> messages)
	{
		List<Mson> target = new ArrayList<Mson>();
		for (Object message : messages)
		{
			target.add(getPrefix().add(message));
		}
		return Mixin.messagePredicate(IsModeratorPredicate.get(), target);
	}
	
	// One
	public static boolean alertOneMessage(CommandSender sender, Object message)
	{
		return alertOneMessage(sender, MUtil.list(message));
	}
	public static boolean alertOneMessage(CommandSender sender, Object... messages)
	{
		return alertOneMessage(sender, Arrays.asList(messages));
	}
	public static boolean alertOneMessage(CommandSender sender, Collection<Object> messages)
	{
		List<Mson> target = new ArrayList<Mson>();
		for (Object message : messages)
		{
			target.add(getPrefix().add(message));
		}
		return Mixin.messageOne(sender, target);
	}
	
	// One by id
	public static boolean alertOneMessage(String senderId, Object message)
	{
		return alertOneMessage(senderId, MUtil.list(message));
	}
	public static boolean alertOneMessage(String senderId, Object... messages)
	{
		return alertOneMessage(senderId, Arrays.asList(messages));
	}
	public static boolean alertOneMessage(String senderId, Collection<Object> messages)
	{
		List<Mson> target = new ArrayList<Mson>();
		for (Object message : messages)
		{
			target.add(getPrefix().add(message));
		}
		return Mixin.messageOne(senderId, target);
	}

	// All Moderators
	public static boolean alertModeratorsMsg(String msg) // WORKS
	{
		return Mixin.msgPredicate(IsModeratorPredicate.get(), MConf.get().getPrefix() + msg);
	}
	public static boolean alertModeratorsMsg(String msg, Object... args)
	{
		return Mixin.msgPredicate(IsModeratorPredicate.get(), MConf.get().getPrefix() + msg, args);
	}
	public static boolean alertModeratorsMsg(Collection<String> msgs)
	{
		List<String> target = new ArrayList<String>();
		for (String msg : msgs)
		{
			target.add(MConf.get().getPrefix() + msg);
		}
		return Mixin.msgPredicate(IsModeratorPredicate.get(), target);
	}
	
	// One
	public static boolean alertOneMsg(CommandSender sender, String msg)
	{
		return Mixin.msgOne(sender, MConf.get().getPrefix() + msg);
	}
	public static boolean alertOneMsg(CommandSender sender, String msg, Object... args)
	{
		return Mixin.msgOne(sender, MConf.get().getPrefix() + msg, args);
	}
	public static boolean alertOneMsg(CommandSender sender, Collection<String> msgs)
	{
		List<String> target = new ArrayList<String>();
		for (String msg : msgs)
		{
			target.add(MConf.get().getPrefix() + msg);
		}
		return Mixin.msgOne(sender, target);
	}
	
	// One by id
	public static boolean alertOneMsg(String senderId, String msg)
	{
		return Mixin.msgOne(senderId, MConf.get().getPrefix() + msg);
	}
	public static boolean alertOneMsg(String senderId, String msg, Object... args)
	{
		return Mixin.msgOne(senderId, MConf.get().getPrefix() + msg, args);
	}
	public static boolean alertOneMsg(String senderId, Collection<String> msgs)
	{
		List<String> target = new ArrayList<String>();
		for (String msg : msgs)
		{
			target.add(MConf.get().getPrefix() + msg);
		}
		return Mixin.msgOne(senderId, target);
	}
	
	private static Mson getPrefix()
	{
		return Mson.fromParsedMessage(Txt.parse(MConf.get().getPrefix()));
	}
	
}
