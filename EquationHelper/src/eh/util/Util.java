package eh.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Util {
	public static Image solidImage(Color c, int width, int height){
		Image i = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = i.getGraphics();
		g.setColor(c);
		g.fillRect(0, 0, width, height);
		return i;
	}
}
