package com.rusd.tddwd.entity;

import com.rusd.tddwd.entity.components.Health;

public class ProjectileCollision  {
	
	
	public int damage;
	public Health health;
	
	public ProjectileCollision(int damage, Health health) {
		this.damage = damage;
		this.health = health;
	}


}
