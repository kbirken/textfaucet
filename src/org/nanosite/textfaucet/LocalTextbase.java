/**
 * (c) Copyright 2014 Klaus Birken - All rights reserved.
 */
package org.nanosite.textfaucet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LocalTextbase extends AbstractTextbase {

	public LocalTextbase (int verbose) {
		super(verbose);
	}

	public boolean load (String filename) {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filename));
			String line = br.readLine();
			while (line != null) {
				sb.append(line);
				sb.append("\n");
				line = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (br!=null)
				try {
					br.close();
				} catch (IOException e) {
					System.err.println("Couldn't close BufferedReader!");
					e.printStackTrace();
				}
		}

		sb.append("\n");
		text += sb.toString();
		return true;
	}
	
}
