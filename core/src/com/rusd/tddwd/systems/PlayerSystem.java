package com.rusd.tddwd.systems;

import com.artemis.Aspect.Builder;
import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.rusd.tddwd.entity.components.DrawableComponent;
import com.rusd.tddwd.entity.components.Physics;
import com.rusd.tddwd.entity.components.Player;
import com.rusd.tddwd.entity.components.Stats;

public class PlayerSystem extends IteratingSystem {
	
	// TightlyCoupled..
	DrawingSystem drawingSystem;
	

	ComponentMapper<Player> playerInputMapper;
	ComponentMapper<Physics> physicsMapper;
	ComponentMapper<Stats> statsMapper;
	ComponentMapper<DrawableComponent> spriteMapper;
	
	
	public PlayerSystem() {
		super(Aspect.all(Player.class,Stats.class));
	}


	
	
	@Override
	protected void process(int entityId) {
		//TODO fix for multiplayer
		Physics player = physicsMapper.get(entityId);
		Stats stats = statsMapper.get(entityId);
		
		Body body = player.fixture.getBody();
		Vector2 pos = body.getPosition();
		
		DrawableComponent dc = spriteMapper.get(entityId);
		
		float speed = stats.speed * Gdx.graphics.getDeltaTime();
		
		if(Gdx.input.isKeyPressed(Input.Keys.A)) {			
			body.applyLinearImpulse(new Vector2(-1 * speed ,0), pos, true);
			dc.sprite.setRegion(dc.map.get("left"));
		}
		if(Gdx.input.isKeyPressed(Input.Keys.E)) {
			body.applyLinearImpulse(new Vector2(1 * speed ,0), pos, true);
			dc.sprite.setRegion(dc.map.get("right"));
		}
		if(Gdx.input.isKeyPressed(Input.Keys.O)) {
			body.applyLinearImpulse(new Vector2(0, -1 * speed), pos, true);
			dc.sprite.setRegion(dc.map.get("down"));
		}
		if(Gdx.input.isKeyPressed(Input.Keys.COMMA)) {
			body.applyLinearImpulse(new Vector2(0 ,1 * speed), pos, true);
			dc.sprite.setRegion(dc.map.get("up"));
		}
		drawingSystem.cam.position.x = pos.x;
		drawingSystem.cam.position.y = pos.y;
		
	}

}
