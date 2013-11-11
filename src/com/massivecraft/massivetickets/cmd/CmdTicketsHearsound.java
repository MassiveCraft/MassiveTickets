package com.massivecraft.massivetickets.cmd;

import java.util.Arrays;
import java.util.List;

import com.massivecraft.massivetickets.Perm;
import com.massivecraft.massivetickets.SoundEffect;
import com.massivecraft.mcore.cmd.VisibilityMode;
import com.massivecraft.mcore.cmd.req.ReqHasPerm;
import com.massivecraft.mcore.cmd.req.ReqIsPlayer;

public class CmdTicketsHearsound extends MassiveTicketsCommand
{
	public CmdTicketsHearsound()
	{
		this.addRequiredArg("sound(s)");
		
		this.setErrorOnToManyArgs(false);
		
		this.addRequirements(ReqHasPerm.get(Perm.HEARSOUND.node));
		this.addRequirements(ReqIsPlayer.get());
		
		// Yup this one is invisible in order to not confuse people.
		this.setVisibilityMode(VisibilityMode.INVISIBLE);
	}
	
	@Override
	public void perform()
	{
		// Args
		String soundsString = this.argConcatFrom(0);
		soundsString = soundsString.trim();
		
		List<String> soundStrings = Arrays.asList(soundsString.split("\\s+"));
		for (String soundString : soundStrings)
		{
			try
			{
				SoundEffect soundEffect = SoundEffect.valueOf(soundString);
				soundEffect.run(me);
			}
			catch (Exception e)
			{
				msg("<b>Hearsound Error: %s", e.getMessage());
			}
		}
		
	}
}
