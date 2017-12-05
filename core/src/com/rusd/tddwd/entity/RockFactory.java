package com.rusd.tddwd.entity;

import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;

import javax.xml.soap.Text;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.rusd.tddwd.GlobalVaribles;
import com.rusd.tddwd.body.BodyFactory;
import com.rusd.tddwd.body.BodyOptions;
import com.rusd.tddwd.body.CollisionBits;
import com.rusd.tddwd.body.Shapes;
import com.rusd.tddwd.entity.parts.DrawablePart;
import com.rusd.tddwd.entity.parts.Health;
import com.rusd.tddwd.entity.parts.Inventory;

public class RockFactory {
	
	
	public static Entity create( BodyOptions bo) {
		
		Entity ent = new Entity();		
		Inventory inv = new Inventory();
		inv.rocks = 3;
		ent.addPart(inv);
		ent.name = "Rocks";
		
		Sprite sprite = new Sprite(GlobalVaribles.gameAssets.get("rocks.png",Texture.class));
		
		sprite.setSize(4, 4);
		DrawablePart drawable = new DrawablePart(ent, sprite);
		ent.addPart(drawable);
		Health h= new Health();
		h.health = Integer.MAX_VALUE; 
		ent.addPart(h);
		
		ResourceCollision collidable = new ResourceCollision(inv,h);
		ent.addPart(collidable);
		
		bo.maskBits = CollisionBits.RESOURCE | CollisionBits.PLAYER | CollisionBits.ENEMY | CollisionBits.PROJECTILE;	
		bo.categoryBits = CollisionBits.RESOURCE;
		bo.size = 2;
		bo.bodyType = BodyType.StaticBody;
		bo.isSensor = false;
		bo.shape = Shapes.SQUARE;
		
		Fixture fixture = BodyFactory.createBody(bo);
		ent.fixture = fixture;		
		
		fixture.setUserData(ent);
		
		return ent;	
	}


}
