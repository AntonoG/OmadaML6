package SoftEng;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class StockTest {

	@Test
	public void testLogin() {
		Stock tester = new Stock();
		assertEquals(false,tester.checked);
		
	}
	
	@BeforeClass
	public static void testSetup(){
		System.out.println("Start Debbugging.!");
	}
	
	@AfterClass
	public static void testCleanup(){
		System.out.println("Done Debbugging.!");
	}

}
