package rollerfall;

public class Background {
	private int bgX, bgY, speedY;
	private double rotation;

	public Background(int x, int y) {
		bgX = x;
		bgY = y;
		speedY = 0;
	}

	public void setRotation(double rotation) {
		this.rotation = rotation;
	}

	public void update() {
		bgY += speedY;

		if (bgY <= -1000) {
			bgY += 2000;
		}
	}

	public int getBgX() {
		return bgX;
	}

	public void setBgX(int bgX) {
		this.bgX = bgX;
	}

	public int getBgY() {
		return bgY;
	}

	public void setBgY(int bgY) {
		this.bgY = bgY;
	}

	public int getSpeedY() {
		return speedY;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}

//	public void rotateLeft(double rad) {
//		this.rotation -= rad;
//
//	}
//
//	public void rotateRight(double rad) {
//		// TODO Auto-generated method stub
//		this.rotation += rad;
//	}
//
//	public double getRotation() {
//		// TODO Auto-generated method stub
//		return this.rotation;
//	}
}
