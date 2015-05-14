/*
 * Licensed Material - Property of PhilemonWorks B.V.
 * 
 * (c) Copyright PhilemonWorks 2004,2005 - All rights reserved.
 * Use, duplication, distribution or disclosure restricted. 
 * See http://www.philemonworks.com for information.
 * 
 * @author emicklei
 * 7-feb-2005: created
 *
 */
package com.philemonworks.util;

import java.util.Date;
import junit.framework.TestCase;

/**
 * 
 */
public class UtilsTest extends TestCase {
    public void testDate2String(){
        System.out.println(Utils.dateToString(new Date()));
    }
    public void testString2Date(){
        System.out.println(Utils.dateFromString(Utils.dateToString(new Date())));
    }    
}
