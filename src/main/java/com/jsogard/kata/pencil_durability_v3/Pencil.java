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

	public void sharpen() {
		pointDurability = initialPointDurability;
	}

	

}
