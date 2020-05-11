package com.github.aha.training.ws.server.service;

import java.util.List;

public interface CalculatorService {

	Integer add(List<Integer> values);

	Long multiply(List<Integer> values);

}