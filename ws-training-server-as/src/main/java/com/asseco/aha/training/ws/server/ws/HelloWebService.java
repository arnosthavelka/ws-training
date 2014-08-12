package com.asseco.aha.training.ws.server.ws;

import javax.jws.WebService;
import javax.xml.ws.soap.Addressing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.asseco.aha.training.ws.server.service.HelloService;

@WebService
@Addressing
public class HelloWebService extends AbstractSoapWebService {

	/**
	 * Class logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(HelloWebService.class);

	@Autowired
	private HelloService service;

	public String sayHello(String name) {
		LOG.info("Starting hello WS method ...");
		return service.sayHello(name);
	}

}
