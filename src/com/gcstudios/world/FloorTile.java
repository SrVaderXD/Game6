package com.gcstudios.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class FloorTile extends Tile {

	public FloorTile(int x, int y, BufferedImage sprite) {
		super(x, y, sprite);
	}

	public void render(Graphics g) {
		if(World.cycle == World.noon) {
			g.drawImage(Tile.NOON_SKY_TILE, x - Camera.x, y - Camera.y, null);
		} else if(World.cycle == World.night) {
			g.drawImage(Tile.NIGHT_SKY_TILE, x - Camera.x, y - Camera.y, null);
		}
	}
}
