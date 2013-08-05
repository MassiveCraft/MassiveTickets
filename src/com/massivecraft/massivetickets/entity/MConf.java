package com.massivecraft.massivetickets.entity;

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
	
	// Format
	
	private String prefix = "<pink><T> ";
	public String getPrefix() { return this.prefix; }
	public void setPrefix(String prefix) { this.prefix = prefix; this.changed(); }
	
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
	
	private Reaction pickReaction = Reaction.EMPTY;
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
			"Nothing",
			Reaction.EMPTY
		),
		1, Level.valueOf(
			"Almost nothing",
			Reaction.EMPTY
		),
		3, Level.valueOf(
			"Almost something",
			Reaction.EMPTY
		),
		5, Level.valueOf(
			"Something",
			Reaction.valueOf(
				null,
				null,
				MUtil.list(
					"give {m} cookie 1 name:Cookie_of_Something"
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
