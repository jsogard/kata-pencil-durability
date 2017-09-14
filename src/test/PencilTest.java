package test;

import main.*;
import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class PencilTest {

	int initialPointDurability;
	Pencil pencil;
	Paper paper;
	
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
		initialPointDurability = 200;
		
		pencil = new Pencil(initialPointDurability);
		Assert.assertEquals(initialPointDurability, pencil.getPointDurability());
	}
	
	/**
	 * Lowercase letters should degrade the pencil point by a value of one
	 */
	@Test
	public void lowerCasePointDegradation(){
		char[] lowerCaseLetters = "qwertyuiopasdfghjklzxcvbnm".toCharArray();
		for(char lowerCaseChar : lowerCaseLetters)
			checkPointDegradation(lowerCaseChar, 1);
	}
	
	/**
	 * Writing spaces and newlines expends no graphite; therefore these characters should not affect the pencil point
	 */
	@Test
	public void whiteSpacePointDegradation(){
		char[] whiteSpaceChars = " \n".toCharArray();
		for(char whiteSpaceChar : whiteSpaceChars)
			checkPointDegradation(whiteSpaceChar, 0);
	}
	
	/**
	 * capital letters should degrade the point by two
	 */
	@Test
	public void upperCasePointDegradation(){
		char[] upperCaseLetters = "QWERTYUIOPASDFGHJKLZXCVBNM".toCharArray();
		for(char upperCaseChar : upperCaseLetters)
			checkPointDegradation(upperCaseChar, 2);
	}
	
	
	private void checkPointDegradation(char writeCharacter, int characterCost){
		initialPointDurability();
		
		int expectedDurability = pencil.getPointDurability() - characterCost;
		pencil.write(paper, Character.toString(writeCharacter));
		
		Assert.assertEquals(expectedDurability, pencil.getPointDurability());
	}
	
	

}
