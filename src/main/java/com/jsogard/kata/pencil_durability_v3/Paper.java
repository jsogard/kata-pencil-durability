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
	
	
}
