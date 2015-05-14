package com.philemonworks.awt.runner;

/**
 * Insert the type's description here.
 * Creation date: (19-12-2003 11:43:38)
 * @author: Ernest Micklei
 */
public class RecordingGUI extends GUI {
	public GUIListener recorder = new GUIListener();
/**
 * Insert the method's description here.
 * Creation date: (19-12-2003 16:08:12)
 */
protected void showFrame(javax.swing.JFrame newFrame) {

	recorder.listenTo(newFrame);
	super.showFrame(newFrame);
}
}
