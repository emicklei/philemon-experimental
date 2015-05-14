package com.philemonworks.awt;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class InteractiveImage extends java.awt.Panel  
	implements 
			MouseListener, 
			MouseMotionListener,
			KeyListener {

	protected InteractiveImageController controller;
	public Image image;
	public Point extent;
	public boolean selectable = true;
	public boolean draggableX = true;
	public boolean draggableY = true;	
	protected Point dragStart;
	protected int dragX;
	protected int dragY;
	public int gridX = 10;
	public int gridY = 10;
	public int dragCornerX = 100;
	public int dragCornerY = 100;
	public boolean selected = false;
/**
 * 
 * @param controller com.philemonworks.awt.InteractiveImageController
 */
public InteractiveImage(InteractiveImageController aController) {
	super();
	controller = aController;
	addMouseListener(this);
	addMouseMotionListener(this);
	addKeyListener(this);
}
/**
 * mouseReleased method comment.
 */
public Rectangle getBounds() {

	return this.getBoundsAt(this.getLocation());
}
/**
 * mouseReleased method comment.
 */
public Rectangle getBoundsAt(Point newPoint) {

	return new Rectangle(newPoint, getSize());
}
/**
 * 
 * @return java.awt.Image
 */
public java.awt.Image getImage() {
	return image;
}
/**
 */
public Dimension getPreferredSize() {

	return new Dimension(extent.x, extent.y);
}
/**
 * mouseClicked method comment.
 */
public boolean hasBeenDragged() {

	if (dragStart == null) return false;
	return !dragStart.equals(getLocation());
}
/**
 * mouseReleased method comment.
 */
public boolean isDragging(){
	return dragStart != null;
}
/**
 * mouseClicked method comment.
 */
public void isSelected(boolean isSelected){
	
	if (selected == isSelected) return;
	selected = isSelected;
	repaint();
}
/**
 * keyPressed method comment.
 */
public void keyPressed(java.awt.event.KeyEvent e) {
	Point newLocation, oldLocation;
	if (!selected)
		return;
	oldLocation = getLocation();
	switch (e.getKeyCode()) {
		case KeyEvent.VK_UP :
			newLocation = new Point(oldLocation.x, oldLocation.y - gridY);
			this.moveTo(newLocation,true);
			break;
		case KeyEvent.VK_DOWN :
			newLocation = new Point(getLocation().x, getLocation().y + gridY);
			this.moveTo(newLocation,true);
			break;
		case KeyEvent.VK_LEFT :
			newLocation = new Point(getLocation().x - gridX, getLocation().y);
			this.moveTo(newLocation,true);
			break;
		case KeyEvent.VK_RIGHT :
			newLocation = new Point(getLocation().x + gridX, getLocation().y);
			this.moveTo(newLocation,true);
			break;
		case KeyEvent.VK_CONTROL :
			;
			break;
	}
}
/**
 * keyReleased method comment.
 */
public void keyReleased(java.awt.event.KeyEvent e) {}
/**
 * keyTyped method comment.
 */
public void keyTyped(java.awt.event.KeyEvent e) {
	
	if (controller.keyTyped(this,e)) repaint();
}
/**
 * mouseClicked method comment.
 */
public void mouseClicked(java.awt.event.MouseEvent e) {
}
/**
 * mouseDragged method comment.
 */
public void mouseDragged(java.awt.event.MouseEvent e) {
	int newX, newY;
	
	if (!draggableX && !draggableY)
		return;
	newX = getLocation().x;
	if (draggableX)
		newX = newX + e.getX() - dragX;
	newY = getLocation().y;		
	if (draggableY)
		newY = newY + e.getY() - dragY;
	setBounds(toGridX(newX),toGridY(newY), getSize().width, getSize().height);
}
/**
 * mouseEntered method comment.
 */
public void mouseEntered(java.awt.event.MouseEvent e) {

	controller.focusGained(this);
}
/**
 * mouseExited method comment.
 */
public void mouseExited(java.awt.event.MouseEvent e) {
	
	controller.focusLost(this);	
}
/**
 * mouseMoved method comment.
 */
public void mouseMoved(java.awt.event.MouseEvent e) {} 
/**
 * mousePressed method comment.
 */
public void mousePressed(java.awt.event.MouseEvent e) {

	if (draggableX || draggableY) {
		if (true) /*(e.getModifiers() != MouseEvent.BUTTON3_MASK)*/ {
			dragStart = getLocation();
			dragX = e.getX();
			dragY = e.getY();
		}
	}
	controller.mousePressed(this, e);
	e.consume();
}
/**
 * mouseReleased method comment.
 */
public void mouseReleased(java.awt.event.MouseEvent e) {

	if (!draggableX && !draggableY)
		return;
		
	if (controller.mouseReleased(this, e)) repaint();
	dragStart = null;
	e.consume();
}
/**
 * mouseReleased method comment.
 */
public void moveTo(Point newLocation, boolean notify) {

	Point oldLocation;
	if (dragStart == null)
		oldLocation = getLocation();
	else
		oldLocation = dragStart;
	setBounds(toGridX(newLocation.x), toGridY(newLocation.y), getSize().width, getSize().height);
	// notify the controller
	if (notify && (!oldLocation.equals(newLocation))) controller.movedFromTo(this,oldLocation,getLocation());
	// update visual
	repaint();
}
/**
 * mouseClicked method comment.
 */
public void paint(Graphics g) {
	
	int w = getSize().width;
	int h = getSize().height;
	if (selected && !isDragging()) {
		this.paintSelectedOn(g);
	}
	this.paintImageOn(g);
}
/**
 */
public void paintImageOn(Graphics g) {
	
	if (getImage() != null) {
		g.drawImage(getImage(),0,0,this);
	}
}
/**
 * mouseClicked method comment.
 */
public void paintSelectedOn(Graphics g) {
	
	int w = getSize().width;
	int h = getSize().height;
	g.setColor(Color.red);
	g.fillRect(0,0,w,h);
	int inset = Math.max(2,Math.min(w/15,h/15));
	g.clipRect(inset, inset, w-inset-inset, h-inset-inset);
}
public int toGridX(int x) {

	// make sure it is placed within the bounds
	int newX = Math.min(Math.max(x,0), dragCornerX - extent.x);
	// make sure its origin is on a grid point
	newX = gridX * (int)Math.floor(newX / (gridX * 1.0)) ;
	return newX;
}
public int toGridY(int y) {

	// make sure it is placed within the bounds
	int newY = Math.min(Math.max(y,0), dragCornerY - extent.y);
	// make sure its origin is on a grid point
	newY = gridY * (int)Math.floor(newY / (gridY * 1.0));

	return newY;
}
}
