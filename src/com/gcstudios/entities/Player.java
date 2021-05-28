package com.gcstudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import com.gcstudios.main.Game;
import com.gcstudios.world.Camera;
import com.gcstudios.world.World;

public class Player extends Entity {

	public boolean right, left;

	public static int maxLife = 10, curLife = maxLife;

	public int dir = 1;

	private double gravity = 0.4;
	private double vspd = 0;
	public boolean jump = false, isJumping = false;
	private boolean grounded;

	private int framesWalk = 0, maxFramesWalk = 15;
	private boolean moved = false;

	private int maxSprite = 2;
	private int curSprite = 0;

	private BufferedImage ATTACK_RIGHT = Game.spritesheet.getSprite(0, 96, 16, 16);
	private BufferedImage ATTACK_LEFT = Game.spritesheet.getSprite(16, 96, 16, 16);
	public boolean attack = false;
	private boolean isAttacking = false, damaged = false;
	private int attackFrames = 0, maxAttackFrames = 10, damagedFrames = 0, maxDamagedFrames = 90;

	public Player(int x, int y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		ATTACK_RIGHT = Game.spritesheet.getSprite(0, 96, 16, 16);
		ATTACK_LEFT = Game.spritesheet.getSprite(16, 96, 16, 16);
	}

	public void tick() {
		depth = 2;

		isGrounded();
		walk();
		jump();
		attack();
		collisionWithEnemy();

		Camera.x = Camera.clamp((int) x - Game.WIDTH / 2, 0, World.WIDTH * 16 - Game.WIDTH);
		Camera.y = Camera.clamp((int) y - Game.HEIGHT / 2, 0, World.HEIGHT * 16 - Game.HEIGHT);

		if (curLife == 0)
			System.exit(1);
	}

	public void render(Graphics g) {
		if (moved) {
			framesWalk++;

			if (framesWalk == maxFramesWalk) {
				curSprite++;
				framesWalk = 0;

				if (curSprite == maxSprite) {
					curSprite = 0;
				}
			}
		}
		if (dir == 1) {
			sprite = Entity.PLAYER_SPRITE_RIGHT[curSprite];
			if (isAttacking) {
				int atkX = getX() - Camera.x + 8;
				int atkY = getY() - Camera.y;
				g.drawImage(ATTACK_RIGHT, atkX, atkY, null);
			}
		} else if (dir == -1) {
			sprite = Entity.PLAYER_SPRITE_LEFT[curSprite];
			if (isAttacking) {
				int atkX = getX() - Camera.x - 8;
				int atkY = getY() - Camera.y;
				g.drawImage(ATTACK_LEFT, atkX, atkY, null);
			}
		}
		super.render(g);
	}

	private void walk() {
		moved = false;

		if (right && World.isFree((int) (x + speed), (int) y)) {
			x += speed;
			dir = 1;
			moved = true;
		} else if (left && World.isFree((int) (x - speed), (int) y)) {
			x -= speed;
			dir = -1;
			moved = true;
		}
	}

	private void isGrounded() {
		if (!World.isFree((int) x, (int) (y + 1)) && !isJumping) {
			grounded = true;
		} else if (World.isFree((int) x, (int) (y + 1))) {
			grounded = false;
		}
	}

	private void jump() {

		vspd += gravity;
		if (!World.isFree((int) x, (int) (y + 1)) && jump) {
			vspd = -6;
			jump = false;
			isJumping = true;
		}

		if (!World.isFree((int) x, (int) (y + vspd))) {

			isJumping = true;

			int signVsp = 0;
			if (vspd >= 0) {
				signVsp = 1;
			} else {
				signVsp = -1;
			}
			while (World.isFree((int) x, (int) (y + signVsp))) {
				y += signVsp;
			}
			vspd = 0;
			isJumping = false;
		}

		y += vspd;
	}

	private void attack() {
		if (attack) {

			if (!isAttacking) {
				attack = false;
				isAttacking = true;
			}
		}

		if (isAttacking) {
			attackFrames++;
			if (attackFrames == maxAttackFrames) {
				attackFrames = 0;
				isAttacking = false;
			}
		}

	}

	private void collisionWithEnemy() {
		for (int i = 0; i < Game.entities.size(); i++) {
			Entity e = Game.entities.get(i);

			if (e instanceof Enemy) {

				if ((Entity.rand.nextInt(100) < 30) && !damaged) {
					if (Entity.isColidding(this, e)) {
						curLife -= 1;
						damaged = true;

						if (isAttacking) {
							((Enemy) e).curLife--;
							((Enemy) e).damaged = true;
						}
					}
				}
			}
		}

		if (damaged) {
			damagedFrames++;

			if (damagedFrames == maxDamagedFrames) {
				damagedFrames = 0;
				damaged = false;
			}
		}
	}
}
