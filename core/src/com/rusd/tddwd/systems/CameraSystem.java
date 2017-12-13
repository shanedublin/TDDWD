package com.rusd.tddwd.systems;

import org.junit.Assume.AssumptionViolatedException;

import com.artemis.Aspect;
import com.artemis.Aspect.Builder;
import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Camera;
import com.rusd.tddwd.entity.components.GameCamera;

public class CameraSystem extends IteratingSystem {

	ComponentMapper<GameCamera> camMapper;
	
	
	
	public CameraSystem(Builder aspect) {
		super(Aspect.all(GameCamera.class));
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void process(int entityId) {
		GameCamera cam = camMapper.get(entityId);
		cam.cam.update();		
	}

}
