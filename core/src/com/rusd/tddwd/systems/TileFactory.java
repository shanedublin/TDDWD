package com.rusd.tddwd.systems;

import com.artemis.ComponentMapper;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.rusd.tddwd.GlobalVaribles;
import com.rusd.tddwd.body.BodyFactory;
import com.rusd.tddwd.body.BodyOptions;
import com.rusd.tddwd.body.CollisionBits;
import com.rusd.tddwd.body.Shapes;
import com.rusd.tddwd.entity.components.DrawableComponent;
import com.rusd.tddwd.entity.components.Physics;

public class TileFactory extends BasicSystem {

	ComponentMapper<DrawableComponent> drawMapper;
	ComponentMapper<Physics> physicsMapper;
	
	public int createTile(Vector2 pos, int quarter, int type ) {
		
		int id = GlobalVaribles.artemisWorld.create(GlobalVaribles.archeTypes.tile);
		
		Sprite sprite = GlobalVaribles.gameAssets.getSprite("Terrian",type);
		sprite.setSize(2, 2);	
//		sprite.setOrigin(-1, -1);
		sprite.setOriginCenter();
		

		
		DrawableComponent drawable = drawMapper.get(id);
		drawable.sprite = sprite;
		drawable.layer = 0;
		drawable.yOffset = -1f;
		drawable.xOffset = -1f;
		
		
		Vector2 quad = getQuadrent(quarter);
		sprite.setPosition(pos.x + quad.x -1 , pos.y + quad.y -1);	
		
		
		return id;		
	}
	
	public Vector2 getQuadrent(int quarter) {
		Vector2 v = new Vector2();
		
		switch (quarter) {
			case 0:
				v.x = -1;
				v.y = 1;
				break;
			case 1: 
				v.x = 1;
				v.y = 1;
				break;
			case 2: 
				v.x = -1;
				v.y = -1;
				break;
			case 3: 
				v.x = 1;
				v.y = -1;
				break;
		}
		
		return v;
	}
	
}
