/**
 * Licensed Material - Property of PhilemonWorks B.V.
 * 
 * (c) Copyright PhilemonWorks 2005 - All rights reserved.
 * Use, duplication, distribution or disclosure restricted. 
 * See http://www.philemonworks.com for information.
 */
package com.philemonworks.video;

import java.io.StringWriter;

/**
 * SRTEntry is
 * 
 * @author E.M.Micklei
 */
public class SRTEntry {
	public Time from;
	public Time to;
	public String[] lines;
	public void add(String line){
		if (lines == null) {
			lines = new String[]{line};
			return;
		}
		String[] newLines = new String[lines.length+1];
		System.arraycopy(lines,0,newLines,0,lines.length);
		newLines[lines.length]=line;
		lines = newLines;
	}
	public String toString(){
		StringBuffer w = new StringBuffer();
		w.append(from.toString());
		w.append(" --> ");
		w.append(to.toString());
		w.append("\n");
		for (int i=0;i<lines.length;i++) w.append(lines[i]+"\n");
		return w.toString();
	}
	public boolean before(SRTEntry entry){
		return from.before(entry.from);
	}
	public boolean before(Time time){
		return from.before(time);
	}
}
