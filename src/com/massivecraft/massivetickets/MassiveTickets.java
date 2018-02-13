package com.massivecraft.massivetickets;

import com.massivecraft.massivecore.MassivePlugin;
import com.massivecraft.massivecore.command.type.RegistryType;
import com.massivecraft.massivecore.mixin.MixinMessage;
import com.massivecraft.massivecore.mson.Mson;
import com.massivecraft.massivecore.util.MUtil;
import com.massivecraft.massivecore.util.Txt;
import com.massivecraft.massivetickets.cmd.CmdTickets;
import com.massivecraft.massivetickets.cmd.CmdTicketsCheat;
import com.massivecraft.massivetickets.cmd.CmdTicketsClose;
import com.massivecraft.massivetickets.cmd.CmdTicketsCreate;
import com.massivecraft.massivetickets.cmd.CmdTicketsDone;
import com.massivecraft.massivetickets.cmd.CmdTicketsHighscore;
import com.massivecraft.massivetickets.cmd.CmdTicketsList;
import com.massivecraft.massivetickets.cmd.CmdTicketsModlist;
import com.massivecraft.massivetickets.cmd.CmdTicketsPick;
import com.massivecraft.massivetickets.cmd.CmdTicketsShow;
import com.massivecraft.massivetickets.cmd.CmdTicketsTeleport;
import com.massivecraft.massivetickets.cmd.CmdTicketsVersion;
import com.massivecraft.massivetickets.cmd.CmdTicketsWorking;
import com.massivecraft.massivetickets.cmd.CmdTicketsYield;
import com.massivecraft.massivetickets.cmd.MassiveTicketsCommand;
import com.massivecraft.massivetickets.cmd.type.TypeReaction;
import com.massivecraft.massivetickets.engine.EngineJoin;
import com.massivecraft.massivetickets.engine.EngineLeave;
import com.massivecraft.massivetickets.engine.TaskBump;
import com.massivecraft.massivetickets.entity.MConf;
import com.massivecraft.massivetickets.entity.MConfColl;
import com.massivecraft.massivetickets.entity.MPlayerColl;
import com.massivecraft.massivetickets.predicate.PredicateIsModerator;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.List;

import static com.massivecraft.massivecore.mson.Mson.mson;

public class MassiveTickets extends MassivePlugin
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static MassiveTickets i;
	public static MassiveTickets get() { return i; }
	public MassiveTickets() { MassiveTickets.i = this; }
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void onEnableInner()
	{
		// Types
		RegistryType.register(Reaction.class, TypeReaction.get());
		
		// Activate
		this.activate(
			// Coll
			MConfColl.class,
			MPlayerColl.class,
				
			// Engines
			EngineJoin.class,
			EngineLeave.class,
			
			// Schedule recurring non-tps-dependent tasks
			TaskBump.class,
			
			// Command
			CmdTickets.class,
			CmdTicketsList.class,
			CmdTicketsShow.class,
			CmdTicketsCreate.class,
			CmdTicketsDone.class,
			CmdTicketsPick.class,
			CmdTicketsYield.class,
			CmdTicketsHighscore.class,
			CmdTicketsModlist.class,
			CmdTicketsWorking.class,
			CmdTicketsTeleport.class,
			CmdTicketsClose.class,
			CmdTicketsCheat.class,
			CmdTicketsVersion.class
		);
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
			MassiveTicketsCommand.BUTTON_LIST.command(CmdTickets.get().cmdTicketsList)
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
		List<Mson> target = new ArrayList<>();
		for (Object message : messages)
		{
			target.add(getPrefix().add(message));
		}
		return MixinMessage.get().messagePredicate(PredicateIsModerator.get(), target);
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
		List<Mson> target = new ArrayList<>();
		for (Object message : messages)
		{
			target.add(getPrefix().add(message));
		}
		return MixinMessage.get().messageOne(sender, target);
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
		List<Mson> target = new ArrayList<>();
		for (Object message : messages)
		{
			target.add(getPrefix().add(message));
		}
		return MixinMessage.get().messageOne(senderId, target);
	}

	// All Moderators
	public static boolean alertModeratorsMsg(String msg) // WORKS
	{
		return MixinMessage.get().msgPredicate(PredicateIsModerator.get(), MConf.get().getPrefix() + msg);
	}
	public static boolean alertModeratorsMsg(String msg, Object... args)
	{
		return MixinMessage.get().msgPredicate(PredicateIsModerator.get(), MConf.get().getPrefix() + msg, args);
	}
	public static boolean alertModeratorsMsg(Collection<String> msgs)
	{
		List<String> target = new ArrayList<>();
		for (String msg : msgs)
		{
			target.add(MConf.get().getPrefix() + msg);
		}
		return MixinMessage.get().msgPredicate(PredicateIsModerator.get(), target);
	}
	
	// One
	public static boolean alertOneMsg(CommandSender sender, String msg)
	{
		return MixinMessage.get().msgOne(sender, MConf.get().getPrefix() + msg);
	}
	public static boolean alertOneMsg(CommandSender sender, String msg, Object... args)
	{
		return MixinMessage.get().msgOne(sender, MConf.get().getPrefix() + msg, args);
	}
	public static boolean alertOneMsg(CommandSender sender, Collection<String> msgs)
	{
		List<String> target = new ArrayList<>();
		for (String msg : msgs)
		{
			target.add(MConf.get().getPrefix() + msg);
		}
		return MixinMessage.get().msgOne(sender, target);
	}
	
	// One by id
	public static boolean alertOneMsg(String senderId, String msg)
	{
		return MixinMessage.get().msgOne(senderId, MConf.get().getPrefix() + msg);
	}
	public static boolean alertOneMsg(String senderId, String msg, Object... args)
	{
		return MixinMessage.get().msgOne(senderId, MConf.get().getPrefix() + msg, args);
	}
	public static boolean alertOneMsg(String senderId, Collection<String> msgs)
	{
		List<String> target = new ArrayList<>();
		for (String msg : msgs)
		{
			target.add(MConf.get().getPrefix() + msg);
		}
		return MixinMessage.get().msgOne(senderId, target);
	}
	
	private static Mson getPrefix()
	{
		return Mson.fromParsedMessage(Txt.parse(MConf.get().getPrefix()));
	}
	
}
