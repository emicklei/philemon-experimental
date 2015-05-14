package com.philemonworks.awt.test;

import com.philemonworks.awt.details.SimpleContainerDetails;
import com.philemonworks.awt.details.SimpleContainerDetailsPanel;

public class Item implements SimpleContainerDetails {

	String key, value;
/**
 * Item constructor comment.
 */
public Item() {
	super();
}
/**
 * Item constructor comment.
 */
public Item(String k, String v) {
	super();
	key = k;
	value = v;
}
public int compareDetails (int indexInDetails, SimpleContainerDetails aDetails, boolean ascending) {

	if (indexInDetails == 0)
		return key.compareTo(((Item)aDetails).getKey());
	if (indexInDetails == 1)
		return value.compareTo(((Item)aDetails).getValue());
	return 0;
}
/**
 * containerDetailsFor method comment.
 */
public java.lang.String[] containerDetailsFor(SimpleContainerDetailsPanel panel) {
	
	String[] details = new String[2];
	details[0] = key;
	details[1] = value;
	return details;
	
}
/**
 * 
 * @return java.lang.String
 */
public java.lang.String getKey() {
	return key;
}
/**
 * 
 * @return java.lang.String
 */
public java.lang.String getValue() {
	return value;
}
/**
 * 
 * @param newKey java.lang.String
 */
public void setKey(java.lang.String newKey) {
	key = newKey;
}
/**
 * 
 * @param newValue java.lang.String
 */
public void setValue(java.lang.String newValue) {
	value = newValue;
}
}
