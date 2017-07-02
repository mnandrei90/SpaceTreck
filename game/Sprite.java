import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Sprite {
	private Image image;
    private int w = -1;
    private int h = -1;

    public Sprite(String imagePath) {
        this.image = getImage(imagePath);
    }

	public Sprite(String imagePath, int w, int h) {
		this.image = getImage(imagePath);
		this.w = w;
		this.h = h;
	}

	private Image getImage(String path) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(path));
        } catch (IOException e) {
        }
        return img;
    }

	public int getWidth() {
		return image.getWidth(null);
	}

	public int getHeight() {
		return image.getHeight(null);
	}

	public void draw(Graphics g, int x, int y) {
	    if (w > 0 && h > 0)
		    g.drawImage(image, x, y, w, h, null);
	    else
            g.drawImage(image, x, y, null);
	}
}
