package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.applications.resources.ActorResource;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {
		var srv = new ActorResource();
	}

}
