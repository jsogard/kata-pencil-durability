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
			paper.appendCharacter(character);
		}
	}
	
	

}
