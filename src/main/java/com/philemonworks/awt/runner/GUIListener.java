package com.philemonworks.awt.runner;

/**
 * Insert the type's description here.
 * Creation date: (16-12-2003 11:43:38)
 * @author: Ernest Micklei
 */
import java.awt.event.*;
import java.util.*;

public class GUIListener implements FocusListener, ActionListener, WindowListener {

	public ArrayList commands = new ArrayList();

	/**
	 * Invoked when an action occurs.
	 */
public void actionPerformed(java.awt.event.ActionEvent e) {

	Command cmd = null;
	javax.swing.JComponent widget = (javax.swing.JComponent)e.getSource();
	String fullName = qualifiedNameFrom(widget);
	if (widget instanceof javax.swing.JButton)
		cmd = new Command(fullName, "doClick");
	else if (widget instanceof javax.swing.JCheckBox)
		cmd = new Command(fullName, "doClick");
	else if (widget instanceof javax.swing.JRadioButton)
		cmd = new Command(fullName, "doClick");
		
	if (cmd != null) commands.add(cmd);
}
	/**
	 * Invoked when a component gains the keyboard focus.
	 */
public void focusGained(java.awt.event.FocusEvent e) {}
/**
 * Invoked when a component loses the keyboard focus.
 */
public void focusLost(java.awt.event.FocusEvent e) {

    java.awt.Component comp = (java.awt.Component)e.getSource();
    Command cmd = null;
	String fullName = qualifiedNameFrom(comp);    
    if (comp instanceof javax.swing.JTextField) {
        javax.swing.JTextField field = (javax.swing.JTextField) e.getSource();
        cmd = new Command(fullName, "setText");
        cmd.addParameter(field.getText());
    } else
        if (comp instanceof javax.swing.JTextPane) {
            javax.swing.JTextPane field = (javax.swing.JTextPane) e.getSource();
            cmd = new Command(fullName, "setText");
            cmd.addParameter(field.getText());
        }
    if (cmd != null) commands.add(cmd);
}
public void listenTo(java.awt.Component widget){
	// due to Java weirdness in its design

	if (widget instanceof javax.swing.JButton){
		this.listenTo((javax.swing.JButton)widget);
		return;
	}
	if (widget instanceof javax.swing.JCheckBox){
		this.listenTo((javax.swing.JCheckBox)widget);
		return;
	}
	if (widget instanceof javax.swing.JRadioButton){
		this.listenTo((javax.swing.JRadioButton)widget);
		return;
	}
	if (widget instanceof javax.swing.JTextField){
		this.listenTo((javax.swing.JTextField)widget);
		return;
	}
	if (widget instanceof javax.swing.JTextPane){
		this.listenTo((javax.swing.JTextPane)widget);
		return;
	}
	if (widget instanceof javax.swing.JFrame){
		this.listenTo((javax.swing.JFrame)widget);
		return;
	}	
	this.listenTo((java.awt.Container)widget);
}
public void listenTo(java.awt.Container widget){

	for (int i=0;i < widget.getComponentCount();i++) {
		this.listenTo(widget.getComponent(i));
	}
}
public void listenTo(java.awt.Panel widget){

	for (int i=0;i < widget.getComponentCount();i++) {
		this.listenTo(widget.getComponent(i));
	}
}
public void listenTo(javax.swing.JButton widget){
	widget.addActionListener(this);
}
public void listenTo(javax.swing.JCheckBox widget){
	widget.addActionListener(this);
}
public void listenTo(javax.swing.JFrame widget){
	widget.addWindowListener(this);
	this.listenTo((java.awt.Container)widget);
}
public void listenTo(javax.swing.JRadioButton widget){
	widget.addActionListener(this);
}
public void listenTo(javax.swing.JRootPane widget){
	this.listenTo((java.awt.Container)widget);
}
public void listenTo(javax.swing.JTextField widget){
	widget.addFocusListener(this);
}
public void listenTo(javax.swing.JTextPane widget){
	widget.addFocusListener(this);
}
public static String qualifiedNameFrom(java.awt.Component component){

	String fullName = "";
	java.awt.Component here = component;
	boolean seenPanel = false;
	boolean ready = false;
	while (!ready){
		fullName = "/" + here.getName() + fullName;
		here = here.getParent();
		ready = here == null || seenPanel;
		seenPanel = here instanceof javax.swing.JPanel;
	}
	return fullName;
}
public void reset(){
	commands = new ArrayList();
}
	/**
	 * Invoked when the window is set to be the user's
	 * active window, which means the window (or one of its
	 * subcomponents) will receive keyboard events.
	 */
public void windowActivated(java.awt.event.WindowEvent e) {

	Command cmd = new Command("@this", "activate");
	cmd.addParameter(((java.awt.Component)e.getSource()).getName());
	commands.add(cmd);
	
	System.out.println(e);
}
	/**
	 * Invoked when a window has been closed as the result
	 * of calling dispose on the window.
	 */
public void windowClosed(java.awt.event.WindowEvent e) {}
	/**
	 * Invoked when the user attempts to close the window
	 * from the window's system menu.  If the program does not 
	 * explicitly hide or dispose the window while processing 
	 * this event, the window close operation will be cancelled.
	 */
public void windowClosing(java.awt.event.WindowEvent e) {}
	/**
	 * Invoked when a window is no longer the user's active
	 * window, which means that keyboard events will no longer
	 * be delivered to the window or its subcomponents.
	 */
public void windowDeactivated(java.awt.event.WindowEvent e) {}
	/**
	 * Invoked when a window is changed from a minimized
	 * to a normal state.
	 */
public void windowDeiconified(java.awt.event.WindowEvent e) {}
	/**
	 * Invoked when a window is changed from a normal to a
	 * minimized state. For many platforms, a minimized window 
	 * is displayed as the icon specified in the window's 
	 * iconImage property.
	 * @see Frame#setIconImage
	 */
public void windowIconified(java.awt.event.WindowEvent e) {}
	/**
	 * Invoked the first time a window is made visible.
	 */
public void windowOpened(java.awt.event.WindowEvent e) {}
}
