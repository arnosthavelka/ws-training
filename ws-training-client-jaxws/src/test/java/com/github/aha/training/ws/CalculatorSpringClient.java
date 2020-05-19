package com.github.aha.training.ws;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.MalformedURLException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.github.aha.training.ws.calc.srv.v1.CalcPortType;
import com.github.aha.training.ws.types.v1.AddRequest;
import com.github.aha.training.ws.types.v1.AddResponse;
import com.github.aha.training.ws.types.v1.ObjectFactory;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:/spring/application-context-test.xml")
class CalculatorSpringClient {

    @Autowired
    private CalcPortType calc;

    @Autowired
    private ObjectFactory of;

    @Test
	void testCalc() throws MalformedURLException {
        AddRequest ar = of.createAddRequest(); // new AddRequest();
        List<Integer> inputs = ar.getNumber();
        inputs.add(5);
        inputs.add(2);
        inputs.add(1);

        AddResponse response = calc.add(ar);
		assertThat(8).isEqualTo(response.getResult());

    }

}
