package com.philemonworks.textprocessor;

/**
 * StringSource is
 * 
 * @author E.M.Micklei
 */
public class StringSource implements StringIterator {
	private boolean consumed = false;
	private String source;
	public StringSource(String theSource){
		super();
		source = theSource;
	}
	/* (non-Javadoc)
	 * @see com.philemonworks.textprocessor.StringIterator#hasNext()
	 */
	public boolean hasNext() {
		return !consumed;
	}
	/* (non-Javadoc)
	 * @see com.philemonworks.textprocessor.StringIterator#next()
	 */
	public String next() {
		consumed = true;
		return source;
	}
}
