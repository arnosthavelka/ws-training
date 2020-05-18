package com.asseco.aha.training.ws.server.ws;

import javax.annotation.PostConstruct;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.asseco.aha.training.ws.server.ServletInitializer;
import com.github.aha.training.ws.common.service.HelloService;

import lombok.extern.slf4j.Slf4j;

@WebService
@Slf4j
public class HelloWebService extends SpringBeanAutowiringSupport {

	@Autowired
	private HelloService service;

	public String sayHello(String name) {
		log.info("Starting hello WS method ...");
		return service.sayHello(name);
	}

	@PostConstruct
	private void initSpring() {
		log.info("Spring initialization ...");
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
				ServletInitializer.getCurrentWebApplicationContext().getServletContext());
	}

}
