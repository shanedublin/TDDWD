package com.rusd.tddwd;

import com.artemis.WorldConfiguration;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.rusd.tddwd.entity.ArcheTypes;
import com.rusd.tddwd.entity.ArtemisWorld;
import com.rusd.tddwd.entity.player.PlayerFactory;
import com.rusd.tddwd.systems.RockFactory;
import com.rusd.tddwd.systems.TreeFactory;

public class GlobalVaribles {
	 
	public static GameAssets gameAssets = new GameAssets();
	public static World world = new World(Vector2.Zero, true);
	public static ArtemisWorld artemisWorld = new ArtemisWorld();;
	public static ArcheTypes archeTypes = new ArcheTypes();
	// should be in game assets
	public static BitmapFont font = new BitmapFont();
	
	
	public static int gridSize = 3;
	
	public static PlayerFactory playerFactory = new PlayerFactory();
	
//	public static RockFactory rockFactory = new RockFactory();
//	public static TreeFactory treeFactory = new TreeFactory();
//	
	
	
	
	
	static{
		Gdx.app.log("what", "WAAAAAAAAAH");
		// move this into another method if you want to have a loading screen		
		gameAssets.init();
		
		artemisWorld.inject(playerFactory);
		
		
		
	}
	
	

}
