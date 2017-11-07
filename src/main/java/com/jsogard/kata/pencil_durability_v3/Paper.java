package com.jsogard.kata.pencil_durability_v3;

public class Paper {

	private String text;
	
	public Paper(){
		text = "";
	}

	public String getText() {
		return text;
	}

	public void appendCharacter(char character) {
		text += character;		
	}

	public void eraseCharacter(int index) {
		char[] textArray = text.toCharArray();
		textArray[index] = ' ';
		text = new String(textArray);
	}

	public void edit(int index, char replaceChar) {
		char[] textArray = text.toCharArray();
		textArray[index] = replaceChar;
		text = new String(textArray);
	}
	
	
}
