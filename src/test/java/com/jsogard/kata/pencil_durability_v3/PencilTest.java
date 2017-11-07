package com.jsogard.kata.pencil_durability_v3;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

public class PencilTest {
	
	private Pencil pencil;
	private Paper paper;
	
	private int initialPointDurability = 200;
	private int initialLength = 10;
	private int initialEraserDurability = 10;
	
	/* 
	 * TC01-PENCIL_INIT
	 * When a pencil is created, it can be provided with a value for point durability
	 * A pencil should also be created with an initial length value.
	 * When a pencil is created, it can be provided with a value for eraser durability
	 */
	@Before
	public void pencilInit(){
		pencil = new Pencil(initialPointDurability, initialLength, initialEraserDurability);
		paper = new Paper();
	}
	
	/*
	 * TC01.1-PENCIL_INIT_POINTDUR
	 * When a pencil is created, it can be provided with a value for point durability
	 */
	@Test
	public void initialPointDurability(){
		Assert.assertEquals(initialPointDurability, pencil.getPointDurability());
	}
	
	/*
	 * TC01.2-PENCIL_INIT_LENGTH
	 * A pencil should also be created with an initial length value.
	 */
	@Test
	public void initialLength(){
		Assert.assertEquals(initialLength, pencil.getLength());
	}
	
	/*
	 * TC01.3-PENCIL_INIT_ERASERDUR
	 * When a pencil is created, it can be provided with a value for eraser durability
	 */
	@Test
	public void initialEraserDurability(){
		Assert.assertEquals(initialEraserDurability, pencil.getEraserDurability());
	}
	
	/*
	 * TC03-POINTDEG_WHITESPACE_WRITE
	 * Writing spaces and newlines expends no graphite; therefore these characters should not affect the pencil point
	 */
	@Test
	public void pointdegWhitespaceWrite(){
		int pointDurabilityBefore,
			expectedCost = 0;
		for(char character : Constants.WHITESPACE_CHARACTERS){
			pointDurabilityBefore = pencil.getPointDurability();
			pencil.write(paper, character);
			Assert.assertEquals(pointDurabilityBefore - expectedCost, pencil.getPointDurability());
		}
	}
	
	/*
	 * TC04-POINTDEG_LOWERCASE_WRITE
	 * Lowercase letters should degrade the pencil point by a value of one
	 */
	@Test
	public void pointdegLowercaseWrite(){
		int pointDurabilityBefore,
			expectedCost = 1;
		for(char character : Constants.LOWERCASE_CHARACTERS){
			pointDurabilityBefore = pencil.getPointDurability();
			pencil.write(paper, character);
			Assert.assertEquals(pointDurabilityBefore - expectedCost, pencil.getPointDurability());
		}
	}
	
	/*
	 * TC05-POINTDEG_UPPERCASE_WRITE
	 * capital letters should degrade the point by two
	 */
	@Test
	public void pointdegUppercaseWrite(){
		int pointDurabilityBefore,
			expectedCost = 2;
		for(char character : Constants.UPPERCASE_CHARACTERS){
			pointDurabilityBefore = pencil.getPointDurability();
			pencil.write(paper, character);
			Assert.assertEquals(pointDurabilityBefore - expectedCost, pencil.getPointDurability());
		}
	}
	
	/*
	 * TC06-SHARPEN_POINT_DUR
	 * When a pencil is sharpened, it regains its initial point durability
	 */
	@Test
	public void sharpenPointDur(){
		pencil.write(paper, "SHARPEN POINT DUR");
		pencil.sharpen();
		
		Assert.assertEquals(initialPointDurability, pencil.getPointDurability());
	}
	
	/*
	 * TC07-SHARPEN_REDUCE_LENGTH
	 * The pencil's length is reduced by one each time it is sharpened
	 */
	@Test
	public void sharpenReduceLength(){
		pencil.sharpen();
		
		Assert.assertEquals(initialLength - 1, pencil.getLength());
	}
	
	/*
	 * TC08-SHARPEN_NO_LENGTH
	 * When a pencil's length is zero, then sharpening it no longer restores its point durabliity
	 */
	@Test
	public void sharpenNoLength(){
		int initialLength = 0;
		pencil = new Pencil(initialPointDurability, initialLength, initialEraserDurability);
		pencil.write(paper, "SHARPEN NO LENGTH");
		pencil.sharpen();
		
		Assert.assertFalse(initialPointDurability == pencil.getPointDurability());
	}
	
	/*
	 * TC10-ERASERDEG_WHITESPACE
	 * all characters except for white space should degrade the eraser by a value of one
	 */
	
	/*
	 * TC11-ERASERDEG_CHARACTER
	 * all characters except for white space should degrade the eraser by a value of one
	 */
	
	

}
