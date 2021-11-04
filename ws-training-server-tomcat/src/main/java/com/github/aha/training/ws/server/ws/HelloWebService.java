package com.github.aha.training.ws.server.ws;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.github.aha.training.ws.common.service.HelloService;

import jakarta.jws.WebService;
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
