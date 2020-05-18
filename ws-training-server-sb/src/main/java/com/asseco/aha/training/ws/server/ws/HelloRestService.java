package com.asseco.aha.training.ws.server.ws;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.github.aha.training.ws.common.service.HelloService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

// http://localhost:8080/ws-server-sb/hello?name=Gabriel
@RestController
@RequiredArgsConstructor
public class HelloRestService {

	@NonNull
    private HelloService service;

	@GetMapping("/hello")
	public String sayHello(@RequestParam String name) {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

        return service.sayHello(name);
    }

}
