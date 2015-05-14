package com.philemonworks.tag;

/**
 * Insert the type's description here.
 * Creation date: (17-11-00 14:44:09)
 * @author: Ernest Micklei
 */
public interface ITaggedAccessible {
public boolean isEmptyElement();
public void setValueForTag(String tag, Object value);
public void writeTaggedValuesOn(java.io.PrintWriter writer);
}
