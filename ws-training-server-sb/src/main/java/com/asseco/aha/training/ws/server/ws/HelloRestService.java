package com.asseco.aha.training.ws.server.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.asseco.aha.training.ws.server.service.HelloService;

// http://localhost:8080/hello?name=Gabriel
@RestController
public class HelloRestService {

    @Autowired
    private HelloService service;

    @RequestMapping("/hello")
    public String sayHello(String name) {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

        return service.sayHello(name);
    }

}
