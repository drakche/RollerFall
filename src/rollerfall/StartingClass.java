package rollerfall;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.net.URL;

public class StartingClass extends Applet implements Runnable, KeyListener {

	private static final long serialVersionUID = 1L;
	private Roller roller;
	private Image image, character;
	private static Image backgrnd;
	private URL base;
	private Graphics second;
	private static Background bg1, bg2;

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
	}

	@Override
	public void start() {
		bg1 = new Background(0, 0);
		bg2 = new Background(0, 1000);
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
			repaint();
			try {
				Thread.sleep(17);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}

		}

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
		BufferedImage bimage = new BufferedImage(480, 800,
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
		// TODO Auto-generated method stub
		double rotationRequired = Math.toRadians(bg1.getRotation());
		if (backgrnd == null)
			return;
		BufferedImage bufferedBg = StartingClass.toBufferedImage(backgrnd);
		AffineTransform tx = AffineTransform.getRotateInstance(
				rotationRequired, 240, 400);
		AffineTransformOp op = new AffineTransformOp(tx,
				AffineTransformOp.TYPE_BILINEAR);

		g.drawImage(op.filter(bufferedBg, null), bg1.getBgX(), bg1.getBgY(),
				this);
		g.drawImage(op.filter(bufferedBg, null), bg2.getBgX(), bg2.getBgY(),
				this);

		g.drawImage(character, roller.getCenterX() - 37,
				roller.getCenterY() - 37, this);
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
