package com.rusd.tddwd.entity;

import com.artemis.World;
import com.artemis.WorldConfiguration;
import com.artemis.WorldConfigurationBuilder;
import com.artemis.managers.PlayerManager;
import com.badlogic.gdx.Gdx;
import com.rusd.tddwd.systems.DrawingSystem;
import com.rusd.tddwd.systems.HealthSystem;
import com.rusd.tddwd.systems.PlayerSystem;
import com.rusd.tddwd.systems.RockFactory;
import com.rusd.tddwd.systems.TreeFactory;

public class ArtemisWorld extends World {
	
	
	private static WorldConfiguration config;
	
	static {
		config = new WorldConfigurationBuilder()
				.with(
						new HealthSystem(),
						new DrawingSystem(),
						new TreeFactory(),
						new RockFactory(),
						new PlayerSystem()
						)
				.build();
	}
	
	public ArtemisWorld() {		
		super(config);
		Gdx.app.log("Artemis World ", "Config Complete");
		
		
	}

}
