package com.rusd.tddwd.entity.buildings;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.rusd.tddwd.GlobalVaribles;
import com.rusd.tddwd.body.BodyFactory;
import com.rusd.tddwd.body.BodyOptions;
import com.rusd.tddwd.body.CollisionBits;
import com.rusd.tddwd.body.Shapes;
import com.rusd.tddwd.entity.Entity;
import com.rusd.tddwd.entity.ResourceInteracttion;
import com.rusd.tddwd.entity.components.Buildable;
import com.rusd.tddwd.entity.components.BuildingDrawable;
import com.rusd.tddwd.entity.components.DrawableComponent;
import com.rusd.tddwd.entity.components.Health;
import com.rusd.tddwd.entity.components.Inventory;

public class BuildingFactory {
	

	
	
	
	public int createWall(Vector2 vector) {
		
		return 0;
		
		
//		Entity ent = new Entity();		
//		ent.name ="wall";
//		Inventory inv = new Inventory();
//		inv.rocks = 3;
//		ent.addPart(inv);
//		
//		Health health= new Health();
//		health.maxHealth = 10; 
//		ent.addPart(health);
//
//		Buildable b = new Buildable(10, 0, 0, health);
//		ent.addPart(b);
//		
//		Sprite sprite = new Sprite(GlobalVaribles.gameAssets.get("palisade.png",Texture.class));
//		
//		sprite.setSize(4, 4);
//		BuildingDrawable drawable = new BuildingDrawable(ent, sprite, b);
//		ent.addPart(drawable);
//		
//		
//		
//		
//		BodyOptions bo = new BodyOptions();
//		bo.pos = vector;
//		
//		bo.maskBits = CollisionBits.RESOURCE | CollisionBits.PLAYER | CollisionBits.ENEMY | CollisionBits.PROJECTILE;	
//		bo.categoryBits = CollisionBits.RESOURCE;
//		bo.size = 2;
//		bo.bodyType = BodyType.StaticBody;
//		bo.isSensor = false;
//		bo.shape = Shapes.SQUARE;
//		
//		Fixture fixture = BodyFactory.createBody(bo);
//		ent.fixture = fixture;		
//		
//		fixture.setUserData(ent);
//		
//		return ent;	
		
		
	}

}
