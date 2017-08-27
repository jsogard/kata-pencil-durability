package pencil_durability;

public class Paper {
	
	private String text;
	
	public Paper(){
		text = "";
	}
	
	public boolean write(String text, Pencil pencil){
		int i = 0, cost;
		char c;
		while(pencil.getPointDurability() > 0 && i < text.length()){
			c = text.charAt(i);
			
			
			this.text += text.charAt(i);
			
		}
		// TODO say something if tip breaks
	}
}