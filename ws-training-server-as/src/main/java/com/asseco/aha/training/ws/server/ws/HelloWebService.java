package com.asseco.aha.training.ws.server.ws;

import javax.annotation.PostConstruct;
import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.asseco.aha.training.ws.server.service.HelloService;

@WebService
// @HandlerChain(file = "/handler-chain.xml")
public class HelloWebService {

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

	/**
	 * Spring initialization ...
	 */
	@PostConstruct
	private void init() {
		LOG.info("Spring initialization ...");
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

}
