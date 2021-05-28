package com.gcstudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.gcstudios.world.FloorTile;
import com.gcstudios.world.Tile;
import com.gcstudios.world.WallTile;
import com.gcstudios.world.World;

public class Enemy extends Entity {

	public boolean right = true, left = false;

	public int life = 3;

	private BufferedImage spriteR, spriteL;

	private int dir = 1;

	public Enemy(double x, double y, int width, int height, double speed, BufferedImage spriteR,
			BufferedImage spriteL) {
		super(x, y, width, height, speed, null);
		this.spriteR = spriteR;
		this.spriteL = spriteL;
	}

	public void tick() {
		if (World.isFree((int) x, (int) (y + 1))) {
			y += 1;
		}

		if (dir == 1) {
			if (World.isFree((int) (x + speed), (int) y)) {
				x += speed;
			} else {
				int nextX = (int) ((x + speed) / 16) + 1;
				int nextY = (int) ((y) / 16);

				if (World.tiles[nextX + nextY * World.WIDTH] instanceof WallTile
						&& !World.tiles[nextX + nextY * World.WIDTH].solid) {
					 
					World.tiles[nextX + nextY * World.WIDTH] = new FloorTile(nextX * 16, nextY * 16, Tile.SKY_TILE);
				}

				dir = -1;
				left = true;
				right = false;
			}
		} else if (dir == -1) {
			if (World.isFree((int) (x - speed), (int) y)) {
				x -= speed;
			} else {
				int nextX = (int) ((x - speed) / 16);
				int nextY = (int) ((y) / 16);

				if (World.tiles[nextX + nextY * World.WIDTH] instanceof WallTile
						&& !World.tiles[nextX + nextY * World.WIDTH].solid) {
					 
					World.tiles[nextX + nextY * World.WIDTH] = new FloorTile(nextX * 16, nextY * 16, Tile.SKY_TILE);
				}
				
				dir = 1;
				right = true;
				left = false;
			}
		}
	}

	public void render(Graphics g) {
		if (right)
			sprite = spriteR;
		else if (left)
			sprite = spriteL;

		super.render(g);
	}

}
