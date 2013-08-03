package com.massivecraft.massivetickets.entity;

import org.bukkit.event.EventPriority;

import com.massivecraft.mcore.store.Entity;

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
	
	private String prefix = "<pink><T> ";
	public String getPrefix() { return this.prefix; }
	public void setPrefix(String prefix) { this.prefix = prefix; this.changed(); }
	
	private boolean bumpOnJoinActive = true;
	public boolean isBumpOnJoinActive() { return this.bumpOnJoinActive; }
	public void setBumpOnJoinActive(boolean bumpOnJoinActive) { this.bumpOnJoinActive = bumpOnJoinActive; this.changed(); }
	
	private EventPriority bumpOnJoinPriority = EventPriority.HIGH;
	public EventPriority getBumpOnJoinPriority() { return this.bumpOnJoinPriority; }
	public void setBumpOnJoinPriority(EventPriority bumpOnJoinPriority) { this.bumpOnJoinPriority = bumpOnJoinPriority; this.changed(); }
	
	private double bumpEachMinutes = 15D;
	public double getBumpEachMinutes() { return this.bumpEachMinutes; }
	public void setBumpEachMinutes(double bumpEachMinutes) { this.bumpEachMinutes = bumpEachMinutes; this.changed(); }

}
