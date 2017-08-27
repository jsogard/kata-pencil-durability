package pencil_durability;

public class Pencil {

	private int pointDurabilityReset = 100;
	private int pointDurability;
	
	private int eraserDurability = 100;
	
	
	
	public Pencil(int initialPointDurability, int initialEraserDurability){
		pointDurabilityReset = initialPointDurability;
		pointDurability = initialPointDurability;
		eraserDurability = initialEraserDurability;
	}
	
	public Pencil(int initialPointDurability){
		pointDurabilityReset = initialPointDurability;
		pointDurability = pointDurabilityReset;
	}
	
	public Pencil(){
		pointDurability = pointDurabilityReset;
	}

	public int getPointDurability() {
		return pointDurability;
	}

	public int getEraserDurability() {
		return eraserDurability;
	}
	
	public void erodePoint(){
		pointDurability--;
	}
	
	public void erodeEraser(){
		eraserDurability--;
	}
	
	public void sharpen(){
		pointDurability = pointDurabilityReset;
	}
	
}
