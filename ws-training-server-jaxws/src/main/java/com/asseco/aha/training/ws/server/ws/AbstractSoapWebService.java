package com.asseco.aha.training.ws.server.ws;

import javax.annotation.PostConstruct;
import javax.jws.HandlerChain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

@HandlerChain(file = "/handler-chain.xml")
public abstract class AbstractSoapWebService {

	/**
	 * Class logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(AbstractSoapWebService.class);

	/**
	 * Spring initialization ...
	 */
	@PostConstruct
	private void init() {
		LOG.info("Spring initialization ...");
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

}
