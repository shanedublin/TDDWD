package com.rusd.tddwd.entity.entity.items;

import com.artemis.BaseSystem;
import com.artemis.ComponentMapper;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.rusd.tddwd.GlobalVaribles;
import com.rusd.tddwd.body.BodyFactory;
import com.rusd.tddwd.body.BodyOptions;
import com.rusd.tddwd.body.CollisionBits;
import com.rusd.tddwd.entity.components.DrawableComponent;
import com.rusd.tddwd.entity.components.Health;
import com.rusd.tddwd.entity.components.Inventory;
import com.rusd.tddwd.entity.components.Item;
import com.rusd.tddwd.entity.components.Name;
import com.rusd.tddwd.entity.components.Physics;

public class ItemFactory extends BaseSystem {
	
	ComponentMapper<Inventory> inventoryMapper;
	ComponentMapper<Name> nameMapper;
	ComponentMapper<Health> healthMapper;
	ComponentMapper<DrawableComponent> drawMapper;
	ComponentMapper<Physics> physicsMapper;
	ComponentMapper<Item> itemMapper;
	
	public int CreateItem(ITEM item, Vector2 pos) {
		

		int id = GlobalVaribles.artemisWorld.create(GlobalVaribles.archeTypes.item);
		
		
		Name n = nameMapper.get(id);
		n.name ="Item:" + id+  item.name;		
		
		Item i = itemMapper.get(id);
		i.type = item;
		
		
		Health h= healthMapper.get(id);
		h.health = Integer.MAX_VALUE;
		
		
		DrawableComponent drawable = drawMapper.get(id);
//		Sprite sprite = new Sprite(GlobalVaribles.gameAssets.playerAnimation.getKeyFrame(drawable.animTime));
		Sprite sprite = GlobalVaribles.gameAssets.getSprite(item.texture);
		sprite.setSize(1, 1);		
		drawable.sprite = sprite;
		drawable.xOffset = -.5f;
		drawable.yOffset = -.5f;
		
		
//		drawable.animation = GlobalVaribles.gameAssets.playerAnimation;
		
		
		
//		ResourceInteracttion collidable = new ResourceInteracttion(inv,h);
//		ent.addPart(collidable);
		BodyOptions bo = new BodyOptions();
		bo.pos = pos;
		bo.density = 1;
		bo.size = 0.50f;
		bo.maskBits = CollisionBits.PLAYER | CollisionBits.RESOURCE;
		bo.categoryBits = CollisionBits.ITEM;
		
		
		Fixture fixture = BodyFactory.createBody(bo);
		
		Physics p = physicsMapper.get(id);
		p.fixture = fixture;
		
		fixture.setUserData(id);
		
		return id;	
	}

	@Override
	protected void processSystem() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected boolean checkProcessing() {
		return false;
	}

}
