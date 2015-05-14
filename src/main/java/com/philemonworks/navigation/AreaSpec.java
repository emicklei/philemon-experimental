package com.philemonworks.navigation;

import com.philemonworks.tag.ITaggedAccessible;

import java.awt.Color;
 
public class AreaSpec implements ITaggedAccessible{

	public String shape;
	public String href;
	public String target = "_blank";
	public String alt = "";
	public int[] coords;
	public String title;
	public Color color = Color.black;
/**
 * isEmptyElement method comment.
 */
public boolean isEmptyElement() {
	return true;
}
/* accepting
  *	color names: red,blue,black,white,green
  *	RGB values in HTML notation (HEX)
  */
public static Color readColorFrom(String value){

	if (value.equalsIgnoreCase("red")) return Color.red;
	else if (value.equalsIgnoreCase("blue")) return Color.blue;
	else if (value.equalsIgnoreCase("green")) return Color.green;
	else if (value.equalsIgnoreCase("black")) return Color.black;
	else if (value.equalsIgnoreCase("white")) return Color.white;
	else if (value.equalsIgnoreCase("gray")) return Color.gray;	
	else try {
		int red = Integer.parseInt(value.substring(1,3),16);
		int green = Integer.parseInt(value.substring(3,5),16);
		int blue = Integer.parseInt(value.substring(5,7),16);			
		return new java.awt.Color(red,green,blue);
	} catch (Throwable t) {
		// bummer
	}
	return Color.black;
}
/**
 * setValueForTag method comment.
 */
public void setValueForTag(java.lang.String tag, java.lang.Object value) {
	
	if (tag.equals("shape")) shape = ((String)value).toLowerCase();
	else if (tag.equals("color")) color = readColorFrom((String)value);
	else if (tag.equals("title")) title =(String)value;
	else if (tag.equals("href")) href =(String)value;
	else if (tag.equals("alt")) alt =(String)value;
	else if (tag.equals("target")) target =(String)value;
	else if (tag.equals("coords")) {
		java.util.StringTokenizer tokenizer = new java.util.StringTokenizer((String)value, ",");
		java.util.Vector strings = new java.util.Vector();
		while (tokenizer.hasMoreTokens()) {
			strings.addElement(tokenizer.nextElement());
		}
		coords = new int[strings.size()];
		for (int i=0;i<strings.size();i++) coords[i]=Integer.parseInt(((String)strings.elementAt(i)).trim());
	}
}
/**
 * writeTaggedValuesOn method comment.
 */
public void writeTaggedValuesOn(java.io.PrintWriter writer) {}
}
