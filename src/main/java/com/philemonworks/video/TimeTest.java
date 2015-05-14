package com.philemonworks.video;

import junit.framework.TestCase;

public class TimeTest extends TestCase {
	public void testTime(){
		Time now = Time.readFrom("11:22:33,444");
	}
}
