package com.massivecraft.massivetickets.entity;

import static com.massivecraft.massivecore.mson.Mson.mson;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.massivecraft.massivecore.mson.Mson;
import com.massivecraft.massivecore.store.SenderEntity;
import com.massivecraft.massivecore.util.IdUtil;
import com.massivecraft.massivecore.util.MUtil;
import com.massivecraft.massivecore.util.Txt;
import com.massivecraft.massivetickets.MassiveTickets;
import com.massivecraft.massivetickets.cmd.CmdTickets;

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
		this.setTotalCount(that.getTotalCount());
				
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

	private int totalCount = 0;
	
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
		this.setModeratorId(IdUtil.getId(moderator));
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

	// FIELD: TOTALCOUNT
	public int getTotalCount()
	{
		return this.totalCount;
	}

	public void setTotalCount(int totalCount)
	{
		this.totalCount = totalCount;
		
		// Mark as changed
		this.changed();
	}
	
	// -------------------------------------------- //
	// FIELDS: HIGH
	// -------------------------------------------- //
	
	/**
	 * @return The oneliner to be used in the ticket list
	 */
	public Mson getListLine(Object watcherObject)
	{
		return mson(
			"#",
			Mson.SPACE,
			this.getDisplayNameMson(watcherObject),
			Mson.SPACE,
			this.getListLineExcerpt(watcherObject)
		)
		.command(CmdTickets.get().cmdTicketsShow, this.getName())
		.color(this.hasModeratorId() ? ChatColor.GREEN : ChatColor.RED);
	}
	
	public Mson getListLineExcerpt(Object watcherObject)
	{
		String message = this.getMessage();
		if (message == null) return mson("no message error").italic(true).color(ChatColor.GRAY);
		String tooltip = Txt.parse("<pink>%s", message);
		if (message.length() > MConf.get().getExcerptLength())
		{
			message = message.substring(0, MConf.get().getExcerptLength());
		}
		return mson(message).color(ChatColor.LIGHT_PURPLE).tooltip(tooltip);
	}
	
	// -------------------------------------------- //
	// MARK AS DONE
	// -------------------------------------------- //
	// The cause is the player closing the ticket. Null means logout is the cause.
	
	public void markAsDone(MPlayer cause)
	{
		// So who should receive the point?
		// probably the moderator
		MPlayer receiver = this.getModerator();
		// but the cause if there were no moderator
		if (receiver == null) receiver = cause;
		// however the reward can never go to the ticket creator itself
		if (this == receiver) receiver = null;
		
		// Done Inform!
		Set<String> moderatorAlerteeIds = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
		if (receiver != null)
		{
			moderatorAlerteeIds.add(receiver.getId());
		}
		if (cause != null)
		{
			moderatorAlerteeIds.add(cause.getId());
		}
		moderatorAlerteeIds.remove(this.getId());
		
		if (cause == null)
		{
			for (String moderatorAlerteeId : moderatorAlerteeIds)
			{
				MassiveTickets.alertOneMsg(moderatorAlerteeId, "<white>%s<pink>'s ticket done-marked on logout.", this.getDisplayName(moderatorAlerteeId));
			}
		}
		else if (cause == this)
		{
			for (String moderatorAlerteeId : moderatorAlerteeIds)
			{
				MassiveTickets.alertOneMsg(moderatorAlerteeId, "<white>%s<pink> done-marked their own ticket.", cause.getDisplayName(moderatorAlerteeId));
			}
			
			MassiveTickets.alertOneMsg(this.getId(), "Thank you for marking your own ticket as done.");
			MassiveTickets.alertOneMsg(this.getId(), "Have a nice day!");
		}
		else
		{
			for (String moderatorAlerteeId : moderatorAlerteeIds)
			{
				MassiveTickets.alertOneMsg(moderatorAlerteeId, "<white>%s<pink> done-marked <white>%s<pink>'s ticket.", cause.getDisplayName(moderatorAlerteeId), this.getDisplayName(moderatorAlerteeId));
			}
			
			MassiveTickets.alertOneMsg(this.getId(), "<white>%s<pink> marked your ticket as done.", cause.getDisplayName(this.getId()));
			MassiveTickets.alertOneMsg(this.getId(), "Have a nice day!");
		}
		
		// Highscore, Point and Level time! \:3/ LEL
		if (receiver != null)
		{
			receiver.givePoint(this.getId());
		}
		
		// Apply
		this.setMessage(null);
		this.setMillis(null);
		this.setModeratorId(null);
	}
	
	// -------------------------------------------- //
	// GIVE POINT
	// -------------------------------------------- //
	
	public void givePoint(String playerId)
	{
		int year = MassiveTickets.getCurrentYear();
		int week = MassiveTickets.getCurrentWeek();
		
		// Weekly count
		int countBefore = this.getCount(year, week);
		int countAfter = countBefore + 1;
		this.setCount(year, week, countAfter);
		
		// Total count
		int totalCountAfter = this.getTotalCount() + 1;
		this.setTotalCount(totalCountAfter);
		
		// Calculate
		int totalMax = MConf.get().ticketsPerReward;
		int remainder = totalCountAfter % totalMax;
		int leftOver = totalMax - remainder;
		int percent = (int) MUtil.probabilityRound(((double)remainder/totalMax) * 100.0);
		
		String progress = "<h>%s<gray>/<h>%s <gray>(<lime>%s<gray>) | ";
		
		this.msg(Txt.titleize("MassiveTickets | Counter"));
		
		if (remainder == 0)
		{
			this.msg(progress + "<g>You will receive a reward!", totalMax, totalMax, 100 + "%");
			String name = MConf.get().getRandomReward();
			MassiveTickets.alertModeratorsMsg("<white>%s<pink> has done <aqua>%d <pink>tickets!", this.getDisplayName(null), countAfter);
			MassiveTickets.alertModeratorsMsg("<pink>Enjoy your <aqua>%s <white>%s<pink>!", name, this.getDisplayName(null));
			
			MConf.get().getRewardReaction(name).run(this.getId(), playerId);
			MConf.get().getDoneReactionLevel().run(this.getId(), playerId);
		}
		else
		{
			String pluralS = leftOver > 1 ? "s" : "";
			this.msg(progress + "<i>Only %s ticket%s left till your next reward!", remainder, totalMax, percent + "%", leftOver, pluralS);
			
			MConf.get().getDoneReactionNormal().run(this.getId(), playerId);
		}
	}
	
}
