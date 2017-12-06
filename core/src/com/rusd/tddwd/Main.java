package com.rusd.tddwd;

import java.awt.Font;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.RayCastCallback;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.TimeUtils;
import com.rusd.tddwd.body.BodyOptions;
import com.rusd.tddwd.body.CollisionBits;
import com.rusd.tddwd.body.GameContactListener;
import com.rusd.tddwd.entity.EnemyFactory;
import com.rusd.tddwd.entity.Entity;
import com.rusd.tddwd.entity.ProjectileFactory;
import com.rusd.tddwd.entity.ResourceInteracttion;
import com.rusd.tddwd.entity.RockFactory;
import com.rusd.tddwd.entity.TreeFactory;
import com.rusd.tddwd.entity.buildings.BuildingFactory;
import com.rusd.tddwd.entity.parts.Buildable;
import com.rusd.tddwd.entity.parts.Collidable;
import com.rusd.tddwd.entity.parts.Health;
import com.rusd.tddwd.entity.parts.Interactable;
import com.rusd.tddwd.entity.parts.Inventory;
import com.rusd.tddwd.entity.parts.Movable;
import com.rusd.tddwd.entity.parts.Tool;
import com.rusd.tddwd.entity.parts.Weapon;
import com.rusd.tddwd.entity.player.PlayerFactory;
import com.rusd.tddwd.events.EventHandler;
import com.rusd.tddwd.events.ResourceEvent;
import com.rusd.tddwd.events.ResourceEvent.ResourceType;
import com.rusd.tddwd.grid.Grid;

public class Main extends ApplicationAdapter {
	
	public enum SelectionMode{
		ATTACK,BUILD,MENU
	}
	
	SpriteBatch uiBatch;
	SpriteBatch batch;
	ShapeRenderer sr;
	
	Box2DDebugRenderer debugRender;
	OrthographicCamera cam;
	OrthographicCamera uiCam;
	Entity player;
	List<Entity> enemies;
	List<Entity> projectiles;
	
	
	
	
	long spawnRate = TimeUnit.SECONDS.toMillis(5);	
	long lastSpawn = -spawnRate;
	boolean paused;
	
	GameMenu gameMenu;
	
	Sprite sprite;
	
	Sprite selectorSprite;
	
	Vector2 mousePos = new Vector2();
	Vector2 rayCast;
	long racastVisibleTime = 100;
	
	
	SelectionMode selectionMode;
	Grid grid;
	
	GameInputHandler inputHandler;
	
	
	@Override
	public void create () {
		GlobalVaribles.init();
		 selectionMode = SelectionMode.ATTACK;
		
		uiBatch = new SpriteBatch();
		batch = new SpriteBatch();
		sr = new ShapeRenderer();
		cam = new OrthographicCamera(1600,900);
		Gdx.app.log("Hmm", "HMMMMMM");
		sprite = new Sprite(GlobalVaribles.gameAssets.get("player.png",Texture.class));
		sprite.setSize(4, 4);
		selectorSprite = new Sprite(GlobalVaribles.gameAssets.get("selector.png",Texture.class));
		
		
		GlobalVaribles.world.setContactListener(new GameContactListener());
		debugRender = new Box2DDebugRenderer();
		
		
//		cam.zoom = 1f;
		cam.update();
		uiCam = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		uiCam.translate(new Vector3(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2,0));
		uiCam.update();
		player =  PlayerFactory.createPlayer();
//		randX*4 + 64 * xOffset
		player.getBody().setTransform(new Vector2(256*1 + 256/2 -2, 256*1 + 256/2 -2), 0);
		enemies = new ArrayList<>();
		projectiles = new ArrayList<>();
		
		gameMenu = new GameMenu();
		
		// world gen
		generateChunk(3);
		
		inputHandler =  new GameInputHandler(this);
		Gdx.input.setInputProcessor(inputHandler);
	}

	@Override
	public void render () {
		input();
		if(paused == false) {			
			GlobalVaribles.world.step(1/45f, 6, 2);
			logic();		
		}
		draw();		
		followPlayer();
	}
	
	public void followPlayer() {
		cam.position.x = player.getBody().getPosition().x;
		cam.position.y = player.getBody().getPosition().y;
	}
	
