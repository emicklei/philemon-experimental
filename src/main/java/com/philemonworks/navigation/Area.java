package com.philemonworks.navigation;

public class Area {

	public AreaSpec spec;
public boolean contains(int x, int y) {
	
	return false;
}
public java.awt.Point getCenter() {
	return null;
}
public static Area newFromSpec(AreaSpec spec) {

	if (spec.shape.equals("rect")) {
		RectangularArea rect = new RectangularArea();
		rect.box = new java.awt.Rectangle(
			spec.coords[0], spec.coords[1],
			spec.coords[2] - spec.coords[0],
			spec.coords[3] - spec.coords[1]);
		rect.spec = spec;
		return rect;
	} else if (spec.shape.equals("circle")) {
		CircularArea circ = new CircularArea();
		circ.centerX = spec.coords[0];
		circ.centerY = spec.coords[1];		
		circ.radius = spec.coords[2];
		circ.spec = spec;
		return circ;
	} else if (spec.shape.equals("poly")) {
		PolygonArea poly = new PolygonArea();
		int npoints = spec.coords.length / 2;
		int[] xpoints = new int[npoints];
		int[] ypoints = new int[npoints];
		for (int i=0;i<npoints;i++) {
			xpoints[i]=spec.coords[i*2];
			ypoints[i]=spec.coords[i*2+1];
		}			
		poly.polygon = new java.awt.Polygon(xpoints, ypoints, npoints);
		poly.spec = spec;
		return poly;
	}
	Area empty = new Area();
	empty.spec = spec;
	return empty;
}
public void paintOn(java.awt.Graphics gc, int offsetX, int offsetY, float scale) {
}
}
