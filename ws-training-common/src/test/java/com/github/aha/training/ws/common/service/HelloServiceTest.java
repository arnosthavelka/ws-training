package com.github.aha.training.ws.common.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class HelloServiceTest {

	@Test
	void hello() {
		assertThat(new HelloService().sayHello("Gugu")).isEqualTo("Spring says: Hello Gugu!");
	}

}
