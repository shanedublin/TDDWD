package com.rusd.tddwd.entity.components;

import com.artemis.Component;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.rusd.tddwd.GlobalVaribles;
import com.rusd.tddwd.entity.Entity;

public class BuildingDrawable extends Component {
	
	Buildable buildInfo;
	
	public BuildingDrawable(Entity entity, Sprite sprite, Buildable buildable) {
//		super(entity, sprite);
//		this.buildInfo = buildable;
	}
	
	
//	@Override
//	public void draw(SpriteBatch batch) {
//		int buildPercentage = buildInfo.buildPercentage();
//		Health h = entity.getPart(Health.class);
//		
//		super.draw(batch);
//		if(h.health < h.maxHealth) {
//			batch.draw(GlobalVaribles.gameAssets.get("red.png",Texture.class), sprite.getX(), sprite.getY() +3, 4, 1);
//			batch.draw(GlobalVaribles.gameAssets.get("green.png",Texture.class), sprite.getX(), sprite.getY() +3, 4*((float) h.health /(float) h.maxHealth), 1);			
//		}
//	}

}
