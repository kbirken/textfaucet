package org.nanosite.textfaucet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Collects information about text analysis for a specific prefix.
 * 
 * @author Klaus Birken
 */
public class CharCounts {

	private int nOverall = 0;
	private Map<Character, Integer> counts = new HashMap<Character, Integer>();
	
	public void count (Character c) {
		nOverall++;
		
		int nOld = getNChar(c);
		counts.put(c, nOld+1);
	}
	
	public int getNAll() {
		return nOverall;
	}
	
	public int getNChar (Character c) {
		return counts.containsKey(c) ? counts.get(c) : 0;
	}
	
	public List<Character> getSortedChars() {
		List<Character> result = new ArrayList<Character>(counts.keySet());
		Collections.sort(result, new Comparator<Character>() {
			@Override
			public int compare(Character a, Character b) {
				Integer na = getNChar(a);
				Integer nb = getNChar(b);
				return -na.compareTo(nb);
			}
		});

		return result;
	}
	
	public double getNCharRel (Character c) {
		int n = getNChar(c);
		if (nOverall==0)
			return 0.0d;
		return ((double)n)/((double)nOverall);
	}

}

