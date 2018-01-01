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
		bo.pos = new Vector2(0, 0);
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
		
	
		
	}
}
