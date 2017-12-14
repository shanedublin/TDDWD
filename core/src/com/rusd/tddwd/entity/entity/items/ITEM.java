package com.rusd.tddwd.entity.entity.items;

public enum ITEM {
	WOOD("Wood",100,"item-log.png"),
	ROCKS("Rocks",50,"item-rocks.png"),
	MONSTER_GUTS("Monster Guts", Integer.MAX_VALUE,"monster-guts.png");
	
	public final String name;
	public final int stack;
	public final String texture;
	
	
	ITEM(String name, int stack,String texture){
		this.name = name;
		this.stack = stack;
		this.texture = texture;
		
		
	}

}
