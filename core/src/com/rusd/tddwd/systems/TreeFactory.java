package com.rusd.tddwd.systems;

import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.artemis.Aspect.Builder;
import com.artemis.BaseEntitySystem;
import com.artemis.BaseSystem;
import com.artemis.ComponentMapper;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.rusd.tddwd.GlobalVaribles;
import com.rusd.tddwd.body.BodyFactory;
import com.rusd.tddwd.body.BodyOptions;
import com.rusd.tddwd.body.CollisionBits;
import com.rusd.tddwd.body.Shapes;
import com.rusd.tddwd.entity.components.Collidable;
import com.rusd.tddwd.entity.components.DrawableComponent;
import com.rusd.tddwd.entity.components.Health;
import com.rusd.tddwd.entity.components.Inventory;
import com.rusd.tddwd.entity.components.Movable;
import com.rusd.tddwd.entity.components.Name;
import com.rusd.tddwd.entity.components.Physics;

public class TreeFactory extends BaseSystem {
	
	

	ComponentMapper<Inventory> inventoryMapper;
	ComponentMapper<Name> nameMapper;
	ComponentMapper<Health> healthMapper;
	ComponentMapper<DrawableComponent> drawMapper;
	ComponentMapper<Physics> physicsMapper;
	
	
	public int create( BodyOptions bo) {
		
		int tree = GlobalVaribles.artemisWorld.create(GlobalVaribles.archeTypes.resource);
		
		Inventory i = inventoryMapper.get(tree);
		i.wood = 10;
		Name n = nameMapper.get(tree);
		n.name ="ID:" + tree+  "Type: tree";
		
		Sprite sprite = new Sprite(GlobalVaribles.gameAssets.get("tree.png",Texture.class));
		sprite.setSize(4, 4);		
		
		DrawableComponent drawable = drawMapper.get(tree);
		drawable.sprite = sprite;
//		drawable.yOffset = -2;
		
		
		Health h= healthMapper.get(tree);
		h.health = Integer.MAX_VALUE; 
		
//		ResourceInteracttion collidable = new ResourceInteracttion(inv,h);
//		ent.addPart(collidable);
		
		bo.maskBits = CollisionBits.RESOURCE | CollisionBits.PLAYER | CollisionBits.ENEMY | CollisionBits.PROJECTILE;	
		bo.categoryBits = CollisionBits.RESOURCE;
		bo.size = 2;
		bo.bodyType = BodyType.StaticBody;
		bo.isSensor = false;
		bo.shape = Shapes.SQUARE;
		
		
		Fixture fixture = BodyFactory.createBody(bo);
		
		Physics p = physicsMapper.get(tree);
		p.fixture = fixture;
		
		fixture.setUserData(tree);
		
		return tree;	
	}


	@Override
	protected void processSystem() {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected boolean checkProcessing() {
		return false;	
	}
	

}
