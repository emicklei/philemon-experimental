package com.philemonworks.navigation;

public class MultiViewNavigatorPanel extends NavigatorPanel {

	public MultiViewNavigator navigator;
	public float lastZoomFactor = 0.0f;
	public int lastOriginX = 0;
	public int lastOriginY = 0;

	public Point3D cam_pos;
	public Point3D cam_focus;	

	public float cam_angle_h;
	public float cam_angle_v;	
/**
 * MultiViewNavigatorPanel constructor comment.
 * @param layout java.awt.LayoutManager
 */
public MultiViewNavigatorPanel(java.awt.LayoutManager layout) {
	super(layout);
}
public void repaint() {
	
	if (lastZoomFactor == zoomFactor && lastOriginX == originX && lastOriginY == originY) {
		super.repaint();
		return;
	}
	lastZoomFactor = zoomFactor;
	lastOriginX = originX;
	lastOriginY = originY;
	navigator.changed(originX,originY,zoomFactor);
	super.repaint();
}
}
