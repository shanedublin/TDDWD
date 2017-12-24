package com.rusd.tddwd.entity.player;

import com.artemis.ComponentMapper;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.rusd.tddwd.GlobalVaribles;
import com.rusd.tddwd.body.BodyFactory;
import com.rusd.tddwd.body.BodyOptions;
import com.rusd.tddwd.body.CollisionBits;
import com.rusd.tddwd.body.Shapes;
import com.rusd.tddwd.entity.Entity;
import com.rusd.tddwd.entity.components.DrawableComponent;
import com.rusd.tddwd.entity.components.Health;
import com.rusd.tddwd.entity.components.Inventory;
import com.rusd.tddwd.entity.components.Movable;
import com.rusd.tddwd.entity.components.Name;
import com.rusd.tddwd.entity.components.Physics;
import com.rusd.tddwd.entity.components.Tool;
import com.rusd.tddwd.entity.components.Weapon;
import com.rusd.tddwd.events.EventHandler;

public class PlayerFactory  {
	
	
	ComponentMapper<Inventory> inventoryMapper;
	ComponentMapper<Name> nameMapper;
	ComponentMapper<Health> healthMapper;
	ComponentMapper<DrawableComponent> drawMapper;
	ComponentMapper<Physics> physicsMapper;
	
	
	public int createPlayer() {
		
		int id = GlobalVaribles.artemisWorld.create(GlobalVaribles.archeTypes.player);
		
		
		Name n = nameMapper.get(id);
		n.name ="Player:" + id+  "Type: Player";		
		
		
		
		
		
		DrawableComponent drawable = drawMapper.get(id);
//		Sprite sprite = new Sprite(GlobalVaribles.gameAssets.playerAnimation.getKeyFrame(drawable.animTime));
		Sprite sprite = GlobalVaribles.gameAssets.getSprite("alien");
		sprite.setSize(2, 2);		
		drawable.sprite = sprite;
		drawable.xOffset = -1;
		drawable.yOffset = -1;
		
		drawable.map.put("up", GlobalVaribles.gameAssets.dogUp);
		drawable.map.put("down", GlobalVaribles.gameAssets.dogDown);
		drawable.map.put("left", GlobalVaribles.gameAssets.dogLeft);
		drawable.map.put("right", GlobalVaribles.gameAssets.dogRight);
		
//		drawable.animation = GlobalVaribles.gameAssets.playerAnimation;
		
		Health h= healthMapper.get(id);
		h.health = Integer.MAX_VALUE; 
		
//		ResourceInteracttion collidable = new ResourceInteracttion(inv,h);
//		ent.addPart(collidable);
		BodyOptions bo = new BodyOptions();
		bo.pos = new Vector2(256*1 + 256/2 -2, 256*1 + 256/2 -2);
		bo.density = 1;
		bo.size = 1.0f;
		bo.linearDamping = 0;
		bo.maskBits = CollisionBits.ENEMY | CollisionBits.RESOURCE | CollisionBits.ITEM;
		bo.categoryBits = CollisionBits.PLAYER;
		
		
		Fixture fixture = BodyFactory.createBody(bo);
		
		Physics p = physicsMapper.get(id);
		p.fixture = fixture;
		
		fixture.setUserData(id);
		
		return id;	
		
		
//		Entity ent = new Entity();
//		ent.name = "player";
//		
//		Health h = new Health();
//		h.health = 10;
//		ent.addPart(h);
//		
//		Movable m = new Movable();
//		m.speed = 1000;
//		ent.addPart(m);
//		Weapon w = new Weapon();
//		w.fireRate = 100;
//		ent.addPart(w);
//		Tool t = new Tool();
//		ent.addPart(t);
//		
//		Inventory i = new Inventory();
//		i.wood = 1000;
//		i.rocks = 1000;
//		i.monsterGuts = 1000;
//		ent.addPart(i);
//		
//		ResourceSubscriber rs = new ResourceSubscriber(i);
//		EventHandler.subscribe(rs);
//		
//		BodyOptions bo = new BodyOptions();
//		bo.setTransform(new Vector2(256*1 + 256/2 -2, 256*1 + 256/2 -2), 0);
//		bo.density = 1;
//		bo.size = 1.8f;
//		bo.maskBits = CollisionBits.ENEMY | CollisionBits.RESOURCE;
//		bo.categoryBits = CollisionBits.PLAYER;
//		
//		Fixture fixture = BodyFactory.createBody(bo);
//		ent.fixture = fixture;
//		
//		fixture.setUserData(ent);
//		
//		return ent;		
		
	}
}
