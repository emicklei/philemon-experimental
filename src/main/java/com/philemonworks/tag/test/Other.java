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

import com.philemonworks.tag.ITaggedAccessible;

/**
 * @author emicklei
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Other implements ITaggedAccessible {
	public String name;

	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param string
	 */
	public void setName(String string) {
		name = string;
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
		if (tag.equals("name")) this.setName((String)value);

	}

	/* (non-Javadoc)
	 * @see com.philemonworks.tag.ITaggedAccessible#writeTaggedValuesOn(java.io.PrintWriter)
	 */
	public void writeTaggedValuesOn(PrintWriter writer) {
		// TODO Auto-generated method stub

	}

}
