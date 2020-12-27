package newTest;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;


public class CalculatorTest {

	Calculator calculator;
	
	@BeforeEach
	public  void init() {
		calculator = new Calculator();
	}
	
	/*
	//CHECK nested test cases
	@Nested
	@DisplayName(" sum testing")
	class AddTest{
		
		@Test
		@DisplayName("Positive sum testing")
		public void testAddPositiveSum() {
			 
			int result = calculator.sum(2, 2);
			
			assertEquals(4,result);
		}
		
		@Test
		@DisplayName("Negative sum testing")
		public void testAddNegativeSum() {
			 
			int result = calculator.sum(-2, -2);
			
			assertEquals(-4,result);
		}	
		
	}
	*/

	
	@Test
	@DisplayName("sum testing")
	public void testSum() {
		 
		int result = calculator.sum(2, 2);
		
		assertEquals(4,result);
	}
	
	//@Test
	@RepeatedTest(value =3) //- repeates the test thrice
	public void testMinus(RepetitionInfo repinfo) {
		 
		int result = calculator.minus(2, 2);
		
		assertEquals(0,result);
	}
	
	@Test
	public void testMul() {
		 
		int result = calculator.multiply(2, 2);
		
		assertEquals(4,result);
	}
	
	@EnabledOnOs(OS.LINUX) //look to run on only linux .The other OS will be disabled
	@EnabledOnJre(JRE.JAVA_13)// chekc features on java 13!
	@Test
	@Disabled //disabling a test case
	public void testDiv() {
		 
		assertThrows(ArithmeticException.class,()->calculator.div(8, 0), "divide by zero gives error!");
		float result = calculator.div(8, 2);
		assertEquals(4.00f,result,0);
		//checking for exception
	}
	

}
