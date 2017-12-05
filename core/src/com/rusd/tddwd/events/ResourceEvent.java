package com.rusd.tddwd.events;

public class ResourceEvent extends GameEvent {
	public enum ResourceType{
		WOOD,ROCKS,MONSTER_GUTS
	}
	
	public ResourceType type;

}
