package com.silvermatch.someutilities.keepxp;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.silvermatch.someutilities.utility.Log;

public class KeepXP {
	
	private List<PlayerXP> deadPlayers;
	
	public KeepXP()
	{
		deadPlayers = new ArrayList<PlayerXP>();
	}
	
	public void addDeadPlayer(PlayerXP ep)
	{
		deadPlayers.add(ep);
		Log.info("Dead player added");
	}
	public PlayerXP getDeadPlayer(UUID playerUUID)
	{
		for (PlayerXP playerXP : deadPlayers)
		{
			if  (playerXP.getPlayerID() == playerUUID)
				deadPlayers.remove(playerXP);
				return playerXP;
		}
		return null;	
	}
	
}
