package com.github.aha.training.ws.common.service;

import static java.util.List.of;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class CalculatorServiceTest {

	static CalculatorService service = new CalculatorService();

	@Nested
	class Sum {

		@Test
		void sumSingleValue() {
			assertThat(service.sum(of(5))).isEqualTo(5);
		}

		@Test
		void sumSeveralValue() {
			assertThat(service.sum(of(1, 2, 3, 4, 5))).isEqualTo(15);
		}

		@Test
		void sumNoValue() {
			assertThat(service.sum(of())).isEqualTo(0);
		}

	}

	@Nested
	class Multiply {

		@Test
		void sumSingleValue() {
			assertThat(service.multiply(of(5))).isEqualTo(5);
		}

		@Test
		void sumSeveralValue() {
			assertThat(service.multiply(of(1, 2, 3, 4, 5))).isEqualTo(120);
		}

		@Test
		void sumNoValue() {
			assertThat(service.multiply(of())).isEqualTo(0);
		}

	}

}
