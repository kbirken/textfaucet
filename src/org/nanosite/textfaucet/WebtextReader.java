/**
 * (c) Copyright 2012 Klaus Birken - All rights reserved.
 */
package org.nanosite.textfaucet;

import java.net.*;
import java.io.*;

public class WebtextReader {
	
	public static String loadWebtext (String addr, int verbose) {
		if (verbose>0)
			System.out.println("loading " + addr + " ...");
		
		final URL url;
		try {
			url = new URL(addr);
		} catch (MalformedURLException e) {
			System.err.println("Error: MalformedURLException " + e.getMessage() + "!");
			e.printStackTrace();
			return "";
		}
		
		String charset = getCharset(url);
		if (charset.isEmpty())
			charset = "UTF-8";
		else {
			if (verbose>0)
				System.out.println("  charset is '" + charset + "'");
		}
		
		String all = "";
		try {
			BufferedReader in = new BufferedReader(
					new InputStreamReader(url.openStream(), charset));

			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				if (verbose>2)
					System.out.println(" >>> " + inputLine);
				
				final String without = removeHtmlNonsense(inputLine);
				if (! without.isEmpty()) {
					if (verbose>1)
						System.out.println(" " + without);
					
					all += without + "\n";
				}
			}
			in.close();
			
		} catch (IOException e) {
			System.err.println("Error: IOException " + e.getMessage() + "!");
			e.printStackTrace();
		}
		
		return all;
	}
	
	
	private static String removeHtmlNonsense (String line) {
		String without = "";
		int i = 0;
		while (i!=-1) {
			int j = line.indexOf('<', i);
			if (j==-1) {
				without += line.substring(i);
				i = j;
			} else {
				if (j>i)
					without += line.substring(i,j);
				int k = line.indexOf('>', j);
				if (k==-1) {
					i = -1;
				} else {
					i = k+1;
				}
			}
		}
		return without;
	}
	

	private static String getCharset (URL url) {
		try {
			BufferedReader in = new BufferedReader(
					new InputStreamReader(url.openStream()));

			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				//System.out.println("IN  " + inputLine);
				String charset = "charset=";
				int cs = inputLine.indexOf(charset);
				if (cs!=-1) {
					int ende = inputLine.indexOf('"', cs + charset.length());
					if (ende!=-1) {
						in.close();
						return inputLine.substring(cs + charset.length(), ende).toUpperCase();
					}
				}
			}
			in.close();
		} catch (IOException e) {
			System.err.println("Error: IOException " + e.getMessage() + "!");
			e.printStackTrace();
		}
		
		return "";
	}

}

