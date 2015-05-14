package com.philemonworks.navigation;

import com.philemonworks.tag.ITaggedAccessible;
 
public class MapSpec implements ITaggedAccessible{

	public String name;
	public Area[] areas = {};
/**
 * isEmptyElement method comment.
 */
public Area areaAt(String alt) {

	for (int a=0;a<areas.length;a++) {
		if (areas[a].spec.alt.equalsIgnoreCase(alt)) return areas[a];
	}
	// no such area
	return null;
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
public void setValueForTag(java.lang.String tag, java.lang.Object value) {

	if (tag.equals("name")) name = (String)value;	
	else if (tag.equals("area")) {
		Area[] newAreas = new Area[areas.length+1];
		System.arraycopy(areas,0,newAreas,0,areas.length);
		newAreas[areas.length]=Area.newFromSpec((AreaSpec)value);
		areas = newAreas;
	}
}
/**
 * writeTaggedValuesOn method comment.
 */
public void writeTaggedValuesOn(java.io.PrintWriter writer) {}
}
