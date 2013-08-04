package com.massivecraft.massivetickets.entity;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.massivecraft.massivetickets.MassiveTickets;
import com.massivecraft.mcore.store.SenderEntity;
import com.massivecraft.mcore.util.MUtil;
import com.massivecraft.mcore.util.SenderUtil;
import com.massivecraft.mcore.util.Txt;

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
		this.setMessage(that.getMessage());
		this.setMillis(that.getMillis());
		this.setModeratorId(that.getModeratorId());
		this.setWorking(that.isWorking());
		this.setCount(that.getCount());
				
		return this;
	}
	
	@Override
	public boolean isDefault()
	{
		if (this.hasMessage()) return false;
		if (this.hasMillis()) return false;
		if (this.hasModeratorId()) return false;
		if (this.isWorking()) return false;
		if (this.hasCount()) return false;	
		
		return true;
	}
	
	// -------------------------------------------- //
	// FIELDS: DECLARE
	// -------------------------------------------- //
	
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
	
	// Is this player a working moderator?
	// The default is false. One must opt in for game management.
	// To save database space we use null for the default value.
	private Boolean working = null;
	
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
	
	// -------------------------------------------- //
	// FIELDS: HIGH
	// -------------------------------------------- //
	
	/**
	 * @return The oneliner to be used in the ticket list
	 */
	public String getListLine()
	{
		StringBuilder builder = new StringBuilder();
		
		if (this.hasModeratorId())
		{
			builder.append(ChatColor.GREEN.toString());
		}
		else
		{
			builder.append(ChatColor.RED.toString());
		}
		
		builder.append("# ");
		
		builder.append(this.getDisplayName());
		
		if (this.hasMessage())
		{
			builder.append(Txt.parse(" <pink>"));
			builder.append(this.getMessage());
		}
		else
		{
			builder.append(Txt.parse(" <silver><em>no message error"));
		}
		
		String ret = builder.toString();
		
		// Avoid line wraps!
		List<String> wrapped = Txt.wrap(ret);
		if (wrapped.size() > 1)
		{
			ret = ret.substring(0, ret.length()-3);
			ret += Txt.parse("<silver>...");
		}
		
		return ret;
	}
	
	// -------------------------------------------- //
	// MARK AS DONE
	// -------------------------------------------- //
	// The cause is the player closing the ticket. Null means logout is the cause.
	
	public void markAsDone(MPlayer cause)
	{
		// What was done-marked and why? Dat inform!
		if (cause == null)
		{
			MassiveTickets.alertMsg("<white>%s<pink>'s ticket done-marked on logout:", this.getDisplayName());
		}
		else if (cause == this)
		{
			MassiveTickets.alertMsg("<white>%s<pink> done-marked their own ticket:", cause.getDisplayName());
			MassiveTickets.alertMsg(cause.getId(), "Thank you for marking your own ticket as done.");
			MassiveTickets.alertMsg(cause.getId(), "Have a nice day!");
		}
		else
		{
			MassiveTickets.alertMsg("<white>%s<pink> done-marked <white>%s<pink>'s ticket:", cause.getDisplayName(), this.getDisplayName());
			MassiveTickets.alertMsg(cause.getId(), "<white>%s<pink> marked your ticket as done.", cause.getDisplayName());
			MassiveTickets.alertMsg(cause.getId(), "Have a nice day!");
		}
		
		// Inform on what the message was
		MassiveTickets.alertMessage(this.getMessage());
		
		// So who should receive the point?
		// probably the moderator
		MPlayer receiver = this.getModerator();
		// but the cause if there were no moderator
		if (receiver == null) receiver = cause;
		// however the reward can never go to the ticket creator itself
		if (this == receiver) receiver = null;
		
		// Modify Highscore
		if (receiver != null)
		{
			MassiveTickets.alertMsg("<white>%s<pink> got a point.", receiver.getDisplayName());
			
			int year = MassiveTickets.getCurrentYear();
			int week = MassiveTickets.getCurrentWeek();
			
			int count = this.getCount(year, week);
			count++;
			this.setCount(year, week, count);
		}
		
		// Apply
		this.setMessage(null);
		this.setMillis(null);
		this.setModeratorId(null);
	}
	
}
