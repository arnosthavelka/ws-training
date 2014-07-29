package com.asseco.aha.training.ws.server.ws;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.asseco.aha.training.ws.server.service.HelloService;
import com.asseco.aha.training.ws.server.service.HelloServiceImpl;

@WebService
public class HelloWebService {

	/**
	 * Class logger
	 */
	private static final Logger LOG = LoggerFactory
			.getLogger(HelloWebService.class);

	// @Autowired is not currently working (in Spring boot)
	private HelloService service = new HelloServiceImpl();

	public String sayHello(String name) {
		LOG.info("Starting hello WS method ...");
		return service.sayHello(name);
	}

}
