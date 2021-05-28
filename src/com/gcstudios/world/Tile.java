package com.gcstudios.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import com.gcstudios.main.Game;

public class Tile {

	public static BufferedImage REMOVE_TILE = Game.spritesheet.getSprite(48, 0, 16, 16);
	public static BufferedImage STONE_TILE = Game.spritesheet.getSprite(48, 16, 16, 16);
	public static BufferedImage GRASS_TILE = Game.spritesheet.getSprite(16, 0, 16, 16);
	public static BufferedImage DIRT_TILE = Game.spritesheet.getSprite(16, 16, 16, 16);
	public static BufferedImage SNOW_TILE = Game.spritesheet.getSprite(32, 0, 16, 16);
	public static BufferedImage SAND_TILE = Game.spritesheet.getSprite(32, 16, 16, 16);
	public static BufferedImage NOON_SKY_TILE = Game.spritesheet.getSprite(0, 0, 16, 16);
	public static BufferedImage NIGHT_SKY_TILE = Game.spritesheet.getSprite(0, 16, 16, 16);

	public static int tileSize = 16;
	private BufferedImage sprite;
	protected int x, y;
	public boolean solid = false;

	public Tile(int x, int y, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}

	public void render(Graphics g) {
		g.drawImage(sprite, x - Camera.x, y - Camera.y, null);
	}

}
