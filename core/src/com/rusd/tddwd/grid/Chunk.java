package com.rusd.tddwd.grid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.rusd.tddwd.body.BodyOptions;
import com.rusd.tddwd.entity.Entity;
import com.rusd.tddwd.entity.RockFactory;
import com.rusd.tddwd.entity.TreeFactory;

public class Chunk {
	
	
	public Entity[][] tiles;
	Vector2 basePos; 
	
	public Chunk(int xOffset, int yOffset, Grid grid) {
		
		
		
		tiles = new Entity[64][64];
		
		 
		
		if(xOffset== 1  && yOffset == 1) {
//			 center of map do something else
			for(int x = 0; x < 64; x ++) {
				BodyOptions bo = new BodyOptions();
				bo.pos.x = x * 4 + 256* xOffset;
				bo.pos.y = 0 * 4 + 256* yOffset;				
				Entity rock = RockFactory.create(bo);
				tiles[x][0] = rock;
				grid.entities.add(rock);
				
				bo = new BodyOptions();
				bo.pos.x = x * 4 + 256 * xOffset;
				bo.pos.y = 63 * 4 + 256 * yOffset;				
				rock = RockFactory.create(bo);
				tiles[x][63] = rock;
				grid.entities.add(rock);
				
				bo = new BodyOptions();
				bo.pos.x = 0 * 4 + 256 * xOffset;
				bo.pos.y = x * 4 + 256 * yOffset;				
				rock = RockFactory.create(bo);
				tiles[0][x] = rock;
				grid.entities.add(rock);
				
				bo = new BodyOptions();
				bo.pos.x = 63 * 4 + 256 * xOffset;
				bo.pos.y = x * 4 + 256* yOffset;				
				rock = RockFactory.create(bo);
				tiles[63][x] = rock;
				grid.entities.add(rock);
				
			}
			
			
			return;
		}
		
//		for(int x = 0; x < 63; x++) {
//			for(int y = 0; y < 63; y++) {
//			BodyOptions bo = new BodyOptions();
//			
//			int randX = MathUtils.random(63);
//			int randY = MathUtils.random(63);
////			randX = i;
////			randY = i;
//			
//			bo.pos.x = randX*4 + 64 * xOffset * 4;
//			bo.pos.y = randY*4 + 64 * yOffset * 4;			
//			
//			Entity tree = RockFactory.create(world, bo);
//			Gdx.app.log(randX+"", randY+"");
//			tiles[randX][randY] = tree;
//				
//			}
//			
//		}
		
		
		for(int i = 0; i < 24; i++) {
			BodyOptions bo = new BodyOptions();
			
			int randX = MathUtils.random(63);
			int randY = MathUtils.random(63);
//			randX = i;
//			randY = i;
			
			bo.pos.x = randX*4 + 64 * xOffset * 4;
			bo.pos.y = randY*4 + 64 * yOffset * 4;			
			
			Entity rock = RockFactory.create(bo);
			tiles[randX][randY] = rock;
			grid.entities.add(rock);
		}
//		
		
		
	}
	
	
	
	
	
	

}
