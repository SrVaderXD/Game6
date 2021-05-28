package com.gcstudios.main;

import java.awt.Color;
import java.awt.Graphics;

import com.gcstudios.world.Camera;
import com.gcstudios.world.FloorTile;
import com.gcstudios.world.Tile;
import com.gcstudios.world.WallTile;
import com.gcstudios.world.World;

public class Inventory {

	public boolean isPressed = false, isPlacing = false;
	public int mx, my;
	private int inventoryBoxSize = 50, selected = 0;;
	public String[] item = { "Remove", "Grass", "Dirt", "Snow", "Sand", "Stone" };
	public int initialPosition = ((Game.WIDTH * Game.SCALE) / 2) - ((item.length * inventoryBoxSize) / 2);

	public void tick() {
		if (isPressed) {
			isPressed = false;

			if (mx >= initialPosition && mx < initialPosition + (inventoryBoxSize * item.length)) {
				if (my >= Game.HEIGHT * Game.SCALE - inventoryBoxSize - 1
						&& my < Game.HEIGHT * Game.SCALE - inventoryBoxSize - 1 + inventoryBoxSize) {
					selected = (int) (mx - initialPosition) / inventoryBoxSize;
				}
			}
		}

		if (isPlacing) {
			isPlacing = false;
			mx = (int) mx / Game.SCALE + Camera.x;
			my = (int) my / Game.SCALE + Camera.y;

			int tileX = mx / Tile.tileSize;
			int tileY = my / Tile.tileSize;

			if (!World.tiles[tileX + tileY * World.WIDTH].solid) {
				if (item[selected] == "Grass") {
					World.tiles[tileX + tileY * World.WIDTH] = new WallTile(tileX * 16, tileY * 16, Tile.GRASS_TILE);
				} else if (item[selected] == "Dirt") {
					World.tiles[tileX + tileY * World.WIDTH] = new WallTile(tileX * 16, tileY * 16, Tile.DIRT_TILE);
				} else if (item[selected] == "Snow") {
					World.tiles[tileX + tileY * World.WIDTH] = new WallTile(tileX * 16, tileY * 16, Tile.SNOW_TILE);
				} else if (item[selected] == "Sand") {
					World.tiles[tileX + tileY * World.WIDTH] = new WallTile(tileX * 16, tileY * 16, Tile.SAND_TILE);
				} else if (item[selected] == "Stone") {
					World.tiles[tileX + tileY * World.WIDTH] = new WallTile(tileX * 16, tileY * 16, Tile.STONE_TILE);
				} else if (item[selected] == "Remove") {
					World.tiles[tileX + tileY * World.WIDTH] = new FloorTile(tileX * 16, tileY * 16, Tile.NOON_SKY_TILE);
				}

				if (!World.isFree(Game.player.getX(), Game.player.getY())) {
					World.tiles[tileX + tileY * World.WIDTH] = new FloorTile(tileX * 16, tileY * 16, Tile.NOON_SKY_TILE);
				}
			}
		}
	}

	public void render(Graphics g) {

		for (int i = 0; i < item.length; i++) {
			g.setColor(Color.gray);
			g.fillRect(initialPosition + (i * inventoryBoxSize) + 1, Game.HEIGHT * Game.SCALE - inventoryBoxSize - 1,
					inventoryBoxSize, inventoryBoxSize);

			g.setColor(Color.black);
			g.drawRect(initialPosition + (i * inventoryBoxSize) + 1, Game.HEIGHT * Game.SCALE - inventoryBoxSize - 1,
					inventoryBoxSize, inventoryBoxSize);

			if (item[i] == "Remove") {
				g.drawImage(Tile.REMOVE_TILE, initialPosition + (i * inventoryBoxSize) + 8,
						Game.HEIGHT * Game.SCALE - inventoryBoxSize + 8, 36, 36, null);
			} else if (item[i] == "Grass") {
				g.drawImage(Tile.GRASS_TILE, initialPosition + (i * inventoryBoxSize) + 8,
						Game.HEIGHT * Game.SCALE - inventoryBoxSize + 8, 36, 36, null);
			} else if (item[i] == "Dirt") {
				g.drawImage(Tile.DIRT_TILE, initialPosition + (i * inventoryBoxSize) + 8,
						Game.HEIGHT * Game.SCALE - inventoryBoxSize + 8, 36, 36, null);
			} else if (item[i] == "Snow") {
				g.drawImage(Tile.SNOW_TILE, initialPosition + (i * inventoryBoxSize) + 8,
						Game.HEIGHT * Game.SCALE - inventoryBoxSize + 8, 36, 36, null);
			} else if (item[i] == "Sand") {
				g.drawImage(Tile.SAND_TILE, initialPosition + (i * inventoryBoxSize) + 8,
						Game.HEIGHT * Game.SCALE - inventoryBoxSize + 8, 36, 36, null);
			} else if (item[i] == "Stone") {
				g.drawImage(Tile.STONE_TILE, initialPosition + (i * inventoryBoxSize) + 8,
						Game.HEIGHT * Game.SCALE - inventoryBoxSize + 8, 36, 36, null);
			}

			if (selected == i) {
				g.setColor(Color.red);
				g.drawRect(initialPosition + (i * inventoryBoxSize), Game.HEIGHT * Game.SCALE - inventoryBoxSize - 1,
						inventoryBoxSize, inventoryBoxSize);
			}

		}

	}
}
