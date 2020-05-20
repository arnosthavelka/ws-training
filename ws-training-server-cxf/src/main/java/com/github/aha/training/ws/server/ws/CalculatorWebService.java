package com.github.aha.training.ws.server.ws;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.aha.training.ws.calc.srv.v1.CalcPortType;
import com.github.aha.training.ws.common.service.CalculatorService;
import com.github.aha.training.ws.types.v1.AddRequest;
import com.github.aha.training.ws.types.v1.AddResponse;
import com.github.aha.training.ws.types.v1.ObjectFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CalculatorWebService implements CalcPortType {

    @Autowired
    private ObjectFactory of;

    @Autowired
    private CalculatorService service;

    @Override
    public AddResponse add(AddRequest request) {
		log.info("Starting calculator#add WS method ...");

        int value = service.sum(request.getNumber());

        AddResponse response = of.createAddResponse();
        response.setResult(value);

        return response;
    }

}
