/**
 * (c) Copyright 2014 Klaus Birken - All rights reserved.
 */
package org.nanosite.textfaucet;

public class WebTextbase extends AbstractTextbase {

	public WebTextbase (int verbose) {
		super(verbose);
	}

	public boolean load (String url) {
		String data = WebtextReader.loadWebtext(url, verbose);
		text += data + "\n";
		return ! data.isEmpty();
	}
}
