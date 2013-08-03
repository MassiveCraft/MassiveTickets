package com.massivecraft.massivetickets.entity;

import com.massivecraft.mcore.store.Entity;

public class MConf extends Entity<MConf>
{
	// -------------------------------------------- //
	// META
	// -------------------------------------------- //
	
	protected static transient MConf i;
	public static MConf get() { return i; }
	
	// -------------------------------------------- //
	// DERP ON BELOW
	// -------------------------------------------- //
	
	private String prefix = "<pink><T> ";
	public String getPrefix() { return this.prefix; }
	public void setPrefix(String prefix) { this.prefix = prefix; this.changed(); }

}
