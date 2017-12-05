package com.rusd.tddwd.entity;

import java.util.Optional;

import com.badlogic.gdx.Gdx;
import com.rusd.tddwd.entity.parts.Collidable;
import com.rusd.tddwd.entity.parts.Health;

public class ProjectileCollision implements Collidable {
	
	
	public int damage;
	public Health health;
	
	public ProjectileCollision(int damage, Health health) {
		this.damage = damage;
		this.health = health;
	}

	@Override
	public void collides(Entity e) {
		Optional<Health> oHealth = Optional.ofNullable(e.getPart(Health.class));
//		Gdx.app.log("Collides", "Projectile");
		oHealth.ifPresent(h -> {
			if(health.alive())
				h.damage(damage);
		});
		this.health.health = 0;
	}

}
