package com.massivecraft.massivetickets.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.bukkit.event.EventPriority;

import com.massivecraft.massivetickets.Level;
import com.massivecraft.massivetickets.Reaction;
import com.massivecraft.mcore.store.Entity;
import com.massivecraft.mcore.util.MUtil;

public class MConf extends Entity<MConf>
{
	// -------------------------------------------- //
	// META
	// -------------------------------------------- //
	
	protected static transient MConf i;
	public static MConf get() { return i; }
	
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	// Command Aliases
	
	public List<String> aliasesOuterTickets = MUtil.list("t", "tickets", "ticket");
	
	public List<String> aliasesInnerTicketsList = MUtil.list("l", "list");
	public List<String> aliasesOuterTicketsList = new ArrayList<String>();
	
	public List<String> aliasesInnerTicketsShow = MUtil.list("s", "show");
	public List<String> aliasesOuterTicketsShow = new ArrayList<String>();
	
	public List<String> aliasesInnerTicketsCreate = MUtil.list("c", "create");
	public List<String> aliasesOuterTicketsCreate = MUtil.list("helpop", "modreq");
	
	public List<String> aliasesInnerTicketsDone = MUtil.list("d", "done");
	public List<String> aliasesOuterTicketsDone = new ArrayList<String>();
	
	public List<String> aliasesInnerTicketsPick = MUtil.list("p", "pick");
	public List<String> aliasesOuterTicketsPick = new ArrayList<String>();
	
	public List<String> aliasesInnerTicketsYield = MUtil.list("y", "yield");
	public List<String> aliasesOuterTicketsYield = new ArrayList<String>();
	
	public List<String> aliasesInnerTicketsHighscore = MUtil.list("hs", "highscore");
	public List<String> aliasesOuterTicketsHighscore = new ArrayList<String>();
	
	public List<String> aliasesInnerTicketsModlist = MUtil.list("m", "modlist");
	public List<String> aliasesOuterTicketsModlist = MUtil.list("modlist");
	
	public List<String> aliasesInnerTicketsWorking = MUtil.list("w", "working");
	public List<String> aliasesOuterTicketsWorking = new ArrayList<String>();
	
	public List<String> aliasesInnerTicketsCheat = MUtil.list("cheat");
	public List<String> aliasesOuterTicketsCheat = new ArrayList<String>();
	
	public List<String> aliasesInnerTicketsHearsound = MUtil.list("hearsound");
	public List<String> aliasesOuterTicketsHearsound = new ArrayList<String>();
	
	public List<String> aliasesInnerTicketsVersion = MUtil.list("v", "version");
	public List<String> aliasesOuterTicketsVersion = new ArrayList<String>();
	
	// Format
	
	private String prefix = "<pink><T> ";
	public String getPrefix() { return this.prefix; }
	public void setPrefix(String prefix) { this.prefix = prefix; this.changed(); }
	
	private int excerptLength = 10;
	public int getExcerptLength() { return this.excerptLength; }
	public void setExcerptLength(int excerptLength) { this.excerptLength = excerptLength; this.changed(); }
	
	// Bump
	
	private boolean bumpOnJoinActive = true;
	public boolean isBumpOnJoinActive() { return this.bumpOnJoinActive; }
	public void setBumpOnJoinActive(boolean bumpOnJoinActive) { this.bumpOnJoinActive = bumpOnJoinActive; this.changed(); }
	
	private EventPriority bumpOnJoinPriority = EventPriority.HIGH;
	public EventPriority getBumpOnJoinPriority() { return this.bumpOnJoinPriority; }
	public void setBumpOnJoinPriority(EventPriority bumpOnJoinPriority) { this.bumpOnJoinPriority = bumpOnJoinPriority; this.changed(); }
	
	private double bumpEachMinutes = 10D;
	public double getBumpEachMinutes() { return this.bumpEachMinutes; }
	public void setBumpEachMinutes(double bumpEachMinutes) { this.bumpEachMinutes = bumpEachMinutes; this.changed(); }
	
	// Reactions
	
	private Reaction createReaction = Reaction.EMPTY;
	public Reaction getCreateReaction() { return this.createReaction; }
	public void setCreateReaction(Reaction createReaction) { this.createReaction = createReaction; this.changed(); }
	
	private Reaction updateReaction = Reaction.EMPTY;
	public Reaction getUpdateReaction() { return this.updateReaction; }
	public void setUpdateReaction(Reaction updateReaction) { this.updateReaction = updateReaction; this.changed(); }
	
	private Reaction doneReaction = Reaction.EMPTY;
	public Reaction getDoneReaction() { return this.doneReaction; }
	public void setDoneReaction(Reaction doneReaction) { this.doneReaction = doneReaction; this.changed(); }
	
