package test;

import main.*;
import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PencilTest {
	
	private final char[] upperCaseCharacters = "QWERTYUIOPASDFGHJKLZXCVBNM".toCharArray();
	private final char[] lowerCaseCharacters = "qwertyuiopasdfghjklzxcvbnm".toCharArray();
	private final char[] whiteSpaceCharacters = " \n".toCharArray();

	Pencil pencil;
	int initialPointDurability 	= 200;
	int initialLength 			= 10;
	int initialEraserDurability = 100;
	
	Paper paper;
	
	/**
	 * When a pencil is created, it can be provided with a value for point durability
	 * A pencil should also be created with an initial length value
	 * When a pencil is created, it can be provided with a value for eraser durability
	 */
	@Before
	public void initializePencil(){
		pencil = new Pencil(initialPointDurability, initialLength, initialEraserDurability);
	}
	
	
	/** POINT DEGRADATION USER STORY
	 * 
	 * As a pencil manufacturer
	 * I want writing to cause a pencil point to go dull
	 * so that I can sell more pencils
	 */
	
	
	/**
	 * When a pencil is created, it can be provided with a value for point durability 
	 */
	@Test
	public void initialPointDurability(){
		initializePencil();
		Assert.assertEquals(initialPointDurability, pencil.getPointDurability());
	}
	
	/**
	 * Lowercase letters should degrade the pencil point by a value of one
	 */
	@Test
	public void lowerCasePointDegradation(){
		
		for(char lowerCaseChar : lowerCaseCharacters)
			checkPointDegradation(lowerCaseChar, 1);
	}
	
	/**
	 * Writing spaces and newlines expends no graphite; therefore these characters should not affect the pencil point
	 */
	@Test
	public void whiteSpacePointDegradation(){
		
		for(char whiteSpaceChar : whiteSpaceCharacters)
			checkPointDegradation(whiteSpaceChar, 0);
	}
	
	/**
	 * capital letters should degrade the point by two
	 */
	@Test
	public void upperCasePointDegradation(){
		
		for(char upperCaseChar : upperCaseCharacters)
			checkPointDegradation(upperCaseChar, 2);
	}
	
	
	private void checkPointDegradation(char writeCharacter, int characterCost){
		
		int expectedDurability = initialPointDurability - characterCost;
		pencil.write(paper, Character.toString(writeCharacter));
		
		Assert.assertEquals(expectedDurability, pencil.getPointDurability());
	}
	
	
	/** SHARPEN USER STORIES
	 * 
	 * As a writer
	 * I want to be able to sharpen my pencil
	 * so that I can continue to write with it after it goes dull
	 */
	
	@Test
	public void initialLength(){
		initializePencil();
		Assert.assertEquals(initialLength, pencil.getLength());
	}
	
	
	/**
	 * When a pencil is sharpened, it regains its initial point durability
	 */
	@Test
	public void sharpenBackToInitialDurability(){
		
		pencil.write(paper, "sharpenBackToInitialDurability");
		pencil.sharpen();
		
		Assert.assertEquals(initialPointDurability, pencil.getPointDurability());
	}
	
	/**
	 * The pencil's length is reduced by one each time it is sharpened
	 */
	@Test
	public void sharpenReducesLength(){
		
		pencil.sharpen();
		Assert.assertEquals(initialLength - 1, pencil.getLength());
	}
	
	/**
	 * When a pencil's length is zero, then sharpening it no longer restores its point durabliity
	 */
	@Test
	public void zeroLengthCanNotSharpen(){
		
		while(pencil.getLength() > 0) pencil.sharpen();
		pencil.write(paper,  "zeroLengthCanNotSharpen");
		pencil.sharpen();
		
		Assert.assertNotEquals(initialLength, pencil.getLength());
	}
	
	/** ERASER DEGRADATION USER STORY
	 * 
	 * As a pencil manufacturer
	 * I want a pencil eraser to eventually wear out
	 * so that I can sell more pencils
	 */
	
	/**
	 * When a pencil is created, it can be provided with a value for eraser durability
	 */
	@Test
	public void initialEraserDurability(){
		initializePencil();
		Assert.assertEquals(initialEraserDurability, pencil.getEraserDurability());
	}
	
	/**
	 * all characters except for white space should degrade the eraser by a value of one
	 */
	@Test
	public void eraseWhiteSpaceNoDegradation(){
		
		for(char whiteSpaceChar : whiteSpaceCharacters)
			checkEraserDegradation(whiteSpaceChar, 0);
	}
	
	@Test
	public void eraseCharacterDegradation(){
		
		for(char upperCaseChar : upperCaseCharacters)
			checkEraserDegradation(upperCaseChar, 1);
		for(char lowerCaseChar : upperCaseCharacters)
			checkEraserDegradation(lowerCaseChar, 1);
	}
	
	private void checkEraserDegradation(char eraseCharacter, int characterCost){
		
		int expectedEraserDurability = initialEraserDurability - characterCost;
		pencil.erase(paper, Character.toString(eraseCharacter));
		
		Assert.assertEquals(expectedEraserDurability, pencil.getEraserDurability());
	}

	

}
