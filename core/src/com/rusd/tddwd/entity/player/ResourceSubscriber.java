package com.rusd.tddwd.entity.player;

import com.rusd.tddwd.entity.parts.Inventory;
import com.rusd.tddwd.events.GameEvent;
import com.rusd.tddwd.events.ResourceEvent;
import com.rusd.tddwd.events.Subscriber;

public class ResourceSubscriber implements Subscriber {
	
	Inventory inventory;
	
	public ResourceSubscriber(Inventory inventory) {
		this.inventory = inventory;
	}
	
	
	@Override
	public void handle(GameEvent e) {
		
		if(e instanceof ResourceEvent) {
			ResourceEvent re = (ResourceEvent) e;
			
			switch (re.type) {
				case MONSTER_GUTS:
					inventory.monsterGuts += re.value;
					break;
				case ROCKS:
					inventory.rocks += re.value;
					break;
				case WOOD:
					inventory.wood += re.value;
					break;
				default:
					break;			
			}
			
		}
		
		// TODO Auto-generated method stub
		
	}

}
