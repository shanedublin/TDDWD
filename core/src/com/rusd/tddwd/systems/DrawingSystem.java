package com.rusd.tddwd.systems;

import com.artemis.Aspect;
import com.artemis.BaseEntitySystem;
import com.artemis.BaseSystem;
import com.artemis.ComponentMapper;
import com.artemis.EntitySubscription;
import com.artemis.systems.IteratingSystem;
import com.artemis.utils.IntBag;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.rusd.tddwd.GlobalVaribles;
import com.rusd.tddwd.entity.components.DrawableComponent;
import com.rusd.tddwd.entity.components.Physics;
import com.rusd.tddwd.entity.components.Player;

public class DrawingSystem extends BaseEntitySystem{

	ComponentMapper<DrawableComponent> drawMapper;
	ComponentMapper<Physics> physicsMapper;
	ComponentMapper<Player> playerMapper;
	
	SpriteBatch batch;
	Box2DDebugRenderer debugRender;	
	public Camera cam;
	
	public DrawingSystem() {
		super(Aspect.all(DrawableComponent.class));
		batch = new SpriteBatch();
		
		float aspectRatio = (float) Gdx.graphics.getWidth() / (float) Gdx.graphics.getHeight();
		float scale = 100;
		cam = new OrthographicCamera(scale * aspectRatio, scale);
		cam.update();
		
		debugRender = new Box2DDebugRenderer();
		
		
//			super(Aspect.all(DrawableComponent.class));
	}
	
	// Updates the sprites to the correct pos then draws them. 
	@Override
	protected void processSystem() {
		
		
		
		
		cam.update();
		batch.setProjectionMatrix(cam.combined);
		batch.begin();
		float deltaTime = Gdx.graphics.getDeltaTime();
		IntBag bag = subscription.getEntities();
		int[] ids = bag.getData();
		for(int i = 0, s = bag.size(); s > i; i++) {
			int id = ids[i];
			DrawableComponent drawableComponent = drawMapper.get(id);			
			Sprite sprite = drawableComponent.sprite;
			if(drawableComponent.animation != null) {
				drawableComponent.animTime += deltaTime;
				sprite.setRegion(drawableComponent.animation.getKeyFrame(drawableComponent.animTime));
				
			}
			Physics physics = physicsMapper.get(id);
			Vector2 pos = physics.fixture.getBody().getPosition();
			sprite.setPosition(pos.x + drawableComponent.xOffset, pos.y + drawableComponent.yOffset);
			sprite.draw(batch);			
		}
		
		batch.end();		
		
		debugRender.render(GlobalVaribles.world, cam.combined);
	}
	

}
