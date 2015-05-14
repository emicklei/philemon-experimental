package com.philemonworks.awt.runner;

/**
 * Insert the type's description here.
 * Creation date: (16-12-2003 13:15:57)
 * @author: Ernest Micklei
 */
import java.util.*;
import java.awt.*;

public class GUIPerformer {

	public Component target= null;
	public ArrayList commands = new java.util.ArrayList();
/**
 * GUIPerformer constructor comment.
 */
public GUIPerformer() {
	super();
}
public void activate(String nameOfFrame){
}
public Component findComponentIn(String itsName, Container container) {

    for (int i = 0; i < container.getComponentCount(); ++i) {
        Component comp = container.getComponent(i);
        if (comp.getName() != null && comp.getName().equals(itsName)) {
            return comp;
        }
        if (comp instanceof java.awt.Container) {
	        Component result = this.findComponentIn(itsName, (Container) comp);
			if (result != null) return result;
        }
    }
    return null;
}
public void passivate(String nameOfFrame){
}
public boolean resolveCommand(Command command) {

	// handle special cases
	if (command.target.equals("@this")) {
		command.target = this;
		return true;
	}
	
	StringTokenizer tokenizer = new StringTokenizer((String)command.target, "/");
	Component here = target;
	while (tokenizer.hasMoreTokens()) {
		here = this.findComponentIn(tokenizer.nextToken(),(Container)here);
		if (here == null) return false;
	}
	command.target = here;
	return true;
}
}
