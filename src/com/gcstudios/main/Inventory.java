package com.gcstudios.main;

import java.awt.Color;
import java.awt.Graphics;

import com.gcstudios.world.Tile;

public class Inventory {

	public boolean isPressed = false;
	public int mx, my;
	private int inventoryBoxSize = 50, selected = 0;;
	public String[] item = { "Grass", "Dirt", "Snow", "Sand", "Sky", "" };
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
	}

	public void render(Graphics g) {

		for (int i = 0; i < item.length; i++) {
			g.setColor(Color.gray);
			g.fillRect(initialPosition + (i * inventoryBoxSize) + 1, Game.HEIGHT * Game.SCALE - inventoryBoxSize - 1,
					inventoryBoxSize, inventoryBoxSize);

			g.setColor(Color.black);
			g.drawRect(initialPosition + (i * inventoryBoxSize) + 1, Game.HEIGHT * Game.SCALE - inventoryBoxSize - 1,
					inventoryBoxSize, inventoryBoxSize);

			if (item[i] == "Grass") {
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
			}

			if (selected == i) {
				g.setColor(Color.red);
				g.drawRect(initialPosition + (i * inventoryBoxSize), Game.HEIGHT * Game.SCALE - inventoryBoxSize - 1,
						inventoryBoxSize, inventoryBoxSize);
			}

		}

	}
}
