package com.philemonworks.awt.test;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
public class ImageLabelTest extends Applet {
	private com.philemonworks.awt.ImageLabel ivjImageLabel = null;
	IvjEventHandler ivjEventHandler = new IvjEventHandler();

class IvjEventHandler implements java.awt.event.ActionListener {
		public void actionPerformed(java.awt.event.ActionEvent e) {
			if (e.getSource() == ImageLabelTest.this.getImageLabel()) 
				connEtoC1();
		};
	};
	private com.philemonworks.awt.ImageLabel ivjImageLabel1 = null;
/**
 * connEtoC1:  (ImageLabel.action. --> ImageLabelTest.imageLabel_Action()V)
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void connEtoC1() {
	try {
		// user code begin {1}
		// user code end
		this.imageLabel_Action();
		// user code begin {2}
		// user code end
	} catch (java.lang.Throwable ivjExc) {
		// user code begin {3}
		// user code end
		handleException(ivjExc);
	}
}
/**
 * Returns information about this applet.
 * @return a string of information about this applet
 */
public String getAppletInfo() {
	return "ImageLabelTest\n" + 
		"\n" + 
		"\n" + 
		"";
}
/**
 * Return the ImageLabel property value.
 * @return com.philemonworks.awt.ImageLabel
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private com.philemonworks.awt.ImageLabel getImageLabel() {
	if (ivjImageLabel == null) {
		try {
			ivjImageLabel = new com.philemonworks.awt.ImageLabel();
			ivjImageLabel.setName("ImageLabel");
			ivjImageLabel.setBounds(81, 59, 42, 43);
			// user code begin {1}
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	}
	return ivjImageLabel;
}
/**
 * Return the ImageLabel1 property value.
 * @return com.philemonworks.awt.ImageLabel
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private com.philemonworks.awt.ImageLabel getImageLabel1() {
	if (ivjImageLabel1 == null) {
		try {
			ivjImageLabel1 = new com.philemonworks.awt.ImageLabel();
			ivjImageLabel1.setName("ImageLabel1");
			ivjImageLabel1.setBounds(185, 120, 118, 33);
			// user code begin {1}
			ivjImageLabel1.label = "Help...";
			ivjImageLabel1.foreColor = Color.white;
			ivjImageLabel1.backColor = Color.red;
			ivjImageLabel1.isButton = true;
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	}
	return ivjImageLabel1;
}
/**
 * Called whenever the part throws an exception.
 * @param exception java.lang.Throwable
 */
private void handleException(java.lang.Throwable exception) {

	/* Uncomment the following lines to print uncaught exceptions to stdout */
	// System.out.println("--------- UNCAUGHT EXCEPTION ---------");
	// exception.printStackTrace(System.out);
}
/**
 * Comment
 */
public void imageLabel_Action() {
	return;
}
/**
 * Initializes the applet.
 * 
 * @see #start
 * @see #stop
 * @see #destroy
 */
public void init() {
	try {
		super.init();
		setName("ImageLabelTest");
		setLayout(null);
		setSize(426, 240);
		add(getImageLabel(), getImageLabel().getName());
		add(getImageLabel1(), getImageLabel1().getName());
		initConnections();
		// user code begin {1}
		// user code end
	} catch (java.lang.Throwable ivjExc) {
		// user code begin {2}
		// user code end
		handleException(ivjExc);
	}
}
/**
 * Initializes connections
 * @exception java.lang.Exception The exception description.
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void initConnections() throws java.lang.Exception {
	// user code begin {1}
	// user code end
	getImageLabel().addActionListener(ivjEventHandler);
}
/**
 * main entrypoint - starts the part when it is run as an application
 * @param args java.lang.String[]
 */
public static void main(java.lang.String[] args) {
	try {
		Frame frame = new java.awt.Frame();
		ImageLabelTest aImageLabelTest;
		Class iiCls = Class.forName("com.philemonworks.awt.test.ImageLabelTest");
		ClassLoader iiClsLoader = iiCls.getClassLoader();
		aImageLabelTest = (ImageLabelTest)java.beans.Beans.instantiate(iiClsLoader,"com.philemonworks.awt.test.ImageLabelTest");
		frame.add("Center", aImageLabelTest);
		frame.setSize(aImageLabelTest.getSize());
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
 * Paints the applet.
 * If the applet does not need to be painted (e.g. if it is only a container for other
 * awt components) then this method can be safely removed.
 * 
 * @param g  the specified Graphics window
 * @see #update
 */
public void paint(Graphics g) {
	super.paint(g);

	// insert code to paint the applet here
}
}
