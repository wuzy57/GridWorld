package hello;

import static org.junit.Assert.*;
import org.junit.Test;
public class JTest{
	public helloworld he=new helloworld();
	@Test
	public void test() {
		String result=he.hellow();
		assertEquals("Hello World",result);
		
	}

}
