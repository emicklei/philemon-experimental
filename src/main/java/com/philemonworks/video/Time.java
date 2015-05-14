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
 * Time is
 * 
 * @author E.M.Micklei
 */
public class Time implements Cloneable {
	long milliseconds = 0;

	public Time() {
		super();
	}
	public Time(long ms) {
		super();
		milliseconds = ms;
	}
	public static Time readFrom(String timeString) {
		// 00:00:21,550
		String[] parts = timeString.split(":");
		Time now = new Time();
		now.addHours(Integer.parseInt(parts[0]));
		now.addMinutes(Integer.parseInt(parts[1]));
		parts = parts[2].split(",");
		now.addSeconds(Integer.parseInt(parts[0]));
		now.addMilliseconds(Integer.parseInt(parts[1]));
		return now;
	}
	public void addHours(int hours) {
		this.addMinutes(hours * 60);
	}
	public void addMinutes(int minutes) {
		this.addSeconds(minutes * 60);
	}
	public void addSeconds(int seconds) {
		this.addMilliseconds(seconds * 1000);
	}
	public void addMilliseconds(long ms) {
		milliseconds += ms;
	}
	public int getHours(){
		return (int)(milliseconds / (60*60*1000));
	}
	public int getMinutes(){
		return (int)(milliseconds / (60*1000)) % 60;
	}
	public int getSeconds(){
		return (int)(milliseconds / 1000) % 60;
	}	
	public String toString(){
		StringBuffer w = new StringBuffer();
		w.append(this.twoDigits(this.getHours()));
		w.append(":");
		w.append(this.twoDigits(this.getMinutes()));
		w.append(":");
		w.append(this.twoDigits(this.getSeconds()));
		w.append(",");
		w.append(this.threeDigits(milliseconds % 1000));		
		return w.toString();
	}
	private String twoDigits(int value){
		return value < 10 ? "0" + value : String.valueOf(value);
	}
	private String threeDigits(long value){
		return value < 100 ? "0" + this.twoDigits((int)value) : String.valueOf(value);
	}
	public boolean before(Time otherTime){
		return milliseconds < otherTime.milliseconds;
	}
	public void subtract(Time otherTime){
		milliseconds -= otherTime.milliseconds;
	}
	public Object clone(){
		return new Time(milliseconds);
	}
}
