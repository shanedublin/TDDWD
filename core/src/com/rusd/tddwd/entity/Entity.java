package com.rusd.tddwd.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.rusd.tddwd.entity.components.Collidable;
import com.rusd.tddwd.entity.components.DrawableComponent;
import com.rusd.tddwd.entity.components.Interactable;
import com.rusd.tddwd.entity.components.Part;


public class Entity {
	
	private static AtomicInteger ID = new  AtomicInteger(0);
	
	public int id = ID.incrementAndGet();
	public String name = "Name Not Set";
	
	public Fixture fixture;
	
	
	public Map<Class<Part> , Part> components = new HashMap<>();

	
	
	@SuppressWarnings("unchecked")
	public void addPart(Part c) {
		
		if (c instanceof Collidable) {			
			@SuppressWarnings("rawtypes")
			Class cc = Collidable.class;
			components.put(cc, c);
		}else if(c instanceof DrawableComponent) {
			@SuppressWarnings("rawtypes")
			Class cc = DrawableComponent.class;
			components.put(cc, c);		
		}else if(c instanceof Interactable) {
			@SuppressWarnings("rawtypes")
			Class cc = Interactable.class;
			components.put(cc, c);
		}
		
		else{
			components.put((Class<Part>) c.getClass(), c);
		}
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Part> T  getPart(Class<T> type){
		return (T) components.get(type);
	}
	
	
	public Body getBody() {
		return fixture.getBody();
	}
	
	@Override
	public String toString() {
		return name + " ID: " + id;
	}

}
