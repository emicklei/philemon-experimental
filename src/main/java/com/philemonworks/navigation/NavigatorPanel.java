package com.philemonworks.navigation;

import java.awt.*;
import com.philemonworks.awt.*;
import java.awt.image.*;
import java.awt.event.*;

public class NavigatorPanel extends java.awt.Panel implements 
		java.awt.event.MouseMotionListener , 
		java.awt.event.MouseListener , 
		java.awt.event.KeyListener ,
		Runnable {

	// parameters
	public Color backColor = Color.gray;
	public int delay = 0;
	public int marge = 10;
	public int grid = 100;
	public float minZoom = 0.2f;
	public float maxZoom = 2.0f;
	public boolean followMouse = true;
	public int moveStep = 10;
	public MapSpec mapSpec;

	// image
	public Image map = null;
	public Image backImage;
	public int originX = 0;
	public int originY = 0;
	public int mapW;
	public int mapH;
	public float zoomFactor = 1.0f;
	public boolean showGrid = false;
	
	// interaction
	public int lastDragX;
	public int lastDragY;
	public long lastDragTime;
	public boolean isDragging = false;
	public int dragDeltaX = 0;
	public int dragDeltaY = 0;
	public Thread motionThread;
	public Area activeArea;
	private boolean alive = false;
	public int mouseX;
	public int mouseY;
	public int targetX;
	public int targetY;

	// statics
	public static Color PopupBackColor = new Color(255,255,192);
	public static Font PopupFont = new Font("verdana",Font.BOLD,12);
/**
 * NavigatorPanel constructor comment.
 * @param layout java.awt.LayoutManager
 */
public NavigatorPanel(java.awt.LayoutManager layout) {
	super(layout);
}
/**
 * run method comment.
 */
public void addListeners() {

	this.addMouseMotionListener(this);
	this.addMouseListener(this);
	this.addKeyListener(this);
}
public void changedZoom(float oldZoom, int screenX, int screenY) {

	// recompute origin
	float mx = (screenX - originX) / oldZoom;
	targetX = originX = Math.round(screenX - (mx * zoomFactor));
	float my = (screenY - originY) / oldZoom;
	targetY = originY = Math.round(screenY - (my * zoomFactor));	
	this.checkPosition();	
}
/**
 * run method comment.
 */
public synchronized void checkMotion() {

	if (targetX  == originX  && targetY == originY) {
		alive = false;
		motionThread = null;
	} else {
		if (motionThread == null) {
			alive = true;
			motionThread = new Thread(this);
			motionThread.start();
		}
	}
}
/**
 * run method comment.
 */
public void checkPosition() {
	
	if (originX > marge) originX = marge;
	if (originY > marge) originY = marge;	
	
	if (targetX > marge) targetX = marge;
	if (targetY > marge) targetY = marge;

	int minOriginX =  Math.min(marge, getSize().width - marge - Math.round(mapW * zoomFactor));
	int minOriginY =  Math.min(marge, getSize().height - marge - Math.round(mapH * zoomFactor));

	if (originX < minOriginX) originX = minOriginX;
	if (targetX < minOriginX) targetX = minOriginX;
	
	if (originY < minOriginY) originY = minOriginY;
	if (targetY < minOriginY) targetY = minOriginY;	
}
/**
 * run method comment.
 */
public void checkZoomFactor() {

	if (zoomFactor < minZoom) zoomFactor = minZoom;
	if (zoomFactor > maxZoom) zoomFactor = maxZoom;
	checkPosition();
}
/**
 * keyPressed method comment.
 */
public void keyPressed(java.awt.event.KeyEvent e) {

	float oldZoom = zoomFactor;
	activeArea = null; // get rid of pop-up	
	switch (e.getKeyCode()) {
		case KeyEvent.VK_UP :
			targetY = targetY+moveStep;
			break;
		case KeyEvent.VK_DOWN :
			targetY = targetY-moveStep;
			break;
		case KeyEvent.VK_LEFT :
			targetX = targetX+moveStep;
			break;
		case KeyEvent.VK_RIGHT :
			targetX = targetX-moveStep;
			break;
		case KeyEvent.VK_ESCAPE :
			zoomFactor = 1;
			targetX = marge;
			targetY = marge;
			break;
		case 71 :	// g
			showGrid = !showGrid;
			repaint();
			break;	
		case 109 : // -
			zoomFactor = zoomFactor - 0.1f;
			this.checkZoomFactor();
			this.changedZoom(oldZoom,getSize().width/2,getSize().height/2);
			repaint();
			break;
		case 107 : // +				
			zoomFactor = zoomFactor + 0.1f;
			this.checkZoomFactor();
			this.changedZoom(oldZoom,getSize().width/2,getSize().height/2);
			repaint();
			break;			
		default: System.out.println(e);
	}
	this.checkPosition();
	this.checkMotion();
}
/**
 * keyReleased method comment.
 */
public void keyReleased(java.awt.event.KeyEvent e) {}
/**
 * keyTyped method comment.
 */
public void keyTyped(java.awt.event.KeyEvent e) {}
/**
 * mouseClicked method comment.
 */
public void mouseClicked(java.awt.event.MouseEvent e) {

	if (activeArea == null) return;
	try {
		((Navigator)getParent()).clickedArea(activeArea);
	} catch (Throwable t) {}
	
}
/**
 * mouseDragged method comment.
 */
public void mouseDragged(java.awt.event.MouseEvent e) {

	if (!isDragging) return;
	activeArea = null; // get rid of pop-up
	
	if (e.getModifiers() == java.awt.event.MouseEvent.BUTTON3_MASK) {
		
		float oldZoom = zoomFactor;
		zoomFactor = zoomFactor + (lastDragY - e.getY()) * 0.001f;
		this.checkZoomFactor();
		if (oldZoom != zoomFactor) {
			this.changedZoom(oldZoom,e.getX(),e.getX());			
			repaint();
		}
		return;
	}
	int dx,dy;
	if (followMouse) {
		dx = e.getX() - lastDragX;
		dy = e.getY() - lastDragY;
	} else {
		dx = lastDragX  - e.getX();
		dy = lastDragY  - e.getY();
	}
	lastDragX = e.getX();
	lastDragY = e.getY();
	originX = originX + dx;
	originY = originY + dy;
	targetX = originX + dx;
	targetY = originY + dy;
	checkPosition();
	repaint();	
}
/**
 * mouseEntered method comment.
 */
public void mouseEntered(java.awt.event.MouseEvent e) {}
/**
 * mouseExited method comment.
 */
public void mouseExited(java.awt.event.MouseEvent e) {}
public void mouseMoved(java.awt.event.MouseEvent e) {

	// remember mouse position for popup
	mouseX = e.getX();
	mouseY = e.getY();

	// compute map coordinates	
	int px = Math.round((mouseX - originX) / zoomFactor);
	int py = Math.round((mouseY - originY) / zoomFactor);

	this.mouseOver(px,py);
}
// Mouse is at map position px, py (corrected for translation and zoomfactor)
// If the mouse is over an area, make it active and repaint the receiver

public void mouseOver(int px, int py) {

	for (int a=0;a<mapSpec.areas.length;a++) {
		if (mapSpec.areas[a].contains(px,py)) {
			activeArea = mapSpec.areas[a];
			this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			// this can be optimized by providing the damage box
			this.repaint();
			return;
		}
	}
	this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	if (activeArea != null)	this.repaint();
	activeArea = null;
}
/**
 * mousePressed method comment.
 */
public void mousePressed(java.awt.event.MouseEvent e) {

	lastDragX = e.getX();
	lastDragY = e.getY();
	lastDragTime = e.getWhen();
	isDragging = true;
	this.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
}
/**
 * mouseReleased method comment.
 */
public void mouseReleased(java.awt.event.MouseEvent e) {
	
	isDragging = false;
	this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
	checkMotion();
}
/**
 * Paints the applet.
 * If the applet does not need to be painted (e.g. if it is only a container for other
 * awt components) then this method can be safely removed.
 * 
 * @param g  the specified Graphics window
 * @see #update
 */
public void paint(Graphics g) {

	if (backImage == null) return;
	Graphics backGC = backImage.getGraphics();
	backGC.setColor(backColor);
	backGC.fillRect(0, 0, getSize().width, getSize().height);
	backGC.setColor(getForeground());
	int w = Math.round(mapW * zoomFactor);
	int h = Math.round(mapH * zoomFactor);
	backGC.drawImage(map,originX,originY, w, h, this);
	if (showGrid)	
		this.paintGridOn(backGC,originX, originY, getSize().width, getSize().height);
	if (activeArea != null) {
		activeArea.paintOn(backGC,originX, originY, zoomFactor);
		this.paintPopupOn(backGC);
	}
	g.drawImage(backImage,0,0,getSize().width,getSize().height,this);
}
/**
 * 
 */
public void paintGridOn(java.awt.Graphics g, int xs, int ys, int w, int h) {

	g.setColor(Color.lightGray);
	int gz = Math.round(grid * zoomFactor);
	for (int x = xs; x < w; x = x + gz)
		g.drawLine(x, 0, x, h + gz);
	for (int x = xs - gz; x > 0; x = x - gz)
		g.drawLine(x, 0, x, h + gz);		
	for (int y = ys; y < h; y = y + gz)
		g.drawLine(0, y, w + gz, y);
	for (int y = ys - gz; y > 0; y = y - gz)
		g.drawLine(0, y, w + gz, y);		
	
}

public void paintPopupOn(Graphics g) {
	
	g.setFont(PopupFont);
	int textWidth = g.getFontMetrics().stringWidth(activeArea.spec.alt);
	int textHeight = g.getFontMetrics().getAscent();
	g.setColor(PopupBackColor);
	g.fillRoundRect(mouseX - (textWidth/2), mouseY + 22, textWidth + 6, textHeight + 4, 2, 2);
	g.setColor(Color.black);
	g.drawRoundRect(mouseX - (textWidth/2), mouseY + 22, textWidth + 6, textHeight + 4, 2, 2);
	g.drawString(activeArea.spec.alt, mouseX + 4 - (textWidth/2), mouseY + textHeight + 22);
}
/**
 * 
 */
public void position(int wherex, int wherey) {

	targetX = Math.round((getSize().width / 2) - (wherex * zoomFactor));
	targetY = Math.round((getSize().height / 2) - (wherey * zoomFactor));
	this.checkPosition();
	mouseX = targetX + Math.round(wherex * zoomFactor);
	mouseY = targetY + Math.round(wherey * zoomFactor);			
	this.checkMotion();
}
/**
 * run method comment.
 */
public void run() {

	originX = targetX;
	originY = targetY;
	repaint();
	checkMotion();
}
public void setMap(Image anImage, int width, int height) {

	map = anImage;
	mapW =  width;
	mapH = height;
	// compute minZoom
	float zoomX = Math.min(1.0f, (getSize().width - marge - marge) * 1.0f / mapW);
	float zoomY = Math.min(1.0f, (getSize().height - marge - marge)* 1.0f / mapH);
	minZoom = Math.min(zoomX,zoomY);
}	
public void setState(int ox,int oy,float zoom) {

	originX = ox;
	originY = oy;
	zoomFactor = zoom;
	repaint();
}
// Pre: map is set using setMap(Image,int,int)

public void setZoomFactor(float newZoom) {

	zoomFactor = Math.max(minZoom, newZoom);
}	
/**
 * 
 */
public void show(String areaTitle) {

	Area area = mapSpec.areaAt(areaTitle);
	if (area != null) {
		activeArea = area;
		this.position(area.getCenter().x,area.getCenter().y);
	}
}
/**
 * run method comment.
 */
public void start() {
	
	this.addMouseMotionListener(this);
	this.addMouseListener(this);
	this.addKeyListener(this);
}
/**
 * run method comment.
 */
public void stop() {

	alive = false;
	this.removeMouseMotionListener(this);
	this.removeMouseListener(this);
	this.removeKeyListener(this);	
}
public void update(Graphics g) {
	// do not clear
	this.paint(g);
}
}
