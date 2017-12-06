package com.rusd.tddwd;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;

public class GameInputHandler extends InputAdapter {

	Main game;
	
	public GameInputHandler(Main main) {
		this.game = main;
	}
	
	@Override
	public boolean scrolled(int amount) {
		
		if(amount > 0) {
			game.cam.zoom +=.1;
		}
		if(amount < 0) {
			game.cam.zoom -=.1;
			
		}
		return super.scrolled(amount);
	}

}
