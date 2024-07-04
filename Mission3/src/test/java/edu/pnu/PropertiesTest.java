package edu.pnu;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

@SpringBootTest
public class PropertiesTest {
	
	@Autowired
	Environment enviroment;
	
	@Test
	public void testMethod() {
		System.out.println();
	}
	
	
}
