package com.massivecraft.massivetickets.entity;

import com.massivecraft.massivecore.collections.MassiveList;
import com.massivecraft.massivecore.store.Entity;
import com.massivecraft.massivecore.util.MUtil;
import com.massivecraft.massivetickets.Reaction;
import org.bukkit.event.EventPriority;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
	
	public List<String> aliasesOuterTickets = MUtil.list("t", "ti", "ticket", "tickets", "massiveticket", "massivetickets", "mt");
	
	public List<String> aliasesInnerTicketsList = MUtil.list("list");
	public List<String> aliasesOuterTicketsList = new ArrayList<String>();
	
	public List<String> aliasesInnerTicketsShow = MUtil.list("show");
	public List<String> aliasesOuterTicketsShow = new ArrayList<String>();
	
	public List<String> aliasesInnerTicketsCreate = MUtil.list("create", "new");
	public List<String> aliasesOuterTicketsCreate = MUtil.list("helpop", "modreq");
	
	public List<String> aliasesInnerTicketsDone = MUtil.list("done");
	public List<String> aliasesOuterTicketsDone = new ArrayList<String>();
	
	public List<String> aliasesInnerTicketsPick = MUtil.list("pick");
	public List<String> aliasesOuterTicketsPick = new ArrayList<String>();
	
	public List<String> aliasesInnerTicketsYield = MUtil.list("yield");
	public List<String> aliasesOuterTicketsYield = new ArrayList<String>();
	
	public List<String> aliasesInnerTicketsHighscore = MUtil.list("hs", "highscore");
	public List<String> aliasesOuterTicketsHighscore = new ArrayList<String>();
	
	public List<String> aliasesInnerTicketsModlist = MUtil.list("ml", "modlist");
	public List<String> aliasesOuterTicketsModlist = MUtil.list("modlist");
	
	public List<String> aliasesInnerTicketsWorking = MUtil.list("working");
	public List<String> aliasesOuterTicketsWorking = new ArrayList<String>();
	
	public List<String> aliasesInnerTicketsCheat = MUtil.list("cheat");
	public List<String> aliasesOuterTicketsCheat = new ArrayList<String>();
	
	public List<String> aliasesInnerTicketsTeleport = MUtil.list("tp", "teleport");
	public List<String> aliasesOuterTicketsTeleport = new ArrayList<String>();
	
	public List<String> aliasesInnerTicketsVersion = MUtil.list("version");
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
			"massivecore hearsound ORB_PICKUP,0.8,0.7 LEVEL_UP,1.0,1.0"
		),
		null,
		null
	);
	public Reaction getDoneReactionNormal() { return this.doneReactionNormal; }
	public void setDoneReactionNormal(Reaction doneReactionNormal) { this.doneReactionNormal = doneReactionNormal; this.changed(); }
	
	private Reaction doneReactionLevel = Reaction.valueOf(
		MUtil.list(
			"massivecore hearsound WITHER_SPAWN,0.5,1.4 ORB_PICKUP,0.8,0.7 LEVEL_UP,1.0,1.0"
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

	// Rewards
	public int ticketsPerReward = 5;

	private Map<String, String> rewards = MUtil.map(
		"Moldy Bread", "give {m} bread 1 name:&aMoldy_Bread lore:Is_this_even_edible...?",
		"Backwash Water", "give {m} waterbottle 1 name:&aBackwash_Water lore:Ew!_Is_that_a_spit_bubble?",
		"Crumbs of Something", "give {m} 351:3 1 name:&aCrumbs_of_Something lore:Well,_this_used_to_be_something!",
		"Dinner Steak", "give {m} steak 1 name:&aDinner_Steak lore:This_could_make_a_good_sandwich.",
		"Dinner Wine", "give {m} potion 1 name:&aDinner_Wine lore:The_delicious_tang_is_almost_too_much! effect:poison power:1 duration:30",
		"Soothing Aloe", "give {m} cactus 1 name:&aSoothing_Aloe lore:Aloe_to_sooth_your_hard_working_hands.|Good_job!",
		"Golden Hammer of Doom", "give {m} 286 1 name:&aGolden_Hammer_of_Doom lore:Crack_nails_and_smash_heads! sharp:1",
		"Anti doctor Apple", "give {m} golden_apple 1 name:&aAnti-Doctor_Apple lore:How_does_this_keep_away_a_doctor?|Oh_you_beat_them_with_it. sharp:5",
		"Watermelon of Compassion", "give {m} melon 3 name:&aWatermelon_of_Compassion lore:The_sweet_reward_of_a_true,_caring_giver.",
		"Winner Winner Chicken Dinner", "give {m} chicken 1 name:&aWinner_Winner_Chicken_Dinner lore:A_well_earned_chicken_dinner! power:1",
		"Amazing Ticket Axe", "give {m} 279 1 name:&aAmazing_Ticket_Axe lore:Wield_the_ability_to_break_things_faster! unbreaking:2 efficiency:5",
		"Vanishing Powder Compact", "give {m} compass 1 name:&aVanishing_Powder_Compact lore:Vanish_in_a_flash! unbreaking:2",
		"Ghastly Ticket Mask", "give {m} dispenser 1 name:&aGhastly_Ticket_Mask lore:&r&7Spooky_II|&5&oFrom_the_mist..._a_moderator_appears_to_help_you! ",
		"Powerful Pokingstick", "give {m} stick 1 name:&aPowerful_Pokingstick lore:Watch_out!_You'll_poke_an_eye_out! sharp:5",
		"Pie of Prosperity", "give {m} 400 1 name:&aPie_of_Prosperity lore:This_pie_is_so_hard_it's_almost_inedible.|Could_rather_be_used_to_bash_heads_in. looting:3",
		"Ticket fondue", "give {m} 282 1 name:&aTicket_Fondue lore:It's_a_FONDUE_just_for_YOU!|Be_warned,_this_food_is_hot. FireAspect:1",
		"{m}_Pebble", "give {m} 77 1 name:&a{m}_Pebble lore:You_are_holding_a_{m}_pebble|in_your_hand.|{m}_brings_good_luck! fortune:3",
		"{m}_The_Fish", "give {m} 349:2 1 name:&a{m}_The_Fish lore:You_are_holding_a_{m}_The_Fish|in_your_hand.|{m}_brings_fortune! fortune:3",
		"Bright Star", "give {m} 402 1 name:&aBright_Star lore:The_one_who_holds_this_item_will|shine_bright_as_a_star! FireAspect: 1",
		"Fishermans Secret", "give {m} 346 1 name:&aFishermans_Secret lore:A_strong_fishing_pole_made_for|only_the_most_experienced_in_the|area_of_fishing. luck:3",
		"Grass Kazoo", "give {m} 31:1 1 name:&aGrass_Kazoo lore:Whistle_away_with_this_fab_instrument!|It's_really_just_a_blade_of_grass. efficiency:1",
		"Musical Bottle", "give {m} 374 1 name:&aMusical_Bottle lore:It_makes_noises_when_wind_blows|across_the_top!_WHOOSH!_WHOOSH! power:2 efficiency:1",
		"Ur Claw", "give {m} 318 1 name:&aUr_Claw lore:This_doesn't_look_like_a_pastry...",
		"Qadir Slaver Rod", "give {m} 369 1 name:&aQadir_Slaver_rod lore:A_rod_used_by_Qadir_slavers|to_keep_their_slaves_in_line. sharpness:3 unbreaking:2",
		"Yanar Apple", "give {m} 322 1 name:&aYanar_Apple lore:&r&7Delicious_II|&5&oA_crisp_sour_apple_grown_in_the_branches_of_a_Yanar. sharpness:1",
		"Ancient Ironhall Ingot", "give {m} 265 1 name:&aAncient_Ironhall_Ingot lore:&r&7Glory_I|&5&oAn_ancient_iron_ingot_stamped|with_the_mark_of_Ironhall. fortune:2",
		"Mourner's Candle", "give {m} 76 1 name:&aMourner's_Candle lore:A_candle_lit_by_those_in_mourning_over_a_lost_lover. Unbreaking:1",
		"Runed Emerald", "give {m} 388 1 name:&aRuned_Emerald lore:&r&7Mystery_III|&5&oA_large_emerald_with_strange_writing|carved_into_every_surface. fortune:4 power:2",
		"Golden Charm", "give {m} 371 1 name:&aGolden_Charm lore:&r&7Prestige_III|&5&oA_golden_charm_worn_by_nobles|in_Regalia_to_show_their_wealth. fortune:3",
		"Deadly Poking Stick", "give {m} 268 1 name:&aDeadly_Poking_Stick lore:A_particularly_deadly_poking_stick.|Fear_the_stick! Power:5 unbreaking:3 Punch:2 sharpness:4",
		"Writer's Ink", "give {m} 351 1 name:&aWriter's_Ink lore:An_empty_ink_bottle,_said_to_have|belonged_to_a_famous_author. unbreaking:3 fortune:1",
		"Fresh Bandages", "give {m} 339 1 name:&aFresh_Bandages lore:&r&7Medicine_V|&5&oClean_bandages_soaked_in_medicinal_herbs_to|ward_off_infection_and_speed_recovery. unbreaking:1",
		"Crude Orcish Helm", "give {m} 302 1 name:&aCrude_Orcish_Helm lore:&r&7Quality_-I|&5&oA_poorly_made_yet_remarkably_sturdy_Orcish_helm. Unbreaking:1 protection:2",
		"Crude Orcish Coat", "give {m} 303 1 name:&aCrude_Orcish_coat lore:&r&7Quality_-I|&5&oA_poorly_made_yet_remarkably_sturdy_Orcish_ringmail_coat. unbreaking:1 protection:2",
		"Crude Orcish Leggings", "give {m} 304 1 name:&aCrude_Orcish_Leggings lore:&r&7Quality_-I|&5&oA_poorly_made_yet_remarkably_sturdy_pair|of_Orcish_ringmail_leggings. unbreaking:1 protection:2",
		"Crude Orcish Boots", "give {m} 305 1 name:&aCrude_Orcish_Boots lore:&r&7Quality_-I|&5&oA_poorly_made_yet_remarkably_sturdy_pair_of_Orcish_boots. unbreaking:1 protection:2",
		"Vampire Bone", "give {m} 352 1 name:&aVampire_Bone lore:A_bone_reported_to_be_from_a_dead_vampire.|The_authenticity_of_this_item_is_questionable. Unbreaking:1 power:1",
		"Lucky Catch", "give {m} 349:1 1 name:&aLucky_Catch lore:A_beautiful_fish_caught_on_the_shores_of_Daendroc.|Perfect_for_hanging_on_your_wall. Luckofthesea:5",
		"Regalian Brick", "give {m} 336 1 name:&aRegalian_Brick lore:A_brick_from_the_streets_of_Regalia.|It_could_be_used_as_a_weapon_in_a_pinch. Power:1 Punch:1",
		"Old Chewing Gum", "give {m} 337 1 name:&aOld_Chewing_Gum lore:&r&7Gross_II|&5&oA_piece_of_old_gum_you_found_stuck_to_tour_shoe.|Certainly_didn't_come_from_your_mouth. unbreaking:1",
		"Fishing Bow", "give {m} 261 1 name:&aFishing_bow lore:A_tool_used_by_some_Tigrans_to_fish|in_the_shallow_waters_of_Daendroc. luckofthesea:3 lure:2 power:2",
		"Qadir Scimitar", "give {m} 283 1 name:&aQadir_Scimitar lore:A_standard_weapon_used_by_many_Qadir.|Its_shine_reminds_you_of_the_desert_sun. flame:1",
		"Regal Rose Bush", "give {m} 175:4 1 name:&aRegal_Rose_Bush lore:A_wonderful_smelling_rose_bush.|Often_used_by_the_wealthy_to_decorate_their_homes. lure:2 thorns:2",
		"Pet Slime", "give {m} 165 1 name:&aPet_Slime lore:Your_very_own_pet_slime._to_ward_off_intruders.|Be_careful,_he_bites..._Somehow. Protection:2",
		"Piggy Back Saddle", "give {m} 329 1 name:&aPiggy_back_saddle lore:Just_sneak_this_badboy_onto_your|nearest_friend_and_go_for_a_ride. lure:1 efficiency:2 ",
		"Gravedigger", "give {m} 269 1 name:&aGravedigger lore:Sometimes_you_have_to_dig_your_own_grave.|Or_just_dig_a_hole_to_hide_in. Efficiency:2 unbreaking:1",
		"Combustible Powder", "give {m} 348 1 name:&aCombustible_Powder lore:&r&7Explosive_II|&5&oAn_easily_combusted_powder_made|of_a_firegrass_and_fireweed_combination. knockback:2 power:3",
		"Anti-Magic Throwing Rock", "give {m} 21 1 name:&aAnti-Magic_Throwing_Rock lore:&r&7Magic_Resistance_III|&5&oA_rock_with_chunks_of_lapis|embedded_in_it. unbreaking:2 ",
		"Pan Flute", "give {m} 2256 1 name:&aPan_Flute lore:Also_known_as_a_pan_pipe,|this_is_made_from_hollowed_reeds. lure:3",
		"Clarinet", "give {m} 2257 1 name:&aClarinet lore:A_cheery_instrument_with_clear,_sharp_tunes. lure:3",
		"Miniature Drum", "give {m} 2258 1 name:&aMiniature_Drum lore:A_tinny_tune_can_be_played_on_this! lure:3",
		"Flute", "give {m} 2259 1 name:&aFlute lore:Music_from_this_instrument_seems_to_draw_A'rafa. lure:",
		"Lute", "give {m} 2261 1 name:&aLute lore:A_favorite_amongst_poets_and_playwrites. lure:3",
		"Saxophone", "give {m} 2260 1 name:&aSaxophone lore:A_deep,_rich_tone_emanates_when_it's_played. lure:3",
		"Violin", "give {m} 2262 1 name:&aViolin lore:For_the_experienced,_a_beautiful_charm.|For_the_inexperienced,_nails_on_a_chalkboard. lure:3 knockback:2",
		"Woodwind Horn", "give {m} 2263 1 name:&aWoodwind_Horn lore:A_curved_horn_made_out_of_hollowed_wood. lure:3",
		"Harp", "give {m} 2264 1 name:&aHarp lore:The_music_of_gods_and_goddesses. lure:3",
		"Bagpipes", "give {m} 2265 1 name:&aBagpipes lore:An_obnoxiously_loud_and_boisterous_musical_instrument. lure:3 power:2",
		"Xylophone", "give {m} 2266 1 name:&aXylophone lore:A_percussion_instrument_with_wooden_and_metal_keys. lure:3",
		"Fiddle", "give {m} 2267 1 name:&aFiddle lore:Perfect_for_playing_rapidly_when_in_high_spirits. lure:3",
		"Picnic Basket", "give {m} chest 1 name:&aPicnic_Basket lore:&r&7Baked_Goods_VI&5&o|A_basket_full_of_delicious_goods! lure:2 unbreaking:1",
		"Galoshes", "give {m} 317 1 name:&aGaloshes lore:&r&7Waterproof_IV&5&o|These_boots_are_perfect_for_puddle_jumping! depth_strider:1",
		"Ur-skin Rug", "give {m} 171:12 1 name:&aUr-skin_Rug lore:&r&7Luxury_III&5&o|A_nice,_thick_rug_made_from|an_Ur._Plush_and_soft. silktouch:1 ",
		"Bedsheet Banner", "give {m} 425:15 1 name:&aBedsheet_Banner lore:&r&7Wishful_Thinking_II&5&o|For_all_the_young_plebians_who|wish_they_could_rule_countries. lure:1",
		"Isldar Ice Bow", "give {m} bow 1 name:&aIsldar_Ice_Bow lore:&r&7Ice_Resistance_III&5&o|Not_actually_icy,_this_bow|is_built_to_withstand_the|strains_of_viciously_cold_weather. unbreaking:3 protection:2",
		"Qadir Fire-Dancing Staff", "give {m} 290 1 name:&aQadir_Fire-Dancing_Staff lore:A_favorite_tool_amongst_Qadir|entertainers,_this_staff_is_used_by|fire_mages_for_quite_a_show! fireprotection:3 unbreaking:2",
		"Rosebud Bread", "give {m} bread 1 name:&aRosebud_Bread lore:A_sweet_dinner_bread_with|rosebuds_infused_in_the_dough. lure:2",
		"Stiletto of Mercy", "give {m} 267 1 name:&aStiletto_of_Mercy lore:A_short,_piercing_blade_which|can_swiftly_end_the_lives_of|the_treacherous. unbreaking:2 protection:1",
		"Hefty War Club", "give {m} stick 1 name:&aHefty_War_Club lore:&r&7Bluntness_V&5&o|War_club_or_tree_branch?|It's_hard_to_tell. unbreaking:3",
		"Horseman's Lance", "give {m} 290 1 name:&aHorseman's_Lance lore:Bear_honor_for_your_house_through_jousts! unbreaking:1 protection:3",
		"Fancy Feather", "give {m} feather 1 name:&aFancy_Feather lore:&r&7Fanciness_IV&5&o|A_fabulously_large_feather,_perfect|for_wearing_in_your_cap! efficiency:2 power:1",
		"Broken Bottle", "give {m} 374 1 name:&aBroken_Bottle lore:From_a_drunken_bar_fight,_most_likely. sharpness:1",
		"{m}_in_a_Box", "give {m} chest 1 name:&a{m}_in_a_Box lore:&r&7Surprise_II&5&o|You've_got_a_{m}_in_a_Box!|Wind_the_musical_crank_to_have|them_pop_out! unbreaking:2",
		"Cabbage", "give {m} 351:2 1 name:&aCabbage lore:This_doesn't_quite_look_like_cabbage,|but_it_sure_smells_like_it. knockback:1",
		"Beggar Bowl", "give {m} bowl 1 name:&aBeggar_Bowl lore:Handy_for_street_scavengers. protection:1",
		"Fish Bowl", "give {m} 95:9 1 name:&aFish_Bowl lore:Fishies_go_blub,_blub,_blub! aquaaffinity:2",
		"Fishing Line", "give {m} string 1 name:&aFishing_Line lore:Some_string_without_a_fishing_rod.|What_now?_How_do_fish? unbreaking:1",
		"Bumbershoot", "give {m} 285 1 name:&aBumbershoot lore:A_fancy_umbrella-type_object_which|is_actually_rather_flimsy. featherfalling:1",
		"Collop", "give {m} 363 1 name:&aCollop lore:A_fancily_named_slab_of_meat.|How_odd. lure:1",
		"A Doozy!", "give {m} emerald 1 name:&aA_Doozy! lore:No_clue_what_this_is,|but_it_must_be_something_great! lure:2 unbreaking:1 power:3",
		"Crabcake Soup", "give {m} rabbit_stew 1 name:&aCrabcake_Soup lore:Oh_my_goodness,_it's_so_tasty.|Don't_be_crabby! lure:1 fireaspect:2 ",
		"Gloop", "give {m} 341 1 name:&aGloop lore:A_ball_dripping_with_some|sort_of_green_slimy_substance. unbreaking:1 aquaaffinity:1",
		"Feather Duster", "give {m} feather 1 name:&aFeather_Duster lore:Well,_this_would_be_perfect_if|it_had_more_than_one_feather. featherfalling:3",
		"Bedazzling Button-Top", "give {m} 299 1 name:&aBedazzling_Button_Top color:120,245,230 lore:Beautiful,_Blue,_and_fashionably_glittery,|who_wouldn't_want_to_be_seen|in_this_glitz_and_glam? unbreaking:2 fortune:2 power:1",
		"Antique Bed Frame", "give {m} bed 1 name:&aAntique_Bed_Frame lore:A_lovely_bedframe_salvaged_from_a_long|gone_noble_estate._Probably_stolen. lure:1 unbreaking:2",
		"Squid Baby", "give {m} inksac 1 name:&aSquid_Baby lore:A_most_precious_baby_squid.|Protect_it_with_your_life.",
		"Apple of my Eye", "give {m} apple 1 name:&aApple_of_My_Eye lore:OH_NO!_PUT_THAT_BACK!|IT'S_COVERED_IN_BLOOD! punch:2",
		"Head Cage", "give {m} stained_glass:11 1 name:&aHead_Cage lore:Keep_your_thoughts_safe! protection:5",
		"Fireflies in a Jar", "give {m} ??? 1 name:&aFireflies_in_a_Jar lore:Glowing_bugs_make_perfect_nightlights.|No_longer_will_monsters_under_the|bed_plague_this_holy_land! protection:2",
		"Sea Monkeys", "give {m} 373 1 name:&aSea_Monkeys lore:&r&7Fragile_III&5&o|It_may_not_look_like_much|but_there_are_itty_bitty_shrimp|just_waiting_to_hatch! ",
		"Literally Nothing", "give {m} glass 1 name:&aLiterally_Nothing lore:There_is_literally_nothing_in_this_glass_box.",
		"Chef's Toque", "give {m} 298 1 name:&aChef's_Toque color:247,247,247 lore:&r&7Skill_III&5&o|A_pristine_white_chef's_toque.|Worn_by_the_master_of_the_kitchen. power:2",
		"Chef's Apron", "give {m} 299 1 name:&aChef's_Apron color:247,247,247 lore:&r&7Skill_II&5&o|A_well_made_white_chef's_apron.|A_must_have_for_any_chef. protection:2",
		"Diamond Ring", "give {m} 264 1 name:&aDiamond_Ring lore:A_golden_band_with_a_large_diamond_set_in_it.|This_must_be_worth_a_fortune. unbreaking:3 sharpness:1 fortune:3",
		"Rotten Toadstool", "give {m} 40 1 name:&aRotten_Toadstool lore:&r&7Gross_II&5&o|Why_would_anyone_want_this?|The_smell_alone_might_kill_you. sharpness:1",
		"Treeheart Gem Necklace", "give {m} 388 1 name:&aTreeheart_Gem_Necklace lore:A_beautiful_silver_necklace_centered|with_a_humble_green_gem_taken_from_the|middle_of_a_tree_affected_by_Evergrowth_Magic.",
		"A Regal", "give {m} 371 1 name:&aA_Regal lore:A_single_regal,_not_worth_much.|A_regal_for_your_thoughts? fortune:1",
		"Wooden Mannequin", "give {m} 416 1 name:&aWooden_Mannequin lore:A_cheap_wooden_stand_for|displaying_tailored_clothing. efficiency:2",
		"Coat Rack", "give {m} 416 1 name:&aCoat_Rack lore:A_nice_wooden_stand_to|hang_your_coats_on. efficiency:1",
		"Drawing Board","give {m} 425:15 1 name:&aDrawing_Board lore:An_eisel_with_white_canvas|stretched_across_the_frame.|Back_to_the_drawing_board! unbreaking:1 efficiency:1",
		"Spilt Milk","give {m} 335 1 name:&aSpilt_Milk lore:This_glass_is_missing_some_milk.|No_need_to_cry_over_it. unbreaking:1",
		"Mystery Egg","give {m} egg 1 name:&aMystery_Egg lore:Don't_count_your_chickens_til_they've_hatched. respiration:1",
		"Cloud Puff","give {m} 35 1 name:&aCloud_Puff lore:A_fluffy_white_piece_of_cloud.|Have_you_been_drinking_Cloud_Wine? silktouch:1 featherfalling:X",
		"Grapevine", "give {m} 106 1 name:&aGrapevine lore:I_heard_on_the_grapevine_that|you_like_juice_and_wine!|Begin_your_own_vineyard_with|these_grapevine_cuttings! fortune:1",
		"The Last Straw", "give {m} wheat 1 name:&aThe_Last_Straw lore:Is_this_the_straw_that|broke_the_camel's_back?|No,_this_is_the_LAST_straw! thorns:1",
		"Short Story", "give {m} book 1 name:&aShort_Story lore:What?_Someone_has_taken_this_novel|and_cut_out_half_of_the_pages_to_shorten_it! efficiency:1",
		"Piece of Cake", "give {m} cake 1 name:&aPiece_of_Cake lore:Baking_this_was_a_cakewalk,|and_it's_delicious_to_boot! unbreaking:1 lure:2",
		"Half-Baked Story", "give {m} book 1 name:&aHalf-Baked_Story lore:This_book_appears_to_be|singed_and_smells_of_Cake. fireprotection:1",
		"Painting of Ceardia", "give {m} painting 1 name:&aPainting_of_Ceardia lore:A_painting_of_old_Ceardia,|long_lost_to_the_void. unbreaking:1",
		"Carved Bone Doll", "give {m} bone 1 name:&aCarved_Bone_Doll lore:A_crudely_carved_doll,_made|out_of_bone. unbreaking:3",
		"Rag Doll", "give {m} leather 1 name:&aRag_Doll lore:A_raggedy_doll_made_out|of_scrap_fabrics. protection:2 lure:1",
		"Clay Figurine", "give {m} 172 1 name:&aClay_Figurine lore:A_small_figurine_carved_out|of_clay._It_looks_like|a_hound. protection:1 unbreaking:1",
		"Riding Stick-Horse", "give {m} 418 1 name:&aRiding_Stick-Horse lore:A_gold_painted_wooden_horse|on_a_stick._Pretend_you|have_your_own_majestic_steed! unbreaking:2 protection:2",
		"Doll Cradle", "give {m} boat 1 name:&aDoll_Cradle lore:&r&7Charming_II&5&o|A_pretty_little_wooden_cradle|to_rock_a_child's_doll_to_sleep. protection:2 unbreaking:1",
		"Hand Puppet", "give {m} 415 1 name:&aHand_Puppet lore:A_small_hand_puppet_for|entertaining_masses_of_children. unbreaking:1 lure:1",
		"Pin-Wheel", "give {m} 369 1 name:&aPin-Wheel lore:A_colorful_wheel_spins_atop|this_stick. lure:1",
		"Magic Ticket Dust", "give {m} 351:6 1 name:&aMagic_Ticket_Dust lore:&r&7Magic_X&5&o|Vibrantly_colored_dust,_left_over|from_finished_tickets. featherfalling:3",
		"Coloring Book", "give {m} book 1 name:&aColoring_Book lore:A_book_full_of_detailed_drawings|perfect_for_coloring._Can_you|stay_between_the_lines? unbreaking:1 looting:1",
		"Leather-Bound Journal", "give {m} book 1 name:&aLeather-Bound_Journal lore:A_carefully_bound_journal,_perfect|for_keeping_detailed_or_scattered|thoughts_safe. unbreaking:3 protection:2 efficiency:3",
		"Stilts", "give {m} stick 2 name:&aStilts lore:&r&7Height_IV&5&o|You'll_need_two_of_these_to|balance_properly!_Woah,_don't_fall! unbreaking:2",
		"Snowflake", "give {m} 370 1 name:&aSnowflake lore:&r&7Complexity_III&5&o|A_perfectly_flawed,_perfectly|unique_snowflake._There_is_no|other_just_like_it. infinity:1",
		"Pollen", "give {m} 348 1 name:&aPollen lore:&r&7Allergen_VI&5&o|Woe_be_to_those_with_allergies!|Collected_from_spring_blooming_flowers. smite:2",
		"Big BOOM!", "give {m} TNT 1 name:&aBig_BOOM! lore:&r&7Explosive_IV&5&o|A_clunky_crate_full_of|fireworks,_put_together_by_an|explosive-happy_Orc.|Big_box_make_big_boom! fireaspect:3",
		"Clay Mug", "give {m} flowerpot 1 name:&aClay_Mug lore:A_thick_clay_mug_for_holding|steaming_hot_beverages_or|cold_ales. protection:2 unbreaking:2",
		"Trampoline", "give {m} slime 1 name:&aTrampoline lore:&r&7Bouncy_III&5&o|How_high_can_you_jump?|Bounce! protection:1 unbreaking:3",
		"Nesting Material", "give {m} Haybale 1 name:&aNesting_Material lore:Bits_of_straw_and_fabric|scraps_which_are_perfect_for|bedding_in_Q'urebo_burrows. silktouch:1",
		"Staff Propulsion Device", "give {m} bow 1 name:&aStaff_Propulsion_Device lore:A_large_bow_for_firing|staff_members_into_the_air|instead_of_arrows! unbreaking:3 power:3 punch:1 ",
		"Dakkar Spit", "give {m} lavabucket 1 name:&aDakkar_Spit lore:This_is_totally,_definitely|spit_from_a_Dakkar.|Clearly_not_lava. flame:3 fireaspect:4",
		"Trade Routes", "give {m} map 1 name:&aTrade_Routes lore:A_map_which_marks_the|shipping_routes_for_a_common|merchant_vessel. fortune:2",
		"Kleinfolk Jabbing Blade", "give {m} shears 1 name:&aKleinfolk_Jabbing_Blade lore:A_small_blade_made_specifically|for_smaller_hands_and_eye|jabbing._An_inscription_reads|\"Go_for_the_eyes!\" sharpness:1 unbreaking:2 power:1",
		"Makeshift Scissors", "give {m} shears 1 name:&aMakeshift_Scissors lore:&r&7Unsafe_II&5&o|Two_knives_haphazardly_tied|together_in_an_attempt_to|create_scissors. punch:1",
		"Cooking Pot", "give {m} cauldron 1 name:&aCooking_Pot lore:A_large_cooking_pot,_perfect|for_cooking_bulky_and|questionable_Orcish_stews. unbreaking:3",
		"Bucket o' Sunshine", "give {m} lavabucket 1 name:&aBucket_o'_Sunshine lore:&r&7Scalding_III&5&o|Pure,_liquid_sunshine!|Completely_safe_to_bathe_in. fireaspect:1 flame:3",
		"The Force", "give {m} 283 1 name:&aThe_Force lore:A_sword_through_which_to|channel_your_inner_willpower. smite:2 unbreaking:3 power:5 knockback:1",
		"Ticket Lasso", "give {m} lead 1 name:&aTicket_Lasso lore:Give_it_a_whirl_and_rope_in|a_staff_member_for_assistance! lure:2 power:1 unbreaking:1",
		"Smithing Apron", "give {m} 299 1 name:&aSmithing_Apron color:61,28,10 lore:A_heavy_leather_apron_for_blacksmiths. unbreaking:2 ",
		"Bathrobe of Luxury", "give {m} 299 1 name:&aBathrobe_of_Luxury color:191,34,74 lore:A_thick,fur-lined_bathrobe_which|costs_more_than_one_may_think. fortune:2 silktouch:1",
		"Giant Lollipop Stick", "give {m} blazerod 1 name:&aGiant_Lollipop_Stick lore:This_stick_has_less_lolli|and_more_pop! lure:3",
		"Rock Candy", "give {m} 77 1 name:&aRock_Candy lore:&r&7Jawbreaker_II&5&o|This_so-called_candy_is|literally_a_rock. unbreaking:3 infinity:1",
		"Broken Skeleton Key", "give {m} bone 1 name:&aBroken_Skeleton_Key lore:I_guess_you'll_never_know|what_this_key_unlocks... power:1",
		"Skull Music Box", "give {m} skull 1 name:&aSkull_Music_Box lore:Tiny_mechanical_gears_have|been_inserted_into_the_skull.|It_plays_music_whenever_its|jaw_is_unhinged. power:2 efficiency:3 unbreaking:1",
		"Brigande Aviator Goggles", "give {m} 184 1 name:&aBrigande_Aviator_Goggles lore:Goggles_built_by_crazy_sky|brigande_members_to_protect_your|eyes_from_the_wind_during_aviation. unbreaking:3",
		"Guard's Batton ", "give {m} stick 1 name:&aGuard's_Batton lore:A_sturdy_batton_used_by_guards_when|they_need_non-lethal_force. unbreaking:2 power:1",
		"Missing Key", "give {m} 131 1 name:&aMissing_Key lore:Funny_how_something_so_little_is_so_important.|Perhaps_it_unlocks_gold,_or_jewels.|Probably_just_the_key_to_someone's_barn. protection:1",
		"Wooden Turtle", "give {m} 143 1 name:&aWooden_Turtle lore:A_small_piece_of_wood_carved_into_a_turtle.|Truly_the_work_of_a_master_craftsman. fortune:1",
		"Stone Cat", "give {m} 77 1 name:&aStone_Cat lore:A_small_piece_of_stone_carved_into|the_shape_of_a_sleeping_cat.|It_looks_almost_real. fortune:1 unbreaking:1",
		"Blindman's Bandages", "give {m} 70 1 name:&aBlindman's_Bandages lore:Bandages_worn_around_the_head_by_those|unfortunate_enough_to_lose_their_eyes. protection:1",
		"Toy Carriage", "give {m} 342 1 name:&aToy_Carriage lore:A_miniature_replica_of_a_wealthy_noble's|carriage,_great_for_hours_of_fun.|Wait,_where_are_the_toy_horses? infinity:1",
		"Wooden Helmet", "give {m} 281 1 name:&aWooden_Helmet lore:A_bizarre_helmet_carved_from_a_chunk|of_oak._It_doesn't_even_fit_on|your_head. protection:1 unbreaking:1",
		"Cat Toy", "give {m} 287 1 name:&aCat_Toy lore:&r&7Amusement_X&5&o|Endless_fun_for_your_favorite_furry|friend._Keep_away_from_tigrans. infinity:1",
		"Shark Tooth", "give {m} 318 1 name:&aShark_Tooth lore:Whatever_shark_lost_this_must_have_been|massive._Best_stay_clear_of_the_water. sharpness:3 unbreaking:1",
		"Pink Blush", "give {m} 351:9 1 name:&aPink_Blush lore:&r&7Beauty_3&5&o|Quality_makeup_in_just_the_right_shade|to_make_you_look_irresistable._Time_for|a_night_on_the_town. fortune:1 lure:2",
		"Magnifying Glass", "give {m} 131 1 name:&aMagnifying_Glass lore:A_beautiful_magnifying_glass_with_an_ornately|carved_wooden_handle._Used_for_looking_over|log_books,_or_starting_fires. power:1 flame:1",
		"Qadir Sun Idol", "give {m} 175 1 name:&aQadir_Sun_Idol lore:A_golden_statue_of_the_sun.|Used_by_Shambala_faithful|to_offer_prayers. fortune:1 flame:1",
		"Flying Carpet", "give {m} 171:1 name:&aFlying_Carpet lore:See_the_world_on_this_magic_flying_carpet.|[Warning_carpet_does_not_actually_fly.] featherfalling:3",
		"Cookie Jar", "give {m} 390 1 name:&aCookie_Jar lore:Quick!_Hide_your_cookies_in_here|to_keep_them_safe_from_the_Klein. Protection:2 lure:5"
	);

	public Map<String, String> getRewards()
	{
		return new TreeMap<String, String>(this.rewards);
	}

	public void setRewards(Map<String, String> rewards)
	{
		this.rewards = new TreeMap<String, String>(rewards);
		this.changed();
	}

	public String getRandomReward()
	{
		return MUtil.random(this.getRewards().keySet());
	}

	public Reaction getRewardReaction(String name)
	{
		return Reaction.valueOf(null, null, new MassiveList<String>(this.getRewards().get(name)));
	}

}
