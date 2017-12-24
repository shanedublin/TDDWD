package com.rusd.tddwd.systems;

import com.artemis.Aspect.Builder;
import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.TimeUtils;
import com.rusd.tddwd.GlobalVaribles;
import com.rusd.tddwd.body.BodyOptions;
import com.rusd.tddwd.entity.EnemyFactory;
import com.rusd.tddwd.entity.ProjectileFactory;
import com.rusd.tddwd.entity.components.DrawableComponent;
import com.rusd.tddwd.entity.components.Movable;
import com.rusd.tddwd.entity.components.Physics;
import com.rusd.tddwd.entity.components.Player;
import com.rusd.tddwd.entity.components.Stats;
import com.rusd.tddwd.entity.components.Weapon;
import com.rusd.tddwd.entity.entity.items.ITEM;
import com.rusd.tddwd.entity.entity.items.ItemFactory;

public class PlayerSystem extends IteratingSystem {
	
	Interpolation i = Interpolation.exp10Out;
	
	// TightlyCoupled..
	DrawingSystem drawingSystem;
	ItemFactory itemFactory;
	PointFactory pointFactory;
	EnemyFactory enemyFactory;
	ProjectileFactory projectileFactory;
	
	ComponentMapper<Player> playerInputMapper;
	ComponentMapper<Physics> physicsMapper;
	ComponentMapper<Stats> statsMapper;
	ComponentMapper<DrawableComponent> spriteMapper;
	ComponentMapper<Movable> moveMapper;
	ComponentMapper<Weapon> weaponMapper;
	
	public PlayerSystem() {
		super(Aspect.all(Player.class,Stats.class));
	}


	
	
	@Override
	protected void process(int entityId) {
		
		Player p = playerInputMapper.get(entityId);
		trackMouseCursor(p);
		
		//TODO fix for multiplayer
		Physics player = physicsMapper.get(entityId);
	
		
		Body body = player.fixture.getBody();
		Vector2 pos = body.getPosition();
		
		DrawableComponent dc = spriteMapper.get(entityId);
		
//		
		if(Gdx.input.isKeyPressed(Input.Keys.A)) {		
			dc.sprite.setRegion(dc.map.get("left"));
		}
		if(Gdx.input.isKeyPressed(Input.Keys.E)) {
			dc.sprite.setRegion(dc.map.get("right"));
		}
		if(Gdx.input.isKeyPressed(Input.Keys.O)) {
			dc.sprite.setRegion(dc.map.get("down"));
		}
		if(Gdx.input.isKeyPressed(Input.Keys.COMMA)) {
			dc.sprite.setRegion(dc.map.get("up"));
		}

		handleMovement(entityId);
		
		
		drawingSystem.cam.position.x = pos.x;
		drawingSystem.cam.position.y = pos.y;
		
		if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {			
			itemFactory.CreateItem(ITEM.WOOD, p.mousePos);
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) {			
			enemyFactory.createEnemy(p.mousePos);
		}
		
		if(Gdx.input.isButtonPressed(0)) {
			handleMouseClick(p.mousePos,entityId);
			pointFactory.create(p.mousePos);
		}
		
	}
	
	private void handleMovement(int entityId) {
		
		Physics player = physicsMapper.get(entityId);	
		
		Body body = player.fixture.getBody();
		Vector2 pos = body.getPosition();
		
		Stats stats = statsMapper.get(entityId);
		
		Movable m = moveMapper.get(entityId);
		int x = 0;
		int y = 0;
		if(Gdx.input.isKeyPressed(Input.Keys.A)) {
			x -= 1;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.E)) {
			x +=1;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.O)) {
			y -=1;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.COMMA)) {
			y += 1;
		}
		
		float delta = Gdx.graphics.getDeltaTime();
		
		
				
		m.speed.x = getMoveSpeedTime(x, m.speed.x, delta,m.rate);
		m.speed.y = getMoveSpeedTime(y, m.speed.y, delta,m.rate);
		
		
		float valX = i.apply(Math.abs(m.speed.x)) * Math.signum(m.speed.x);
		float valY = i.apply(Math.abs(m.speed.y)) * Math.signum(m.speed.y);
//		Gdx.app.log("ValX","" + valX);
//		Gdx.app.log("m.speed.x","" + m.speed.x);
		
		body.setLinearVelocity(valX * stats.speed, valY * stats.speed);
		
		
		
	}


	
	public void handleMouseClick(Vector2 mousePos,int playerId) {
		Weapon w = weaponMapper.get(playerId);
		
		if(TimeUtils.timeSinceMillis(w.lastFire) >= w.fireRate) {
			
			Physics physics = physicsMapper.get(playerId);			
			Body body = physics.fixture.getBody();
			Vector2 pos = body.getPosition();
			
			w.fire();
			
			BodyOptions bo = new BodyOptions();
			bo.pos = new Vector2(pos.x,pos.y);
			
			int projectileID = projectileFactory.createProjectile(bo);
			Stats stats = statsMapper.get(projectileID);
			Vector3 v =  new Vector3(mousePos.x-pos.x, mousePos.y-pos.y,0).nor().scl(stats.speed);
			
			Physics projectilePhysics = physicsMapper.get(projectileID);
			projectilePhysics.fixture.getBody().setLinearVelocity(v.x,v.y);
			
			
		}
	}

	public float getMoveSpeedTime(int dir, float current, float delta,float x) {
		
		
		switch (dir) {
		case 1:
			return Math.min(x, current + delta);			
		case -1:
			return Math.max(-x, current - delta);			
		case 0:
			return moveToward0(delta, current);			
		}
		return 0;
		
		
	}
	
	// ammount should be positive
	public float moveToward0(float ammount , float current) {
		if(current > 0) {
			current = Math.max(0, current - ammount);
		}
		else if(current < 0) {
			current = Math.min(0, current + ammount);
		}
		return current;
		
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
