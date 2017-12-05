package com.rusd.tddwd.entity;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.rusd.tddwd.body.BodyFactory;
import com.rusd.tddwd.body.BodyOptions;
import com.rusd.tddwd.body.CollisionBits;
import com.rusd.tddwd.entity.parts.Health;
import com.rusd.tddwd.entity.parts.Movable;

public class EnemyFactory {
	
	
	public static Entity createEnemy( BodyOptions bo) {
		Entity ent = new Entity();
		
		Health h = new Health();
		h.health = 1;
		ent.addPart(h);
		
		Movable m = new Movable();
		m.speed = 900;
		ent.addPart(m);		
		bo.maskBits = CollisionBits.PROJECTILE | CollisionBits.PLAYER | CollisionBits.ENEMY | CollisionBits.RESOURCE;
		bo.categoryBits = CollisionBits.ENEMY;
		
		Fixture fixture = BodyFactory.createBody(bo);
		ent.fixture = fixture;		
		
		fixture.setUserData(ent);
		
		return ent;	
		
		
	}
	

}
