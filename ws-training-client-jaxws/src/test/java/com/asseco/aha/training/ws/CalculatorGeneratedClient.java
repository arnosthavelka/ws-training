package com.asseco.aha.training.ws;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.xml.namespace.QName;

import org.junit.Assert;
import org.junit.Test;

import com.asseco.aha.training.ws.calc.srv.v1.CalcPortType;
import com.asseco.aha.training.ws.calc.srv.v1.CalcService;
import com.asseco.aha.training.ws.types.v1.AddRequest;
import com.asseco.aha.training.ws.types.v1.AddResponse;

public class CalculatorGeneratedClient {

    @Test
    public void testCalc() throws MalformedURLException {
        URL url = new URL("http://localhost:8080/ws-server-as/CalcService?WSDL");
        QName qname = new QName("http://ws.server.ws.training.aha.asseco.com/", "CalcService");

        CalcService service = new CalcService(url, qname);
        CalcPortType calc = service.getPort(CalcPortType.class);

        AddRequest ar = new AddRequest();
        List<Integer> inputs = ar.getNumber();
        inputs.add(5);
        inputs.add(2);
        inputs.add(1);

        AddResponse response = calc.add(ar);
        Assert.assertEquals(8, response.getResult());

    }

}
