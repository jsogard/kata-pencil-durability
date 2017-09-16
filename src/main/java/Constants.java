package main.java;

public class Constants {

	public static final char COLLISION_CHARACTER = '@';
	
	public static final char[] UPPER_CASE_CHARACTERS = "QWERTYUIOPASDFGHJKLZXCVBNM".toCharArray();
	public static final char[] LOWER_CASE_CHARACTERS = "qwertyuiopasdfghjklzxcvbnm".toCharArray();
	public static final char[] WHITE_SPACE_CHARACTERS = " \n".toCharArray();
	
	public static final int UPPER_CASE_WRITE_COST 	= 2;
	public static final int LOWER_CASE_WRITE_COST 	= 1;
	public static final int WHITE_SPACE_WRITE_COST 	= 0;
	public static final int MISC_WRITE_COST 		= 1;
	
	public static final int UPPER_CASE_ERASE_COST 	= 1;
	public static final int LOWER_CASE_ERASE_COST 	= 1;
	public static final int WHITE_SPACE_ERASE_COST 	= 0;
	public static final int MISC_ERASE_COST			= 1;
	
	public static int getCharacterWriteCost(char character){
		
		if(Character.isUpperCase(character)) return Constants.UPPER_CASE_WRITE_COST;
		if(Character.isLowerCase(character)) return Constants.LOWER_CASE_WRITE_COST;
		if(Character.isWhitespace(character)) return Constants.WHITE_SPACE_WRITE_COST;
		
		return Constants.MISC_WRITE_COST;
	}
	
	public static int getCharacterEraseCost(char character){
		
		if(Character.isUpperCase(character)) return Constants.UPPER_CASE_ERASE_COST;
		if(Character.isLowerCase(character)) return Constants.LOWER_CASE_ERASE_COST;
		if(Character.isWhitespace(character)) return Constants.WHITE_SPACE_ERASE_COST;
		
		return Constants.MISC_ERASE_COST;
	}
}
