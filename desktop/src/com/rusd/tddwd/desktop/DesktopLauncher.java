package com.rusd.tddwd.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.tools.texturepacker.TexturePacker.Settings;
import com.rusd.tddwd.Main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.width = 1600;
		config.height = 900;
		
		Settings settings = new Settings();
		
		TexturePacker.process(settings, "./images/", "./packed", "game");
		
		new LwjglApplication(new Main(), config);
	}
}
