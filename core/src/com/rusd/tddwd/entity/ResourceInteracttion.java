package com.rusd.tddwd.entity;

import com.badlogic.gdx.Gdx;
import com.rusd.tddwd.body.CollisionBits;
import com.rusd.tddwd.entity.components.Collidable;
import com.rusd.tddwd.entity.components.Health;
import com.rusd.tddwd.entity.components.Interactable;
import com.rusd.tddwd.entity.components.Inventory;
import com.rusd.tddwd.entity.components.Tool;
import com.rusd.tddwd.events.EventHandler;
import com.rusd.tddwd.events.GameEvent;
import com.rusd.tddwd.events.ResourceEvent;
import com.rusd.tddwd.events.ResourceEvent.ResourceType;

public class ResourceInteracttion {
	
	
	public Inventory inventory;
	public Health health;
	
	
//	public ResourceInteracttion(Inventory inventory, Health health) {
//		this.health = health;
//		this.inventory = inventory;
//		
//	}
//	
//	@Override
//	public void interact(Entity e) {
//		
//		Tool t = e.getPart(Tool.class);
//		mineResources(t.damage);
//		
//	}
	
	
//	public void mineResources(int i) {
//		if(inventory.rocks > 0) {
//			Gdx.app.log("Resource", "Got Rocks");
//			ResourceEvent re = new ResourceEvent();			
//			re.type = ResourceType.ROCKS;
//			re.value = i;
//			EventHandler.publish(re);
//			inventory.rocks -= i;			
//		}
//		
//		if(inventory.wood> 0) {
//			Gdx.app.log("Resource", "Got Wood");
//			ResourceEvent re = new ResourceEvent();			
//			re.type = ResourceType.WOOD;
//			re.value = i;
//			EventHandler.publish(re);
//			inventory.wood --;			
//		}
//		
//		if(inventory.wood+inventory.monsterGuts +inventory.rocks <=0 ) {			
//			Gdx.app.log("What", "Hmm");
//			this.health.health = 0;
//		}
//	}

}
