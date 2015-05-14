package com.philemonworks.textprocessor;


/**
 * LineSplitter is
 * 
 * @author E.M.Micklei
 */
public class LineSplitter implements StringIterator {
	private static final String DELIM = "\n";
	StringIterator source;
	String[] lines = new String[0];
	int index = 1;

	public LineSplitter(StringIterator iterator) {
		super();
		source = iterator;
	}
	public boolean hasNext() {
		return index < lines.length || source.hasNext();
	}
	public String next() {
		if (index < lines.length) {
			return lines[index++];
		} else {
			lines = source.next().split(DELIM);
			index = lines.length == 0 ? 1 : 0;
			return this.next();
		}
	}
}
