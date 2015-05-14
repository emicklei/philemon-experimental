package com.philemonworks.navigation;

/**
 * Insert the type's description here.
 * Creation date: (3-1-01 14:40:08)
 * @author: Ernest Micklei
 */
public class Point3D {

	public double x,y,z;
/**
 * Point3D constructor comment.
 */
public Point3D(double nx, double ny, double nz) {
	super();
	x = nx;
	y = ny;
	z = nz;
}
/**
 * Point3D constructor comment.
 */
public Point3D(String xyzString) {
	super();
	java.util.StringTokenizer tokenizer = new java.util.StringTokenizer(xyzString, ",");
	x = Double.valueOf(tokenizer.nextToken()).doubleValue();
	y = Double.valueOf(tokenizer.nextToken()).doubleValue();
	z = Double.valueOf(tokenizer.nextToken()).doubleValue();
}
/**
 * Point3D constructor comment.
 */
public Point3D crossProduct(Point3D aPoint) {

	return new Point3D(
		y * aPoint.z - ( z * aPoint.y ) ,
		z * aPoint.x - ( x * aPoint.z ) ,
		x * aPoint.y - ( y * aPoint.x ) );
}
/**
 * Point3D constructor comment.
 */
public Point3D divide(double c) {

	return new Point3D(x / c, y / c, z / c);
}
/**
 * Point3D constructor comment.
 */
public double dotProduct(Point3D aPoint) {

	return x* aPoint.x +  (y * aPoint.y) + (z* aPoint.z);
}
/**
 * Point3D constructor comment.
 */
public double length() {

	return Math.sqrt(x * x + (y * y) + ( z * z));
}
/**
 * Point3D constructor comment.
 */
public Point3D minus(Point3D p) {

	return new Point3D(x - p.x,y - p.y,z - p.z);
}
/**
 * Point3D constructor comment.
 */
public Point3D plus(Point3D p) {

	return new Point3D(x + p.x,y + p.y,z + p.z);
}
/**
 * Point3D constructor comment.
 */
public Point3D times(double c) {

	return new Point3D(x * c,y * c,z * c);
}
/**
 * Point3D constructor comment.
 */
public String toString() {

	StringBuffer b = new StringBuffer();
	b.append(x);
	b.append(',');
	b.append(y);
	b.append(',');
	b.append(z);
	return b.toString();
}
}
