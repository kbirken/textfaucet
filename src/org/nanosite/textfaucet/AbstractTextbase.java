/**
 * (c) Copyright 2014 Klaus Birken - All rights reserved.
 */
package org.nanosite.textfaucet;

/**
 * This abstract class assumes that the whole text database can be stored
 * in a Javs string in memory.
 * 
 * @author Klaus Birken
 */
public abstract class AbstractTextbase implements ITextbase {

	protected int verbose = 0;

	// derived class should provide the text
	protected String text = "";

	public AbstractTextbase (int verbose) {
		this.verbose = verbose;
	}

	@Override
	public CharCounts count (String prefix) {
		CharCounts result = new CharCounts();

		int i = 0;
		int n = prefix.length();
		while (i != -1) {
			int j = text.indexOf(prefix, i);
			if (j==-1) {
				i = j;
			} else {
				if (verbose>0)
					System.out.println("found! j=" + j + " : " + text.substring(j, j+20));

				Character c = text.charAt(j+n);
				result.count(c);
				i = j + n;
			}
		}
		return result;
	}

	public void dump() {
		System.out.println("\n");
		System.out.println(text);
		System.out.println("\n");
	}
}
