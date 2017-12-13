package com.rusd.tddwd.entity.components;

import java.util.HashMap;
import java.util.Map;

import com.artemis.Component;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.rusd.tddwd.entity.Entity;

public class DrawableComponent extends Component {	
	public Sprite sprite;
	public Animation<TextureRegion> animation;
	public float animTime;
	
	public int xOffset = -2;
	public int yOffset = -2;
	public String current;
	public Map<String, TextureRegion> map = new HashMap<>(); 
	
}