	private Reaction doneReactionNormal = Reaction.valueOf(
		MUtil.list(
			"ticket hearsound ORB_PICKUP,0.8,0.7 LEVEL_UP,1.0,1.0"
		),
		null,
		null
	);
	public Reaction getDoneReactionNormal() { return this.doneReactionNormal; }
	public void setDoneReactionNormal(Reaction doneReactionNormal) { this.doneReactionNormal = doneReactionNormal; this.changed(); }
	
	private Reaction doneReactionLevel = Reaction.valueOf(
		MUtil.list(
			"ticket hearsound WITHER_SPAWN,0.5,1.4 ORB_PICKUP,0.8,0.7 LEVEL_UP,1.0,1.0"
		),
		null,
		null
	);
	public Reaction getDoneReactionLevel() { return this.doneReactionLevel; }
	public void setDoneReactionLevel(Reaction doneReactionLevel) { this.doneReactionLevel = doneReactionLevel; this.changed(); }
	
	private Reaction pickReaction = Reaction.valueOf(
		MUtil.list(
			"msg {p}"
		),
		MUtil.list(
			"msg {m}"
		),
		null
	);
	public Reaction getPickReaction() { return this.pickReaction; }
	public void setPickReaction(Reaction pickReaction) { this.pickReaction = pickReaction; this.changed(); }
	
	private Reaction showReaction = Reaction.EMPTY;
	public Reaction getShowReaction() { return this.showReaction; }
	public void setShowReaction(Reaction showReaction) { this.showReaction = showReaction; this.changed(); }
	
	private Reaction workingOnReaction = Reaction.EMPTY;
	public Reaction getWorkingOnReaction() { return this.workingOnReaction; }
	public void setWorkingOnReaction(Reaction workingOnReaction) { this.workingOnReaction = workingOnReaction; this.changed(); }
	
	private Reaction workingOffReaction = Reaction.EMPTY;
	public Reaction getWorkingOffReaction() { return this.workingOffReaction; }
	public void setWorkingOffReaction(Reaction workingOffReaction) { this.workingOffReaction = workingOffReaction; this.changed(); }
	
	private Reaction yieldReaction = Reaction.EMPTY;
	public Reaction getYieldReaction() { return this.yieldReaction; }
	public void setYieldReaction(Reaction yieldReaction) { this.yieldReaction = yieldReaction; this.changed(); }
	
