package hello;

import static org.junit.Assert.*;

import org.junit.Test;
public class JTest {
	@Test
	public void test() {
		String result=helloworld.hellow();
		assertEquals("Hello World",result);
		
	}

}
