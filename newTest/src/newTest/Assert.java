package newTest;



import static org.junit.Assert.*;

import org.junit.Test;

public class Assert {

	@Test
	public void testAssertions() {
		String str = new String("me");
		String str2 = new String("me");
		
		String str3 = null;
		String str4 = "me";
		int val = 5;
		int val2 = 6;
		String[]array1 = {"1","2","3"};
		String[]array2 = {"1","2","3"};
		
		//check for same reference
		assertEquals(str,str2);
		//check for true
		assertTrue(val<val2);
		//ceheck for false
		assertFalse(val>val2);
		//check for null
		assertNotNull(str);
		//check if it null
		assertNull(str3);
		assertNotSame(str,str2);
		//look for arrays
		assertArrayEquals(array1,array2);

}
}
