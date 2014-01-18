/**
 * (c) Copyright 2012 Klaus Birken - All rights reserved.
 */
package org.nanosite.textfaucet;


public class TextFaucet {

	private static TextFaucet myself = null;
	
	private final int nGenerated = 800;
	private final int nMaxColumns = 60;
	private final int delayPerChar = 50;  // milliseconds

	private final String startWord = "This ";
	private final String baseURL = "http://en.wikipedia.org/wiki/"; 

	private final String[] keywords = {
			"Einstein", "Beethoven", "Mozart", "Schiller",
			"Banana", "Apple", "Music", "Tree"
		};

	private final String[] localFiles = {
			"deutsch/32385.txt",
			"deutsch/17362.txt",
			"deutsch/34811.txt"
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
		ITextbase textbase = loadFromWeb(keywords, verbose);
//		ITextbase textbase = loadLocal("../textbase", localFiles, verbose);
		System.out.println("----");
//		textbase.dump();
//		System.out.println("----");

//		CharCounts result = textbase.count(startWord);
//		System.out.println("Prefix '" + startWord + "'. Occurrences: " + result.getNAll());
//		for(Character c : result.getSortedChars()) {
//			System.out.println("  '" + c + "' : " + result.getNChar(c) +
//					"\t(" + 100.0*result.getNCharRel(c) + "%)");
//		}

		IPrinter printer = new TypeWriterPrinter(delayPerChar, nMaxColumns);
		TextGenerator generator = new TextGenerator(verbose, textbase);
		generator.generate(nGenerated, startWord, printer);
	}

	
	private ITextbase loadLocal (String path, String[] files, int verbose) {
		LocalTextbase textbase = new LocalTextbase(verbose);
		for(String file : files) {
			if (textbase.load(path + "/" + file)) {
				System.out.println("Local file '" + file + "' loaded.");
			} else {
				System.out.println("Couldn't load local file '" + file + "'!");
			}
		}
		return textbase;
	}

	
	private ITextbase loadFromWeb (String[] keywords, int verbose) {
		WebTextbase textbase = new WebTextbase(verbose);
		for(String word : keywords) {
			if (textbase.load(baseURL + word)) {
				System.out.println("text for '" + word + "' loaded.");
			} else {
				System.out.println("text for '" + word + "' couldn't be loaded.");
			}
		}
		return textbase;
	}
}
