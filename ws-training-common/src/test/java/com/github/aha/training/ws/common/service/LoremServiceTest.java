package com.github.aha.training.ws.common.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class LoremServiceTest {

	@Test
	void someText() {
		assertThat(new LoremService().generateText(1)).isNotBlank();
	}

	@Test
	void noText() {
		assertThat(new LoremService().generateText(0)).isEmpty();
	}


}
