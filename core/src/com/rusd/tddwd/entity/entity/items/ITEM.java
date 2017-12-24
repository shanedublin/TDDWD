package com.rusd.tddwd.entity.entity.items;

public enum ITEM {
	WOOD("Wood",100,"item-log"),
	ROCKS("Rocks",50,"item-rocks"),
	MONSTER_GUTS("Monster Guts", Integer.MAX_VALUE,"monster-guts");
	
	public final String name;
	public final int stack;
	public final String texture;
	
	
	ITEM(String name, int stack,String texture){
		this.name = name;
		this.stack = stack;
		this.texture = texture;
		
		
	}

}