	// Levels
	private Map<Integer, Level> countToLevel = MUtil.map(
		0, Level.valueOf(
			"Not a big help",
			Reaction.EMPTY
		),
		1, Level.valueOf(
			"Almost nothing",
			Reaction.valueOf(
				null,
				null,
				MUtil.list(
					"give {m} 297 1 name:Moldy_Bread",
					"give {m} 263 1 name:Tiny_Piece_of_Antimatter"
				)
			)
		),
		3, Level.valueOf(
			"It's Something",
			Reaction.valueOf(
				null,
				null,
				MUtil.list(
					"give {m} 357 1 name:Cookie_of_Something"
				)
			)
		),
		5, Level.valueOf(
			"A poor twerksman blames his tools",
			Reaction.valueOf(
				null,
				null,
				MUtil.list(
					"give {m} 261 1 name:Bow_of_the_Trendy_Twerker unbreaking:3 lore:Let_the_Twerking_Commence!"
				)
			)
		),
		7, Level.valueOf(
			"A ticket a day keeps the doctor away",
			Reaction.valueOf(
				null,
				null,
				MUtil.list(
					"give {m} 373:8257 2"
				)
			)
		),
		10, Level.valueOf(
			"Over 9... it's... Dinner Time!!!",
			Reaction.valueOf(
				null,
				null,
				MUtil.list(
					"give {m} 297 1 name:Dinner_Bread",
					"give {m} 260 1 name:Dinner_Apple",
					"give {m} 373:8258 1 name:Dinner_Lemonade"
				)
			)
		),
		20, Level.valueOf(
			"20 is Plenty!",
			Reaction.valueOf(
				null,
				null,
				MUtil.list(
					"give {m} 371 20"
				)
			)
		),
		30, Level.valueOf(
			"Remember: Sharing is Caring",
			Reaction.valueOf(
				null,
				null,
				MUtil.list(
					"give {m} 354 3 name:Cake_of_Compassion"
				)
			)
		),
		40, Level.valueOf(
			"Remember: Know thyself... you be fish?",
			Reaction.valueOf(
				null,
				null,
				MUtil.list(
					"give {m} 349 1 name:{m}_the_Fish lore:You_are_holding_{m}_in_your_hand.|{m}_brings_fortune! fortune:3"
				)
			)
		),
		50, Level.valueOf(
			"Honorable Helper",
			Reaction.valueOf(
				null,
				null,
				MUtil.list(
					"give {m} 347 1 name:Honorable_Helper_{m}_{y}_{w} lore:{m}_did_50_tickets_Year_{y}_Week_{w}.|For_this_we_grant_the_Honorable_Helper_Award.|So_Hysterically_Honorable! unbreaking:3",
					"give {m} 280 1 name:Pathetic_Pokingstick unbreaking:3 knockback:1 lore:What_an_outrageously_pathetic_poking_device!",
					"money give {m} 20"
				)
			)
		),
		60, Level.valueOf(
			"Sandals of the Trendy Twerker",
			Reaction.valueOf(
				null,
				null,
				MUtil.list(
					"give {m} 301 1 name:Sandals_of_the_Trendy_Twerker color:255,0,230 unbreaking:3 featherfalling:4 lore:Trendy_Sneakin'_YO!"
				)
			)
		),
		70, Level.valueOf(
			"Pants of the Trendy Twerker",
			Reaction.valueOf(
				null,
				null,
				MUtil.list(
					"give {m} 300 1 name:Pants_of_the_Trendy_Twerker color:255,0,230 unbreaking:3 lore:The_NO1_Dancefloor_Choice!"
				)
			)
		),
		80, Level.valueOf(
			"Vest of the Trendy Twerker",
			Reaction.valueOf(
				null,
				null,
				MUtil.list(
					"give {m} 299 1 name:Vest_of_the_Trendy_Twerker color:255,0,230 unbreaking:3 lore:This_Vest_be_PHAT_and_makes_ya_curves_POP_YO!"
				)
			)
		),
		90, Level.valueOf(
			"Headband of the Trendy Twerker",
			Reaction.valueOf(
				null,
				null,
				MUtil.list(
					"give {m} 147 1 name:Headband_of_the_Trendy_Twerker respiration:3 aquaaffinity:1 lore:You'll_se_nothn'_my_niqqa_but_it's_all_'bout_the_feeeel!"
				)
			)
		),
		99, Level.valueOf(
			"Close but no Cigar",
			Reaction.valueOf(
				null,
				null,
				MUtil.list(
					"give {m} 391 1 name:No_Cigar! lore:So_you_thought_this_was_a_cigar?|You're_wearing_that_trendy_headband_aren't_you? BaneofArthropods:3"
				)
			)
		),
		100, Level.valueOf(
			"Magnificent Moderator",
			Reaction.valueOf(
				null,
				null,
				MUtil.list(
					"give {m} 396 1 name:Magnificent_Moderator_{m}_{y}_{w} lore:{m}_did_100_tickets_Year_{y}_Week_{w}.|For_this_we_grant_the_Magnificent_Moderator_Award.|So_Marvelously_Magnificent! unbreaking:3",
					"give {m} 369 1 name:Powerful_Pokingstick unbreaking:3 knockback:3 lore:Quite_a_powerful_poking_device!",
					"give {m} 400 1 name:Pie_of_Prosperity lore:This_pie_is_so_hard_it's_almost_inedible.|Could_rather_be_used_to_bash_heads_in. looting:3",
					"money give {m} 50"
				)
			)
		),
		125, Level.valueOf(
			"Master of the Ticket Fondue",
			Reaction.valueOf(
				null,
				null,
				MUtil.list(
					"give {m} 282 10 name:Ticket_Fondue lore:It's_a_FONDUE_just_for_YOU!|Be_warned,_this_food_is_hot. FireAspect:1"
				)
			)
		),
		150, Level.valueOf(
			"Potato Party with the Party Potatoes!",
			Reaction.valueOf(
				null,
				null,
				MUtil.list(
					"give {m} 392 15 name:Party_Potato lore:Bring_ze_party_on!|No_party_poopers!|Only_potatoes_allowed! unbreaking:1"
				)
			)
		),
		199, Level.valueOf(
			"Yes Cigar!",
			Reaction.valueOf(
				null,
				null,
				MUtil.list(
					"give {m} 396 1 name:Yes_Cigar!"
				)
			)
		),
		200, Level.valueOf(
			"Tip-Top-Ticketeer",
			Reaction.valueOf(
				null,
				null,
				MUtil.list(
					"money give {m} 70"
				)
			)
		)
	);
	public Map<Integer, Level> getCountToLevel()
	{
		return new TreeMap<Integer, Level>(this.countToLevel);
	}
	public void setCountToAchievement(Map<Integer, Level> countToLevel)
	{
		this.countToLevel = new TreeMap<Integer, Level>(countToLevel);
		this.changed();
	}
	
	public Level getLevelForCount(int count)
	{
		Level ret = null;
		for (Entry<Integer, Level> entry : this.getCountToLevel().entrySet())
		{
			if (entry.getKey() > count) break;
			ret = entry.getValue();
		}
		return ret;
	}

}
