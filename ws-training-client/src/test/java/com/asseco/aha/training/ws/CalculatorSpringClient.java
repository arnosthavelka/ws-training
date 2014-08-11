package com.asseco.aha.training.ws;

import java.net.MalformedURLException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.asseco.aha.training.types.v1.AddRequest;
import com.asseco.aha.training.types.v1.AddResponse;
import com.asseco.aha.training.types.v1.ObjectFactory;
import com.asseco.aha.training.ws.v1.WsTrainingPortType;

@ContextConfiguration("classpath:/spring/application-context-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class CalculatorSpringClient {

	@Autowired
	private WsTrainingPortType calc;

	@Autowired
	private ObjectFactory of;

	@Test
	public void testCalc() throws MalformedURLException {
		AddRequest ar = of.createAddRequest(); // new AddRequest();
		List<Integer> inputs = ar.getNumber();
		inputs.add(5);
		inputs.add(2);
		inputs.add(1);

		AddResponse response = calc.add(ar);
		Assert.assertEquals(8, response.getResult());

	}

}
