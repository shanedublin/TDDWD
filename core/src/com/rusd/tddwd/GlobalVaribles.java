package com.rusd.tddwd;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class GlobalVaribles {
	 
	public static GameAssets gameAssets = new GameAssets();
	public static World world = new World(Vector2.Zero, true);
	
	public static void init(){
		Gdx.app.log("what", "WAAAAAAAAAH");
		// move this into another method if you want to have a loading screen
		gameAssets.init();
		
		
		
	}
	
	

}
