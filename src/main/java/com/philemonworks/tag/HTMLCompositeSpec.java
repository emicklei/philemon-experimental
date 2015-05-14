package com.philemonworks.tag;

public class HTMLCompositeSpec implements com.philemonworks.tag.ITaggedAccessible {

	java.util.Hashtable attributes = new java.util.Hashtable();

public Object get(String tag) {
	
	return attributes.get(tag);
}
/**
 * isEmptyElement method comment.
 */
public boolean isEmptyElement() {
	return false;
}
/**
 * setValueForTag method comment.
 */
public void setValueForTag(String tag, Object value) {

	// maybe a collection ?
	String lower = tag.toLowerCase();
	Object oldValue = attributes.get(lower);
	if (oldValue == null)
		attributes.put(lower,value);
	else {
		if (oldValue instanceof java.util.Vector) {
			((java.util.Vector)oldValue).addElement(value);
		} else {
			java.util.Vector newList = new java.util.Vector();
			newList.addElement(oldValue);
			newList.addElement(value);
			attributes.put(lower,newList);
		}
	}	
}
/**
 * writeTaggedValuesOn method comment.
 */
public void writeTaggedValuesOn(java.io.OutputStream out) {}
/**
 * writeTaggedValuesOn method comment.
 */
public void writeTaggedValuesOn(java.io.PrintWriter writer) {}
}
