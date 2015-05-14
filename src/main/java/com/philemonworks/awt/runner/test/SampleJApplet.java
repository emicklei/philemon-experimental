package com.philemonworks.awt.runner.test;

import java.awt.*;

import com.philemonworks.awt.runner.Command;
import com.philemonworks.awt.runner.CommandAccessor;
import com.philemonworks.awt.runner.GUI;
import com.philemonworks.awt.runner.GUIListener;
import com.philemonworks.awt.runner.GUIPerformer;
import com.philemonworks.awt.runner.RecordingGUI;
/**
 * Insert the type's description here.
 * Creation date: (16-12-2003 12:42:02)
 * @author: Ernest Micklei
 */
public class SampleJApplet extends javax.swing.JApplet {
	private javax.swing.JPanel ivjJAppletContentPane = null;
	private javax.swing.JButton ivjJButton1 = null;
	private javax.swing.JLabel ivjJLabel1 = null;
	private javax.swing.JTextField ivjJTextField1 = null;
	private javax.swing.JButton ivjJButton2 = null;
	private javax.swing.JLabel ivjJLabel2 = null;
	private javax.swing.JTextField ivjJTextField2 = null;
	IvjEventHandler ivjEventHandler = new IvjEventHandler();
	private javax.swing.JButton ivjTest = null;
	private javax.swing.JCheckBox ivjJCheckBox1 = null;
	private javax.swing.JRadioButton ivjJRadioButton1 = null;
	private javax.swing.JButton ivjJButton3 = null;
	private javax.swing.JLabel ivjJLabel3 = null;
	private javax.swing.JPanel ivjJPanel1 = null;
	private javax.swing.JTextPane ivjJTextPane1 = null;
	private javax.swing.JButton ivjJButton4 = null;

class IvjEventHandler implements java.awt.event.ActionListener {
		public void actionPerformed(java.awt.event.ActionEvent e) {
			if (e.getSource() == SampleJApplet.this.getJButton3()) 
				connEtoC2(e);
			if (e.getSource() == SampleJApplet.this.getTest()) 
				connEtoC1(e);
			if (e.getSource() == SampleJApplet.this.getJButton4()) 
				connEtoC3(e);
			if (e.getSource() == SampleJApplet.this.getJButton1()) 
				connEtoC4(e);
		};
	};
/**
 * connEtoC1:  (Test.action.actionPerformed(java.awt.event.ActionEvent) --> SampleJApplet.doTest(Ljava.awt.event.ActionEvent;)V)
 * @param arg1 java.awt.event.ActionEvent
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void connEtoC1(java.awt.event.ActionEvent arg1) {
	try {
		// user code begin {1}
		// user code end
		this.doTestRead(arg1);
		// user code begin {2}
		// user code end
	} catch (java.lang.Throwable ivjExc) {
		// user code begin {3}
		// user code end
		handleException(ivjExc);
	}
}
/**
 * connEtoC2:  (JButton3.action.actionPerformed(java.awt.event.ActionEvent) --> SampleJApplet.doTestWrite(Ljava.awt.event.ActionEvent;)V)
 * @param arg1 java.awt.event.ActionEvent
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void connEtoC2(java.awt.event.ActionEvent arg1) {
	try {
		// user code begin {1}
		// user code end
		this.doTestWrite(arg1);
		// user code begin {2}
		// user code end
	} catch (java.lang.Throwable ivjExc) {
		// user code begin {3}
		// user code end
		handleException(ivjExc);
	}
}
/**
 * connEtoC3:  (JButton4.action.actionPerformed(java.awt.event.ActionEvent) --> SampleJApplet.doTestClear(Ljava.awt.event.ActionEvent;)V)
 * @param arg1 java.awt.event.ActionEvent
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void connEtoC3(java.awt.event.ActionEvent arg1) {
	try {
		// user code begin {1}
		// user code end
		this.doTestClear(arg1);
		// user code begin {2}
		// user code end
	} catch (java.lang.Throwable ivjExc) {
		// user code begin {3}
		// user code end
		handleException(ivjExc);
	}
}
/**
 * connEtoC4:  (JButton1.action.actionPerformed(java.awt.event.ActionEvent) --> SampleJApplet.doOpenNew(Ljava.awt.event.ActionEvent;)V)
 * @param arg1 java.awt.event.ActionEvent
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void connEtoC4(java.awt.event.ActionEvent arg1) {
	try {
		// user code begin {1}
		// user code end
		this.doOpenNew(arg1);
		// user code begin {2}
		// user code end
	} catch (java.lang.Throwable ivjExc) {
		// user code begin {3}
		// user code end
		handleException(ivjExc);
	}
}
/**
 * Comment
 */
public void doOpenNew(java.awt.event.ActionEvent actionEvent) {

    javax.swing.JFrame frame = new javax.swing.JFrame();
    SampleJApplet aSampleJApplet = new SampleJApplet();
    aSampleJApplet.init();
    frame.getContentPane().add("Center", aSampleJApplet);
    frame.setSize(aSampleJApplet.getSize());
    GUI.show(frame);
    java.awt.Insets insets = frame.getInsets();
    frame.setSize(
        frame.getWidth() + insets.left + insets.right,
        frame.getHeight() + insets.top + insets.bottom);
    frame.setVisible(true);

}
/**
 * Comment
 */
public void doTestClear(java.awt.event.ActionEvent actionEvent) {

	GUIListener rec = ((RecordingGUI)GUI.current).recorder;	
	rec.reset();
}
/**
 * Comment
 */
public void doTestRead(java.awt.event.ActionEvent actionEvent) {

	CommandAccessor accessor = new CommandAccessor();
	java.util.ArrayList list = accessor.readCommands();
	GUIPerformer performer = new GUIPerformer();
	performer.commands = list;
	performer.target = this;
	for (int i=0;i<list.size();i++){
		Command cmd = (Command)list.get(i);
		performer.resolveCommand(cmd);
		//System.out.println(cmd);
		cmd.execute();
		if (cmd.hasFailed()) cmd.getException().printStackTrace(System.out);
	}
}
/**
 * Comment
 */
public void doTestWrite(java.awt.event.ActionEvent actionEvent) {

	CommandAccessor accessor = new CommandAccessor();
	GUIListener rec = ((RecordingGUI)GUI.current).recorder;
	accessor.writeCommands(rec.commands);
	
}
/**
 * Returns information about this applet.
 * @return a string of information about this applet
 */
public String getAppletInfo() {
	return "SampleJApplet\n" + 
		"\n" + 
		"Insert the type's description here.\n" + 
		"Creation date: (16-12-2003 12:42:02)\n" + 
		"@author: Ernest Micklei\n" + 
		"";
}
/**
 * Return the JAppletContentPane property value.
 * @return javax.swing.JPanel
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private javax.swing.JPanel getJAppletContentPane() {
	if (ivjJAppletContentPane == null) {
		try {
			ivjJAppletContentPane = new javax.swing.JPanel();
			ivjJAppletContentPane.setName("JAppletContentPane");
			ivjJAppletContentPane.setPreferredSize(new java.awt.Dimension(300, 200));
			ivjJAppletContentPane.setLayout(null);
			ivjJAppletContentPane.setMinimumSize(new java.awt.Dimension(300, 200));
			getJAppletContentPane().add(getJButton1(), getJButton1().getName());
			getJAppletContentPane().add(getJTextField1(), getJTextField1().getName());
			getJAppletContentPane().add(getJLabel1(), getJLabel1().getName());
			getJAppletContentPane().add(getJLabel2(), getJLabel2().getName());
			getJAppletContentPane().add(getJTextField2(), getJTextField2().getName());
			getJAppletContentPane().add(getJButton2(), getJButton2().getName());
			getJAppletContentPane().add(getTest(), getTest().getName());
			getJAppletContentPane().add(getJCheckBox1(), getJCheckBox1().getName());
			getJAppletContentPane().add(getJRadioButton1(), getJRadioButton1().getName());
			getJAppletContentPane().add(getJButton3(), getJButton3().getName());
			getJAppletContentPane().add(getJPanel1(), getJPanel1().getName());
			getJAppletContentPane().add(getJButton4(), getJButton4().getName());
			// user code begin {1}
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	}
	return ivjJAppletContentPane;
}
/**
 * Return the JButton1 property value.
 * @return javax.swing.JButton
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private javax.swing.JButton getJButton1() {
	if (ivjJButton1 == null) {
		try {
			ivjJButton1 = new javax.swing.JButton();
			ivjJButton1.setName("JButton1");
			ivjJButton1.setText("Open...");
			ivjJButton1.setBounds(22, 152, 85, 25);
			// user code begin {1}
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	}
	return ivjJButton1;
}
/**
 * Return the JButton2 property value.
 * @return javax.swing.JButton
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private javax.swing.JButton getJButton2() {
	if (ivjJButton2 == null) {
		try {
			ivjJButton2 = new javax.swing.JButton();
			ivjJButton2.setName("JButton2");
			ivjJButton2.setText("JButton2");
			ivjJButton2.setBounds(113, 152, 85, 25);
			// user code begin {1}	
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	}
	return ivjJButton2;
}
/**
 * Return the JButton3 property value.
 * @return javax.swing.JButton
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private javax.swing.JButton getJButton3() {
	if (ivjJButton3 == null) {
		try {
			ivjJButton3 = new javax.swing.JButton();
			ivjJButton3.setName("JButton3");
			ivjJButton3.setText("Write Commands");
			ivjJButton3.setBounds(139, 209, 146, 25);
			// user code begin {1}
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	}
	return ivjJButton3;
}
/**
 * Return the JButton4 property value.
 * @return javax.swing.JButton
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private javax.swing.JButton getJButton4() {
	if (ivjJButton4 == null) {
		try {
			ivjJButton4 = new javax.swing.JButton();
			ivjJButton4.setName("JButton4");
			ivjJButton4.setText("Clear");
			ivjJButton4.setBounds(53, 209, 81, 25);
			// user code begin {1}
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	}
	return ivjJButton4;
}
/**
 * Return the JCheckBox1 property value.
 * @return javax.swing.JCheckBox
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private javax.swing.JCheckBox getJCheckBox1() {
	if (ivjJCheckBox1 == null) {
		try {
			ivjJCheckBox1 = new javax.swing.JCheckBox();
			ivjJCheckBox1.setName("JCheckBox1");
			ivjJCheckBox1.setText("JCheckBox1");
			ivjJCheckBox1.setBounds(26, 75, 97, 22);
			// user code begin {1}
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	}
	return ivjJCheckBox1;
}
/**
 * Return the JLabel1 property value.
 * @return javax.swing.JLabel
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private javax.swing.JLabel getJLabel1() {
	if (ivjJLabel1 == null) {
		try {
			ivjJLabel1 = new javax.swing.JLabel();
			ivjJLabel1.setName("JLabel1");
			ivjJLabel1.setText("JLabel1");
			ivjJLabel1.setBounds(14, 17, 45, 14);
			// user code begin {1}
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	}
	return ivjJLabel1;
}
/**
 * Return the JLabel2 property value.
 * @return javax.swing.JLabel
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private javax.swing.JLabel getJLabel2() {
	if (ivjJLabel2 == null) {
		try {
			ivjJLabel2 = new javax.swing.JLabel();
			ivjJLabel2.setName("JLabel2");
			ivjJLabel2.setText("JLabel2");
			ivjJLabel2.setBounds(12, 42, 45, 14);
			// user code begin {1}
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	}
	return ivjJLabel2;
}
/**
 * Return the JLabel3 property value.
 * @return javax.swing.JLabel
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private javax.swing.JLabel getJLabel3() {
	if (ivjJLabel3 == null) {
		try {
			ivjJLabel3 = new javax.swing.JLabel();
			ivjJLabel3.setName("JLabel3");
			ivjJLabel3.setText("Text");
			ivjJLabel3.setBounds(15, 6, 45, 14);
			// user code begin {1}
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	}
	return ivjJLabel3;
}
/**
 * Return the JPanel1 property value.
 * @return javax.swing.JPanel
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private javax.swing.JPanel getJPanel1() {
	if (ivjJPanel1 == null) {
		try {
			ivjJPanel1 = new javax.swing.JPanel();
			ivjJPanel1.setName("JPanel1");
			ivjJPanel1.setBorder(new javax.swing.border.EtchedBorder());
			ivjJPanel1.setLayout(null);
			ivjJPanel1.setBounds(179, 15, 229, 120);
			getJPanel1().add(getJTextPane1(), getJTextPane1().getName());
			getJPanel1().add(getJLabel3(), getJLabel3().getName());
			// user code begin {1}
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	}
	return ivjJPanel1;
}
/**
 * Return the JRadioButton1 property value.
 * @return javax.swing.JRadioButton
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private javax.swing.JRadioButton getJRadioButton1() {
	if (ivjJRadioButton1 == null) {
		try {
			ivjJRadioButton1 = new javax.swing.JRadioButton();
			ivjJRadioButton1.setName("JRadioButton1");
			ivjJRadioButton1.setText("JRadioButton1");
			ivjJRadioButton1.setBounds(26, 103, 108, 22);
			// user code begin {1}			
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	}
	return ivjJRadioButton1;
}
/**
 * Return the JTextField1 property value.
 * @return javax.swing.JTextField
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private javax.swing.JTextField getJTextField1() {
	if (ivjJTextField1 == null) {
		try {
			ivjJTextField1 = new javax.swing.JTextField();
			ivjJTextField1.setName("JTextField1");
			ivjJTextField1.setBounds(65, 15, 81, 20);
			// user code begin {1}
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	}
	return ivjJTextField1;
}
/**
 * Return the JTextField2 property value.
 * @return javax.swing.JTextField
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private javax.swing.JTextField getJTextField2() {
	if (ivjJTextField2 == null) {
		try {
			ivjJTextField2 = new javax.swing.JTextField();
			ivjJTextField2.setName("JTextField2");
			ivjJTextField2.setBounds(65, 41, 81, 20);
			// user code begin {1}	
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	}
	return ivjJTextField2;
}
/**
 * Return the JTextPane1 property value.
 * @return javax.swing.JTextPane
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private javax.swing.JTextPane getJTextPane1() {
	if (ivjJTextPane1 == null) {
		try {
			ivjJTextPane1 = new javax.swing.JTextPane();
			ivjJTextPane1.setName("JTextPane1");
			ivjJTextPane1.setBounds(14, 24, 145, 84);
			// user code begin {1}	
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	}
	return ivjJTextPane1;
}
/**
 * Return the Test property value.
 * @return javax.swing.JButton
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private javax.swing.JButton getTest() {
	if (ivjTest == null) {
		try {
			ivjTest = new javax.swing.JButton();
			ivjTest.setName("Test");
			ivjTest.setText("Read Commands");
			ivjTest.setBounds(287, 209, 133, 25);
			// user code begin {1}
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	}
	return ivjTest;
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
 * Initializes the applet.
 * 
 * @see #start
 * @see #stop
 * @see #destroy
 */
public void init() {
	try {
		setName("SampleJApplet");
		setSize(426, 240);
		setContentPane(getJAppletContentPane());
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
	getJButton3().addActionListener(ivjEventHandler);
	getTest().addActionListener(ivjEventHandler);
	getJButton4().addActionListener(ivjEventHandler);
	getJButton1().addActionListener(ivjEventHandler);
}
/**
 * Comment
 */
public void jSlider1_Value(int arg1) {
	return;
}
/**
 * main entrypoint - starts the part when it is run as an application
 * @param args java.lang.String[]
 */
public static void main(java.lang.String[] args) {
	try {
		javax.swing.JFrame frame = new javax.swing.JFrame();
		SampleJApplet aSampleJApplet;
		Class iiCls = Class.forName("com.philemonworks.awt.runner.test.SampleJApplet");
		ClassLoader iiClsLoader = iiCls.getClassLoader();
		aSampleJApplet = (SampleJApplet)java.beans.Beans.instantiate(iiClsLoader,"com.philemonworks.awt.runner.test.SampleJApplet");
		frame.getContentPane().add("Center", aSampleJApplet);
		frame.setSize(aSampleJApplet.getSize());
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent e) {
				System.exit(0);
			};
		});
		GUI.show(frame);
		java.awt.Insets insets = frame.getInsets();
		frame.setSize(frame.getWidth() + insets.left + insets.right, frame.getHeight() + insets.top + insets.bottom);
		frame.setVisible(true);
	} catch (Throwable exception) {
		System.err.println("Exception occurred in main() of javax.swing.JApplet");
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
