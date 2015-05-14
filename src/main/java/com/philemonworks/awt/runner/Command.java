package com.philemonworks.awt.runner;

/**
 * Insert the type's description here.
 * Creation date: (16-12-2003 9:44:09)
 * @author: Ernest Micklei
 */
public class Command {
	public String methodName = null;
	public Object target = null;
	private Object[] parameters = {};
	private Class[] parameterTypes = {};
	private Exception error = null;
/**
 * Command constructor comment.
 */
public Command() {
	super();
}
/**
 * Command constructor comment.
 */
public Command(Object newTarget, String newMethodName) {
	super();
	this.target = newTarget;
	this.methodName = newMethodName;
}
public void addBooleanParameter(String value){
	this.addParameter(value.equals("true"));
}
public void addIntParameter(String value){
	this.addParameter(Integer.parseInt(value));
}
public void addParameter(int value){

	this.growParametersAndTypes();
	parameters[parameters.length-1] = new Integer(value);
	parameterTypes[parameters.length-1] = int.class;
}
public void addParameter(Object value){

	this.growParametersAndTypes();
	parameters[parameters.length-1] = value;
	if (value != null) parameterTypes[parameters.length-1] = value.getClass();
}
public void addParameter(boolean value){

	this.growParametersAndTypes();
	parameters[parameters.length-1] = new Boolean(value);
	parameterTypes[parameters.length-1] = boolean.class;
}
public void addStringParameter(String value){
	this.addParameter(value);
}
/**
 * Command constructor comment.
 */
public Object execute(){
	// Send the message to the target

	error = null; // forget the last error if any
    Object result = null;
	Class receiverClass = target.getClass();
	
    try {       
        java.lang.reflect.Method method = receiverClass.getMethod(methodName, parameterTypes);
        result = method.invoke(target, parameters);
    } catch (Exception ex) {
        error = ex;
    }
    return result;
}
public Exception getException(){
	return error;
}
private void growParametersAndTypes(){

	Object[] newParameters = new Object[parameters.length+1];
	System.arraycopy(parameters,0,newParameters,0,parameters.length);
	parameters = newParameters;

	Class[] newParameterTypes = new Class[parameterTypes.length+1];
	System.arraycopy(parameterTypes,0,parameterTypes,0,parameterTypes.length);
	parameterTypes = newParameterTypes;
}
public boolean hasFailed(){
	return error != null;
}
private void printParameterXMLOn(int p, StringBuffer buffer) {

    if (parameters[p] instanceof java.lang.String) {
        buffer.append(" string");
    } else
        if (parameters[p] instanceof java.lang.Boolean) {
            buffer.append(" boolean");
        }
    buffer.append(p);
    buffer.append("=\"");
    buffer.append(parameters[p]);
    buffer.append("\"");

}
public String toString(){

	StringBuffer buffer = new StringBuffer();
	buffer.append(target.toString());
	buffer.append('.');
	buffer.append(methodName);
	buffer.append('(');
	for (int p=0;p<parameters.length;) {
		buffer.append(parameters[p]);
		if (++p!=parameters.length) buffer.append(',');
	}
	buffer.append(')');
	return buffer.toString();
}
public String toXML(){

	StringBuffer buffer = new StringBuffer();
	buffer.append("<command id=\"");
	buffer.append(target);
	buffer.append("\" method=\"");	
	buffer.append(methodName);
	buffer.append("\"" );
	for (int p=0;p<parameters.length;p++) {
		this.printParameterXMLOn(p,buffer);
	}
	buffer.append("/>");
	return buffer.toString();
}
}
