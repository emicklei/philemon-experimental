package com.philemonworks.awt.runner;

/**
 * Insert the type's description here.
 * Creation date: (16-12-2003 11:43:38)
 * @author: Ernest Micklei
 */
public class GUI {
public static final GUI current = new RecordingGUI();
/**
 * GUI constructor comment.
 */
public GUI() {
	super();
}
public static Command command(Object target, String methodName){

	Command cmd =  new Command(target, methodName);
	return cmd;
}
public static Object exectue(Object target, String methodName){

	Command cmd =  command(target,methodName);
	return cmd.execute();
}
public static javax.swing.JFrame show(javax.swing.JFrame newFrame){

	current.showFrame(newFrame);
	return newFrame;
}
protected void showFrame(javax.swing.JFrame newFrame){

	newFrame.show();
}
}
