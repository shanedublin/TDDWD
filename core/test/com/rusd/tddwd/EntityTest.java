package com.rusd.tddwd;

import org.junit.Test;

import com.rusd.tddwd.entity.Entity;
import com.rusd.tddwd.entity.ProjectileCollision;
import com.rusd.tddwd.entity.parts.Collidable;
import com.rusd.tddwd.entity.parts.Health;

import junit.framework.Assert;

public class EntityTest {
	
	
	@Test
	public void testSettingColidable() {
		
		Entity e = new Entity();
		Health h = new Health();
		ProjectileCollision pc = new ProjectileCollision(1, h);
		e.addPart(h);
		e.addPart(pc);
		
		
		Health hh = e.getPart(Health.class);
		Assert.assertNotNull(hh);
		Collidable c = e.getPart(Collidable.class);
		Assert.assertNotNull(c);
		
		c.collides(e);
		
	}

}
