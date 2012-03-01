/**
 * (c) Copyright 2012 Klaus Birken - All rights reserved.
 */
package org.nanosite.textfaucet;


public class TextFaucet {

	private static TextFaucet myself = null;
	
	private final int nGenerated = 800;
	private final int nMaxColumns = 60;
	private final int delayPerChar = 50;  // milliseconds

	private final String startWord = "Those";
	private final String baseURL = "http://en.wikipedia.org/wiki/"; 

	private final String[] keywords = {
			"Einstein", "Beethoven", "Mozart", "Schiller",
			"Banana", "Apple", "Music", "Tree"
		};

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("TextFaucet starting.");
		myself = new TextFaucet();
		myself.run(0);
		System.out.println("\nTextFaucet done.");
	}

	
	public void run (int verbose) {
		String all = "";
		for(String word : keywords) {
			all += WebtextReader.loadWebtext(baseURL + word, verbose);
			System.out.append(word + " ");
		}
		System.out.println("----");
		
		IPrinter printer = new TypeWriterPrinter(delayPerChar, nMaxColumns);
		TextGenerator generator = new TextGenerator(verbose, all);
		generator.generate(nGenerated, startWord, printer);
	}
	

}
