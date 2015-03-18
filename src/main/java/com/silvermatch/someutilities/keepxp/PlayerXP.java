package com.silvermatch.someutilities.keepxp;

import java.util.UUID;

public class PlayerXP {
	private UUID PlayerID;
	private float experience;
	private int experienceLevel;
	private int experienceTotal;
	
	
	public PlayerXP(UUID playerID, float experience, int experienceLevel, int experienceTotal) {
		PlayerID = playerID;
		this.experience = experience;
		this.experienceLevel = experienceLevel;
		this.experienceTotal = experienceTotal;
	}
	public UUID getPlayerID() {
		return PlayerID;
	}
	public float getExperience() {
		return experience;
	}
	public int getExperienceLevel() {
		return experienceLevel;
	}
	public int getExperienceTotal() {
		return experienceTotal;
	}
		
}
