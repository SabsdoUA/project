package com.Subbotin.project;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "app.cli.enabled=false")
class ProjectApplicationTests {

	@Test
	void contextLoads() {
	}

}
