package com.asseco.aha.training.ws.server.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CalculatorServiceImpl implements CalculatorService {

	@Override
	public Integer add(List<Integer> values) {
		int value = 0;
		for (Integer number : values) {
			value += number;
		}
		return value;
	}

	@Override
	public Long multiply(List<Integer> values) {
		long value = 0;
		for (Integer number : values) {
			value *= number;
		}
		return value;
	}

}
