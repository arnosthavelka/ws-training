package com.asseco.aha.training.ws.server.ws;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.asseco.aha.training.types.v1.AddRequest;
import com.asseco.aha.training.types.v1.AddResponse;
import com.asseco.aha.training.types.v1.ObjectFactory;
import com.asseco.aha.training.ws.server.service.CalulatorService;
import com.asseco.aha.training.ws.v1.WsTrainingPortType;

@WebService(endpointInterface = "com.asseco.aha.training.ws.v1.WsTrainingPortType")
public class CalculatorWebService extends AbstractSoapWebService implements WsTrainingPortType {

	/**
	 * Class logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(CalculatorWebService.class);

	@Autowired
	private ObjectFactory of;

	@Autowired
	private CalulatorService service;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.asseco.aha.training.ws.v1.WsTrainingPortType#add(com.asseco.aha.training.types.v1.AddRequest)
	 */
	@Override
	public AddResponse add(AddRequest request) {
		LOG.info("Starting calculator#add WS method ...");

		int value = service.add(request.getNumber());

		AddResponse response = of.createAddResponse();
		response.setResult(value);

		return response;
	}

}