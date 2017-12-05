package com.rusd.tddwd.entity.parts;

import com.badlogic.gdx.Gdx;

public class Health implements Part {

	public  int health = 1;
	
	public boolean alive(){
		return health > 0;
	}
	
	public void damage(int ammount) {
		health -= ammount;
	}
	
	
}
