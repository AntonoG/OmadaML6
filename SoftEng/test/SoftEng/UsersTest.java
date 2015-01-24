package SoftEng;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class UsersTest {
	@Test
	public void testLogin() {
		Users tester = new Users();
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
