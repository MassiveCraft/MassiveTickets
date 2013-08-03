package com.massivecraft.massivetickets.entity;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.command.CommandSender;

import com.massivecraft.mcore.store.SenderEntity;
import com.massivecraft.mcore.util.MUtil;
import com.massivecraft.mcore.util.SenderUtil;

public class MPlayer extends SenderEntity<MPlayer>
{
	// -------------------------------------------- //
	// META
	// -------------------------------------------- //
	
	public static MPlayer get(Object oid)
	{
		return MPlayerColl.get().get(oid);
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public MPlayer load(MPlayer that)
	{
		this.setWorking(that.isWorking());
		this.setMessage(that.getMessage());
		this.setMillis(that.getMillis());
		this.setModeratorId(that.getModeratorId());
		this.setCount(that.getCount());
				
		return this;
	}
	
	@Override
	public boolean isDefault()
	{
		if (this.isWorking()) return false;
		if (this.hasMessage()) return false;
		if (this.hasMillis()) return false;
		if (this.hasModeratorId()) return false;
		if (this.hasCount()) return false;
		
		return true;
	}
	
	// -------------------------------------------- //
	// FIELDS: DECLARE
	// -------------------------------------------- //
	
	// Is this player a working moderator?
	// The default is false. One must opt in for game management.
	// To save database space we use null for the default value.
	private Boolean working = null;
	
	// Did this player create a ticket?
	// In such case this is the ticket message.
	// Yeah, only one ticket per player at a time.
	private String message = null;
	
	// Did this player create a ticket?
	// In such case this is the ticket creation millis.
	// Yeah, only one ticket per player at a time.
	private Long millis = null;
	
	// Did a moderator pick this ticket?
	// In such case this is the id/playername of that moderator.
	private String moderatorId = null;
	
	// This ticket done count is used for the highscore.
	// "year --> week --> count" like this example:
	// "count": {
	//     "2013": {
	//         "32": 176,
	//         "31": 30,
	//         "29": 72
	//     }
	// }
	private Map<Integer, Map<Integer, Integer>> count = null;
	
	// -------------------------------------------- //
	// FIELDS: LOW
	// -------------------------------------------- //
	
	// FIELD: WORKING
	public boolean isWorking() { return this.working != null; }
	public void setWorking(boolean working)
	{
		// Clean input
		Boolean target = working;
		if (target == false) target = null;
		
		// Detect Nochange
		if (MUtil.equals(this.working, target)) return;
		
		// Apply
		this.working = target;
		
		// Mark as changed
		this.changed();
	}
	
	// FIELD: MESSAGE
	public String getMessage() { return this.message; }
	public boolean hasMessage() { return this.message != null; }
	public void setMessage(String message)
	{
		// Clean input
		String target = message;
		
		// Detect Nochange
		if (MUtil.equals(this.message, target)) return;
		
		// Apply
		this.message = target;
		
		// Mark as changed
		this.changed();
	}
	
	// FIELD: MILLIS
	public Long getMillis() { return this.millis; }
	public boolean hasMillis() { return this.millis != null; }
	public void setMillis(Long millis)
	{
		// Clean input
		Long target = millis;
		
		// Detect Nochange
		if (MUtil.equals(this.millis, target)) return;
		
		// Apply
		this.millis = target;
		
		// Mark as changed
		this.changed();
	}
	
	// FIELD: MODERATORID
	public String getModeratorId() { return this.moderatorId; }
	public boolean hasModeratorId() { return this.moderatorId != null; }
	public void setModeratorId(String moderatorId)
	{
		// Clean input
		String target = moderatorId;
		
		// Detect Nochange
		if (MUtil.equals(this.moderatorId, target)) return;
		
		// Apply
		this.moderatorId = target;
		
		// Mark as changed
		this.changed();
	}
	
	// FIELD: COUNT
	public Map<Integer, Map<Integer, Integer>> getCount()
	{
		Map<Integer, Map<Integer, Integer>> ret = mapcopy(this.count);
		if (ret == null) ret = new LinkedHashMap<Integer, Map<Integer, Integer>>();
		return ret;
	}
	public boolean hasCount() { return this.count != null; }
	public void setCount(Map<Integer, Map<Integer, Integer>> count)
	{
		// Clean input
		Map<Integer, Map<Integer, Integer>> target = mapcopy(count);
		
		// Detect Nochange
		if (MUtil.equals(this.count, target)) return;
		
		// Apply
		this.count = target;
		
		// Mark as changed
		this.changed();
	}
	
	// This method creates a deep copy of a map.
	// The keys are assumed to be immutable.
	// It recursively removes entries where the value is null, 0 or an empty map.
	@SuppressWarnings("unchecked")
	private static <K, V> Map<K, V> mapcopy(Map<K, V> map)
	{
		if (map == null) return null;
		Map<K, V> ret = new LinkedHashMap<K, V>();
		for (Entry<K, V> entry : map.entrySet())
		{
			K key = entry.getKey();
			V value = entry.getValue();
			
			if (value instanceof Map<?, ?>) value = (V) mapcopy((Map<Object, Object>) value);
			if (value == null) continue;
			if (value instanceof Integer && ((Integer)value).equals(0)) continue;
			ret.put(key, value);
		}
		
		if (map.size() == 0) return null;
		return ret;
	}
	
	// -------------------------------------------- //
	// FIELDS: MEDIUM
	// -------------------------------------------- //
	
	// FIELD: MODERATORID
	public MPlayer getModerator()
	{
		return MPlayer.get(this.getModeratorId());
	}
	public void setModerator(SenderEntity<?> moderator)
	{
		this.setModeratorId(moderator == null ? null : moderator.getId());
	}
	public void setModerator(CommandSender moderator)
	{
		this.setModeratorId(SenderUtil.getSenderId(moderator));
	}
	
	// FIELD: COUNT
	public Map<Integer, Integer> getCount(int year)
	{
		Map<Integer, Integer> ret = this.getCount().get(year);
		if (ret == null) ret = new LinkedHashMap<Integer, Integer>();
		return ret;
	}
	public void setCount(int year, Map<Integer, Integer> count)
	{
		Map<Integer, Map<Integer, Integer>> target = this.getCount();
		target.put(year, count);
		this.setCount(target);
	}
	
	public int getCount(int year, int week)
	{
		Integer ret = this.getCount(year).get(week);
		if (ret == null) ret = 0;
		return ret;
	}
	public void setCount(int year, int week, int count)
	{
		Map<Integer, Integer> target = this.getCount(year);
		target.put(week, count);
		this.setCount(year, target);
	}
	
}
