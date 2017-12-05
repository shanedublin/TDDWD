package com.rusd.tddwd.entity.parts;

import com.rusd.tddwd.entity.Entity;

public interface Collidable extends Part {

	public void collides(Entity e);
}
