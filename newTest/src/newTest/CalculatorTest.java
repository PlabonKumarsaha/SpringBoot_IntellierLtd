package newTest;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class CalculatorTest {

	Calculator calculator;
	@Before
	public void init() {
		calculator = new Calculator();
	}
	
	@Test
	public void testSum() {
		 
		int result = calculator.sum(2, 2);
		
		assertEquals(4,result);
	}
	
	@Test
	public void testMinus() {
		 
		int result = calculator.minus(2, 2);
		
		assertEquals(0,result);
	}
	
	@Test
	public void testMul() {
		 
		int result = calculator.multiply(2, 2);
		
		assertEquals(4,result);
	}
	
	@Test
	public void testDiv() {
		 
		float result = calculator.div(8, 2);
		assertEquals(4.00f,result,0);
		//checking for exception
	}
	@After
	public void off() {
		//calculator
	}
}
