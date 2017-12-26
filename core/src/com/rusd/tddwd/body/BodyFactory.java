package com.rusd.tddwd.body;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.rusd.tddwd.GlobalVaribles;

public class BodyFactory {

	public static Fixture createBody( BodyOptions bo) {
		
		
			
		
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = bo.bodyType;
		
		bodyDef.position.set(bo.pos.x,bo.pos.y);
		bodyDef.fixedRotation = true;
		bodyDef.angle = bo.angle;
//		bodyDef.angle 
//		bodyDef.angle = ;
		
		
		Body body = GlobalVaribles.world.createBody(bodyDef);
		body.setLinearDamping(bo.linearDamping);
		
		FixtureDef fd = new FixtureDef();
		fd.density = bo.density;
		fd.friction = .1f;
		fd.restitution = .2f;
		fd.isSensor = bo.isSensor;
		fd.filter.maskBits = bo.maskBits;
		fd.filter.categoryBits = bo.categoryBits;
		
		
		
		Shape shape = null;
		
		
		switch (bo.shape) {
			case CIRCLE:
				shape = new CircleShape();
				shape.setRadius(bo.size);		
				fd.shape = shape;				
				break;
			case SQUARE:
				shape = new PolygonShape();
				((PolygonShape) shape).setAsBox(bo.size, bo.size);
				fd.shape = shape;				
				break;
			default:
				break;		
		}
		
		Fixture fixture = body.createFixture(fd);
		shape.dispose();
//		fixture.getShape().dispose();
		
		
		
		
		
		return fixture;
	}
	
}
