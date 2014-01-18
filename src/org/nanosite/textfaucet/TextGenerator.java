/**
 * (c) Copyright 2012 Klaus Birken - All rights reserved.
 */
package org.nanosite.textfaucet;

import java.util.List;

public class TextGenerator {

	private final int verbose;
	private final ITextbase textbase;
	
	public TextGenerator (int verbose, ITextbase textbase) {
		this.verbose = verbose;
		this.textbase = textbase;
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
		CharCounts counts = textbase.count(window);
		List<Character> sorted = counts.getSortedChars();
		if (sorted.isEmpty()) {
			// no results from analysis, try shorter window
			int n = window.length();
			if (n==0) {
				return ' ';
			} else {
				return getNextCharacter(window.substring(0, n-1), verbose);
			}
		} else {
			int idx = 0;
			if (sorted.size()>1 && Math.random()<.3) {
				idx = 1;
			}
			return sorted.get(idx);
		}
	}
	
}
