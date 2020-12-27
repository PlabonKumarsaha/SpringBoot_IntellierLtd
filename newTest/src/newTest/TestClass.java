package newTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TestClass {

	@Test
	public void setUp() {
		String mssage = "My first junit";
		assertEquals( "My first junit", mssage);
	}

}
