package com.rusd.tddwd;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.rusd.tddwd.entity.Entity;
import com.rusd.tddwd.entity.parts.Movable;
import com.rusd.tddwd.entity.player.PlayerFactory;

public class PlayerFactoyrTest {

	@Test
	public void testCreatePlayer() {
//		World world = new World(Vector2.Zero, true);
		Entity createPlayer = PlayerFactory.createPlayer();
		Movable m = createPlayer.getPart(Movable.class);
//		System.out.println(createPlayer.components.keySet());
//		System.out.println(createPlayer.components.get(Movable.class));
		System.out.println("*******");
		System.out.println(Movable.class);
		System.out.println("*******");
		System.out.println( createPlayer.getPart(Movable.class));
		Arrays.toString( createPlayer.components.keySet().toArray());
		Assert.assertNotNull(m);

	}
	

}