	public void draw() {
//		Gdx.gl.glClearColor(87f/255,122f/255,30f/255, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		cam.update();
		uiCam.update();
		
		batch.setProjectionMatrix(cam.combined);
		batch.begin();		
		sprite.setPosition(player.getBody().getPosition().x - sprite.getWidth()/2
				, player.getBody().getPosition().y - sprite.getHeight() /2);
		
		sprite.draw(batch);		
		
		grid.draw(batch);
		
		if(selectionMode == SelectionMode.BUILD) {			
			selectorSprite.draw(batch);
		}
		batch.end();
		
		
		if(Gdx.input.isButtonPressed(1)) {
			sr.setProjectionMatrix(cam.combined);
			sr.setAutoShapeType(true);
			sr.begin();
			sr.setColor(Color.RED);
			if(rayCast != null) {
				sr.line(player.getBody().getPosition(), rayCast);
				
			}
			sr.end();
		}
		
		
		uiBatch.setProjectionMatrix(uiCam.combined);

		debugRender.render(GlobalVaribles.world, cam.combined);
		
		Inventory i = player.getPart(Inventory.class);
		uiBatch.begin();
//		uiBatch.draw(tx, player.getBody().getPosition().x,player.getBody().getPosition().y);
		String wood = "Wood: " + i.wood;
		String rocks = "Rocks: " + i.rocks;
		String guts = "Monster Guts: " + i.monsterGuts;
		GlobalVaribles.font.draw(uiBatch, wood  + "\n" + rocks + "\n" + guts ,8,Gdx.graphics.getHeight() -8);
		if(paused) {			
			drawMenu();
		}
		
		uiBatch.end();
		
		
	}
	
	public void drawMenu() {
		gameMenu.act(Gdx.graphics.getDeltaTime());
		gameMenu.draw();
		
	}
	
	public void logic() {
		enemies = enemies.stream()
				.filter(UtilMethods::cleanseList)
				.collect(Collectors.toList());
		projectiles = projectiles.stream()
				.filter(UtilMethods::cleanseList)
				.collect(Collectors.toList());
		
//		spawnEnemies();
		grid.cleanse();
		
		ai();
	}
	
	
	

	public void input() {
		racastVisibleTime -=10;
		
		if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
			paused = !paused;
			if(paused) {
				selectionMode = SelectionMode.MENU;
			}
		}
		
		if(paused) {
			return;			
		}
		
		
		mousePos.x = Gdx.input.getX();
		mousePos.y = Gdx.input.getY();
		Vector3 v3 =new Vector3(mousePos, 0);
		
		
		v3 = cam.unproject(v3);
		mousePos.x =v3.x;
		mousePos.y =v3.y;
		
		trackMouseCursor();
		
		
		Body body = player.getBody();
		Vector2 pos = body.getPosition();
		Movable m = player.getPart(Movable.class);
		
		float speed = m.speed * Gdx.graphics.getDeltaTime();
		
