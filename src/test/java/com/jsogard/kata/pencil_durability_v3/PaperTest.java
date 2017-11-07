package com.jsogard.kata.pencil_durability_v3;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

public class PaperTest {
	
	private Pencil pencil;
	private Paper paper;
	
	private int initialPointDurability = 200;
	private int initialLength = 10;
	private int initialEraserDurability = 10;
	
	
	@Before
	public void paperInit(){
		pencil = new Pencil(initialPointDurability, initialLength, initialEraserDurability);
		paper = new Paper();
	}
	
	/* 
	 * TC00-WRITE_APPEND
	 * Text written by the pencil should always be appended to existing text on the paper:
	 * SCENARIO: 
	 * 	given a piece of paper with the text "She sells sea shells", 
	 * 	when a pencil is instructed to write " down by the sea shore" on the paper, 
	 * 	the paper will then contain the entire string
	 */
	@Test
	public void writeAppend(){
		String initialString = "She sells sea shells",
				appendString = " down by the sea shore",
				expectedString = "She sells sea shells down by the sea shore";
		pencil.write(paper, initialString);
		pencil.write(paper, appendString);
		
		Assert.assertEquals(expectedString, paper.getText());
	}
	
	
	/* 
	 * TC02-POINTDEG_DULL_WRITE
	 * after it goes dull, every character it is directed to write will appear as a space
	 * SCENARIO:
	 * 	if a pencil with point durability of four is instructed to write the string "Text", 
	 * 	the paper will only show "Tex "
	 */
	
	/*
	 * TC09-ERASE_LAST_OCCURANCE
	 * When the pencil is instructed to erase text from the paper, the last occurrence of that text on the paper will be replaced with empty spaces
	 * SCENARIO: 
	 * 	Given a piece of the paper containing the string:
	 * 	"How much wood would a woodchuck chuck if a woodchuck could chuck wood?"
	 * 	when the string "chuck" is erased, the paper should read:
	 * 	"How much wood would a woodchuck chuck if a woodchuck could       wood?"
	 * 	and if the string "chuck" is erased again, the paper should read:
	 * 	"How much wood would a woodchuck chuck if a wood       could       wood?"
	 */
	
	/*
	 * TC12-ERASERDEG_INSUFF_DURABILITY
	 * Once the eraser durability is zero, the eraser is worn out and can no longer erase
	 * SCENARIO:
	 * 	Thus if a pencil's eraser has remaining durability of three, 
	 * 	and it is instructed to erase the word "Bill" from "Buffalo Bill", 
	 * 	then the text remaining on the paper is "Buffalo B   "
	 */
	
	/*
	 * TC13-EDIT_OVER_WHITESPACE
	 * Once text has been erased from the paper, a pencil may be instructed to write new text over the resulting white space
	 * SCENARIO:
	 * 	if the paper contains the text "An       a day keeps the doctor away", 
	 * 	a pencil can can be instructed to write the word "onion" in the white space gap, 
	 * 	so the text reads "An onion a day keeps the doctor away".
	 */
	
	/*
	 * TC14-EDIT_COLLISION
	 * If the new text is longer than the allocated whitespace 
	 * and thus would collide with other existing non-whitespace characters on the page, 
	 * these character collisions should be represented by the "@" character
	 * SCENARIO:
	 * 	writing "artichoke" 
	 * 	in the middle of "An       a day keeps the doctor away" 
	 * 	would result in "An artich@k@ay keeps the doctor away"
	 */

}
