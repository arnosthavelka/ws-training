package com.github.aha.training.ws.common.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

	public Integer sum(List<Integer> values) {
		int value = 0;
		for (Integer number : values) {
			value += number;
		}
		return value;
	}

	public Long multiply(List<Integer> values) {
		if (values.isEmpty()) {
			return 0L;
		}
		long value = 1;
		for (Integer number : values) {
			value *= number;
		}
		return value;
	}

}
