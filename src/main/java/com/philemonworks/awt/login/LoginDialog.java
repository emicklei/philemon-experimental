package com.philemonworks.awt.login;

/**
 * Insert the type's description here.
 * Creation date: (7/4/2000 12:31:21 AM)
 * @author: Ernest Micklei
 */
public class LoginDialog extends java.awt.Dialog {
	private java.awt.Panel ivjContentsPane = null;
	IvjEventHandler ivjEventHandler = new IvjEventHandler();
	private java.awt.Label ivjLabel1 = null;
	private java.awt.Label ivjLabel2 = null;
	private java.awt.Button ivjLoginButton = null;
	private java.awt.TextField ivjPasswordField = null;
	private java.awt.TextField ivjNameField = null;
	public boolean accepted = false;

class IvjEventHandler implements java.awt.event.ActionListener, java.awt.event.TextListener, java.awt.event.WindowListener {
		public void actionPerformed(java.awt.event.ActionEvent e) {
			if (e.getSource() == LoginDialog.this.getLoginButton()) 
				connEtoC2(e);
			if (e.getSource() == LoginDialog.this.getPasswordField()) 
				connEtoC5(e);
		};
		public void textValueChanged(java.awt.event.TextEvent e) {
			if (e.getSource() == LoginDialog.this.getNameField()) 
				connEtoC3(e);
			if (e.getSource() == LoginDialog.this.getPasswordField()) 
				connEtoC4(e);
		};
		public void windowActivated(java.awt.event.WindowEvent e) {};
		public void windowClosed(java.awt.event.WindowEvent e) {};
		public void windowClosing(java.awt.event.WindowEvent e) {
			if (e.getSource() == LoginDialog.this) 
				connEtoC1(e);
		};
		public void windowDeactivated(java.awt.event.WindowEvent e) {};
		public void windowDeiconified(java.awt.event.WindowEvent e) {};
		public void windowIconified(java.awt.event.WindowEvent e) {};
		public void windowOpened(java.awt.event.WindowEvent e) {};
	};
/**
 * LoginDialog constructor comment.
 * @param parent java.awt.Frame
 */
public LoginDialog(java.awt.Frame parent) {
	super(parent);
	initialize();
}
/**
 * LoginDialog constructor comment.
 * @param parent java.awt.Frame
 * @param title java.lang.String
 */
public LoginDialog(java.awt.Frame parent, String title) {
	super(parent, title);
}
/**
 * LoginDialog constructor comment.
 * @param parent java.awt.Frame
 * @param title java.lang.String
 * @param modal boolean
 */
public LoginDialog(java.awt.Frame parent, String title, boolean modal) {
	super(parent, title, modal);
}
/**
 * LoginDialog constructor comment.
 * @param parent java.awt.Frame
 * @param modal boolean
 */
public LoginDialog(java.awt.Frame parent, boolean modal) {
	super(parent, modal);
}
/**
 * connEtoC1:  (LoginDialog.window.windowClosing(java.awt.event.WindowEvent) --> LoginDialog.dispose()V)
 * @param arg1 java.awt.event.WindowEvent
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void connEtoC1(java.awt.event.WindowEvent arg1) {
	try {
		// user code begin {1}
		// user code end
		this.dispose();
		// user code begin {2}
		// user code end
	} catch (java.lang.Throwable ivjExc) {
		// user code begin {3}
		// user code end
		handleException(ivjExc);
	}
}
/**
 * connEtoC2:  (Button1.action.actionPerformed(java.awt.event.ActionEvent) --> LoginDialog.doLogin(Ljava.awt.event.ActionEvent;)V)
 * @param arg1 java.awt.event.ActionEvent
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void connEtoC2(java.awt.event.ActionEvent arg1) {
	try {
		// user code begin {1}
		// user code end
		this.login(arg1);
		// user code begin {2}
		// user code end
	} catch (java.lang.Throwable ivjExc) {
		// user code begin {3}
		// user code end
		handleException(ivjExc);
	}
}
/**
 * connEtoC3:  (NaamField.text.textValueChanged(java.awt.event.TextEvent) --> LoginDialog.validate(Ljava.awt.event.TextEvent;)V)
 * @param arg1 java.awt.event.TextEvent
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void connEtoC3(java.awt.event.TextEvent arg1) {
	try {
		// user code begin {1}
		// user code end
		this.validate(arg1);
		// user code begin {2}
		// user code end
	} catch (java.lang.Throwable ivjExc) {
		// user code begin {3}
		// user code end
		handleException(ivjExc);
	}
}
/**
 * connEtoC4:  (PasswordField.text.textValueChanged(java.awt.event.TextEvent) --> LoginDialog.validate(Ljava.awt.event.TextEvent;)V)
 * @param arg1 java.awt.event.TextEvent
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void connEtoC4(java.awt.event.TextEvent arg1) {
	try {
		// user code begin {1}
		// user code end
		this.validate(arg1);
		// user code begin {2}
		// user code end
	} catch (java.lang.Throwable ivjExc) {
		// user code begin {3}
		// user code end
		handleException(ivjExc);
	}
}
/**
 * connEtoC5:  (PasswordField.action.actionPerformed(java.awt.event.ActionEvent) --> LoginDialog.loginIfValid(Ljava.awt.event.ActionEvent;)V)
 * @param arg1 java.awt.event.ActionEvent
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void connEtoC5(java.awt.event.ActionEvent arg1) {
	try {
		// user code begin {1}
		// user code end
		this.loginIfValid(arg1);
		// user code begin {2}
		// user code end
	} catch (java.lang.Throwable ivjExc) {
		// user code begin {3}
		// user code end
		handleException(ivjExc);
	}
}
/**
 * Return the ContentsPane property value.
 * @return java.awt.Panel
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private java.awt.Panel getContentsPane() {
	if (ivjContentsPane == null) {
		try {
			ivjContentsPane = new java.awt.Panel();
			ivjContentsPane.setName("ContentsPane");
			ivjContentsPane.setLayout(null);
			getContentsPane().add(getLoginButton(), getLoginButton().getName());
			getContentsPane().add(getNameField(), getNameField().getName());
			getContentsPane().add(getPasswordField(), getPasswordField().getName());
			getContentsPane().add(getLabel1(), getLabel1().getName());
			getContentsPane().add(getLabel2(), getLabel2().getName());
			// user code begin {1}
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	}
	return ivjContentsPane;
}
/**
 * Return the Label1 property value.
 * @return java.awt.Label
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private java.awt.Label getLabel1() {
	if (ivjLabel1 == null) {
		try {
			ivjLabel1 = new java.awt.Label();
			ivjLabel1.setName("Label1");
			ivjLabel1.setFont(new java.awt.Font("dialog", 0, 14));
			ivjLabel1.setText("Toegangsnaam:");
			ivjLabel1.setBounds(14, 21, 114, 23);
			// user code begin {1}
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	}
	return ivjLabel1;
}
/**
 * Return the Label2 property value.
 * @return java.awt.Label
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private java.awt.Label getLabel2() {
	if (ivjLabel2 == null) {
		try {
			ivjLabel2 = new java.awt.Label();
			ivjLabel2.setName("Label2");
			ivjLabel2.setFont(new java.awt.Font("dialog", 0, 14));
			ivjLabel2.setText("Wachtwoord:");
			ivjLabel2.setBounds(17, 57, 98, 23);
			ivjLabel2.setVisible(true);
			// user code begin {1}
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	}
	return ivjLabel2;
}
/**
 * Return the Button1 property value.
 * @return java.awt.Button
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private java.awt.Button getLoginButton() {
	if (ivjLoginButton == null) {
		try {
			ivjLoginButton = new java.awt.Button();
			ivjLoginButton.setName("LoginButton");
			ivjLoginButton.setFont(new java.awt.Font("dialog", 0, 14));
			ivjLoginButton.setBounds(87, 100, 141, 23);
			ivjLoginButton.setEnabled(false);
			ivjLoginButton.setLabel("Login");
			// user code begin {1}
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	}
	return ivjLoginButton;
}
/**
 * Return the TextField1 property value.
 * @return java.awt.TextField
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private java.awt.TextField getNameField() {
	if (ivjNameField == null) {
		try {
			ivjNameField = new java.awt.TextField();
			ivjNameField.setName("NameField");
			ivjNameField.setFont(new java.awt.Font("dialog", 0, 14));
			ivjNameField.setBackground(java.awt.Color.white);
			ivjNameField.setBounds(131, 21, 140, 23);
			// user code begin {1}
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	}
	return ivjNameField;
}
public String getPassword() {

	return this.getPasswordField().getText();
}
/**
 * Return the TextField2 property value.
 * @return java.awt.TextField
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private java.awt.TextField getPasswordField() {
	if (ivjPasswordField == null) {
		try {
			ivjPasswordField = new java.awt.TextField();
			ivjPasswordField.setName("PasswordField");
			ivjPasswordField.setEchoChar('X');
			ivjPasswordField.setFont(new java.awt.Font("dialog", 0, 14));
			ivjPasswordField.setBackground(java.awt.Color.white);
			ivjPasswordField.setBounds(131, 57, 141, 23);
			ivjPasswordField.setVisible(true);
			// user code begin {1}
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	}
	return ivjPasswordField;
}
public String getUserName() {

	return this.getNameField().getText();
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
 * Initializes connections
 * @exception java.lang.Exception The exception description.
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void initConnections() throws java.lang.Exception {
	// user code begin {1}
	// user code end
	this.addWindowListener(ivjEventHandler);
	getNameField().addTextListener(ivjEventHandler);
	getPasswordField().addTextListener(ivjEventHandler);
	getLoginButton().addActionListener(ivjEventHandler);
	getPasswordField().addActionListener(ivjEventHandler);
}
/**
 * Initialize the class.
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void initialize() {
	try {
		// user code begin {1}
		setBounds(200, 200, 320, 164);
		// user code end
		setName("LoginDialog");
		setLayout(new java.awt.BorderLayout());
		setSize(319, 195);
		setTitle("Toegangscontrole");
		add(getContentsPane(), "Center");
		initConnections();
	} catch (java.lang.Throwable ivjExc) {
		handleException(ivjExc);
	}
	// user code begin {2}
	// user code end
}
/**
 * Insert the method's description here.
 * Creation date: (7/7/2000 3:41:56 PM)
 * @return boolean
 */
public boolean isAccepted() {
	return accepted;
}
/**
 * Comment
 */
public void login(java.awt.event.ActionEvent actionEvent) {
	
	accepted = true;
	dispose();
}
/**
 * Comment
 */
public void loginIfValid(java.awt.event.ActionEvent actionEvent) {
	
	if (getNameField().getText().length() > 0 && getPasswordField().getText().length() > 0)
		this.login(actionEvent);
}
/**
 * main entrypoint - starts the part when it is run as an application
 * @param args java.lang.String[]
 */
public static void main(java.lang.String[] args) {
	try {
		LoginDialog aLoginDialog = new LoginDialog(new java.awt.Frame());
		aLoginDialog.setModal(true);
		aLoginDialog.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent e) {
				System.exit(0);
			};
		});
		aLoginDialog.setVisible(true);
	} catch (Throwable exception) {
		System.err.println("Exception occurred in main() of java.awt.Dialog");
		exception.printStackTrace(System.out);
	}
}
public void setPassword(String newPassw) {

	this.getPasswordField().setText(newPassw);
	this.validate(null);
}
public void setUserName(String newName) {

	this.getNameField().setText(newName);
}
/**
 * Comment
 */
public void validate(java.awt.event.TextEvent textEvent) {

	boolean valid = this.getUserName().length() > 0 && this.getPassword().length() > 0;
	this.getLoginButton().setEnabled(valid);
}
}
