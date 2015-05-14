/*
 * Licensed Material - Property of PhilemonWorks B.V.
 * 
 * (c) Copyright PhilemonWorks 2004 - All rights reserved.
 * Use, duplication, distribution or disclosure restricted. 
 * See http://www.philemonworks.com for information.
 * 
 * VERSION HISTORY
 * Jul 2, 2004 : created
 */
package com.philemonworks.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import junit.framework.TestCase;

/**
 * @author mvhulsentop
 * 
 * Use of getXXX() methods of Date() is deprecated. However, for test purposes,
 * it's still usefull. Disables warning for deprecated methods in this project.
 */
public class TestDateAndTime extends TestCase {
    public void testDateString() {
        Date current = new Date();
        String stringRep = Utils.dateToString(current);
        System.out.println("Date: " + current.toString() + " = " + stringRep);
        Date read = Utils.dateFromString(stringRep);
    }

    public void testTimeString() {
        Date current = new Date();
        String stringRep = Utils.timeToString(current);
        System.out.println("Time: " + current.toString() + " = " + stringRep);
        Date read = Utils.timeFromString(stringRep);
        Calendar cal = new GregorianCalendar();
        cal.setTime(current);
        Calendar readCal = new GregorianCalendar();
        readCal.setTime(read);
        assertEquals(cal.get(Calendar.HOUR), readCal.get(Calendar.HOUR));
        assertEquals(cal.get(Calendar.MINUTE), readCal.get(Calendar.MINUTE));
        assertEquals(cal.get(Calendar.SECOND), readCal.get(Calendar.SECOND));
    }

    public void testDateTimeString() {
        Date current = new Date();
        String stringRep = Utils.dateTimeToString(current);
        System.out.println("Date&Time: " + current.toString() + " = " + stringRep);
        Date read = Utils.dateTimeFromString(stringRep);
    }
}