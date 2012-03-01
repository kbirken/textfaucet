/**
 * (c) Copyright 2012 Klaus Birken - All rights reserved.
 */
package org.nanosite.textfaucet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TextGenerator {

	private final int verbose;
	private final String foundry;
	
	public TextGenerator (int verbose, String foundry) {
		this.verbose = verbose;
		this.foundry = foundry;
	}
	
	
	public void generate (int nGenerated, String start, IPrinter printer) {
		String window = start;
		String all = window;
		if (verbose==0) {
			printer.printString(window);
		}
		for(int i = 0; i < nGenerated; i++) {
			Character c = getNextCharacter(window, verbose);
			window = window.substring(1) + c;
			all += c;
			if (verbose==0)
				printer.printCharacter(c);
			else
				System.out.println("window: '" + window + "' gesamt: " + all);
		}
		
	}
	
	
	private Character getNextCharacter (String window, int verbose) {
		Set<Integer> found = new HashSet<Integer>();
		Map<Character,Integer> howmany = new HashMap<Character,Integer>(); 
		int i = 0;
		int n = window.length();
		while (i != -1) {
			int j = foundry.indexOf(window, i);
			if (j==-1) {
				i = j;
			} else {
				if (verbose>0)
					System.out.println("found! j=" + j + " : " + foundry.substring(j, j+20));
				found.add(j);
				int k = 0;
				Character c = foundry.charAt(j+n);
				if (howmany.containsKey(c)) {
					k = howmany.get(c);
				}
				k = k + 1;
				howmany.put(c, k);
				i = j + window.length();
			}
		}
		

		Character master = getMaster(howmany);
		howmany.remove(master);
		if (! howmany.isEmpty()) {
			if (Math.random()<.5) {
				master = getMaster(howmany);
			}
		}
		
		return master;
	}

	
	private Character getMaster (Map<Character,Integer> howmany) {
		Character m = ' ';
		int nMax = 0;
		for(Character c : howmany.keySet()) {
			int n = howmany.get(c);
			if (n > nMax) {
				nMax = n;
				m = c;
			}
		}
		return m;
	}
	
}
