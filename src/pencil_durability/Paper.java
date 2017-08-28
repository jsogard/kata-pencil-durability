package pencil_durability;

public class Paper {
	
	private String text;
	
	public Paper(){
		text = "";
	}
	
	public String getText(){
		return text;
	}
	
	public boolean write(String text, Pencil pencil){
		int i = 0;
		char c;
		while(pencil.getPointDurability() > 0 && i < text.length()){
			c = text.charAt(i++);
			if(pencil.erodePoint( CharacterCost.getWriteCost(c) ))
				this.text += c;
			else
				return false;
		}
		return true;
	}
	
	public boolean erase(String text, Pencil pencil){
		int lastIndex = this.text.lastIndexOf(text);
		if(lastIndex == -1) return false;
		
		char[] cstring = this.text.toCharArray();
		for(int i = lastIndex + text.length()-1; i >= lastIndex; i--){
			
			if(pencil.erodeEraser( CharacterCost.getEraseCost(cstring[i]) ))
				cstring[i] = ' ';
			else {
				this.text = new String(cstring);
				return false;
			}
		}
		
		this.text = new String(cstring);
		return true;
	}
	
	public boolean edit(String text, int index, Pencil pencil){
		char[] cstring = this.text.toCharArray();
		String overflow = null;
		int i = 0;
		char write_c;
		
		if(index + text.length() > this.text.length())
			overflow = text.substring(this.text.length() - index);
		
		while(pencil.getPointDurability() > 0 && index < this.text.length() && i < text.length()){
			
			write_c = text.charAt(i);
			if(pencil.erodePoint( CharacterCost.getWriteCost(write_c) )){
				cstring[index] = getOverwriteChar(write_c, cstring[index]);
			}
			else
				return false;
			i++;
			index++;
		}
		this.text = new String(cstring);
		
		if(overflow != null) return write(overflow, pencil);
		return true;
	}
	
	private char getOverwriteChar(char newChar, char oldChar){
		if(Character.isWhitespace(newChar)) return oldChar;
		if(Character.isWhitespace(oldChar)) return newChar;
		if(newChar == oldChar) return oldChar;
		return '@';
	}
	
}