package main;

public class Pencil {
	
	public static final char[] UPPER_CASE_CHARACTERS = "QWERTYUIOPASDFGHJKLZXCVBNM".toCharArray();
	public static final char[] LOWER_CASE_CHARACTERS = "qwertyuiopasdfghjklzxcvbnm".toCharArray();
	public static final char[] WHITE_SPACE_CHARACTERS = " \n".toCharArray();
	
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
			
			if(pointDurability < getCharacterWriteCost(writeChar))
				writeChar = ' ';
			paper.appendCharacter(writeChar);
			pointDurability -= getCharacterWriteCost(writeChar);
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
			if(eraserDurability >= getCharacterEraseCost(eraseChar)){
				paper.eraseChar(eraseIndex);
				eraserDurability -= getCharacterEraseCost(eraseChar);
			}
			else return;
		}
		
	}

	public void edit(Paper paper, String editText, int index) {
		
		char editChar;
		for(int editIndex = 0; editIndex < editText.length(); editIndex++){
			
			editChar = editText.charAt(editIndex);
			if(pointDurability < getCharacterWriteCost(editChar))
				editChar = ' ';
			paper.editChar(index + editIndex, editChar);
		}
	}
	
	private static int getCharacterWriteCost(char character){
		for(char upperCase : UPPER_CASE_CHARACTERS)
			if(character == upperCase) return 2;
		for(char lowerCase : LOWER_CASE_CHARACTERS)
			if(character == lowerCase) return 1;
		for(char whiteSpace : WHITE_SPACE_CHARACTERS)
			if(character == whiteSpace) return 0;
		
		return 1;
	}
	
	private static int getCharacterEraseCost(char character){
		for(char upperCase : UPPER_CASE_CHARACTERS)
			if(character == upperCase) return 1;
		for(char lowerCase : LOWER_CASE_CHARACTERS)
			if(character == lowerCase) return 1;
		for(char whiteSpace : WHITE_SPACE_CHARACTERS)
			if(character == whiteSpace) return 0;
		
		return 1;
	}

}
