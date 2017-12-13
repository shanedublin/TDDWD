package com.rusd.tddwd.entity.components;

import com.artemis.Component;
import com.badlogic.gdx.utils.TimeUtils;

public class Weapon extends Component {
	
	public long fireRate = 100;
	public long lastFire = -500;
	public void fire() {
		lastFire = TimeUtils.millis();
		
	}

}
