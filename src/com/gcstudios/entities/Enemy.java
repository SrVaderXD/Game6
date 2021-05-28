package com.gcstudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.gcstudios.graficos.UI;
import com.gcstudios.main.Game;
import com.gcstudios.world.Camera;
import com.gcstudios.world.FloorTile;
import com.gcstudios.world.Tile;
import com.gcstudios.world.WallTile;
import com.gcstudios.world.World;

public class Enemy extends Entity {

	public boolean right = true, left = false;

	public int maxLife = 3, curLife = maxLife;

	private BufferedImage spriteR, spriteL;

	private int dir = 1;

	public boolean damaged = false;
	private int showFrames = 0, maxShowFrames = 60*5;

	public Enemy(double x, double y, int width, int height, double speed, BufferedImage spriteR,
			BufferedImage spriteL) {
		super(x, y, width, height, speed, null);
		this.spriteR = spriteR;
		this.spriteL = spriteL;
	}

	public void tick() {
		if (World.isFree((int) x, (int) (y + 1))) {
			y += speed;
		}

		if (dir == 1) {
			if (World.isFree((int) (x + speed), (int) y)) {
				x += speed;
			} else {
				int nextX = (int) ((x + speed) / 16) + 1;
				int nextY = (int) ((y) / 16);

				if (World.tiles[nextX + nextY * World.WIDTH] instanceof WallTile
						&& !World.tiles[nextX + nextY * World.WIDTH].solid) {

					World.tiles[nextX + nextY * World.WIDTH] = new FloorTile(nextX * 16, nextY * 16,
							Tile.NOON_SKY_TILE);
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

					World.tiles[nextX + nextY * World.WIDTH] = new FloorTile(nextX * 16, nextY * 16,
							Tile.NOON_SKY_TILE);
				}

				dir = 1;
				right = true;
				left = false;
			}
		}

		if (curLife == 0) {
			Game.entities.remove(this);
			return;
		}
	}

	public void render(Graphics g) {

		if (right)
			sprite = spriteR;
		else if (left)
			sprite = spriteL;

		if (damaged) {
			
			showFrames++;
			
			if(showFrames == maxShowFrames) {
				showFrames = 0;
				damaged = false;
			}

			int heartY = this.getY() - Camera.y - 7;
			for (int i = 0; i < maxLife; i++) {
				int heartX = this.getX() + (i * 12) - Camera.x - 9;
				g.drawImage(UI.HEART2, heartX, heartY, 10, 10, null);
			}

			for (int i = 0; i < curLife; i++) {
				int heartX = this.getX() + (i * 12) - Camera.x - 9;
				g.drawImage(UI.HEART1, heartX, heartY, 10, 10, null);
			}
		}

		super.render(g);
	}

}
