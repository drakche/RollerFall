package rollerfall;

import java.awt.Image;

public class Tile {
	private int tileX, tileY, speedY;
	public Image tileImage;

	private Background bg = StartingClass.getBg1();

	public Tile(int x, int y) {
		// TODO Auto-generated constructor stub
		tileX = x;
		tileY = y;

		tileImage = StartingClass.tile;
	}

	public void update() {
		// TODO Auto-generated method stub
		tileY += speedY;
	}

	public int getTileX() {
		return tileX;
	}

	public void setTileX(int tileX) {
		this.tileX = tileX;
	}

	public int getTileY() {
		return tileY;
	}

	public void setTileY(int tileY) {
		this.tileY = tileY;
	}

	public int getSpeedY() {
		return speedY;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}

	public Image getTileImage() {
		return tileImage;
	}

	public void setTileImage(Image tileImage) {
		this.tileImage = tileImage;
	}

	public Background getBg() {
		return bg;
	}

	public void setBg(Background bg) {
		this.bg = bg;
	}
}
