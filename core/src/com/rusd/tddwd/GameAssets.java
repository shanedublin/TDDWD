package com.rusd.tddwd;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GameAssets  extends AssetManager{


	public Animation<TextureRegion> playerAnimation;
	public TextureRegion dogUp;
	public TextureRegion dogDown;
	public TextureRegion dogLeft;
	public TextureRegion dogRight;

	
	
	public GameAssets() {
		
		this.load("player.png",Texture.class);	
		this.load("rocks.png",Texture.class);	
		this.load("palisade.png",Texture.class);	
		this.load("selector.png",Texture.class);	
		this.load("red.png",Texture.class);	
		this.load("green.png",Texture.class);	
		this.load("tree.png",Texture.class);	
		this.load("tall-tree.png",Texture.class);	
		this.load("blob.png",Texture.class);	
		this.load("dog.png",Texture.class);	
		this.load("dog-sheet.png",Texture.class);	
		this.load("item-log.png",Texture.class);	
		this.load("item-rocks.png",Texture.class);	
		
		
	
	}
	
	public void init() {
		Gdx.app.log("Loading assets", "Start");		
		this.finishLoading();
		Gdx.app.log("Done Loading assets", "Finish");
		
		Texture t = GlobalVaribles.gameAssets.get("blob.png",Texture.class);
		
		TextureRegion[][] tmp = TextureRegion.split(t, 64, 64);
		TextureRegion[] frames = new TextureRegion[2];
		frames[0] = tmp[0][0];
		frames[1] = tmp[0][1];
		playerAnimation = new Animation<TextureRegion>(0.25f,frames);
		playerAnimation.setPlayMode(PlayMode.LOOP);
		
		TextureRegion[][] dogRegion = TextureRegion.split(get("dog-sheet.png"), 32, 32);
		dogDown = dogRegion[0][0];
		dogUp = dogRegion[1][0];
		dogRight = dogRegion[2][0];
		dogLeft = dogRegion[3][0];
		
		
		
	}
	
	

}
