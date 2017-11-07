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
		textArray[index] = checkCollision(textArray[index], replaceChar);
		text = new String(textArray);
	}
	
	private char checkCollision(char charPresent, char charWrite){
		if(charPresent == charWrite) return charPresent;
		if(charPresent == ' ') return charWrite;
		if(charWrite == ' ') return charPresent;
		return Constants.COLLISION_CHARACTER;
	}
	
}
