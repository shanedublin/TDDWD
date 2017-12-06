package com.rusd.tddwd.grid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.rusd.tddwd.body.BodyOptions;
import com.rusd.tddwd.entity.Entity;
import com.rusd.tddwd.entity.RockFactory;
import com.rusd.tddwd.entity.TreeFactory;
import com.rusd.tddwd.entity.parts.Health;

public class Chunk {
	
	
	public Entity[][] tiles;
	Vector2 basePos; 
	public Grid grid;
	
	public Chunk(int xOffset, int yOffset, Grid grid) {
		this.grid = grid;
		basePos = new Vector2(xOffset,yOffset);
		
		
		tiles = new Entity[64][64];
		
		 
	
		
		
		for(int i = 0; i < 256; i++) {			
			int randX = MathUtils.random(63);
			int randY = MathUtils.random(63);
			if(tiles[randX][randY] != null) {
				i --;
				continue;
			}			
			createTree(randX, randY);
		}
		
		for(int i = 0; i < 64; i++) {			
			int randX = MathUtils.random(63);
			int randY = MathUtils.random(63);
			if(tiles[randX][randY] != null) {
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
					Entity e = tiles[i][j];
					if(e != null) {
						Health h = e.getPart(Health.class);
						h.health = 0;
					}
					
				}
			
		}
			
		}
		
	}
	public Entity createRock(int x , int y) {
		BodyOptions bo = new BodyOptions();
		bo.pos.x = x * 4 + 256* basePos.x;
		bo.pos.y = y * 4 + 256* basePos.y;
		Entity rock = RockFactory.create(bo);
		tiles[x][y] = rock;
		grid.entities.add(rock);
		return rock;
	}
	public Entity createTree(int x, int y) {
		BodyOptions bo = new BodyOptions();
		bo.pos.x = x * 4 + 256* basePos.x;
		bo.pos.y = y * 4 + 256* basePos.y;
		Entity rock = TreeFactory.create(bo);
		tiles[x][y] = rock;
		grid.entities.add(rock);
		return rock;
		
	}
	
	
	
	
	

}
