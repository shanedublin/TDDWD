package com.rusd.tddwd.grid;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.rusd.tddwd.UtilMethods;
import com.rusd.tddwd.entity.Entity;
import com.rusd.tddwd.entity.parts.DrawablePart;
import com.rusd.tddwd.entity.parts.Health;

public class Grid  {
	
	
	public List<Entity> entities = new ArrayList<>();
	
	Chunk[][] chunks;
	
	public Grid(int x) {
		chunks = new Chunk[x][x];
		for(int i =0; i < x; i++) {
			for(int j =0; j < x; j++) {			
			Chunk c  = new Chunk(i,j,this);
			chunks[i][j] = c;
			}
		}		
	}
	
	public void draw(SpriteBatch batch) {
		
		entities.stream().forEach(e -> {
			DrawablePart dp = e.getPart(DrawablePart.class);
			if(dp != null)
				dp.draw(batch);
		});
		
	}
	
	public void cleanse() {
		
		List<Entity> dead = entities.stream().filter(e ->{
			Health h= e.getPart(Health.class);
			return h.alive() == false;
		}).collect(Collectors.toList());
		
		entities= entities.stream()				
				.filter(UtilMethods::cleanseList)
				.collect(Collectors.toList());
		
		dead.stream().forEach(e -> {
			deleteEntity(e.getBody().getPosition());
		});
		
		
	}
	
	public void deleteEntity(Vector2 mousePos) {
		ChunkInfo ci = new ChunkInfo(mousePos);
		ci.setEntity(null);
		
	}
	
	public boolean tileEmpty(Vector2 mousePos) {
		
		if(mousePos.x <0 || mousePos.y < 0)
			return false;
		
		ChunkInfo ci = new ChunkInfo(mousePos);
		
		if(ci.getEntity() == null)
			return true;
		
		return false;		
	}
	
	
	public void addEntityToGrid(Entity e, Vector2 mousePos) {
		ChunkInfo ci = new ChunkInfo(mousePos);
		int x = (((int)ci.mouseX)) / 4 * 4; 
		int y = (((int)ci.mouseY)) / 4 * 4;
		
		e.getBody().setTransform(new Vector2(x,y), 0);
				
		ci.setEntity(e);	
		entities.add(e);
	}
	
	
	public class ChunkInfo{
		public int mouseX;
		public int mouseY;
		public int chunkX;
		public int chunkY;
		public int tileX;
		public int tileY;

		public ChunkInfo(Vector2 mousePos) {
			mouseX = (int) (mousePos.x +2);
			mouseY = (int) (mousePos.y +2);
			chunkX = (int)mouseX / 256;
			chunkY = (int)mouseY / 256;		
			tileX = (int) mouseX % 256 / 4; 
			tileY = (int) mouseY % 256 / 4;		
		}
		
		public Chunk getChunk() {
			return chunks[chunkX][chunkY];
		}
		
		public Entity getEntity() {
			return getChunk().tiles[tileX][tileY];
		}
		
		public void setEntity(Entity e) {
			getChunk().tiles[tileX][tileY] = e;
		}
	}

}
