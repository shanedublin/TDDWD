package com.rusd.tddwd.systems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.artemis.Aspect;
import com.artemis.BaseEntitySystem;
import com.artemis.BaseSystem;
import com.artemis.ComponentMapper;
import com.artemis.utils.IntBag;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.rusd.tddwd.entity.components.DrawableComponent;
import com.rusd.tddwd.entity.components.Physics;
import com.rusd.tddwd.entity.components.Player;
import com.rusd.tddwd.entity.components.TileComponent;

public class TileSystem extends BaseEntitySystem {
	


	ComponentMapper<DrawableComponent> drawMapper;
	ComponentMapper<Physics> physicsMapper;
	ComponentMapper<Player> playerMapper;
	
	DrawingSystem drawingSystem;
	
	SpriteBatch batch;
	
	public TileSystem() {
		super(Aspect.all(TileComponent.class));
		batch = new SpriteBatch();
		
	}
	
	@Override
	protected void processSystem() {
		
		
		
		
		drawingSystem.cam.update();
		batch.setProjectionMatrix(drawingSystem.cam.combined);
		batch.begin();
		float deltaTime = Gdx.graphics.getDeltaTime();
		IntBag bag = subscription.getEntities();
		int[] ids = bag.getData();

		HashMap<Integer, List<Sprite>> map = new HashMap<>();
		
		for(int i = 0, s = bag.size(); s > i; i++) {
			int id = ids[i];
			DrawableComponent drawableComponent = drawMapper.get(id);			
			Sprite sprite = drawableComponent.sprite;
			if(drawableComponent.animation != null) {
				drawableComponent.animTime += deltaTime;
				sprite.setRegion(drawableComponent.animation.getKeyFrame(drawableComponent.animTime));
				
			}
			// TODO this might be to exspensive. might need to change to aabb collision checking later.
			if(drawingSystem.cam.frustum.boundsInFrustum(sprite.getX(), sprite.getY(), 0, sprite.getWidth(), sprite.getHeight(), 0)) {
				sprite.draw(batch);
			}
			
		}
		
		batch.end();		
	}
	

}
