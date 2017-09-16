package main.java;

public class Paper {
	
	private String text;
	
	public Paper(){ text = "";}

	public String getText() {
		return text;
	}

	public void appendCharacter(char writeChar) {
		text += writeChar;
	}

	public void editChar(int editIndex, char editChar) {
		if(editIndex >= text.length() || editIndex < 0) return;
		// out of bounds
		
		char existingChar = text.charAt(editIndex);
		if(existingChar == editChar || Character.isWhitespace(editChar)) return;
		// no change
		
		char replacementChar = (Character.isWhitespace(existingChar) ? editChar : Constants.COLLISION_CHARACTER);
		// yes change
		
		replaceCharacter(editIndex, replacementChar);
	}

	public void eraseChar(int eraseIndex) {
		if(eraseIndex >= text.length() || eraseIndex < 0) return;
		replaceCharacter(eraseIndex, ' ');
	}
	
	private void replaceCharacter(int index, char replaceChar){
		char[] paperText = text.toCharArray();
		paperText[index] = replaceChar;
		text = new String(paperText);
	}

}
