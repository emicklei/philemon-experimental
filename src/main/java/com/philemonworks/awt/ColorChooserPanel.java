package com.philemonworks.awt;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class ColorChooserPanel extends java.awt.Panel 	implements 
			java.awt.event.MouseListener {

	transient java.beans.PropertyChangeListener changeListener;
	public Color[] colors = {
		Color.red, Color.green, Color.blue, Color.yellow, 
		Color.pink,Color.lightGray,Color.cyan,
		Color.magenta,Color.orange,
		Color.black,Color.gray,Color.white	};

	public int columns = 4;
	public Point extent = new Point(20,20);
	public int margin = 2;
	private java.awt.Color fieldColor = new Color(0);
	protected transient java.beans.PropertyChangeSupport propertyChange;
/**
 * ColorChooserPanel constructor comment.
 */
public ColorChooserPanel() {
	super();
	this.addMouseListener(this);
}
/**
 * ColorChooserPanel constructor comment.
 * @param layout java.awt.LayoutManager
 */
public ColorChooserPanel(java.awt.LayoutManager layout) {
	super(layout);
}
/**
 * The addPropertyChangeListener method was generated to support the propertyChange field.
 */
public synchronized void addPropertyChangeListener(java.beans.PropertyChangeListener listener) {
	getPropertyChange().addPropertyChangeListener(listener);
}
/**
 * The firePropertyChange method was generated to support the propertyChange field.
 */
public void firePropertyChange(java.lang.String propertyName, java.lang.Object oldValue, java.lang.Object newValue) {
	getPropertyChange().firePropertyChange(propertyName, oldValue, newValue);
}
/**
 * Gets the color property (java.awt.Color) value.
 * @return The color property value.
 * @see #setColor
 */
public java.awt.Color getColor() {
	return fieldColor;
}
/**
 * Accessor for the propertyChange field.
 */
protected java.beans.PropertyChangeSupport getPropertyChange() {
	if (propertyChange == null) {
		propertyChange = new java.beans.PropertyChangeSupport(this);
	};
	return propertyChange;
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
public void mouseExited(java.awt.event.MouseEvent e) {}
/**
 * mousePressed method comment.
 */
public void mousePressed(java.awt.event.MouseEvent e) {
	
	int x = e.getX();
	int y = e.getY();
	int index = (y / (extent.y + margin)) * columns + (x / (extent.x + margin));
	this.setColor(colors[index]);
	this.repaint();
}
/**
 * mouseReleased method comment.
 */
public void mouseReleased(java.awt.event.MouseEvent e) {}
/**
 * mouseReleased method comment.
 */
public void paint(Graphics g) {
	int w = getSize().width;
	int h = getSize().height;
	int x = 0;
	int y = 0;
	int i = 0;
	for (int c = 0; c < colors.length; c++) {
		g.setColor(Color.gray);
		ImageUtils.draw3DRect(g, x, y, extent.x, extent.y, colors[c] != fieldColor);
		g.setColor(colors[c]);
		g.fillRect(x + 1, y + 1, extent.x - 2, extent.y - 2);
		i++;
		if (i == columns) {
			x = 0;
			y = y + extent.y + margin;
			i = 0;
		} else
			x = x + extent.x + margin;
	}
}
/**
 * The removePropertyChangeListener method was generated to support the propertyChange field.
 */
public synchronized void removePropertyChangeListener(java.beans.PropertyChangeListener listener) {
	getPropertyChange().removePropertyChangeListener(listener);
}
/**
 * Sets the color property (java.awt.Color) value.
 * @param color The new value for the property.
 * @see #getColor
 */
public void setColor(java.awt.Color color) {
	Color oldValue = fieldColor;
	fieldColor = color;
	firePropertyChange("color", oldValue, color);
	this.repaint();
}
}
