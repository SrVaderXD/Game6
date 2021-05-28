package com.gcstudios.entities;

import com.gcstudios.main.Game;

public class EnemySpawn {
	
	public int spawnTimer = 60*2;
	private int curTime = 0;
	
	public void tick() {
		curTime++;
		
		if(curTime == spawnTimer) {
			curTime = 0;
			Enemy enemy = new Enemy(16,16,16,16,1,Entity.ENEMY1_RIGHT, Entity.ENEMY1_LEFT);
			Game.entities.add(enemy);
		}
	}

}
