package com.philemonworks.awt;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public interface InteractiveImageController {
/**
 * 
 * @return whether repaint is needed
 */
public boolean focusGained(InteractiveImage image);
/**
 * 
 * @return whether repaint is needed
 */
public boolean focusLost(InteractiveImage image);
/**
 * 
 * @return whether repaint is needed
 */
public boolean keyTyped(InteractiveImage image, KeyEvent e);
/**
 * 
 * @return whether repaint is needed
 */
public boolean mousePressed(InteractiveImage image, MouseEvent e);
/**
 * 
 * @return whether repaint is needed
 */
public boolean mouseReleased(InteractiveImage image, MouseEvent e);
/**
 * 
 * @return whether repaint is needed
 */
public boolean movedFromTo(InteractiveImage image, Point from, Point to);
}
