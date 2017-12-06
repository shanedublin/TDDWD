package com.rusd.tddwd.entity.parts;

import com.badlogic.gdx.math.MathUtils;
import com.rusd.tddwd.entity.Entity;

public class Buildable implements Interactable {

	public int wood;
	public int woodCost;
	public int rock;
	public int rockCost;
	public int monsterGut;
	public int monsterGutCost;
	public Health health;
	
	
	public Buildable(int wood, int rock, int monsterGuts, Health health) {
		this.woodCost = wood;
		this.rockCost =rock;
		this.monsterGutCost = monsterGuts;
		this.health = health;
		
	}
	
	
	@Override
	public void interact(Entity e) {
		Inventory i = e.getPart(Inventory.class);
		Tool t = e.getPart(Tool.class);
		
		int damage = t.damage;
		
		int woodRemaining = woodCost - wood;
		int min = Math.min(woodRemaining, Math.min(damage, i.wood));
		wood += min;		
		i.wood -= min;
		damage -= min;
		
		int rockRemaining = rockCost - rock;
		min = Math.min(rockRemaining, Math.min(damage, i.rocks));
		rock += min;	
		i.rocks -= min;
		damage -= min;
		
		int gutsRemaining = monsterGutCost - monsterGut;
		min = Math.min(gutsRemaining, Math.min(damage, i.monsterGuts));
		monsterGut += min;
		i.monsterGuts -=min;
		damage -= min;
		
		health.health =  (wood + rock + monsterGut) * health.maxHealth / (woodCost + rockCost + monsterGutCost);
		

	}
	
	public int buildPercentage() {
		return (wood + rock + monsterGut) * 100 / (woodCost + rockCost + monsterGutCost);
	}
	

}
