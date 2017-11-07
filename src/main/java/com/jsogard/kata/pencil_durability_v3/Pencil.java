package com.jsogard.kata.pencil_durability_v3;

public class Pencil {
	
	private int initialPointDurability;
	private int pointDurability;
	private int length;
	private int eraserDurability;

	public Pencil(int initialPointDurability, int initialLength, int initialEraserDurability) {
		this.initialPointDurability = initialPointDurability;
		pointDurability = initialPointDurability;
		length = initialLength;
		eraserDurability = initialEraserDurability;
	}

	public int getPointDurability() {
		return pointDurability;
	}

	public int getLength() {
		return length;
	}

	public int getEraserDurability() {
		return eraserDurability;
	}

	public void write(Paper paper, String string) {
		for(char character : string.toCharArray()){
			write(paper, character);
		}
	}
	
	public void write(Paper paper, char character) {
		int writeCost = getWriteCost(character);
		if(pointDurability < writeCost)
			character = ' ';
		else
			pointDurability -= writeCost;
		paper.appendCharacter(character);
	}
	
	private int getWriteCost(char ch){
		if(Character.isUpperCase(ch)) return Constants.UPPER_CASE_WRITE_COST;
		if(Character.isLowerCase(ch)) return Constants.LOWER_CASE_WRITE_COST;
		return Constants.WHITESPACE_WRITE_COST;
	}
	
	private int getEraseCost(char ch){
		if(Character.isWhitespace(ch)) return Constants.WHITESPACE_ERASE_COST;
		return Constants.CHARACTER_ERASE_COST;
	}

	public void sharpen() {
		if(length == 0) return;
		length--;
		pointDurability = initialPointDurability;
	}

	public void erase(Paper paper, String eraseString) {
		int end = paper.getText().lastIndexOf(eraseString),
				eraseCost;
		if(end == -1) return;
		int index = end + eraseString.length() - 1;
		for(; index >= end; index--){
			eraseCost = getEraseCost(paper.getText().charAt(index));
			if(eraserDurability < eraseCost) return;
			eraserDurability -= eraseCost;
			paper.eraseCharacter(index);
		}
	}

	public void erase(Paper paper, char c) {
		erase(paper, Character.toString(c));
	}

	public void edit(Paper paper, String writeString, int index) {
		// i feel weird about it, but not error checking and not adding write cost bc user stories didnt say to...
		int writeIndex = 0;
		while(writeIndex < writeString.length()){
			paper.edit(index, writeString.charAt(writeIndex));
			writeIndex++;
			index++;
		}
	}

	

}
