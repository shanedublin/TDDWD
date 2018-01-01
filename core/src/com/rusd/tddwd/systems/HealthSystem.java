package com.rusd.tddwd.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.rusd.tddwd.GlobalVaribles;
import com.rusd.tddwd.entity.components.Health;
import com.rusd.tddwd.entity.components.Physics;

public class HealthSystem extends IteratingSystem {
	ComponentMapper<Health> mapper;
	ComponentMapper<Physics> physicsMapper;

	public HealthSystem() {
		super(Aspect.all(Health.class));
	}


	
	
	
	@Override
	protected void process(int entityId) {
		Health h = mapper.get(entityId);
		if(h.health <= 0) {
			Physics p = physicsMapper.get(entityId);
			GlobalVaribles.world.destroyBody(p.fixture.getBody());;
			GlobalVaribles.artemisWorld.delete(entityId);
			
		}
	}
	
	
	public boolean alive(int health){
		return health > 0;
	}
	
	

}
