/*
 * Licensed Material - Property of PhilemonWorks B.V.
 * 
 * (c) Copyright PhilemonWorks 2004 - All rights reserved.
 * Use, duplication, distribution or disclosure restricted. 
 * See http://www.philemonworks.com for information.
 * 
 * VERSION HISTORY
 * 27-mrt-04: created
 *
 */
package com.philemonworks.tag.test;

import java.io.PrintWriter;
import java.util.ArrayList;

import com.philemonworks.tag.ITaggedAccessible;

/**
 * @author emicklei
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Any implements ITaggedAccessible {
	public int id = -1;
	public ArrayList others = new ArrayList();

	/**
	 * @return
	 */
	public ArrayList getOthers() {
		return others;
	}

	/**
	 * @param list
	 */
	public void setOthers(ArrayList list) {
		others = list;
	}

	/* (non-Javadoc)
	 * @see com.philemonworks.tag.ITaggedAccessible#isEmptyElement()
	 */
	public boolean isEmptyElement() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.philemonworks.tag.ITaggedAccessible#setValueForTag(java.lang.String, java.lang.Object)
	 */
	public void setValueForTag(String tag, Object value) {
		if (tag.equals("id")) id = Integer.valueOf((String)value).intValue();
		if (tag.equals("other")) this.getOthers().add(value);

	}

	/* (non-Javadoc)
	 * @see com.philemonworks.tag.ITaggedAccessible#writeTaggedValuesOn(java.io.PrintWriter)
	 */
	public void writeTaggedValuesOn(PrintWriter writer) {
		// TODO Auto-generated method stub

	}

}
