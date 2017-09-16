package test.java;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.java.*;

public class PencilTest {

	private Pencil pencil;
	private int initialPointDurability 	= 200;
	private int initialLength 			= 10;
	private int initialEraserDurability = 100;
	
	private Paper paper;
	
	/**
	 * When a pencil is created, it can be provided with a value for point durability
	 * A pencil should also be created with an initial length value
	 * When a pencil is created, it can be provided with a value for eraser durability
	 */
	@Before
	public void initializePencil(){
		pencil = new Pencil(initialPointDurability, initialLength, initialEraserDurability);
		paper = new Paper();
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
		assertEquals(initialPointDurability, pencil.getPointDurability());
	}
	
	/**
	 * Lowercase letters should degrade the pencil point by a value of one
	 */
	@Test
	public void lowerCasePointDegradation(){
		
		for(char lowerCaseChar : Constants.LOWER_CASE_CHARACTERS)
			checkPointDegradation(lowerCaseChar, Constants.LOWER_CASE_WRITE_COST);
	}
	
	/**
	 * Writing spaces and newlines expends no graphite; therefore these characters should not affect the pencil point
	 */
	@Test
	public void whiteSpacePointDegradation(){
		
		for(char whiteSpaceChar : Constants.WHITE_SPACE_CHARACTERS)
			checkPointDegradation(whiteSpaceChar, Constants.WHITE_SPACE_WRITE_COST);
	}
	
	/**
	 * capital letters should degrade the point by two
	 */
	@Test
	public void upperCasePointDegradation(){
		
		for(char upperCaseChar : Constants.UPPER_CASE_CHARACTERS)
			checkPointDegradation(upperCaseChar, Constants.UPPER_CASE_WRITE_COST);
	}
	
	
	private void checkPointDegradation(char writeCharacter, int characterCost){
		initializePencil();
		
		int expectedDurability = initialPointDurability - characterCost;
		pencil.write(paper, Character.toString(writeCharacter));
		
		assertEquals(expectedDurability, pencil.getPointDurability());
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
		assertEquals(initialLength, pencil.getLength());
	}
	
	
	/**
	 * When a pencil is sharpened, it regains its initial point durability
	 */
	@Test
	public void sharpenBackToInitialDurability(){
		
		pencil.write(paper, "sharpenBackToInitialDurability");
		pencil.sharpen();
		
		assertEquals(initialPointDurability, pencil.getPointDurability());
	}
	
	/**
	 * The pencil's length is reduced by one each time it is sharpened
	 */
	@Test
	public void sharpenReducesLength(){
		
		pencil.sharpen();
		assertEquals(initialLength - 1, pencil.getLength());
	}
	
	/**
	 * When a pencil's length is zero, then sharpening it no longer restores its point durabliity
	 */
	@Test
	public void zeroLengthCanNotSharpen(){
		
		while(pencil.getLength() > 0) pencil.sharpen();
		pencil.write(paper,  "zeroLengthCanNotSharpen");
		pencil.sharpen();
		
		assertNotEquals(initialPointDurability, pencil.getPointDurability());
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
		
		assertEquals(initialEraserDurability, pencil.getEraserDurability());
	}
	
	/**
	 * all characters except for white space should degrade the eraser by a value of one
	 */
	@Test
	public void eraseWhiteSpaceNoDegradation(){
		
		for(char whiteSpaceChar : Constants.WHITE_SPACE_CHARACTERS)
			checkEraserDegradation(whiteSpaceChar, Constants.WHITE_SPACE_ERASE_COST);
	}
	
	@Test
	public void eraseCharacterDegradation(){
		
		for(char upperCaseChar : Constants.UPPER_CASE_CHARACTERS)
			checkEraserDegradation(upperCaseChar, Constants.UPPER_CASE_ERASE_COST);
		for(char lowerCaseChar : Constants.LOWER_CASE_CHARACTERS)
			checkEraserDegradation(lowerCaseChar, Constants.LOWER_CASE_ERASE_COST);
	}
	
	private void checkEraserDegradation(char eraseCharacter, int characterCost){
		initializePencil();
		
		int expectedEraserDurability = initialEraserDurability - characterCost;
		pencil.write(paper, Character.toString(eraseCharacter));
		pencil.erase(paper, Character.toString(eraseCharacter));
		
		assertEquals(expectedEraserDurability, pencil.getEraserDurability());
	}

	

}