package com.rusd.tddwd.systems;

import com.artemis.BaseSystem;
import com.artemis.ComponentMapper;
import com.badlogic.gdx.Gdx;
import com.rusd.tddwd.entity.components.Health;
import com.rusd.tddwd.entity.components.Interactable;
import com.rusd.tddwd.entity.components.Inventory;
import com.rusd.tddwd.entity.components.Item;
import com.rusd.tddwd.entity.components.Name;
import com.rusd.tddwd.entity.components.Player;
import com.rusd.tddwd.entity.components.Point;

public class PointContactSystem extends BaseSystem {

	

	PointFactory pointFactory;
	
	ComponentMapper<Item> itemMapper;
	ComponentMapper<Inventory> inventoryMapper;
	ComponentMapper<Health> healthMapper;
	ComponentMapper<Player> playerMapper;
	ComponentMapper<Point> pointMapper;
	ComponentMapper<Interactable> interactableMapper;
	ComponentMapper<Name> nameMapper;

	
	public void handleContact(int a , int b) {
		if(pointMapper.has(a)){
			if(interactableMapper.has(b)) {
				interact(a, b);
			}
		}
		if(pointMapper.has(b)) {
			if(interactableMapper.has(b)) {
				interact(b, a);				
			}			
		}
	}
	
	public void interact(int point, int interactable) {
		Health h = healthMapper.get(point);
		h.health = 0;
		
		Name n = nameMapper.get(interactable);
		Gdx.app.log("You selected", n.name);
		
	}
	
	
	@Override
	protected void processSystem() {		
	}
	
	@Override
	protected boolean checkProcessing() {
		return false;
	}

}
