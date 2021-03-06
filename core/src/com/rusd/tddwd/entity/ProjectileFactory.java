package com.rusd.tddwd.entity;

import com.artemis.BaseSystem;
import com.artemis.ComponentMapper;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.rusd.tddwd.GlobalVaribles;
import com.rusd.tddwd.body.BodyFactory;
import com.rusd.tddwd.body.BodyOptions;
import com.rusd.tddwd.body.CollisionBits;
import com.rusd.tddwd.body.GameContactListener;
import com.rusd.tddwd.body.Shapes;
import com.rusd.tddwd.entity.components.Damage;
import com.rusd.tddwd.entity.components.DrawableComponent;
import com.rusd.tddwd.entity.components.Health;
import com.rusd.tddwd.entity.components.Inventory;
import com.rusd.tddwd.entity.components.Movable;
import com.rusd.tddwd.entity.components.Name;
import com.rusd.tddwd.entity.components.Physics;
import com.rusd.tddwd.entity.components.SoundComponent;
import com.rusd.tddwd.entity.components.Stats;

public class ProjectileFactory extends BaseSystem {

	
	ComponentMapper<Inventory> inventoryMapper;
	ComponentMapper<Name> nameMapper;
	ComponentMapper<Health> healthMapper;
	ComponentMapper<DrawableComponent> drawMapper;
	ComponentMapper<Physics> physicsMapper;
	ComponentMapper<SoundComponent> soundMapper;
	ComponentMapper<Damage> damageMapper;
	ComponentMapper<Stats> statsMapper;


	public int createProjectile(BodyOptions bo) {
	int id = GlobalVaribles.artemisWorld.create(GlobalVaribles.archeTypes.projectile);
		
		Name n = nameMapper.get(id);
		n.name ="ID:" + id+  "Type: projectile";
		
		Sprite sprite = GlobalVaribles.gameAssets.getSprite("laser");
		sprite.setSize(1, 1);
		sprite.setOriginCenter();
//		sprite.setOrigin(1, 1);
				
		
		DrawableComponent drawable = drawMapper.get(id);
		drawable.sprite = sprite;

		Stats stats = statsMapper.get(id);
		stats.speed = 50;
		
		Damage damage = damageMapper.get(id);
		damage.amount = 1;
		
		SoundComponent s = soundMapper.get(id);
		s.sound = GlobalVaribles.gameAssets.get("bfxr/laser.wav",Sound.class);
		
		s.sound.play();
		
		drawable.yOffset = -.5f;
		drawable.xOffset = -.5f;
				
//		ResourceInteracttion collidable = new ResourceInteracttion(inv,h);
//		ent.addPart(collidable);
		
		bo.maskBits = CollisionBits.RESOURCE  | CollisionBits.ENEMY ;	
		bo.categoryBits = CollisionBits.PROJECTILE;
		bo.size = .5f;
		
		bo.bodyType = BodyType.DynamicBody;
		bo.isSensor = true;
		bo.shape = Shapes.SQUARE;
		
		
		Fixture fixture = BodyFactory.createBody(bo);
		
		Physics p = physicsMapper.get(id);
		p.fixture = fixture;
		
		fixture.setUserData(id);
		
		return id;	
	}


	@Override
	protected void processSystem() {
	}
	
	@Override
	protected boolean checkProcessing() {
		return false;
	}
}

