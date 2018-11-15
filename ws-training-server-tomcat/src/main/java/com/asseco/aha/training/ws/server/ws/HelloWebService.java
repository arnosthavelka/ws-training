package com.asseco.aha.training.ws.server.ws;

import javax.annotation.PostConstruct;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.asseco.aha.training.ws.server.service.HelloService;

import lombok.extern.slf4j.Slf4j;

@WebService
@Slf4j
public class HelloWebService {

	@Autowired
	private HelloService service;

	public String sayHello(String name) {
		if (service == null) {
			initSpring();
		}
		log.info("Starting hello WS method ...");
		return service.sayHello(name);
	}

	@PostConstruct
	public void initSpring() {
		log.info("Spring initialization ...");
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
}
