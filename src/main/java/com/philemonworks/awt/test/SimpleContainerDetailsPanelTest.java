package com.philemonworks.awt.test;

import java.applet.Applet;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.TextField;
import java.util.Vector;

import com.philemonworks.awt.details.SimpleContainerDetailsPanel;
/**
 * Insert the type's description here.
 * Creation date: (7/4/2000 12:39:15 AM)
 * @author: Ernest Micklei
 */
public class SimpleContainerDetailsPanelTest extends Applet {
	private SimpleContainerDetailsPanel ivjDetailsPanel = null;
	private java.util.Vector rows = new Vector();
	private Button ivjButton1 = null;
	IvjEventHandler ivjEventHandler = new IvjEventHandler();
	private TextField ivjKeyField = null;
	private TextField ivjValueField = null;

class IvjEventHandler implements java.awt.event.ActionListener, java.beans.PropertyChangeListener {
		public void actionPerformed(java.awt.event.ActionEvent e) {
			if (e.getSource() == SimpleContainerDetailsPanelTest.this.getButton1()) 
				connEtoC1(e);
			if (e.getSource() == SimpleContainerDetailsPanelTest.this.getButton2()) 
				connEtoC3(e);
		};
		public void propertyChange(java.beans.PropertyChangeEvent evt) {
			if (evt.getSource() == SimpleContainerDetailsPanelTest.this.getDetailsPanel() && (evt.getPropertyName().equals("selectionIndex"))) 
				connEtoC2(evt);
		};
	};
	private Button ivjButton2 = null;
	private TextField ivjindexField = null;
/**
 * Comment
 */
public void addMoreItems(java.awt.event.ActionEvent actionEvent) {
	return;
}
/**
 * connEtoC1:  (Button1.action.actionPerformed(java.awt.event.ActionEvent) --> SimpleContainerDetailsPanelTest.addMoreItems(Ljava.awt.event.ActionEvent;)V)
 * @param arg1 java.awt.event.ActionEvent
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void connEtoC1(java.awt.event.ActionEvent arg1) {
	try {
		// user code begin {1}
		// user code end
		this.addMoreItems(arg1);
		// user code begin {2}
		// user code end
	} catch (java.lang.Throwable ivjExc) {
		// user code begin {3}
		// user code end
		handleException(ivjExc);
	}
}
/**
 * connEtoC2:  (DetailsPanel.selectionIndex --> SimpleContainerDetailsPanelTest.detailsPanel_SelectionIndex(I)V)
 * @param arg1 java.beans.PropertyChangeEvent
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void connEtoC2(java.beans.PropertyChangeEvent arg1) {
	try {
		// user code begin {1}
		// user code end
		this.detailsPanel_SelectionIndex(getDetailsPanel().getSelectionIndex());
		// user code begin {2}
		// user code end
	} catch (java.lang.Throwable ivjExc) {
		// user code begin {3}
		// user code end
		handleException(ivjExc);
	}
}
/**
 * connEtoC3:  (Button2.action.actionPerformed(java.awt.event.ActionEvent) --> SimpleContainerDetailsPanelTest.select_ActionPerformed(Ljava.awt.event.ActionEvent;)V)
 * @param arg1 java.awt.event.ActionEvent
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void connEtoC3(java.awt.event.ActionEvent arg1) {
	try {
		// user code begin {1}
		// user code end
		this.select_ActionPerformed(arg1);
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
public void detailsPanel_SelectionIndex(int arg1) {

	Item item = (Item)getDetailsPanel().getSelectedItem();
	if (item == null) {
		getKeyField().setText("");
		getValueField().setText("");
	} else {
		getKeyField().setText(item.getKey());
		getValueField().setText(item.getValue());
	}
}
/**
 * Returns information about this applet.
 * @return a string of information about this applet
 */
public String getAppletInfo() {
	return "SimpleContainerDetailsPanelTest\n" + 
		"\n" + 
		"Insert the type's description here.\n" + 
		"Creation date: (7/4/2000 12:39:15 AM)\n" + 
		"@author: Ernest Micklei\n" + 
		"";
}
/**
 * Return the Button1 property value.
 * @return java.awt.Button
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private java.awt.Button getButton1() {
	if (ivjButton1 == null) {
		try {
			ivjButton1 = new java.awt.Button();
			ivjButton1.setName("Button1");
			ivjButton1.setBounds(34, 17, 109, 24);
			ivjButton1.setLabel("Add More...");
			// user code begin {1}
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	}
	return ivjButton1;
}
/**
 * Return the Button2 property value.
 * @return java.awt.Button
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private java.awt.Button getButton2() {
	if (ivjButton2 == null) {
		try {
			ivjButton2 = new java.awt.Button();
			ivjButton2.setName("Button2");
			ivjButton2.setBounds(30, 312, 56, 23);
			ivjButton2.setLabel("Select");
			// user code begin {1}
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	}
	return ivjButton2;
}
/**
 * Return the DetailsPanel property value.
 * @return com.philemonworks.awt.SimpleContainerDetailsPanel
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private com.philemonworks.awt.details.SimpleContainerDetailsPanel getDetailsPanel() {
	if (ivjDetailsPanel == null) {
		try {
			ivjDetailsPanel = new com.philemonworks.awt.details.SimpleContainerDetailsPanel();
			ivjDetailsPanel.setName("DetailsPanel");
			ivjDetailsPanel.setLocation(28, 59);
			ivjDetailsPanel.setColumns(2);
			// user code begin {1}
			int[] w = { 20,  50 };
			ivjDetailsPanel.setCellWidths(w);
			String[] h = { "key", "value" };
			ivjDetailsPanel.setHeaders(h);
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	}
	return ivjDetailsPanel;
}
/**
 * Return the indexField property value.
 * @return java.awt.TextField
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private java.awt.TextField getindexField() {
	if (ivjindexField == null) {
		try {
			ivjindexField = new java.awt.TextField();
			ivjindexField.setName("indexField");
			ivjindexField.setBounds(90, 312, 24, 23);
			// user code begin {1}
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	}
	return ivjindexField;
}
/**
 * Return the KeyField property value.
 * @return java.awt.TextField
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private java.awt.TextField getKeyField() {
	if (ivjKeyField == null) {
		try {
			ivjKeyField = new java.awt.TextField();
			ivjKeyField.setName("KeyField");
			ivjKeyField.setBounds(168, 16, 59, 23);
			// user code begin {1}
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	}
	return ivjKeyField;
}
/**
 * Return the ValueField property value.
 * @return java.awt.TextField
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private java.awt.TextField getValueField() {
	if (ivjValueField == null) {
		try {
			ivjValueField = new java.awt.TextField();
			ivjValueField.setName("ValueField");
			ivjValueField.setBounds(230, 15, 59, 23);
			// user code begin {1}
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	}
	return ivjValueField;
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
		super.init();
		setName("SimpleContainerDetailsPanelTest");
		setLayout(null);
		setSize(584, 407);
		add(getDetailsPanel(), getDetailsPanel().getName());
		add(getButton1(), getButton1().getName());
		add(getKeyField(), getKeyField().getName());
		add(getValueField(), getValueField().getName());
		add(getButton2(), getButton2().getName());
		add(getindexField(), getindexField().getName());
		initConnections();
		// user code begin {1}
		Vector items = new Vector();
		items.addElement(new Item("0","e"));		
		items.addElement(new Item("1","d"));
		items.addElement(new Item("2","c"));
		items.addElement(new Item("3","b"));
		items.addElement(new Item("4","a"));
		items.addElement(new Item("5","d"));
		items.addElement(new Item("6","c"));
		items.addElement(new Item("7","b"));
		items.addElement(new Item("8","a"));
		items.addElement(new Item("9","d"));
		items.addElement(new Item("A","c"));
		items.addElement(new Item("B","b"));
		items.addElement(new Item("C","a"));			
		getDetailsPanel().setItems(items);
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
	getButton1().addActionListener(ivjEventHandler);
	getDetailsPanel().addPropertyChangeListener(ivjEventHandler);
	getButton2().addActionListener(ivjEventHandler);
}
/**
 * main entrypoint - starts the part when it is run as an application
 * @param args java.lang.String[]
 */
public static void main(java.lang.String[] args) {
	try {
		Frame frame = new java.awt.Frame();
		SimpleContainerDetailsPanelTest aSimpleContainerDetailsPanelTest;
		Class iiCls = Class.forName("com.philemonworks.awt.test.SimpleContainerDetailsPanelTest");
		ClassLoader iiClsLoader = iiCls.getClassLoader();
		aSimpleContainerDetailsPanelTest = (SimpleContainerDetailsPanelTest)java.beans.Beans.instantiate(iiClsLoader,"com.philemonworks.awt.test.SimpleContainerDetailsPanelTest");
		frame.add("Center", aSimpleContainerDetailsPanelTest);
		frame.setSize(aSimpleContainerDetailsPanelTest.getSize());
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
/**
 * Comment
 */
public void select_ActionPerformed(java.awt.event.ActionEvent actionEvent) {

	int index = Integer.parseInt(getindexField().getText());
	getDetailsPanel().setSelectionIndex(index);
	
	return;
}
/**
 * Comment
 */
public void updateItems() {
	return;
}
}
