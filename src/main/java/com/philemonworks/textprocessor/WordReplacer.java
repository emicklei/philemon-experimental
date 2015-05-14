package com.philemonworks.textprocessor;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * WordReplacer is
 * 
 * @author E.M.Micklei
 */
public class WordReplacer implements StringIterator {
	private StringIterator source;
	private Map dictionary = new HashMap();

	public WordReplacer(StringIterator theSource) {
		super();
		source = theSource;
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
		String line = source.next();
		Iterator keyIterator = dictionary.keySet().iterator();
		while (keyIterator.hasNext()){
			String word = (String)keyIterator.next();
			String replacement = (String)dictionary.get(word);
			line = line.replaceAll(word,replacement);
		}
		return line;
	}

	/**
	 * @return
	 */
	public Map getDictionary() {
		return dictionary;
	}

	/**
	 * @return
	 */
	public StringIterator getSource() {
		return source;
	}

	/**
	 * @param map
	 */
	public void setDictionary(Map map) {
		dictionary = map;
	}

	/**
	 * @param iterator
	 */
	public void setSource(StringIterator iterator) {
		source = iterator;
	}

}
