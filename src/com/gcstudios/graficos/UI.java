package com.gcstudios.graficos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.gcstudios.entities.Player;
import com.gcstudios.main.Game;

public class UI {

	public static BufferedImage HEART1 = Game.spritesheet.getSprite(0, 80, 16, 16);
	public static BufferedImage HEART2 = Game.spritesheet.getSprite(16, 80, 16, 16);

	private int frames = 0, seconds = 0, minutes = 0;

	public void tick() {

		frames++;

		if (frames == 60) {
			frames = 0;
			seconds++;

			if (seconds == 60) {
				seconds = 0;
				minutes++;
			}
		}

	}

	public void render(Graphics g) {

		drawHeart(g);
		drawTimer(g);
	}

	private void drawHeart(Graphics g) {
		for (int i = 0; i < Player.maxLife; i++) {
			g.drawImage(HEART2, 5 + (i * 34), 8, 40, 40, null);
		}

		for (int i = 0; i < Player.curLife; i++) {
			g.drawImage(HEART1, 5 + (i * 34), 8, 40, 40, null);
		}
	}

	private void drawTimer(Graphics g) {
		String timerFormat = "";

		if (minutes < 10) {
			timerFormat += "0" + minutes + ":";
		} else {
			timerFormat += minutes + ":";
		}

		if (seconds < 10) {
			timerFormat += "0" + seconds;
		} else {
			timerFormat += seconds;
		}

		g.setColor(Color.gray);
		g.setFont(new Font("arial", Font.BOLD, 23));
		g.drawString(timerFormat, 630, 30);
	}
}
