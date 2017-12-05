package com.rusd.tddwd;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.graphics.Texture;

public class GameAssets  extends AssetManager{


	
	 
	
	
	public GameAssets() {
		
		this.load("player.png",Texture.class);	
		this.load("rocks.png",Texture.class);	
		this.load("palisade.png",Texture.class);	
		this.load("selector.png",Texture.class);	
		
	
	}
	
	public void init() {
		Gdx.app.log("Loading assets", "Start");		
		this.finishLoading();
		Gdx.app.log("Done Loading assets", "Finish");
	}
	
	

}
