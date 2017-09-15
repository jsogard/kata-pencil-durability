package test;

import main.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

public class PaperTest {

	private static Paper paper;
	private static Pencil pencil;
	
	@Before
	public static Paper initializePaper(){
		paper = new Paper();
		pencil = PencilTest.initializePencil();
		return paper;
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
		
		Assert.assertTrue(paper.getText().equals(appendedString));
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
		
		Assert.assertTrue(paper.getText().equals(textBefore + appendedString));
	}

}
