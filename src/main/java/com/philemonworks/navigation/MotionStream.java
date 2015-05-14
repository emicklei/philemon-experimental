package com.philemonworks.navigation;

/**
 * Insert the type's description here.
 * Creation date: (25-11-00 16:16:55)
 * @author: Ernest Micklei
 */
public class MotionStream {

	float distance;
	float fraction = 0.0f;
	float vstart;
	float vmax;
/**
 * MotionStream constructor comment.
 */
public MotionStream(float length, float initialSpeed, float maxSpeed) {
	super();
	distance = length;
	vstart = initialSpeed;
	vmax = maxSpeed;
}
public boolean atEnd() {
	
	return fraction == 1.0f;
}
public float next() {

	if (fraction == 1.0f) return distance;
	if (fraction == 0.0f) {
		fraction = fraction + 0.1f;
		return 0.0f;
	}
	float next = distance * fraction;
	fraction = fraction + 0.1f;
	return next;
}
}
