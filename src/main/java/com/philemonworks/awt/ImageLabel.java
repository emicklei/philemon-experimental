package com.philemonworks.awt;

import java.awt.AWTEvent;
import java.awt.AWTEventMulticaster;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class ImageLabel extends java.awt.Panel 	implements MouseListener {

	transient ActionListener actionListener;
	public boolean isButton = false;
	public boolean isPressed = false;
	public Image image = null;
	public Point extent = null;
	public Color foreColor = Color.black;
	public Color backColor = Color.gray;
	public String label = "Press...";
/**
 * ImageLabel constructor comment.
 */
public ImageLabel() {
	super();
	addMouseListener(this);	
}
/**
 * ImageLabel constructor comment.
 * @param layout java.awt.LayoutManager
 */
public ImageLabel(java.awt.LayoutManager layout) {
	super(layout);
}
	/**
	 * Adds the specified action listener to receive action events from
	 * this button. Action events occur when a user presses or releases
	 * the mouse over this button.
	 * @param         l the action listener.
	 * @see           java.awt.event.ActionListener
	 * @see           java.awt.Button#removeActionListener
	 * @since         JDK1.1
	 */ 
	public synchronized void addActionListener(ActionListener l) {
	actionListener = AWTEventMulticaster.add(actionListener, l);
	}
/**
 * mouseClicked method comment.
 */
public void mouseClicked(java.awt.event.MouseEvent e) {}
/**
 * mouseEntered method comment.
 */
public void mouseEntered(java.awt.event.MouseEvent e) {}
/**
 * mouseExited method comment.
 */
public void mouseExited(java.awt.event.MouseEvent e) {
	isPressed = false;
}
/**
 * mousePressed method comment.
 */
public void mousePressed(java.awt.event.MouseEvent e) {
	isPressed = true;
	repaint();
}
/**
 * mouseReleased method comment.
 */
public void mouseReleased(java.awt.event.MouseEvent e) {
	isPressed = false;
	repaint();
	if (actionListener != null)
		actionListener.actionPerformed(new ActionEvent(this,0,"action"));
}
public void paint(Graphics g) {

	int off = 0;
	if (isPressed && isButton) off = 1;
	if (image == null) {
		g.setColor(backColor);
		g.fillRect(0,0,getSize().width,getSize().height);
		g.setColor(foreColor);
		// compute offset from text width
		int textWidth = g.getFontMetrics().stringWidth(label);
		int textHeight = g.getFontMetrics().getAscent();
		g.drawString(
			label, 
			(getSize().width - textWidth) / 2 + off, 
			(getSize().height - textHeight) / 2 + textHeight + off);
		if (isButton) {
			g.setColor(Color.gray);
			ImageUtils.draw3DRect(g,0,0,getSize().width,getSize().height,!isPressed);
		}		
	} else {
		g.drawImage(image,off,off,this);
		if (isButton) {
			g.setColor(Color.gray);
			ImageUtils.draw3DRect(g,0,0,extent.x,extent.y,!isPressed);
		}		
	}

}
protected void processEvent(AWTEvent e) {
	if (e instanceof ActionEvent) {
		if (actionListener != null) {
			actionListener.actionPerformed((ActionEvent)e);
		}
		return;
	}
	super.processEvent(e);
}
public void removeActionListener(ActionListener newListener) {
	actionListener = java.awt.AWTEventMulticaster.remove(actionListener, newListener);
	return;
}
public void setImage(Image newImage, Point newExtent) {

	image = newImage;
	extent = newExtent;
	this.setBounds(getLocation().x, getLocation().y, newExtent.x , newExtent.y);
}
}
