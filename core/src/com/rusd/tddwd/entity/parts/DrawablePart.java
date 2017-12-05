package com.rusd.tddwd.entity.parts;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.rusd.tddwd.entity.Entity;

public class DrawablePart implements Part {
	
	Sprite sprite;
	
	Entity entity;
	
	
	public DrawablePart(Entity entity, Sprite sprite) {
		this.sprite = sprite; 
		this.entity = entity;
	}
	
	public void draw(SpriteBatch batch) {		
		Body body = entity.getBody();
		sprite.setPosition(body.getPosition().x - sprite.getWidth()/2, body.getPosition().y - sprite.getHeight()/2);
		sprite.draw(batch);
	}

}
