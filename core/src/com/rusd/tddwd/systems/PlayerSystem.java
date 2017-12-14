package com.rusd.tddwd.systems;

import com.artemis.Aspect.Builder;
import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.rusd.tddwd.entity.components.DrawableComponent;
import com.rusd.tddwd.entity.components.Physics;
import com.rusd.tddwd.entity.components.Player;
import com.rusd.tddwd.entity.components.Stats;
import com.rusd.tddwd.entity.entity.items.ITEM;
import com.rusd.tddwd.entity.entity.items.ItemFactory;

public class PlayerSystem extends IteratingSystem {
	
	// TightlyCoupled..
	DrawingSystem drawingSystem;
	ItemFactory itemFactory;
	

	ComponentMapper<Player> playerInputMapper;
	ComponentMapper<Physics> physicsMapper;
	ComponentMapper<Stats> statsMapper;
	ComponentMapper<DrawableComponent> spriteMapper;
	
	
	public PlayerSystem() {
		super(Aspect.all(Player.class,Stats.class));
	}


	
	
	@Override
	protected void process(int entityId) {
		
		Player p = playerInputMapper.get(entityId);
		trackMouseCursor(p);
		
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
		
		if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
			
			itemFactory.CreateItem(ITEM.WOOD, p.mousePos);
		}
		
	}




	private void trackMouseCursor(Player p) {
		p.mousePos.x = Gdx.input.getX();
		p.mousePos.y = Gdx.input.getY();
		Vector3 v3 =new Vector3(p.mousePos, 0);
		
		
		v3 = drawingSystem.cam.unproject(v3);
		p.mousePos.x =v3.x;
		p.mousePos.y =v3.y;
		
	}

}
