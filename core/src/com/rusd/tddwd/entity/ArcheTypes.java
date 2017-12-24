package com.rusd.tddwd.entity;

import com.artemis.Archetype;
import com.artemis.ArchetypeBuilder;
import com.badlogic.gdx.audio.Sound;
import com.rusd.tddwd.GlobalVaribles;
import com.rusd.tddwd.entity.components.DrawableComponent;
import com.rusd.tddwd.entity.components.Health;
import com.rusd.tddwd.entity.components.Inventory;
import com.rusd.tddwd.entity.components.Item;
import com.rusd.tddwd.entity.components.Movable;
import com.rusd.tddwd.entity.components.Name;
import com.rusd.tddwd.entity.components.Physics;
import com.rusd.tddwd.entity.components.Player;
import com.rusd.tddwd.entity.components.SoundComponent;
import com.rusd.tddwd.entity.components.Stats;
import com.rusd.tddwd.entity.components.Weapon;

public class ArcheTypes {
	
	public Archetype point = new ArchetypeBuilder()
			.add(Physics.class)
			.add(Health.class)
			.build(GlobalVaribles.artemisWorld);
	
	public Archetype item = new ArchetypeBuilder()
			.add(Health.class)
			.add(DrawableComponent.class)
			.add(Name.class)
			.add(Physics.class)
			.add(Item.class)
			.build(GlobalVaribles.artemisWorld);
	
	public Archetype resource = new ArchetypeBuilder()
			.add(Health.class)
			.add(DrawableComponent.class)
			.add(Inventory.class)
			.add(Name.class)
			.add(Physics.class)
			.add(SoundComponent.class)
			.build(GlobalVaribles.artemisWorld);
	
	public Archetype player = new ArchetypeBuilder(resource)
			.add(Player.class)
			.add(Stats.class)
			.add(Movable.class)
			.add(Weapon.class)
			.build(GlobalVaribles.artemisWorld);

	public Archetype projectile = new ArchetypeBuilder()
			.add(Health.class)
			.add(DrawableComponent.class)
			.add(Stats.class)
			.add(Movable.class)
			.add(Name.class)
			.add(Physics.class)
			.add(SoundComponent.class)
			.build(GlobalVaribles.artemisWorld);
	
}
