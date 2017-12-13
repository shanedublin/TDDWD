package com.rusd.tddwd.entity.components;

import com.artemis.Component;
import com.badlogic.gdx.utils.TimeUtils;

public class Tool extends Component {
	
	public long fireRate = 500;
	public long lastFire = -500;
	public int damage = 1;
	public void fire() {
		lastFire = TimeUtils.millis();
		
	}

}
