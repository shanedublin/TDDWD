package com.rusd.tddwd.body;

import com.artemis.BaseSystem;
import com.artemis.ComponentMapper;
import com.badlogic.gdx.Gdx;
import com.rusd.tddwd.entity.components.Damage;
import com.rusd.tddwd.entity.components.Health;
import com.rusd.tddwd.entity.components.Inventory;
import com.rusd.tddwd.entity.components.Item;
import com.rusd.tddwd.entity.components.Physics;
import com.rusd.tddwd.entity.components.Player;
import com.rusd.tddwd.systems.BasicSystem;

public class ProjectileContactSystem extends BasicSystem {
	
	
	
	ComponentMapper<Item> itemMapper;
	ComponentMapper<Inventory> inventoryMapper;
	ComponentMapper<Health> healthMapper;
	ComponentMapper<Player> playerMapper;
	ComponentMapper<Physics> pyhsicsMapper;
	ComponentMapper<Damage> damageMapper;
	
	
	public void handleContact(int a , int b) {
		
		
		checkCollision(a, b);
		checkCollision(b, a);
	
	}
	
	private void checkCollision(int a, int b) {
		Physics p1 = pyhsicsMapper.get(a);
		
		if(p1.fixture.getFilterData().categoryBits == CollisionBits.PROJECTILE) {
			Health h = healthMapper.get(a);
			h.health = 0;
			
			if(damageMapper.has(a)) {
				Health h2 = healthMapper.get(b);
				Damage d = damageMapper.get(a);
				h2.health -= d.amount;				
			}
					
		}
	}

	
	
	

}
