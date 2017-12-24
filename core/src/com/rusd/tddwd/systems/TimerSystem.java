package com.rusd.tddwd.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Aspect.Builder;
import com.artemis.systems.DelayedIteratingSystem;
import com.artemis.systems.IntervalSystem;
import com.rusd.tddwd.entity.components.Health;
import com.rusd.tddwd.entity.components.Timer;

public class TimerSystem extends DelayedIteratingSystem {
	
	ComponentMapper<Timer> timerMapper;
	ComponentMapper<Health> healthMapper;
	
	public TimerSystem() {
		super(Aspect.all(Health.class,Timer.class));
	}


	@Override
	protected float getRemainingDelay(int entityId) {
		Timer t = timerMapper.get(entityId);
		return t.cooldown;
	}

	@Override
	protected void processDelta(int entityId, float accumulatedDelta) {
		Timer t = timerMapper.get(entityId);
		t.cooldown  -= accumulatedDelta;		
	}

	@Override
	protected void processExpired(int entityId) {
		Health h = healthMapper.get(entityId);
		h.health = 0;	
	}


}
