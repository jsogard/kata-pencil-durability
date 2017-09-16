package main.java;

public class Pencil {
	
	private int initialPointDurability;
	private int pointDurability;
	
	private int length;
	
	private int eraserDurability;

	public Pencil(int initialPointDurability, int initialLength, int initialEraserDurability) {
		this.initialPointDurability 	= initialPointDurability;
		this.pointDurability 			= initialPointDurability;
		
		this.length 					= initialLength;
		
		this.eraserDurability 			= initialEraserDurability;
	}

	public int getPointDurability() {
		return pointDurability;
	}

	public void write(Paper paper, String writeText) {
		for(char writeChar : writeText.toCharArray()){
			
			if(pointDurability < Constants.getCharacterWriteCost(writeChar))
				writeChar = ' ';
			paper.appendCharacter(writeChar);
			pointDurability -= Constants.getCharacterWriteCost(writeChar);
		}
	}

	public void sharpen() {
		if(length > 0){
			pointDurability = initialPointDurability;
			length--;
		}
	}

	public int getLength() {
		return length;
	}

	public int getEraserDurability() {
		return eraserDurability;
	}

	public void erase(Paper paper, String eraseText) {
		
		int eraseStart = paper.getText().lastIndexOf(eraseText);
		if(eraseStart == -1) return;
		int eraseIndex = eraseStart + eraseText.length() - 1;
		char eraseChar;
		
		for(; eraseIndex >= eraseStart; eraseIndex--){
			eraseChar = paper.getText().charAt(eraseIndex);
			if(eraserDurability >= Constants.getCharacterEraseCost(eraseChar)){
				paper.eraseChar(eraseIndex);
				eraserDurability -= Constants.getCharacterEraseCost(eraseChar);
			}
			else return;
		}
		
	}

	public void edit(Paper paper, String editText, int index) {
		if(index < 0 || index >= paper.getText().length()) return;
		
		char editChar;
		for(int editIndex = 0; editIndex < editText.length(); editIndex++){
			
			editChar = editText.charAt(editIndex);
			if(pointDurability < Constants.getCharacterWriteCost(editChar))
				editChar = ' ';
			paper.editChar(index + editIndex, editChar);
		}
	}
	
	

}
