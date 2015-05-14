package com.philemonworks.navigation;

import com.philemonworks.tag.ITaggedAccessible;
 
public class ImageSpec implements ITaggedAccessible {

	public String source;
	public int width;
	public int height;
	public String usemap;
	
	// resolved resources
	public java.awt.Image image;
/**
 * isEmptyElement method comment.
 */
public boolean isEmptyElement() {
	return true;
}
/**
 * setValueForTag method comment.
 */
public void setValueForTag(java.lang.String tag, java.lang.Object value) {

	if (tag.equals("src")) source = (String)value;
	else if (tag.equals("width")) width = Integer.parseInt((String)value);
	else if (tag.equals("height")) height = Integer.parseInt((String)value);
	else if (tag.equals("usemap")) usemap = (String)value;		
}
/**
 * writeTaggedValuesOn method comment.
 */
public void writeTaggedValuesOn(java.io.PrintWriter writer) {}
}
