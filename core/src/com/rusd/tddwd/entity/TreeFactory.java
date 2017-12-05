package com.rusd.tddwd.entity;

import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.rusd.tddwd.body.BodyFactory;
import com.rusd.tddwd.body.BodyOptions;
import com.rusd.tddwd.body.CollisionBits;
import com.rusd.tddwd.entity.parts.Collidable;
import com.rusd.tddwd.entity.parts.Health;
import com.rusd.tddwd.entity.parts.Inventory;
import com.rusd.tddwd.entity.parts.Movable;

public class TreeFactory {
	
	public static Entity create(World world, BodyOptions bo) {
		
		Entity ent = new Entity();		
		Inventory inv = new Inventory();
		inv.wood = 5;
		ent.addPart(inv);
		Health h= new Health();
		ent.addPart(h);

		
		ResourceCollision collidable = new ResourceCollision(inv,h);
		ent.addPart(collidable);
		
		
		
				
		bo.maskBits = CollisionBits.RESOURCE | CollisionBits.PLAYER | CollisionBits.ENEMY | CollisionBits.PROJECTILE;	
//		bo.maskBits = CollisionBits.PROJECTILE;
		bo.categoryBits = CollisionBits.RESOURCE;
		bo.size = 3;
		bo.bodyType = BodyType.StaticBody;
		bo.isSensor = false;
		
		Fixture fixture = BodyFactory.createBody(bo);
		ent.fixture = fixture;		
		
		fixture.setUserData(ent);
		
		return ent;	
	}

}
