package com.philemonworks.navigation;

import java.applet.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import com.philemonworks.util.*;
import java.io.*;
import java.util.*;
import java.util.StringTokenizer;

public class MultiViewNavigator extends Navigator {

	MultiViewNavigatorPanel multiPanel;
	boolean isUpdating = false;
/**
 * 
 */
public void changed(int originX, int originY, float zoomFactor) {

	if (isUpdating || (multiPanel.cam_focus == null)) return;
	// first make it work
//	System.out.println("originX="+originX);
//	System.out.println("originY="+originY);	
	int screenW = getMap().getSize().width;
	int screenH = getMap().getSize().height;
	double imageW2 = getMap().mapW * multiPanel.zoomFactor / 2.0d;
	double imageH2 = getMap().mapH * multiPanel.zoomFactor / 2.0d;	
	double d = ((screenW / 2.0d) - originX - imageW2) / imageW2;
	double e = (originY + imageH2 - (screenH / 2.0d)) / imageH2;
//	System.out.println("d="+d);
//	System.out.println("e="+e);
	Point3D xd = multiPanel.cam_focus.minus(multiPanel.cam_pos);
	double la = xd.length() * Math.tan(multiPanel.cam_angle_h / 2.0d);	
	double lb = xd.length() * Math.tan(multiPanel.cam_angle_v / 2.0d);
	Point3D a = new Point3D( xd.y, -xd.x, 0);
	a = a.divide(a.length()).times(la);
	Point3D b = new Point3D( -xd.x * xd.z, -xd.y * xd.z, xd.x * xd.x + (xd.y * xd.y));
	b = b.divide(b.length()).times(lb);
//	System.out.println("a="+a);
//	System.out.println("b="+b);
//	System.out.println("dot="+(a.dotProduct(b)));
	double fac = xd.z / ( xd.z + (d * a.z) + (e * b.z));
	Point3D xp = xd.plus(a.times(d)).plus(b.times(e)).times(fac).minus(xd);
	Point3D xw = multiPanel.cam_focus.plus(xp);
//	System.out.println(xw);

	Enumeration enum = getAppletContext().getApplets();
	while (enum.hasMoreElements()) {
		try {
			MultiViewNavigator otherNavigator = (MultiViewNavigator)(enum.nextElement());
			if (!otherNavigator.equals(this))
				otherNavigator.update(xw,zoomFactor);	
		} catch (ClassCastException ex) { }
	}
//	this.update(xw,multiPanel.zoomFactor);
}
/**
 * Return the Map property value.
 * @return com.philemonworks.navigation.NavigatorPanel
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
public NavigatorPanel getMap() {
	if (multiPanel == null) {
		try {
			multiPanel = new com.philemonworks.navigation.MultiViewNavigatorPanel(null);
			multiPanel.setName("Map");
			multiPanel.setLayout(null);
			multiPanel.setBounds(0, 0, getSize().width, getSize().height);
			// user code begin {1}
			multiPanel.navigator = this;
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	}
	return (NavigatorPanel)multiPanel;
}
/**
 * 
 */
public void loadParameters(){

	StringTokenizer tokenizer;
		
	super.loadParameters();
	multiPanel.cam_pos = new Point3D(getParameter("cameraposition"));
	multiPanel.cam_focus  = new Point3D(getParameter("camerafocus"));
	tokenizer = new StringTokenizer(getParameter("viewangles"), ",");	
	multiPanel.cam_angle_h = Integer.parseInt(tokenizer.nextToken()) / 57.29577951f;
	multiPanel.cam_angle_v =  Integer.parseInt(tokenizer.nextToken()) / 57.29577951f;
	
}
/**
 * main entrypoint - starts the part when it is run as an application
 * @param args java.lang.String[]
 */
public static void main(java.lang.String[] args) {
	try {
		Frame frame = new java.awt.Frame();
		MultiViewNavigator aNavigator;
		Class iiCls = Class.forName("com.philemonworks.navigation.MultiViewNavigator");
		ClassLoader iiClsLoader = iiCls.getClassLoader();
		aNavigator = (MultiViewNavigator)java.beans.Beans.instantiate(iiClsLoader,"com.philemonworks.navigation.MultiViewNavigator");
		frame.add("Center", aNavigator);
		frame.setSize(aNavigator.getSize());
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent e) {
				System.exit(0);
			};
		});
		frame.setVisible(true);
	} catch (Throwable exception) {
		System.err.println("Exception occurred in main() of java.applet.Applet");
		exception.printStackTrace(System.out);
	}
}
/**
 * 
 */
public void update(Point3D xw, float zoomFactor) {

	Point3D xp = xw.minus(multiPanel.cam_focus);
//	System.out.println(xp);
	Point3D xd = multiPanel.cam_focus.minus(multiPanel.cam_pos);
	Point3D xv = xd.plus(xp);

	double la = xd.length() * Math.tan(multiPanel.cam_angle_h / 2.0d);	
	double lb = xd.length() * Math.tan(multiPanel.cam_angle_v / 2.0d);
	Point3D a = new Point3D( xd.y, -xd.x, 0);
	a = a.divide(a.length()).times(la);
	Point3D b = new Point3D( -xd.x * xd.z, -xd.y * xd.z, xd.x * xd.x + (xd.y * xd.y));
	b = b.divide(b.length()).times(lb);
	
	double m = a.y/a.x;
	double l = b.y-(m*b.x);
	double n = b.z/l;

	double c = ((xd.y-(m*xd.x))*n-xd.z)/((xv.y-(m*xv.x))*n -xv.z);
	double e = (m*xd.x-xd.y-((m*xv.x-xv.y)*c))/l;			// -1 <= e <= 1
	double d = (-xd.x+(xv.x*c)-(b.x*e))/a.x;					// -1 <= d <= 1

//	System.out.println("d="+d);
//	System.out.println("e="+e);
		
	int screenW = getMap().getSize().width;
	int screenH = getMap().getSize().height;
	double imageW2 = getMap().mapW * zoomFactor / 2.0d;
	double imageH2 = getMap().mapH * zoomFactor / 2.0d;	

	
	double originX = -(1+d)*imageW2+(screenW/2.0d);
	double originY = -(1-e)*imageH2+(screenH/2.0d);
//	System.out.println("originX="+originX);
//	System.out.println("originY="+originY);

	isUpdating = true;	
	getMap().setState((int)Math.round(originX),(int)Math.round(originY),zoomFactor);
	isUpdating = false;
}
}
