/**
 * (c) Copyright 2014 Klaus Birken - All rights reserved.
 */
package org.nanosite.textfaucet;

public interface ITextbase {
	// search the whole textbase for prefix and count its successor characters
	CharCounts count (String prefix);
	
	// debugging
	void dump();
}
