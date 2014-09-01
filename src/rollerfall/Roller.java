package rollerfall;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;

public class Roller {
	final int MOVESPEED = 15;

	private int FALLSPEED = 1;

	private int centerX = 240;
	private int centerY = 0;
	private int centerXvirt = 240;
	private int centerYvirt = 0;

	private boolean movingLeft = false;
	private boolean movingRight = false;

	private static Background bg1 = StartingClass.getBg1();
	private static Background bg2 = StartingClass.getBg2();

	private static Graphics g = StartingClass.getG();
	private static Path2D.Double path = StartingClass.getPath();

	private int speedX = 0;
	private int speedY = FALLSPEED;

	private int rotate=0;

	private double yTranslate = 0;
	private double xTranslate = 0;

	public void update() {

		// Moves Character or Scrolls Background accordingly.
		if (speedY < 0) {
			centerY += speedY;
			centerYvirt += speedY;
		}
		if (speedY == 0 || speedY < 0) {
			{
				bg1.setSpeedY(0);
				bg2.setSpeedY(0);

			}
		}

		if (centerY <= 400 && speedY > 0) {
			centerY += speedY;
			centerYvirt += speedY;
		}
		if (speedY > 0 && centerY > 400) {
			bg1.setSpeedY(-FALLSPEED * 5);
			bg2.setSpeedY(-FALLSPEED * 5);
//			centerYvirt += speedY;
			yTranslate -= FALLSPEED;
//			xTranslate -= 0.8;
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

	public int getCenterXvirt() {
		return centerXvirt;
	}

	public int getCenterYvirt() {
		return centerYvirt;
	}

	public void moveRight() {
		// speedX = MOVESPEED;
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

	// public static Background getBg2() {
	// return bg2;
	// }
	//
	// public static void setBg2(Background bg2) {
	// Roller.bg2 = bg2;
	// }

	public int getMOVESPEED() {
		return MOVESPEED;
	}

	public int getRotate() {
		return rotate;
	}

	public void setRotate(int rotate) {
		this.rotate = rotate;
	}

	public double getYTranslate() {
		// TODO Auto-generated method stub
		return this.yTranslate;
	}

	public double getXTranslate() {
		// TODO Auto-generated method stub
		return this.xTranslate;
	}

}
