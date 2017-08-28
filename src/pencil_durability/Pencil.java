package pencil_durability;

public class Pencil {

	private int pointDurabilityReset = 100;
	private int pointDurability;
	
	private int eraserDurability = 100;
	private int length = 10;
	
	
	public Pencil(int initialPointDurability, int initialEraserDurability, int length){
		pointDurabilityReset = initialPointDurability;
		pointDurability = initialPointDurability;
		eraserDurability = initialEraserDurability;
		this.length = length;
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
	
	public int getLength() {
		return length;
	}
	
	public boolean erodePoint(int cost){
		pointDurability -= cost;
		if(pointDurability < 0){
			pointDurability = 0;
			return false;
		}
		return true;
	}
	
	public boolean erodeEraser(int cost){
		eraserDurability -= cost;
		if(eraserDurability < 0){
			eraserDurability = 0;
			return false;
		}
		return true;
	}
	
	public boolean sharpen(){
		if(length == 0) return false;
		pointDurability = pointDurabilityReset;
		length--;
		return true;
	}
	
}
