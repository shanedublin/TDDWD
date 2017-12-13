package com.rusd.tddwd.entity;

import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.rusd.tddwd.body.BodyFactory;
import com.rusd.tddwd.body.BodyOptions;
import com.rusd.tddwd.body.CollisionBits;
import com.rusd.tddwd.body.GameContactListener;
import com.rusd.tddwd.entity.components.Damage;
import com.rusd.tddwd.entity.components.Health;
import com.rusd.tddwd.entity.components.Movable;

public class ProjectileFactory {



	public static int createProjectile(BodyOptions bo) {
		return 0;
//		Entity ent = new Entity();
//		
//		Damage d = new Damage();
//		ent.addPart(d);
//		
//		Movable m = new Movable();
//		m.speed = 50;
//		ent.addPart(m);
//		
//		Health h = new Health();
//		ent.addPart(h);
//		
//		ProjectileCollision pc = new ProjectileCollision(1, h);
//		ent.addPart(pc);
//		
//		bo.size = .7f;
//		bo.isSensor = false;
//		bo.bodyType = BodyType.DynamicBody;
//		bo.maskBits = CollisionBits.RESOURCE | CollisionBits.ENEMY;
//		bo.categoryBits = CollisionBits.PROJECTILE;
//		bo.linearDamping = 0;
//		
//		
//		
//		Fixture fixture = BodyFactory.createBody(bo);
//		ent.fixture = fixture;
//		
//		fixture.setUserData(ent);
//		
//		return ent;
	}
}

