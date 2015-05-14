package com.philemonworks.awt.runner.test;

import com.philemonworks.awt.runner.Command;
import com.philemonworks.awt.runner.GUI;

/**
 * Insert the type's description here.
 * Creation date: (10-12-2003 16:30:59)
 * @author: Ernest Micklei
 */
public class CommandTest extends junit.framework.TestCase {
	public CommandTest(String newName){super(newName);}

public void testExecuteCommand1(){

	java.util.Vector target = new java.util.Vector();
	Command command = new Command(target, "size");
	Object result = command.execute();
	assertTrue(((Integer)result).intValue() == 0);
}
public void testExecuteCommand2(){

	java.util.Vector target = new java.util.Vector();
	target.add("first");
	Command command = new Command(target, "elementAt");
	command.addParameter(0);
	Object result = command.execute();
	assertTrue(result.equals("first"));
}
public void testExecuteCommand3(){

	java.util.Vector target = new java.util.Vector();
	Command command = new Command(target, "bogus");
	command.addParameter(0);
	Object result = command.execute();
	assertTrue("hasFailed", command.hasFailed());
	assertNotNull("error", command.getException());
	assertTrue("exception", command.getException() instanceof NoSuchMethodException);
}
public void testExecuteCommand4(){

	java.util.Vector target = new java.util.Vector();
	Object result = GUI.exectue(target,"size");
	assertTrue( ((Integer)result).intValue() == 0);
}
public void testExecuteCommand5(){

	java.util.Vector target = new java.util.Vector();
	target.add("first");
	Command command = GUI.command(target, "elementAt");
	command.addParameter(0);
	Object result = command.execute();
	assertTrue(result.equals("first"));
}
public void testExecuteCommand6(){

	java.awt.Label label = new java.awt.Label();
	Command command = GUI.command(label, "setText");
	command.addParameter(null);
	Object result = command.execute();
}
}
