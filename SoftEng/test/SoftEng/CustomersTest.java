package SoftEng;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class CustomersTest {

	@Test
	public void testLogin() {
		Customers tester = new Customers();
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
