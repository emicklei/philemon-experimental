package com.philemonworks.util;
/**
 * Insert the type's description here.
 * Creation date: (05-07-2000 13:22:11)
 * @author: Ernest Micklei
 */
public class Block {

	public Object parameter;
	public Block() {}
	public Block(Object referenceParameter) {
		super();
		parameter = referenceParameter;
	}
	public static Block stringSortBlock() {

		return new Block() {
			public Object value(Object arg1, Object arg2) {
				return new Boolean(
					((String) arg1).compareTo((String) arg2) == -1);
			}
		};
	}
	public Object value() {
		return null;
	}
	public Object value(Object arg1) {
		return null;
	}
	public Object value(Object arg1, Object arg2) {
		return null;
	}
}
