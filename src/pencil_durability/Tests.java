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
	
	@Before
	public void implementDefaults(){
		paper = new Paper();
		pencil = new Pencil();
	}
	
	@Test
	public void test_write(){
		
		String testString = "This is a test string";
		Assert.assertTrue(paper.write(testString, pencil));
		Assert.assertEquals(testString, paper.getText());
	}
	
	@Test
	public void test_erase(){
		
		test_write();
		Assert.assertTrue(paper.erase("test", pencil));
		Assert.assertEquals("This is a      string", paper.getText());
	}
	
	@Test
	public void test_edit(){
		
		test_write();
		Assert.assertTrue(paper.edit("edit", 10, pencil));
		Assert.assertEquals("This is a @@@t string", paper.getText());
	}
}
