package com.philemonworks.textprocessor.test;

import java.util.HashMap;

import com.philemonworks.textprocessor.LineEncloser;
import com.philemonworks.textprocessor.LineSplitter;
import com.philemonworks.textprocessor.StringIterator;
import com.philemonworks.textprocessor.StringSource;
import com.philemonworks.textprocessor.WordReplacer;

/**
 * ChainText is
 * 
 * @author E.M.Micklei
 */
public class ChainTest {
	public static void main(String[] args){
		StringIterator it0 = new StringSource("Help\nme");
		// split into lines
		StringIterator it1 = new LineSplitter(it0);
		// replace words
		WordReplacer it2 = new WordReplacer(it1);
		HashMap map = new HashMap();
		map.put("me","you");
		it2.setDictionary(map);
		// enclose with xml stuff
		LineEncloser it3 = new LineEncloser(it2);
		it3.setPrefix("<line>");
		it3.setSuffix("</line>");
		// see result
		StringIterator itEnd = it3;
		while (itEnd.hasNext()) System.out.println(itEnd.next());
	}

}
