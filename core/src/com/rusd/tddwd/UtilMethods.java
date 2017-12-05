package com.rusd.tddwd;

import com.rusd.tddwd.body.CollisionBits;
import com.rusd.tddwd.entity.Entity;
import com.rusd.tddwd.entity.parts.Health;
import com.rusd.tddwd.events.EventHandler;
import com.rusd.tddwd.events.ResourceEvent;
import com.rusd.tddwd.events.ResourceEvent.ResourceType;

public class UtilMethods {
	
	public static boolean cleanseList(Entity e){
		Health h = e.getPart(Health.class);
		boolean alive = h.alive();			
		if(alive== false) {
			if(e.fixture.getFilterData().categoryBits == CollisionBits.ENEMY) {
				ResourceEvent re = new ResourceEvent();
				re.value = 1; 
				re.type  = ResourceType.MONSTER_GUTS;
				EventHandler.publish(re);				
			}
			
			GlobalVaribles.world.destroyBody(e.getBody());
		}
		return alive;		
	}

}
