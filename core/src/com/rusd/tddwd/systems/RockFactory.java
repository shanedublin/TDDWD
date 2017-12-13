package com.rusd.tddwd.systems;

import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;

import javax.xml.soap.Text;

import com.artemis.BaseSystem;
import com.artemis.ComponentMapper;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.rusd.tddwd.GlobalVaribles;
import com.rusd.tddwd.body.BodyFactory;
import com.rusd.tddwd.body.BodyOptions;
import com.rusd.tddwd.body.CollisionBits;
import com.rusd.tddwd.body.Shapes;
import com.rusd.tddwd.entity.components.DrawableComponent;
import com.rusd.tddwd.entity.components.Health;
import com.rusd.tddwd.entity.components.Inventory;
import com.rusd.tddwd.entity.components.Name;
import com.rusd.tddwd.entity.components.Physics;

public class RockFactory extends BaseSystem {
	
	ComponentMapper<Inventory> inventoryMapper;
	ComponentMapper<Name> nameMapper;
	ComponentMapper<Health> healthMapper;
	ComponentMapper<DrawableComponent> drawMapper;
	ComponentMapper<Physics> physicsMapper;
	
	
	public  int create( BodyOptions bo) {
		int id = GlobalVaribles.artemisWorld.create(GlobalVaribles.archeTypes.resource);
		
		Inventory i = inventoryMapper.get(id);
		i.wood = 10;
		Name n = nameMapper.get(id);
		n.name ="ID:" + id+  "Type: rock";
		
		Sprite sprite = new Sprite(GlobalVaribles.gameAssets.get("rocks.png",Texture.class));
		sprite.setSize(4, 4);		
		
		DrawableComponent drawable = drawMapper.get(id);
		drawable.sprite = sprite;
		
		
		Health h= healthMapper.get(id);
		h.health = Integer.MAX_VALUE; 
		
//		ResourceInteracttion collidable = new ResourceInteracttion(inv,h);
//		ent.addPart(collidable);
		
		bo.maskBits = CollisionBits.RESOURCE | CollisionBits.PLAYER | CollisionBits.ENEMY | CollisionBits.PROJECTILE;	
		bo.categoryBits = CollisionBits.RESOURCE;
		bo.size = 2;
		bo.bodyType = BodyType.StaticBody;
		bo.isSensor = false;
		bo.shape = Shapes.SQUARE;
		
		
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
