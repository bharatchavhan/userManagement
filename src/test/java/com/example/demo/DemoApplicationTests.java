package com.example.demo;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
class DemoApplicationTests {
	@Mock
	private UserRepository servicel;
	
	@InjectMocks
	private UserController controller;
	
	@Test
	public void shouldReturnWelcome() throws Exception{
		
	}

}
