package com.github.aha.training.ws;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.junit.jupiter.api.Test;

import com.github.aha.training.ws.calc.srv.v1.CalcPortType;
import com.github.aha.training.ws.types.v1.AddRequest;
import com.github.aha.training.ws.types.v1.AddResponse;

public class CalculatorRuntimeClient {

	@Test
    public void testCalc() throws MalformedURLException {
        URL url = new URL("http://localhost:8080/ws-server-as/CalcService?WSDL");
		QName qname = new QName("http://ws.server.ws.training.aha.github.com/", "CalcService");

        Service service = Service.create(url, qname);
        CalcPortType calc = service.getPort(CalcPortType.class);

        AddRequest ar = new AddRequest();
        List<Integer> inputs = ar.getNumber();
        inputs.add(5);
        inputs.add(2);
        inputs.add(1);

        AddResponse response = calc.add(ar);
		assertThat(8).isEqualTo(response.getResult());

    }

}
