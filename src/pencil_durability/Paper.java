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
			c = text.charAt(i);
			if(pencil.erodePoint( CharacterCost.getWriteCost(c) ))
				this.text += c;
			else
				return false;
		}
		return true;
	}
	
	public boolean erase()
	
}