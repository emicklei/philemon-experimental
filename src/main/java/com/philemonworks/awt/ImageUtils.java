package com.philemonworks.awt;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;

public class ImageUtils {
public static void draw3DRect(Graphics g, int x, int y, int width, int height, boolean raised) {
	
	Color c = g.getColor();
	Color brighter = c.brighter();
	Color darker = c.darker();

	g.setColor(raised ? Color.gray : Color.black);
	g.fillRect(x, y, 2, height);
	g.fillRect(x, y, width, 2);
	g.setColor(raised ? Color.black : Color.gray);
	g.fillRect(x + 1, y + height - 2, width, 2);
	g.fillRect(x + width - 1, y, 2, height);
	g.setColor(c);
}
public static java.awt.Image getResourceImage(java.applet.Applet applet, String s, int i) {
	java.awt.Image image = null;
	java.io.InputStream inputstream = applet.getClass().getResourceAsStream(s);
	if (inputstream == null) {
		System.out.println("resource not found:" + s);
		return null;
	}
	java.io.ByteArrayOutputStream bytearrayoutputstream = new java.io.ByteArrayOutputStream();
	java.io.BufferedInputStream bufferedinputstream = new java.io.BufferedInputStream(inputstream);
	try {
		int j;
		int k;
		for (k = 0;(j = bufferedinputstream.read()) >= 0; k++)
			bytearrayoutputstream.write(j);
		if (false /*k != i*/)
			System.out.println("Wrong size of resource" + s + ": " + k);
		else
			image = applet.getToolkit().createImage(bytearrayoutputstream.toByteArray());
		bufferedinputstream.close();
	} catch (java.io.IOException ioexception) {
		ioexception.printStackTrace();
	}
	return image;
}
/**
 * 
 * @return java.awt.Image
 */
public static java.awt.image.ImageProducer mirrored(java.awt.Image toMirror, int x, int y, int w, int h, boolean horizontal) {
	
	PixelGrabber grab = new PixelGrabber(toMirror, x, y, w, h, true);
	try {
		grab.grabPixels();
	} catch (InterruptedException ex) {
		return null;
	};
	int[] pixels = (int[]) grab.getPixels();
	int[] mirrored = new int[w * h];
	if (horizontal) {
		for (int r = 0; r < h; r++)
			for (int c = 0; c < w; c++) {
				mirrored[(r+1)*w - c - 1] = pixels[c + (w * r)];
			}
	} else {
		for (int r = 0; r < h; r++)
			for (int c = 0; c < w; c++) {
				mirrored[w*(h-1-r)+c] = pixels[c + (w * r)];
			}
	}
	return new MemoryImageSource(w, h, mirrored, 0, w);
}
/**
 * 
 * @return java.awt.Image
 */
public static java.awt.image.ImageProducer rotated90(java.awt.Image toRotate, int x, int y, int w, int h, boolean clockwise) {
	
	PixelGrabber grab = new PixelGrabber(toRotate, x, y, w, h, true);
	try {
		grab.grabPixels();
	} catch (InterruptedException ex) {
		return null;
	};
	int[] pixels = (int[]) grab.getPixels();
	int[] rotated = new int[w * h];
	if (clockwise) {
		for (int r = 0; r < h; r++)
			for (int c = 0; c < w; c++) {		
				rotated[(c+1)*h-r-1] = pixels[c + (w * r)];
			}
	} else {
		for (int r = 0; r < h; r++)
			for (int c = 0; c < w; c++) {
				rotated[ (w-c-1)*h + r] = pixels[c + (w * r)];
			}
	}
	return new MemoryImageSource(h, w, rotated, 0, h);
}
}
