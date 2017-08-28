package pencil_durability;

public class CharacterCost {
	
	public static final int UppercaseWriteCost = 2;
	public static final int LowercaseWriteCost = 1;
	public static final int WhitespaceWriteCost = 0;
	public static final int DigitWriteCost = 1;
	public static final int SymbolWriteCost = 1;
	
	public static int getWriteCost(char c){
		if(Character.isDigit(c)) 		return DigitWriteCost;
		if(Character.isLowerCase(c)) 	return LowercaseWriteCost;
		if(Character.isUpperCase(c)) 	return UppercaseWriteCost;
		if(Character.isWhitespace(c))	return WhitespaceWriteCost;
		return SymbolWriteCost;
	}
	
	public static int getWriteCost(String text){
		int cost = 0;
		for(int i = 0; i < text.length(); i++)
			cost += CharacterCost.getWriteCost(text.charAt(i));
		return cost;
	}
	
	public static final int UppercaseEraseCost = 1;
	public static final int LowercaseEraseCost = 1;
	public static final int WhitespaceEraseCost = 0;
	public static final int DigitEraseCost = 1;
	public static final int SymbolEraseCost = 1;
	
	public static int getEraseCost(char c){
		if(Character.isDigit(c)) 		return DigitEraseCost;
		if(Character.isLowerCase(c)) 	return LowercaseEraseCost;
		if(Character.isUpperCase(c)) 	return UppercaseEraseCost;
		if(Character.isWhitespace(c))	return WhitespaceEraseCost;
		return SymbolEraseCost;
	}
	
	public static int getEraseCost(String text){
		int cost = 0;
		for(int i = 0; i < text.length(); i++)
			cost += CharacterCost.getEraseCost(text.charAt(i));
		return cost;
	}
}
