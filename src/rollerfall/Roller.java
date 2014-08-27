package rollerfall;

import java.awt.Graphics;

public class Roller {
	final int MOVESPEED = 15;

	private int FALLSPEED = 1;

	private int centerX = 240;
	private int centerY = 0;

	private boolean movingLeft = false;
	private boolean movingRight = false;

	private static Background bg1 = StartingClass.getBg1();
	private static Background bg2 = StartingClass.getBg2();

	private static Graphics g = StartingClass.getG();
	
	private int speedX = 0;
	private int speedY = FALLSPEED;

	private int rotate;
	
	public void update() {

		// Moves Character or Scrolls Background accordingly.
		if (speedY < 0) {
			centerY += speedY;
		}
		if (speedY == 0 || speedY < 0) {
			{
				bg1.setSpeedY(0);
				bg2.setSpeedY(0);

			}
		}
		if (centerY <= 400 && speedY > 0) {
			centerY += speedY;
		}
		if (speedY > 0 && centerY > 400) {
			bg1.setSpeedY(-FALLSPEED);
			bg2.setSpeedY(-FALLSPEED);
		}

		// Updates X Position

		if (centerX + speedX >= 382) {
			centerX = 382;
		} else {
			centerX += speedX;
		}

		// Prevents going beyond Y coordinate of 0
		if (centerX + speedX <= 37) {
			centerX = 37;
		}
	}

	public void moveRight() {
//		speedX = MOVESPEED;
		this.rotate += MOVESPEED;
		
		
	}

	public void moveLeft() {
		this.rotate -= MOVESPEED;
		
		
	}

	public void stopRight() {
		setMovingRight(false);
		stop();
	}

	public void stopLeft() {
		setMovingLeft(false);
		stop();
	}

	public void stop() {
		if (isMovingRight() == false && isMovingLeft() == false) {
			speedX = 0;
		}

		if (isMovingRight() == false && isMovingLeft() == true) {
			moveLeft();
		}

		if (isMovingRight() == true && isMovingLeft() == false) {
			moveRight();
		}
	}

	public int getCenterX() {
		return centerX;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	public int getSpeedX() {
		return speedX;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public int getSpeedY() {
		return speedY;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}

	public boolean isMovingLeft() {
		return movingLeft;
	}

	public void setMovingLeft(boolean movingLeft) {
		this.movingLeft = movingLeft;
	}

	public boolean isMovingRight() {
		return movingRight;
	}

	public void setMovingRight(boolean movingRight) {
		this.movingRight = movingRight;
	}

	public static Background getBg1() {
		return bg1;
	}

	public static void setBg1(Background bg1) {
		Roller.bg1 = bg1;
	}

//	public static Background getBg2() {
//		return bg2;
//	}
//
//	public static void setBg2(Background bg2) {
//		Roller.bg2 = bg2;
//	}

	public int getMOVESPEED() {
		return MOVESPEED;
	}

	public int getRotate() {
		return rotate;
	}

	public void setRotate(int rotate) {
		this.rotate = rotate;
	}

}