		if(Gdx.input.isKeyPressed(Input.Keys.A)) {			
			body.applyLinearImpulse(new Vector2(-1 * speed ,0), pos, true);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.E)) {
			body.applyLinearImpulse(new Vector2(1 * speed ,0), pos, true);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.O)) {
			body.applyLinearImpulse(new Vector2(0, -1 * speed), pos, true);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.COMMA)) {
			body.applyLinearImpulse(new Vector2(0 ,1 * speed), pos, true);
		}		
		if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
			Gdx.app.exit();
		}
		
		
		
		if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
			switch (selectionMode) {
				case ATTACK:
					selectionMode = SelectionMode.BUILD;
					break;				
				default:
					break;
			}
		}else {
			switch (selectionMode) {
			case BUILD:
				selectionMode = SelectionMode.ATTACK;
				break;				
			default:
				break;
			}
			
		}
		
		
		if(selectionMode == selectionMode.BUILD) {
			// TODO check currently selceted building and resources..
			if(Gdx.input.justTouched()) {
				if(Gdx.input.isButtonPressed(0)) {
					if(grid.tileEmpty(mousePos)) {
						grid.addEntityToGrid(BuildingFactory.createWall(mousePos), mousePos);
					}		
					
				}
			}
		}
		
		
		
		if(Gdx.input.isButtonPressed(0) ) {
			if(selectionMode != SelectionMode.ATTACK)
				return;
			
			Weapon w = player.getPart(Weapon.class);
			if(TimeUtils.timeSinceMillis(w.lastFire) >= w.fireRate) {
				w.fire();
				BodyOptions bo = new BodyOptions();
				bo.pos = new Vector2(pos.x,pos.y);
				bo.size = .1f;
				
				Entity projectile = ProjectileFactory.createProjectile(bo);
				Movable pM = projectile.getPart(Movable.class);
				Vector3 v =  new Vector3(mousePos.x-pos.x, mousePos.y-pos.y,0).nor().scl(pM.speed);
				
				projectile.getBody().setLinearVelocity(v.x,v.y);
				projectiles.add(projectile);
			}
		}
		
		if(racastVisibleTime <=0)
			rayCast = null;
		if(Gdx.input.isButtonPressed(1) ) {
//			Gdx.app.log("Try RayCast", "RayCast Mother Fucer");
			
			GlobalVaribles.world.rayCast(new RayCastCallback() {
				
				@Override
				public float reportRayFixture(Fixture fixture, Vector2 point, Vector2 normal, float fraction) {
					//RESOURCE LOGIC
					Entity e =(Entity) fixture.getUserData();
//					Gdx.app.log("raycasted", e.toString());e
					Interactable i = e.getPart(Interactable.class);
					if(i != null) {
						if(i instanceof Buildable) {
							Buildable b = (Buildable) i;
							if(b.buildPercentage() == 100) {
								return 1;
							}
						}
						
						Tool t = player.getPart(Tool.class);							
						if(TimeUtils.timeSinceMillis(t.lastFire) >= t.fireRate) {
							rayCast = fixture.getBody().getPosition();
							t.fire();
							racastVisibleTime = 100;
							i.interact(player);
						}
					}
					
					
					
					return 0;
				}
			}, player.getBody().getPosition()	, mousePos);
		}
		
	}
	
	private void trackMouseCursor() {
		// TODO Auto-generated method stub
		selectorSprite.setSize(4, 4);
		int tileX = (((int)mousePos.x -2) / 4); 
		int tileY = (((int)mousePos.y -2) / 4);
		selectorSprite.setPosition(tileX *4 +2, tileY * 4 +2);
	}

	@Override
	public void resize(int width, int height) {
		float aspectRatio = (float) width / (float) height;
		float scale = 100;
		cam = new OrthographicCamera(scale * aspectRatio, scale);
		cam.update();
	}
	
	public void ai() {
		Vector2 pos = player.getBody().getPosition();
		
		enemies.forEach(e ->{
			Body body = e.getBody();
			Vector2 ePos = body.getPosition();
			Vector2 dir = new Vector2(pos.x - ePos.x, pos.y - ePos.y);
			dir = dir.nor();
			body.applyLinearImpulse(dir.scl(1), ePos, true);
			
		});
	}
	
	
	public void spawnEnemies() {
		Vector2 playerPos = player.getBody().getPosition();
		
		if(TimeUtils.timeSinceMillis(lastSpawn) >= spawnRate) {
			float rad = MathUtils.random(0, 360);
			rad *= MathUtils.degreesToRadians;
			float radius = MathUtils.random(30, 50);
			
			Vector2 spawnpos  = new Vector2();
			spawnpos.x = playerPos.x;
			spawnpos.y = playerPos.y;
			
			spawnpos.x += MathUtils.cos(rad) * radius; 
			spawnpos.y += MathUtils.sin(rad) * radius;
			
			lastSpawn = TimeUtils.millis();
			BodyOptions bo = new BodyOptions();
			bo.pos = spawnpos;
			enemies.add(EnemyFactory.createEnemy(bo));
		}
	}
	
	/**
	 *  not really like a minecraft chunk but kinda
	 */
	public void generateChunk(int x) {
		grid = new Grid(x);
		
		
	}
	
	@Override
	public void dispose () {
		uiBatch.dispose();
		gameMenu.dispose();
	}
}
