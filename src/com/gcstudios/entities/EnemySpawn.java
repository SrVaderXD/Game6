package com.gcstudios.entities;

import com.gcstudios.main.Game;
import com.gcstudios.world.World;

public class EnemySpawn {

	public int spawnTimer = 60*10;
	private int curTime = 0;

	public void tick() {
		curTime++;

		if (curTime == spawnTimer) {
			curTime = 0;
			int initialPosX = Entity.rand.nextInt((World.WIDTH/2) * 16 - 32) + 16;
			int initialPosY = 16;
			Enemy enemy = new Enemy(initialPosX, initialPosY, 16, 16, 1, Entity.ENEMY1_RIGHT, Entity.ENEMY1_LEFT);
			Game.entities.add(enemy);
		}
	}

}
