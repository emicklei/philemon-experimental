package com.philemonworks.util;

/**
 * Collection or simple type transformation utilities.
 * 
 * @author: Ernest Micklei
 */
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

public class Utils {
	public static Date dateFromString(String input) {
		if (input == null)
			return null;
		Date result = null;
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
			result = formatter.parse(input);
		} catch (Throwable t) {
			// invalid date
		}
		return result;
	}
	public static Date timeFromString(String input) {
		Date result = null;
		try {
			StringTokenizer tokenizer = new StringTokenizer(input, ".-: /");
			int hour = Integer.parseInt(tokenizer.nextToken());
			int minute = Integer.parseInt(tokenizer.nextToken());
			int second = Integer.parseInt(tokenizer.nextToken());
			java.util.GregorianCalendar calender = new java.util.GregorianCalendar(0, 0, 0, hour, minute, second);
			result = calender.getTime();
		} catch (Throwable t) {
			// invalid date
		}
		return result;
	}
	public static Date dateTimeFromString(String input) {
		Date result = null;
		try {
			StringTokenizer tokenizer = new StringTokenizer(input, ";");
			java.util.GregorianCalendar readDate = new java.util.GregorianCalendar();
			readDate.setTime(dateFromString(tokenizer.nextToken()));
			java.util.GregorianCalendar readTime = new java.util.GregorianCalendar();
			readTime.setTime(timeFromString(tokenizer.nextToken()));
			java.util.GregorianCalendar combined = new java.util.GregorianCalendar();
			combined.set(Calendar.YEAR, readDate.get(Calendar.YEAR));
			combined.set(Calendar.MONTH, readDate.get(Calendar.MONTH));
			combined.set(Calendar.DAY_OF_MONTH, readDate.get(Calendar.DAY_OF_MONTH));
			combined.set(Calendar.HOUR, readTime.get(Calendar.HOUR));
			combined.set(Calendar.MINUTE, readTime.get(Calendar.MINUTE));
			combined.set(Calendar.SECOND, readTime.get(Calendar.SECOND));
			result = combined.getTime();
		} catch (Throwable t) {
		}
		return result;
	}
	/**
	 * Insert the method's description here. Creation date: (22-06-2000 13:48:24)
	 */
	public static String dateToString(java.util.Date aDate) {
		if (aDate == null)
			return null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
		return formatter.format(aDate);
	}
	public static String timeToString(java.util.Date aTime) {
		if (aTime == null)
			return null;
		StringBuffer buffer = new StringBuffer(16);
		java.util.GregorianCalendar calender = new java.util.GregorianCalendar();
		calender.setTime(aTime);
		int value = calender.get(Calendar.HOUR_OF_DAY);
		if (value < 10)
			buffer.append('0');
		buffer.append(value);
		buffer.append(":");
		value = calender.get(Calendar.MINUTE);
		if (value < 10)
			buffer.append('0');
		buffer.append(value);
		buffer.append(":");
		value = calender.get(Calendar.SECOND);
		if (value < 10)
			buffer.append('0');
		buffer.append(value);
		return buffer.toString();
	}
	public static String dateTimeToString(java.util.Date aTimestamp) {
		return dateToString(aTimestamp) + ";" + timeToString(aTimestamp);
	}
	/**
	 * Read a floating point number from the input string. The format is {integer number}.{integer fraction part}
	 */
	public static float floatFromString(String numberString) {
		int intpart;
		int fractionpart;
		float number;
		int dot = numberString.indexOf(".");
		if (dot == -1) {
			intpart = Integer.parseInt(numberString);
			fractionpart = 0;
		} else {
			intpart = Integer.parseInt(numberString.substring(0, dot));
			fractionpart = Integer.parseInt(numberString.substring(dot + 1, numberString.length()));
		}
		number = (float) fractionpart;
		while (number > 1)
			number = number / 10f;
		return (float) intpart + number;
	}
}