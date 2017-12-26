package com.rusd.tddwd.body;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class BodyOptions {

	public Vector2 pos = new Vector2(0, 0);
	public float size = 1;
	public float density = 1.0f;
	public boolean isSensor = false;
	public BodyType bodyType = BodyType.DynamicBody;
	/**
	 * This is what it can collide with
	 */
	public short maskBits;
	/**
	 * This is what it is, Others will reference this.
	 */
	public short categoryBits;
	public float linearDamping = 5f;
	public Shapes shape = Shapes.CIRCLE;
	public float angle = 0;

}
