package com.rusd.tddwd;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class GameAssets  extends AssetManager{


//	public Animation<TextureRegion> playerAnimation;
	public TextureRegion dogUp;
	public TextureRegion dogDown;
	public TextureRegion dogLeft;
	public TextureRegion dogRight;
	public TextureAtlas atlas;

	
	
	public GameAssets() {
		
//		this.load("player.png",Texture.class);	
//		this.load("rocks.png",Texture.class);	
//		this.load("palisade.png",Texture.class);	
//		this.load("selector.png",Texture.class);	
//		this.load("red.png",Texture.class);	
//		this.load("green.png",Texture.class);	
//		this.load("tree.png",Texture.class);	
//		this.load("tall-tree.png",Texture.class);	
//		this.load("blob.png",Texture.class);	
//		this.load("dog.png",Texture.class);	
//		this.load("mantis.png",Texture.class);	
//		this.load("dog-sheet.png",Texture.class);	
//		this.load("item-log.png",Texture.class);	
//		this.load("item-rocks.png",Texture.class);
//		this.load("laser.png",Texture.class);		
		this.load("packed/game.atlas",TextureAtlas.class);		
		this.load("bfxr/tree-chop.wav",Sound.class);
		this.load("bfxr/laser.wav",Sound.class);	
	}
	
	public Sprite getSprite(String name) {
		return atlas.createSprite(name);		
	}
	
	public void init() {
		Gdx.app.log("Loading assets", "Start");		
		this.finishLoading();
		Gdx.app.log("Done Loading assets", "Finish");
		
		this.atlas = this.get("packed/game.atlas",TextureAtlas.class);
		
		
//		Texture t = GlobalVaribles.gameAssets.get("blob.png",Texture.class);
//		
//		TextureRegion[][] tmp = TextureRegion.split(t, 64, 64);
//		TextureRegion[] frames = new TextureRegion[2];
//		frames[0] = tmp[0][0];
//		frames[1] = tmp[0][1];
//		playerAnimation = new Animation<TextureRegion>(0.25f,frames);
//		playerAnimation.setPlayMode(PlayMode.LOOP);
		Array<AtlasRegion> dogRegion = atlas.findRegions("dog-sheet");
		
		dogDown = dogRegion.get(0);
		dogUp = dogRegion.get(1);
		dogRight = dogRegion.get(2);
		dogLeft = dogRegion.get(3);
		
		
		
	}
	
	

}
