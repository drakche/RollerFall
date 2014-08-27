package rollerfall;

import java.applet.Applet;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Stroke;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.net.URL;
import java.util.ArrayList;

public class StartingClass extends Applet implements Runnable, KeyListener {

	private static final long serialVersionUID = 1L;
	private Roller roller;
	private Image image, character;
	private static Image backgrnd;
	private URL base;
	private static Graphics second;
	private static Background bg1, bg2;
	public static Image tile;
	private ArrayList<Tile> tileArray = new ArrayList<Tile>();
	private static Path2D.Double path;

	@Override
	public void init() {
		setSize(480, 800);
		setBackground(Color.BLACK);
		setFocusable(true);

		addKeyListener(this);

		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle("RollerFall");

		try {
			base = getDocumentBase();
		} catch (Exception e) {
			// TODO: handle exception
		}
		// Image Setups
		character = getImage(base, "data/roller.png");
		backgrnd = getImage(base, "data/back.png");
		tile = getImage(base, "data/tile.png");
	}

	@Override
	public void start() {
		bg1 = new Background(0, 0);
		bg2 = new Background(0, 1000);

		for (int yCoord = 0; yCoord < 500; yCoord++) {
			float x = 0;
			if (yCoord > 10)
				x = Noise.InterpolatedNoise(yCoord);
			int xCoord = 240 + (int) (240 * x);
			tileArray.add(new Tile(xCoord, yCoord * 40));

		}

		roller = new Roller();
		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		super.stop();
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

	@Override
	public void run() {
		while (true) {

			roller.update();
			bg1.update();
			bg2.update();
			updateTiles();
			repaint();
			try {
				Thread.sleep(17);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}

		}

	}

	private void updateTiles() {

		for (int i = 0; i < tileArray.size(); i++) {
			Tile t = (Tile) tileArray.get(i);
			t.update();
		}

	}

	private void paintTiles(Graphics g) {
		// Path2D.Double path = new Path2D.Double();
		Graphics2D g2D = (Graphics2D) g;
		g2D.setColor(Color.BLACK);

		for (int i = 1; i < tileArray.size() / 2; i++) {
			g2D.setStroke(new BasicStroke(100, BasicStroke.CAP_ROUND,
					BasicStroke.JOIN_ROUND));
			path = new Path2D.Double();
			Tile t1 = (Tile) tileArray.get(i * 2 - 1);
			Tile t2 = (Tile) tileArray.get(i * 2);
			Tile t3 = (Tile) tileArray.get(i * 2 + 1);
			path.moveTo(t1.getTileX(), t1.getTileY());
			double cx1a = t1.getTileX() + (t2.getTileX() - t1.getTileX()) / 3;
			double cy1a = t1.getTileY() + (t2.getTileY() - t1.getTileY()) / 3;
			double cx1b = t2.getTileX() - (t3.getTileX() - t1.getTileX()) / 3;
			double cy1b = t2.getTileY() - (t3.getTileY() - t1.getTileY()) / 3;
			double cx2a = t2.getTileX() + (t3.getTileX() - t1.getTileX()) / 3;
			double cy2a = t2.getTileY() + (t3.getTileY() - t1.getTileY()) / 3;
			double cx2b = t3.getTileX() - (t3.getTileX() - t2.getTileX()) / 3;
			double cy2b = t3.getTileY() - (t3.getTileY() - t2.getTileY()) / 3;
			// g.drawLine(t.getTileX(), t.getTileY(), t2.getTileX(),
			// t2.getTileY());
			path.curveTo(cx1a, cy1a, cx1b, cy1b, t2.getTileX(), t2.getTileY());
			path.curveTo(cx2a, cy2a, cx2b, cy2b, t3.getTileX(), t3.getTileY());
			// BufferedImage bufferedBg =
			// StartingClass.toBufferedImage(backgrnd);
			// AffineTransform tx = AffineTransform.getRotateInstance(
			// Math.toRadians(roller.getRotate()), 240, 400);
			// AffineTransform tx2 = AffineTransform.getTranslateInstance(0,
			// -5);
			// AffineTransformOp op = new AffineTransformOp(tx,
			// AffineTransformOp.TYPE_BILINEAR);
			AffineTransform at = new AffineTransform();
			AffineTransform at2 = new AffineTransform();
			at.rotate(Math.toRadians(roller.getRotate()), 240, roller.getCenterYvirt());
			at2.translate(0, roller.getYTranslate());
			// g2D.rotate(Math.toRadians(roller.getRotate()));
			// path.transform(tx);
			path.transform(at);
			
			path.transform(at2);
			g2D.draw(path);
		}
	}

	public static Path2D.Double getPath() {
		return StartingClass.path;
	}

	@Override
	public void update(Graphics g) {
		if (image == null) {
			image = createImage(this.getWidth(), this.getHeight());
			second = image.getGraphics();
		}

		second.setColor(getBackground());
		second.fillRect(0, 0, getWidth(), getHeight());
		second.setColor(getForeground());
		paint(second);

		g.drawImage(image, 0, 0, this);

	}

	/**
	 * Converts a given Image into a BufferedImage
	 * 
	 * @param img
	 *            The Image to be converted
	 * @return The converted BufferedImage
	 */
	public static BufferedImage toBufferedImage(Image img) {
		if (img instanceof BufferedImage) {
			return (BufferedImage) img;
		}

		// Create a buffered image with transparency
		BufferedImage bimage = new BufferedImage(1000, 1000,
				BufferedImage.TYPE_INT_ARGB);

		// Draw the image on to the buffered image
		Graphics2D bGr = bimage.createGraphics();
		bGr.drawImage(img, 0, 0, null);
		bGr.dispose();

		// Return the buffered image
		return bimage;
	}

	@Override
	public void paint(Graphics g) {
		setGraphics(g);
		// TODO Auto-generated method stub
		// double rotationRequired = Math.toRadians(roller.getRotate());
		if (backgrnd == null)
			return;

		g.drawImage(backgrnd, bg1.getBgX(), bg1.getBgY(), this);
		g.drawImage(backgrnd, bg2.getBgX(), bg2.getBgY(), this);
		paintTiles(g);
		g.drawImage(character, roller.getCenterX() - 37,
				roller.getCenterY() - 37, this);

	}

	private void setGraphics(Graphics g) {
		StartingClass.second = g;

	}

	public static Graphics getG() {
		return StartingClass.second;
	}

	public static Background getBg2() {
		return bg2;
	}

	public static void setBg2(Background bg2) {
		StartingClass.bg2 = bg2;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch (e.getKeyCode()) {
		case KeyEvent.VK_RIGHT:
			roller.moveRight();
			break;
		case KeyEvent.VK_LEFT:
			roller.moveLeft();
			break;

		default:
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		switch (e.getKeyCode()) {
		case KeyEvent.VK_RIGHT:
		case KeyEvent.VK_LEFT:
			roller.stop();
			break;

		default:
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public static Background getBg1() {
		return bg1;
	}

	// public static Background getBg2() {
	// return bg2;
	// }

	public static Image getBackgrnd() {
		return backgrnd;
	}
}
