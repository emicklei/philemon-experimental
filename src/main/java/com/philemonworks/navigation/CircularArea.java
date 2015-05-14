package com.philemonworks.navigation;

import java.awt.*;

public class CircularArea extends Area {

	public int radius;
	public int centerX;
	public int centerY;
public boolean contains(int x, int y) {

	// can be optimized
	return Math.sqrt((x - centerX) * (x - centerX) + ((y - centerY) * (y - centerY))) <= radius;
}
public java.awt.Point getCenter() {	
	return new Point(centerX,centerY);
}
public void paintOn(java.awt.Graphics gc, int offsetX, int offsetY, float scale) {
	
	int cx = Math.round((centerX - radius) * scale + offsetX);
	int cy = Math.round((centerY - radius) * scale + offsetY);
	int cr = Math.round(radius * 2 * scale);
	gc.setColor(spec.color);
	gc.drawArc(cx, cy, cr, cr, 0, 359);
	gc.setColor(Color.white);
	gc.drawArc(cx + 1, cy + 1, cr - 2, cr - 2, 0, 359);
}
}
