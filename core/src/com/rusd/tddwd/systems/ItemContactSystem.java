package com.rusd.tddwd.systems;

import com.artemis.BaseSystem;
import com.artemis.ComponentMapper;
import com.badlogic.gdx.Gdx;
import com.rusd.tddwd.entity.components.Health;
import com.rusd.tddwd.entity.components.Inventory;
import com.rusd.tddwd.entity.components.Item;
import com.rusd.tddwd.entity.components.Player;

public class ItemContactSystem extends BaseSystem {

	
	ComponentMapper<Item> itemMapper;
	ComponentMapper<Inventory> inventoryMapper;
	ComponentMapper<Health> healthMapper;
	ComponentMapper<Player> playerMapper;
	
	
	
	
	public void handleContact(int a , int b) {
		if(itemMapper.has(a)){			
			if(playerMapper.has(b)) {
				depositItem(a, b);
				
			}			
		}
		if(itemMapper.has(b)) {			
			if(playerMapper.has(a)) {
				depositItem(b, a);
			}
		}
	}
	
	/**
	 * Assumes that the checks have already been made
	 * @param item
	 * @param inventory
	 */
	private void depositItem(int item, int inventory) {
		Item i = itemMapper.get(item);
		Inventory inv = inventoryMapper.get(inventory);
		switch (i.type) {
		case MONSTER_GUTS:
			break;
		case ROCKS:
			break;
		case WOOD:
			inv.rocks++;
			killEntity(item);
			break;
		default:
			break;		
		}
	}
	public void killEntity(int e) {
		Health h = healthMapper.get(e);
		h.health = 0;
	}
	
	@Override
	protected void processSystem() {		
	}
	
	@Override
	protected boolean checkProcessing() {
		return false;
	}

}
