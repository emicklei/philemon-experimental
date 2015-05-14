package com.philemonworks.navigation;

import java.awt.*;

public class PolygonArea extends RectangularArea {

	public Polygon polygon;
	Point center;
public boolean contains(int x,int y) {
	return polygon.contains(x,y);
}
public java.awt.Point getCenter() {
	
	if (center == null) {
		Rectangle box = polygon.getBounds();
		center = new Point(box.x + (box.width / 2), box.y + (box.height / 2));
	}
	return center;
}
public void paintOn(java.awt.Graphics gc, int offsetX, int offsetY, float scale) {

	int cx = Math.round(getCenter().x * scale) + offsetX;
	int cy = Math.round(getCenter().y * scale) + offsetY;
	int l = polygon.xpoints.length;
	int[] xPoints = new int[l];
	int[] yPoints = new int[l];
	System.arraycopy(polygon.xpoints,0,xPoints,0,l);
	System.arraycopy(polygon.ypoints,0,yPoints,0,l);	

	for (int i=0;i<l;i++) xPoints[i] = Math.round(xPoints[i]*scale) + offsetX;
	for (int i=0;i<l;i++) yPoints[i] = Math.round(yPoints[i]*scale) + offsetY;

	gc.setColor(spec.color);	
	gc.drawPolyline(xPoints,yPoints,polygon.npoints);
	for (int i=0;i<l;i++) if (xPoints[i] < cx) xPoints[i]++; else xPoints[i]--;
	for (int i=0;i<l;i++) if (yPoints[i] < cy) yPoints[i]++; else yPoints[i]--;
	gc.setColor(Color.white);	
	gc.drawPolyline(xPoints,yPoints,polygon.npoints);
	
}
}
