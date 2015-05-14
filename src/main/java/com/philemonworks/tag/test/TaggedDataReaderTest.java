/*
 * Created on 28-okt-2003
 *
 * XP Team
 * */
package com.philemonworks.tag.test;

import java.util.Hashtable;
import java.util.Vector;

import com.philemonworks.tag.TaggedDataParser;

import junit.framework.*;

/**
 * @author emicklei
 * 
 * Purpose: This class is for testing TagggedDataReader
 */
public class TaggedDataReaderTest extends TestCase {
    public TaggedDataReaderTest(String newName) {
        super(newName);
    }

    public void testHeader() {
        String header = "<?xml version=\"1.0\" >";
        Hashtable table = new Hashtable();
        TaggedDataParser parser = new TaggedDataParser(table);
        parser.parse(header);
    }
    public void testAny() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("<any />");
        Hashtable table = new Hashtable();
        table.put("any", Any.class);
        TaggedDataParser parser = new TaggedDataParser(table);
        parser.parse(buffer.toString());
        Vector result = parser.getObjects();
        assertEquals(1, result.size());
    }
    public void testAny2() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("<any id=\"10\" />");
        Hashtable table = new Hashtable();
        table.put("any", Any.class);
        TaggedDataParser parser = new TaggedDataParser(table);
        parser.parse(buffer.toString());
        Vector result = parser.getObjects();
        assertEquals(1, result.size());
        Any any = (Any)result.get(0);
        assertEquals(10, any.id);
    }    
}