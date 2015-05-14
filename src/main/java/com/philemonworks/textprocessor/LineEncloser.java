package com.philemonworks.textprocessor;

/**
 * LineEncloser is
 * 
 * @author E.M.Micklei
 */
public class LineEncloser implements StringIterator {
	private StringIterator source;
	private String prefix;
	private String suffix;
	
	public LineEncloser(StringIterator sourceIterator){
		super();
		source = sourceIterator;
	}
	/**
	 * @return
	 */
	public String getPrefix() {
		return prefix;
	}

	/**
	 * @return
	 */
	public StringIterator getSource() {
		return source;
	}

	/**
	 * @return
	 */
	public String getSuffix() {
		return suffix;
	}

	/**
	 * @param string
	 */
	public void setPrefix(String string) {
		prefix = string;
	}

	/**
	 * @param iterator
	 */
	public void setSource(StringIterator iterator) {
		source = iterator;
	}

	/**
	 * @param string
	 */
	public void setSuffix(String string) {
		suffix = string;
	}
	/* (non-Javadoc)
	 * @see com.philemonworks.textprocessor.StringIterator#hasNext()
	 */
	public boolean hasNext() {
		return source.hasNext();
	}
	/* (non-Javadoc)
	 * @see com.philemonworks.textprocessor.StringIterator#next()
	 */
	public String next() {
		return prefix + source.next() + suffix;
	}

}
