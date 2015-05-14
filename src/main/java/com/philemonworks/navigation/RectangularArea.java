package com.philemonworks.navigation;

import java.awt.*;

public class RectangularArea extends Area {

	public Rectangle box;
public boolean contains(int x, int y) {
	
	return box.contains(x,y);
}
public java.awt.Point getCenter() {
	
	return new Point(box.x + (box.width/2), box.y + (box.height/2));
}
public void paintOn(java.awt.Graphics gc, int offsetX, int offsetY, float scale) {

	gc.setColor(spec.color);
	int px = Math.round(box.x * scale) + offsetX;
	int py = Math.round(box.y * scale) + offsetY;
	int w = Math.round(box.width * scale);
	int h = Math.round(box.height * scale);
	gc.drawRect(px,py,w,h);
	gc.setColor(Color.white);
	gc.drawRect(px+1,py+1,w-2,h-2);	
}
}
