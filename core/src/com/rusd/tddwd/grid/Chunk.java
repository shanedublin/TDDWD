package com.rusd.tddwd.grid;

import com.artemis.BaseSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.rusd.tddwd.GlobalVaribles;
import com.rusd.tddwd.body.BodyOptions;
import com.rusd.tddwd.entity.Entity;
import com.rusd.tddwd.entity.components.Health;
import com.rusd.tddwd.systems.RockFactory;
import com.rusd.tddwd.systems.TreeFactory;

public class Chunk{
	
	
	TreeFactory treeFactory;
	RockFactory rockFactory;
	
	public int[][] tiles;
	Vector2 basePos; 
	public Grid grid;
	
	public Chunk(int xOffset, int yOffset, Grid grid) {
		treeFactory = GlobalVaribles.artemisWorld.getSystem(TreeFactory.class);
		rockFactory = GlobalVaribles.artemisWorld.getSystem(RockFactory.class);
		this.grid = grid;
		basePos = new Vector2(xOffset,yOffset);
		
		
		tiles = new int[64][64];
		
		 
	
		
		
		for(int i = 0; i < 256; i++) {			
			int randX = MathUtils.random(63);
			int randY = MathUtils.random(63);
			if(tiles[randX][randY] != 0) {
				i --;
				continue;
			}			
			createTree(randX, randY);
		}
		
		for(int i = 0; i < 64; i++) {			
			int randX = MathUtils.random(63);
			int randY = MathUtils.random(63);
			if(tiles[randX][randY] != 0) {
				i --;
				continue;
			}			
			createRock(randX, randY);
		}
//		
		
		if(xOffset== 1  && yOffset == 1) {
				createStartChunk();
		}
		
	}
	
	
	public void createStartChunk() {
		
		for(int i = 0; i < 64; i++) {
			for(int j = 0; j < 64; j++) {
				if(i >29 && i < 34 && j  > 29 && j < 34) {
					int e = tiles[i][j];
					if(e != 0) {
//						Health h = e.getPart(Health.class);
//						h.health = 0;
					}
					
				}
			
		}
			
		}
		
	}
	public int createRock(int x , int y) {
		BodyOptions bo = new BodyOptions();
		bo.pos.x = x * 4 + 256* basePos.x;
		bo.pos.y = y * 4 + 256* basePos.y;
		int rock = rockFactory.create(bo);
		tiles[x][y] = rock;
		return rock;
	}
	public int createTree(int x, int y) {
		BodyOptions bo = new BodyOptions();
		bo.pos.x = x * 4 + 256* basePos.x;
		bo.pos.y = y * 4 + 256* basePos.y;
		int rock = treeFactory.create(bo);
		tiles[x][y] = rock;
		return rock;
		
	}


	
	
	
	

}
