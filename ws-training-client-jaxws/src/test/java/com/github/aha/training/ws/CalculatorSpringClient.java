package com.github.aha.training.ws;

import static java.util.List.of;
import static org.assertj.core.api.Assertions.assertThat;

import java.net.MalformedURLException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.github.aha.training.ws.calc.srv.v1.CalcPortType;
import com.github.aha.training.ws.types.v1.AddRequest;
import com.github.aha.training.ws.types.v1.AddResponse;
import com.github.aha.training.ws.types.v1.ObjectFactory;

@SpringBootTest
@ContextConfiguration("classpath:/spring/application-context-test.xml")
class CalculatorSpringClient {

    @Autowired
    private CalcPortType calc;

    @Autowired
    private ObjectFactory of;

    @Test
	void testCalc() throws MalformedURLException {
		AddRequest ar = of.createAddRequest();
		ar.getNumber().addAll(of(5, 2, 1));

        AddResponse response = calc.add(ar);
		assertThat(8).isEqualTo(response.getResult());

    }

}
