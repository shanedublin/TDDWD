package com.rusd.tddwd.entity;

import com.badlogic.gdx.Gdx;
import com.rusd.tddwd.body.CollisionBits;
import com.rusd.tddwd.entity.parts.Collidable;
import com.rusd.tddwd.entity.parts.Health;
import com.rusd.tddwd.entity.parts.Inventory;
import com.rusd.tddwd.events.EventHandler;
import com.rusd.tddwd.events.GameEvent;
import com.rusd.tddwd.events.ResourceEvent;
import com.rusd.tddwd.events.ResourceEvent.ResourceType;

public class ResourceCollision implements Collidable {
	
	
	public Inventory inventory;
	public Health health;
	
	
	public ResourceCollision(Inventory inventory, Health health) {
		this.health = health;
		this.inventory = inventory;
		
	}
	

	@Override
	public void collides(Entity e) {
		
		if(e.fixture.getFilterData().categoryBits == CollisionBits.PROJECTILE) {
			mineResources(1);
		
		}
		
	}
	
	public void mineResources(int i) {
		if(inventory.rocks > 0) {
			Gdx.app.log("Resource", "Got Rocks");
			ResourceEvent re = new ResourceEvent();			
			re.type = ResourceType.ROCKS;
			re.value = i;
			EventHandler.publish(re);
			inventory.rocks -= i;			
		}
		
		if(inventory.wood> 0) {
			Gdx.app.log("Resource", "Got Wood");
			ResourceEvent re = new ResourceEvent();			
			re.type = ResourceType.WOOD;
			re.value = i;
			EventHandler.publish(re);
			inventory.wood --;			
		}
		
		if(inventory.wood+inventory.monsterGuts +inventory.rocks <=0 ) {			
			Gdx.app.log("What", "Hmm");
			this.health.health = 0;
		}
	}

}