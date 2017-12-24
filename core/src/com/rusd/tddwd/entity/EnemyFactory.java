package com.rusd.tddwd.entity;

import com.artemis.Archetype;
import com.artemis.ArchetypeBuilder;
import com.artemis.BaseSystem;
import com.artemis.ComponentMapper;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.rusd.tddwd.GlobalVaribles;
import com.rusd.tddwd.body.BodyFactory;
import com.rusd.tddwd.body.BodyOptions;
import com.rusd.tddwd.body.CollisionBits;
import com.rusd.tddwd.body.Shapes;
import com.rusd.tddwd.entity.components.DrawableComponent;
import com.rusd.tddwd.entity.components.Health;
import com.rusd.tddwd.entity.components.Inventory;
import com.rusd.tddwd.entity.components.Movable;
import com.rusd.tddwd.entity.components.Name;
import com.rusd.tddwd.entity.components.Physics;
import com.rusd.tddwd.entity.components.SoundComponent;

public class EnemyFactory extends BaseSystem {
	

	ComponentMapper<Inventory> inventoryMapper;
	ComponentMapper<Name> nameMapper;
	ComponentMapper<Health> healthMapper;
	ComponentMapper<DrawableComponent> drawMapper;
	ComponentMapper<Physics> physicsMapper;
	ComponentMapper<SoundComponent> soundMapper;
	
	
	public int createEnemy( Vector2 pos ) {
		
		int id = GlobalVaribles.artemisWorld.create(GlobalVaribles.archeTypes.resource);
		
		Name n = nameMapper.get(id);
		n.name ="ID:" + id+  "Type: tree";
		
		Sprite sprite = new Sprite(GlobalVaribles.gameAssets.get("mantis.png",Texture.class));
		sprite.setSize(2, 2);		
		
		DrawableComponent drawable = drawMapper.get(id);
		drawable.sprite = sprite;
		drawable.xOffset = -1;
		drawable.yOffset = -1;

		
		
		
		Health h= healthMapper.get(id);
		h.health = 1; 
		
//		ResourceInteracttion collidable = new ResourceInteracttion(inv,h);
//		ent.addPart(collidable);
		BodyOptions bo = new BodyOptions();
		bo.maskBits = CollisionBits.PROJECTILE | CollisionBits.PLAYER | CollisionBits.ENEMY | CollisionBits.RESOURCE;
		bo.categoryBits = CollisionBits.ENEMY;
		bo.pos = pos;
		
		
		Fixture fixture = BodyFactory.createBody(bo);
		
		Physics p = physicsMapper.get(id);
		p.fixture = fixture;
		
		fixture.setUserData(id);
		
		return id;	

//		
//		Health h = new Health();
//		h.health = 1;
//		ent.addPart(h);
//		
//		Movable m = new Movable();
//		m.speed = 900;
//		ent.addPart(m);		
//		bo.maskBits = CollisionBits.PROJECTILE | CollisionBits.PLAYER | CollisionBits.ENEMY | CollisionBits.RESOURCE;
//		bo.categoryBits = CollisionBits.ENEMY;
//		
//		Fixture fixture = BodyFactory.createBody(bo);
//		ent.fixture = fixture;		
//		
//		fixture.setUserData(ent);
//		
//		return ent;	
		
		
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
