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
import com.rusd.tddwd.entity.buildings.BuildingFactory;
import com.rusd.tddwd.entity.components.Buildable;
import com.rusd.tddwd.entity.components.Collidable;
import com.rusd.tddwd.entity.components.Health;
import com.rusd.tddwd.entity.components.Interactable;
import com.rusd.tddwd.entity.components.Inventory;
import com.rusd.tddwd.entity.components.Movable;
import com.rusd.tddwd.entity.components.Tool;
import com.rusd.tddwd.entity.components.Weapon;
import com.rusd.tddwd.entity.player.PlayerFactory;
import com.rusd.tddwd.events.EventHandler;
import com.rusd.tddwd.events.ResourceEvent;
import com.rusd.tddwd.events.ResourceEvent.ResourceType;
import com.rusd.tddwd.grid.Grid;
import com.rusd.tddwd.systems.DrawingSystem;
import com.rusd.tddwd.systems.RockFactory;
import com.rusd.tddwd.systems.TreeFactory;
import com.rusd.tddwd.userinterface.GameMenu;
import com.rusd.tddwd.userinterface.HUD;

public class Main extends ApplicationAdapter {
	
	public enum SelectionMode{
		ATTACK,BUILD,MENU
	}
	
	SpriteBatch uiBatch;
	ShapeRenderer sr;
	
	
	OrthographicCamera uiCam;
	
	
	
	GameMenu gameMenu;
	HUD hud;
	
	Vector2 mousePos = new Vector2();
	Vector2 rayCast;
	long racastVisibleTime = 100;
	
	SelectionMode selectionMode;
	Grid grid;
	
	GameInputHandler inputHandler;
	boolean paused;
	
	
	// should be injected..
	DrawingSystem drawingSystem;
	
	@Override
	public void create () {
		
		GlobalVaribles.artemisWorld.inject(this);
		
		selectionMode = SelectionMode.ATTACK;
		
		uiBatch = new SpriteBatch();
		sr = new ShapeRenderer();
	
		Gdx.app.log("Creating main Game", "HMMMMMM");
		GameContactListener gcl = new GameContactListener();
		GlobalVaribles.artemisWorld.inject(gcl);
		GlobalVaribles.world.setContactListener(gcl);
				
		gameMenu = new GameMenu();
		hud = new HUD();
		
		// world gen
		grid = new Grid(GlobalVaribles.gridSize);
		
		inputHandler =  new GameInputHandler(this);
		Gdx.input.setInputProcessor(inputHandler);
		
		GlobalVaribles.playerFactory.createPlayer();
	}

	@Override
	public void render () {
		input();
		if(paused == false) {			
			GlobalVaribles.world.step(1/45f, 6, 2);
		}
		draw();		
		followPlayer();
	}
	
	public void followPlayer() {
//		cam.position.x = player.getBody().getPosition().x;
//		cam.position.y = player.getBody().getPosition().y;
	}
	
	public void draw() {
//		Gdx.gl.glClearColor(87f/255,122f/255,30f/255, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		GlobalVaribles.artemisWorld.setDelta(Gdx.graphics.getDeltaTime());
		GlobalVaribles.artemisWorld.process();

		
		
//		if(Gdx.input.isButtonPressed(1)) {
//			sr.setProjectionMatrix(cam.combined);
//			sr.setAutoShapeType(true);
//			sr.begin();
//			sr.setColor(Color.RED);
//			if(rayCast != null) {
////				sr.line(player.getBody().getPosition(), rayCast);
//				
//			}
//			sr.end();
//		}
		
		
//		uiBatch.setProjectionMatrix(uiCam.combined);

//		
		
//		if(paused) {			
//			drawMenu();
//		}
//		
//		
//		uiBatch.end();
//		hud.act(Gdx.graphics.getDeltaTime());
//		hud.draw();
		
		
	}
//	
//	public void drawMenu() {
//		gameMenu.act(Gdx.graphics.getDeltaTime());
//		gameMenu.draw();
//		
//	}
	
	

	public void input() {
		racastVisibleTime -=10;
		
		if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
			paused = !paused;
			if(paused) {
				selectionMode = SelectionMode.MENU;
			}else {
				selectionMode = SelectionMode.ATTACK;
			}
		}
		
		if(paused) {
			return;			
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
		
		
//		if(selectionMode == selectionMode.BUILD) {
//			// TODO check currently selceted building and resources..
//			if(Gdx.input.justTouched()) {
//				if(Gdx.input.isButtonPressed(0)) {
//					if(grid.tileEmpty(mousePos)) {
//						grid.addEntityToGrid(BuildingFactory.createWall(mousePos), mousePos);
//					}		
//					
//				}
//			}
//		}
		
		
		
		if(Gdx.input.isButtonPressed(0) ) {
			if(selectionMode != SelectionMode.ATTACK)
				return;
			
//			Weapon w = player.getPart(Weapon.class);
//			if(TimeUtils.timeSinceMillis(w.lastFire) >= w.fireRate) {
//				w.fire();
//				BodyOptions bo = new BodyOptions();
//				bo.pos = new Vector2(pos.x,pos.y);
//				bo.size = .1f;
//				
//				Entity projectile = ProjectileFactory.createProjectile(bo);
//				Movable pM = projectile.getPart(Movable.class);
//				Vector3 v =  new Vector3(mousePos.x-pos.x, mousePos.y-pos.y,0).nor().scl(pM.speed);
//				
//				projectile.getBody().setLinearVelocity(v.x,v.y);
//				projectiles.add(projectile);
//			}
		}
		
		if(racastVisibleTime <=0)
			rayCast = null;
//		if(Gdx.input.isButtonPressed(1) ) {
//			
//			GlobalVaribles.world.rayCast(new RayCastCallback() {				
//				@Override
//				public float reportRayFixture(Fixture fixture, Vector2 point, Vector2 normal, float fraction) {
//					//RESOURCE LOGIC
//					Entity e =(Entity) fixture.getUserData();
////					Gdx.app.log("raycasted", e.toString());e
//					Interactable i = e.getPart(Interactable.class);
////					if(i != null) {
////						if(i instanceof Buildable) {
////							Buildable b = (Buildable) i;
////							if(b.buildPercentage() == 100) {
////								return 1;
////							}
////						}
////						
////						Tool t = player.getPart(Tool.class);							
////						if(TimeUtils.timeSinceMillis(t.lastFire) >= t.fireRate) {
////							rayCast = fixture.getBody().getPosition();
////							t.fire();
////							racastVisibleTime = 100;
////							i.interact(player);
////						}
////					}
////					
//					
//					
//					return 0;
//				}
//			}, player.getBody().getPosition()	, mousePos);
//		}
		
	}
	
	private void trackMouseCursor() {
		// TODO Auto-generated method stub
		int tileX = (((int)mousePos.x -2) / 4); 
		int tileY = (((int)mousePos.y -2) / 4);
//		selectorSprite.setPosition(tileX *4 +2, tileY * 4 +2);
	}

	@Override
	public void resize(int width, int height) {
		float aspectRatio = (float) width / (float) height;
		float scale = 50;
		drawingSystem.cam = new OrthographicCamera(scale * aspectRatio, scale);
		drawingSystem.cam.update();
	}
	
	

	
	@Override
	public void dispose () {
		uiBatch.dispose();
		gameMenu.dispose();
	}
}
