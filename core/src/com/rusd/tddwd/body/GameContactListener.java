package com.rusd.tddwd.body;

import com.artemis.ComponentMapper;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.rusd.tddwd.entity.Entity;
import com.rusd.tddwd.entity.components.Collidable;
import com.rusd.tddwd.entity.components.Item;
import com.rusd.tddwd.systems.ItemContactSystem;

public class GameContactListener implements ContactListener {
	
	ItemContactSystem itemContactSystem;

	@Override
	public void beginContact(Contact contact) {
		int a = (int) contact.getFixtureA().getUserData();
		int b = (int) contact.getFixtureB().getUserData();
		
		itemContactSystem.handleContact(a, b);
		
//		Entity entityA = (Entity) contact.getFixtureA().getUserData();		
//		Entity entityB = (Entity) contact.getFixtureB().getUserData();
		
//		Collidable cA = entityA.getPart(Collidable.class);
//		Collidable cB = entityB.getPart(Collidable.class);
//		if(cA != null) cA.collides(entityB);
//		if(cB != null) cB.collides(entityA);

		// TODO Auto-generated method stub
		
	}

	@Override
	public void endContact(Contact contact) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub
		
	}

}
