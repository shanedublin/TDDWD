package com.rusd.tddwd.entity.player;

import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.rusd.tddwd.GlobalVaribles;
import com.rusd.tddwd.body.BodyFactory;
import com.rusd.tddwd.body.BodyOptions;
import com.rusd.tddwd.body.CollisionBits;
import com.rusd.tddwd.entity.Entity;
import com.rusd.tddwd.entity.parts.Health;
import com.rusd.tddwd.entity.parts.Inventory;
import com.rusd.tddwd.entity.parts.Movable;
import com.rusd.tddwd.entity.parts.Tool;
import com.rusd.tddwd.entity.parts.Weapon;
import com.rusd.tddwd.events.EventHandler;

public class PlayerFactory {

	
	public static Entity createPlayer() {
		Entity ent = new Entity();
		ent.name = "player";
		
		Health h = new Health();
		h.health = 10;
		ent.addPart(h);
		
		Movable m = new Movable();
		m.speed = 1000;
		ent.addPart(m);
		Weapon w = new Weapon();
		w.fireRate = 100;
		ent.addPart(w);
		Tool t = new Tool();
		ent.addPart(t);
		
		Inventory i = new Inventory();
		i.wood = 1000;
		i.rocks = 1000;
		i.monsterGuts = 1000;
		ent.addPart(i);
		
		ResourceSubscriber rs = new ResourceSubscriber(i);
		EventHandler.subscribe(rs);
		
		BodyOptions bo = new BodyOptions();
		bo.density = 1;
		bo.size = 1.8f;
		bo.maskBits = CollisionBits.ENEMY | CollisionBits.RESOURCE;
		bo.categoryBits = CollisionBits.PLAYER;
		
		Fixture fixture = BodyFactory.createBody(bo);
		ent.fixture = fixture;
		
		fixture.setUserData(ent);
		
		return ent;		
		
	}
}
