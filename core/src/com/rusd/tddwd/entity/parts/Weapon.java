package com.rusd.tddwd.entity.parts;

import com.badlogic.gdx.utils.TimeUtils;

public class Weapon implements Part {
	
	public long fireRate = 100;
	public long lastFire = -500;
	public void fire() {
		lastFire = TimeUtils.millis();
		
	}

}
