/**
 * (c) Copyright 2012 Klaus Birken - All rights reserved.
 */
package org.nanosite.textfaucet;

public class TypeWriterPrinter implements IPrinter {

	private int delay;
	private int maxColumns;

	private int col;
	
	public TypeWriterPrinter (int delay, int maxColumns) {
		this.delay = delay;
		this.maxColumns = maxColumns;
		this.col = 0;
	}
	
	
	@Override
	public void printString (String text) {
		System.out.append(text);
		col += text.length();
		
	}

	@Override
	public void printCharacter(char c) {
		System.out.append(c);
		if (c == '\n') {
			col = 0;
		} else {
			col++;
			if (col>maxColumns && c==' ') {
				System.out.append('\n');
				col = 0;
			}
		}
		
		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
