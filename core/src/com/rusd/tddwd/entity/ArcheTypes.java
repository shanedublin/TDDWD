package com.rusd.tddwd.entity;

import com.artemis.Archetype;
import com.artemis.ArchetypeBuilder;
import com.rusd.tddwd.GlobalVaribles;
import com.rusd.tddwd.entity.components.DrawableComponent;
import com.rusd.tddwd.entity.components.Health;
import com.rusd.tddwd.entity.components.Inventory;
import com.rusd.tddwd.entity.components.Name;
import com.rusd.tddwd.entity.components.Physics;
import com.rusd.tddwd.entity.components.Player;
import com.rusd.tddwd.entity.components.Stats;

public class ArcheTypes {
	
	public Archetype item = new ArchetypeBuilder()
			.add(DrawableComponent.class)
			.add(Name.class)
			.add(Physics.class)
			.build(GlobalVaribles.artemisWorld);
	
	public Archetype resource = new ArchetypeBuilder()
			.add(Health.class)
			.add(DrawableComponent.class)
			.add(Inventory.class)
			.add(Name.class)
			.add(Physics.class)
			.build(GlobalVaribles.artemisWorld);
	
	public Archetype player = new ArchetypeBuilder(resource)
			.add(Player.class)
			.add(Stats.class)
			.build(GlobalVaribles.artemisWorld);
	
}
