package com.massivecraft.massivetickets;

import java.util.*;

import com.massivecraft.mcore.SimpleConfig;
import com.massivecraft.mcore.util.MUtil;

public class ConfServer extends SimpleConfig
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static transient ConfServer i = new ConfServer();
	public static ConfServer get() { return i; }
	public ConfServer() { super(MassiveTickets.get()); }
	
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	public static String dburi = "default";
	
	public static List<String> aliasesOuterTickets = MUtil.list("t", "tickets", "ticket");
	
	public static List<String> aliasesInnerTicketsList = MUtil.list("l", "list");
	public static List<String> aliasesOuterTicketsList = new ArrayList<String>();
	
	public static List<String> aliasesInnerTicketsShow = MUtil.list("s", "show");
	public static List<String> aliasesOuterTicketsShow = new ArrayList<String>();
	
	public static List<String> aliasesInnerTicketsCreate = MUtil.list("c", "create");
	public static List<String> aliasesOuterTicketsCreate = MUtil.list("helpop", "modreq");
	
	public static List<String> aliasesInnerTicketsDone = MUtil.list("d", "done");
	public static List<String> aliasesOuterTicketsDone = new ArrayList<String>();
	
	public static List<String> aliasesInnerTicketsPick = MUtil.list("p", "pick");
	public static List<String> aliasesOuterTicketsPick = new ArrayList<String>();
	
	public static List<String> aliasesInnerTicketsYield = MUtil.list("y", "yield");
	public static List<String> aliasesOuterTicketsYield = new ArrayList<String>();
	
	public static List<String> aliasesInnerTicketsHighscore = MUtil.list("hs", "highscore");
	public static List<String> aliasesOuterTicketsHighscore = new ArrayList<String>();
	
	public static List<String> aliasesInnerTicketsModlist = MUtil.list("m", "modlist");
	public static List<String> aliasesOuterTicketsModlist = MUtil.list("modlist");
	
	public static List<String> aliasesInnerTicketsWorking = MUtil.list("w", "working");
	public static List<String> aliasesOuterTicketsWorking = new ArrayList<String>();
	
	public static List<String> aliasesInnerTicketsCheat = MUtil.list("cheat");
	public static List<String> aliasesOuterTicketsCheat = new ArrayList<String>();
	
	public static List<String> aliasesInnerTicketsHearsound = MUtil.list("hearsound");
	public static List<String> aliasesOuterTicketsHearsound = new ArrayList<String>();
	
	public static List<String> aliasesInnerTicketsVersion = MUtil.list("v", "version");
	public static List<String> aliasesOuterTicketsVersion = new ArrayList<String>();
	
}
