package com.github.aha.training.ws.server.ws;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.aha.training.ws.calc.srv.v1.CalcPortType;
import com.github.aha.training.ws.common.CalculatorService;
import com.github.aha.training.ws.types.v1.AddRequest;
import com.github.aha.training.ws.types.v1.AddResponse;
import com.github.aha.training.ws.types.v1.ObjectFactory;

import lombok.extern.slf4j.Slf4j;

@WebService(serviceName = "CalcService", portName = "CalcPort", endpointInterface = "com.github.aha.training.ws.calc.srv.v1.CalcPortType")
@Slf4j
public class CalculatorWebService extends AbstractSoapWebService implements CalcPortType {

    @Autowired
    private ObjectFactory of;

    @Autowired
    private CalculatorService service;

    @Override
    public AddResponse add(AddRequest request) {
		log.info("Starting calculator#add WS method ...");

        int value = service.add(request.getNumber());

        AddResponse response = of.createAddResponse();
        response.setResult(value);

        return response;
    }

}
