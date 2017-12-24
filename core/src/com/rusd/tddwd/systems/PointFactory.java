package com.rusd.tddwd.systems;

import com.artemis.BaseSystem;
import com.artemis.ComponentMapper;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
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
import com.rusd.tddwd.entity.components.SoundComponent;

public class PointFactory  extends BaseSystem {
	
	

	ComponentMapper<Inventory> inventoryMapper;
	ComponentMapper<Name> nameMapper;
	ComponentMapper<Health> healthMapper;
	ComponentMapper<DrawableComponent> drawMapper;
	ComponentMapper<Physics> physicsMapper;
	ComponentMapper<SoundComponent> soundMapper;
	
	
	public int create(Vector2 v) {
		
		int id = GlobalVaribles.artemisWorld.create(GlobalVaribles.archeTypes.point);
		

		Health h= healthMapper.get(id);
		h.health = Integer.MAX_VALUE; 

		BodyOptions bo = new BodyOptions();
		bo.maskBits = CollisionBits.RESOURCE;	
		bo.categoryBits = CollisionBits.POINT;
		bo.size = 0;
		bo.bodyType = BodyType.StaticBody;
		bo.isSensor = false;
		bo.shape = Shapes.CIRCLE;
		
		
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
