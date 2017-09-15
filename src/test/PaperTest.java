package test;

import main.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

public class PaperTest {

	private Paper paper;
	private Pencil pencil;
	private int initialPointDurability 	= 200;
	private int initialLength 			= 10;
	private int initialEraserDurability = 100;
	
	@Before
	public void initializePaper(){
		paper = new Paper();
		pencil = new Pencil(initialPointDurability, initialLength, initialEraserDurability);
	}
	
	/** WRITE USER STORY
	 * 
	 * As a writer
	 * I want to be able use a pencil to write text on a sheet of paper
	 * so that I can better remember my thoughts
	 */
	
	/**
	 * When the pencil is instructed to write a string of text on a sheet of paper, the paper will reflect the text that was written
	 */
	@Test
	public void textWrittenAppendedToNothing(){
		
		String appendedString = "textWrittenAppendedToNothing";
		pencil.write(paper, appendedString);
		
		Assert.assertEquals(appendedString, paper.getText());
	}
	
	/**
	 * Text written by the pencil should always be appended to existing text on the paper
	 */
	@Test
	public void textWrittenAppended(){
		
		textWrittenAppendedToNothing();
		String appendedString = "textWrittenAppended",
				textBefore = paper.getText();
		pencil.write(paper, appendedString);
		
		Assert.assertEquals(textBefore + appendedString, paper.getText());
	}
	
	
	/** POINT DEGRADATION USER STORY
	 * 
	 * As a pencil manufacturer
	 * I want writing to cause a pencil point to go dull
	 * so that I can sell more pencils
	 */
	
	/**
	 * after it goes dull, every character it is directed to write will appear as a space
	 */
	@Test
	public void dullPencilWritesSpaces(){
		
		pencil = new Pencil(0, initialLength, initialEraserDurability);
		String textBefore = paper.getText();
		pencil.write(paper, "a");
		
		Assert.assertEquals(textBefore + " ", paper.getText());
	}
	
	
	/** ERASE USER STORY
	 * 
	 * As a writer
	 * I want to be able to erase previously written text
	 * so that I can remove my mistakes
	 */
	
	/**
	 * When the pencil is instructed to erase text from the paper, the last occurrence of that text on the paper will be replaced with empty spaces
	 */
	@Test
	public void eraseLastOccurance(){
		
		String textContents = "How much wood would a woodchuck chuck if a woodchuck could chuck wood?",
				expectedText = "How much wood would a woodchuck chuck if a woodchuck could       wood?",
				eraseString = "chuck";
		
		pencil.write(paper, textContents);
		pencil.erase(paper, eraseString);
		
		Assert.assertEquals(expectedText, paper.getText());
	}
	
	
	/** ERASER DEGRADATION USER STORY
	 * 
	 * As a pencil manufacturer
	 * I want a pencil eraser to eventually wear out
	 * so that I can sell more pencils
	 */
	
	/**
	 * Once the eraser durability is zero, the eraser is worn out and can no longer erase
	 */
	@Test
	public void noEraserDoesNotErase(){
		
		pencil = new Pencil(initialPointDurability, 0, initialEraserDurability);
		String appendedText = "noEraserDoesNotErase";
		pencil.write(paper, appendedText);
		String expectedText = paper.getText();
		pencil.erase(paper, appendedText);
		
		Assert.assertEquals(expectedText, paper.getText());
	}
	
	/**
	 * Text should be erased in the opposite order it was written
	 */
	@Test
	public void partialEraseBackwardsOrder(){
		
		pencil = new Pencil(initialPointDurability, 3, initialEraserDurability);
		String textBefore = "Buffalo Bill",
				eraseString = "Bill",
				expectedText = "Buffalo B   ";
		
		pencil.write(paper, textBefore);
		pencil.erase(paper, eraseString);
		
		Assert.assertEquals(expectedText, paper.getText());
	}
	
	
	/** EDITING USER STORY
	 * 
	 * As a writer
	 * I want to be able edit previously written text
	 * so that I can change my writing without starting over
	 */
	
	/**
	 *  a pencil may be instructed to write new text over the resulting white space
	 */
	@Test
	public void writeTextOverWhiteSpace(){
		
		String editText = "writeTextOverWhiteSpace", whiteSpaceText = "";
		for(int i = 0; i < editText.length(); i++) whiteSpaceText += " ";
		pencil.write(paper, whiteSpaceText);
		pencil.edit(paper, editText, 0);
		
		Assert.assertEquals(editText, paper.getText());
	}
	
	/**
	 * collisions should be represented by the "@" character
	 */
	@Test
	public void writeCollisions(){
		
		String textBefore = "writecollisions", editText = "", collisionText = "";
		for(char character : textBefore.toCharArray()){
			editText += (character + 1);
			collisionText += "@";
		}
		pencil.write(paper, textBefore);
		pencil.edit(paper, editText, 0);
		
		Assert.assertEquals(collisionText, paper.getText());
	}

}
