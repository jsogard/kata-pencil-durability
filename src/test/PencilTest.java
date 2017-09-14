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
	public void lowercasePointDegradation(){
		initialPointDurability();
		
		int pointDurabilityBefore = pencil.getPointDurability();
		pencil.write(paper, "a");
		
		Assert.assertEquals(pointDurabilityBefore - 1, pencil.getPointDurability());
	}
	
	/**
	 * Writing spaces and newlines expends no graphite; therefore these characters should not affect the pencil point
	 */
	@Test
	public void whitespacePointDegradation(){
		initialPointDurability();
		
		int pointDurabilityBefore = pencil.getPointDurability();
		pencil.write(paper, " ");
		
		Assert.assertEquals(pointDurabilityBefore, pencil.getPointDurability());
	}
	
	/**
	 * capital letters should degrade the point by two
	 */
	@Test
	public void uppercasePointDegradation(){
		initialPointDurability();
		
		int pointDurabilityBefore = pencil.getPointDurability();
		pencil.write(paper, "A");
		
		Assert.assertEquals(pointDurabilityBefore - 2, pencil.getPointDurability());
	}
	

}
