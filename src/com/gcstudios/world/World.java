package com.gcstudios.world;

import java.awt.Graphics;

import com.gcstudios.entities.Entity;
import com.gcstudios.main.Game;

public class World {

	public static Tile[] tiles;
	public static int WIDTH, HEIGHT;
	public static final int TILE_SIZE = 16;

	public World() {
		String[] tilesType = { "Grass", "Dirt", "Snow", "Sand", "Stone" };
		WIDTH = 200;
		HEIGHT = 80;
		int mapDivider = WIDTH / tilesType.length;
		tiles = new Tile[WIDTH * HEIGHT];
		for (int xx = 0; xx < WIDTH; xx++) {
			int initialHeight = Entity.rand.nextInt(12 - 8) + 8;
			for (int yy = 0; yy < HEIGHT; yy++) {
				if (yy == HEIGHT - 1 || xx == WIDTH - 1 || xx == 0 || yy == 0) {
					tiles[xx + yy * WIDTH] = new WallTile(xx * 16, yy * 16, Tile.STONE_TILE);
					tiles[xx + yy * WIDTH].solid = true;
				} else {

					if (yy >= initialHeight) {
						int biomeIndex = xx / mapDivider;

						if (tilesType[biomeIndex] == "Grass") {
							tiles[xx + yy * WIDTH] = new WallTile(xx * 16, yy * 16, Tile.GRASS_TILE);
						} else if (tilesType[biomeIndex] == "Dirt") {
							tiles[xx + yy * WIDTH] = new WallTile(xx * 16, yy * 16, Tile.DIRT_TILE);
						} else if (tilesType[biomeIndex] == "Snow") {
							tiles[xx + yy * WIDTH] = new WallTile(xx * 16, yy * 16, Tile.SNOW_TILE);
						} else if (tilesType[biomeIndex] == "Sand") {
							tiles[xx + yy * WIDTH] = new WallTile(xx * 16, yy * 16, Tile.SAND_TILE);
						} else if (tilesType[biomeIndex] == "Stone") {
							tiles[xx + yy * WIDTH] = new WallTile(xx * 16, yy * 16, Tile.STONE_TILE);
						} else {
							tiles[xx + yy * WIDTH] = new FloorTile(xx * 16, yy * 16, Tile.SKY_TILE);
						}

					} else {
						tiles[xx + yy * WIDTH] = new FloorTile(xx * 16, yy * 16, Tile.SKY_TILE);

					}
				}
			}
		}

	}

	public static boolean isFree(int xnext, int ynext) {

		int x1 = xnext / TILE_SIZE;
		int y1 = ynext / TILE_SIZE;

		int x2 = (xnext + TILE_SIZE - 1) / TILE_SIZE;
		int y2 = ynext / TILE_SIZE;

		int x3 = xnext / TILE_SIZE;
		int y3 = (ynext + TILE_SIZE - 1) / TILE_SIZE;

		int x4 = (xnext + TILE_SIZE - 1) / TILE_SIZE;
		int y4 = (ynext + TILE_SIZE - 1) / TILE_SIZE;

		return !((tiles[x1 + (y1 * World.WIDTH)] instanceof WallTile)
				|| (tiles[x2 + (y2 * World.WIDTH)] instanceof WallTile)
				|| (tiles[x3 + (y3 * World.WIDTH)] instanceof WallTile)
				|| (tiles[x4 + (y4 * World.WIDTH)] instanceof WallTile));
	}

	public static void restartGame() {
		// TODO
		return;
	}

	public void render(Graphics g) {
		int xstart = Camera.x >> 4;
		int ystart = Camera.y >> 4;

		int xfinal = xstart + (Game.WIDTH >> 4);
		int yfinal = ystart + (Game.HEIGHT >> 4);

		for (int xx = xstart; xx <= xfinal; xx++) {
			for (int yy = ystart; yy <= yfinal; yy++) {
				if (xx < 0 || yy < 0 || xx >= WIDTH || yy >= HEIGHT)
					continue;
				Tile tile = tiles[xx + (yy * WIDTH)];
				tile.render(g);
			}
		}
	}

}
