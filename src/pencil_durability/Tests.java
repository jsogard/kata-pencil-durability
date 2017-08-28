package pencil_durability;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class Tests {

	private Paper paper;
	private Pencil pencil;
	
	private String baseTestString = "This is a test string";
	
	@Before
	public void implementDefaults(){
		paper = new Paper();
		pencil = new Pencil();
	}
	
	/* WRITE TESTS */
	
	@Test
	public void test_write(){
		
		Assert.assertTrue(paper.write(baseTestString, pencil));
		Assert.assertEquals(baseTestString, paper.getText());
	}
	
	@Test
	public void test_writeBreakPoint(){
		
		String breakString = "";
		for(int i = 0; i < pencil.getPointDurability() / CharacterCost.LowercaseWriteCost; i++)
			breakString += 'a';
		Assert.assertTrue(paper.write(breakString, pencil));
		Assert.assertEquals(breakString, paper.getText());
		Assert.assertEquals(0, pencil.getPointDurability());
	}
	
	@Test
	public void test_brokenPointDoesNotWrite(){
		
		test_writeBreakPoint();
		String initialString = paper.getText();
		Assert.assertFalse(paper.write("test", pencil));
		Assert.assertEquals(initialString, paper.getText());
	}
	
	/* ERASE TESTS */
	
	@Test
	public void test_erase(){
		
		test_write();
		Assert.assertTrue(paper.erase("test", pencil));
		Assert.assertEquals("This is a      string", paper.getText());
	}
	
	@Test
	public void test_eraseStringNotPresent(){
		
		String eraseString = "cucumber";
		paper.write(eraseString, pencil);
		Assert.assertFalse(paper.erase("pickle", pencil));
		Assert.assertEquals(eraseString, paper.getText());
	}
	
	@Test
	public void test_eraseLastInstance(){
		
		paper.write("test test test test", pencil);
		Assert.assertTrue(paper.erase("test", pencil));
		Assert.assertEquals("test test test     ", paper.getText());
	}
	
	@Test
	public void test_breakEraser(){
		
		pencil = new Pencil(100, 20, 10);
		String breakString = "";
		for(int i = 0; i < pencil.getEraserDurability() / CharacterCost.LowercaseEraseCost; i++)
			breakString += 'a';
		paper.write(breakString, pencil);
		Assert.assertTrue(paper.erase(breakString, pencil));
		Assert.assertEquals(0, pencil.getEraserDurability());
	}
	
	@Test
	public void test_brokenEraserDoesNotErase(){
		
		test_breakEraser();
		paper.write("text", pencil);
		String textBefore = paper.getText();
		Assert.assertFalse(paper.erase("text", pencil));
		Assert.assertEquals(textBefore, paper.getText());
	}
	
	/* EDIT TESTS */
	
	@Test
	public void test_edit(){
		
		test_write();
		Assert.assertTrue(paper.edit("edit", 10, pencil));
		Assert.assertEquals("This is a @@@t string", paper.getText());
	}
	
	/* RESHARPEN TESTS */
	
	@Test
	public void test_resharpen(){
		
		int initialPointDurability = pencil.getPointDurability();
		test_write();
		Assert.assertTrue(pencil.sharpen());
		Assert.assertEquals(initialPointDurability, pencil.getPointDurability());
	}
	
	@Test
	public void test_resharpenTooMuch(){
		
		int resharpens = pencil.getLength();
		for(int i = 0; i < resharpens; i++)
			Assert.assertTrue(pencil.sharpen());
		Assert.assertFalse(pencil.sharpen());
		Assert.assertEquals(0, pencil.getLength());
	}
}
