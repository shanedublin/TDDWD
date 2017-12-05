package com.rusd.tddwd.entity.parts;

import com.badlogic.gdx.utils.TimeUtils;

public class Tool implements Part {
	
	public long fireRate = 500;
	public long lastFire = -500;
	public int damage = 1;
	public void fire() {
		lastFire = TimeUtils.millis();
		
	}

}
