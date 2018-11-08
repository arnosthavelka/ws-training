package com.asseco.aha.training.ws.server.ws;

import javax.annotation.PostConstruct;
import javax.jws.HandlerChain;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import lombok.extern.slf4j.Slf4j;

@HandlerChain(file = "/handler-chain.xml")
@Slf4j
abstract class AbstractSoapWebService {

	@PostConstruct
	private void initSpring() {
		log.info("Spring initialization ...");
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

}
