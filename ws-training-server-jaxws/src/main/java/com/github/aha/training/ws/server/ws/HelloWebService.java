package com.github.aha.training.ws.server.ws;

import javax.jws.WebService;
import javax.xml.ws.soap.Addressing;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.aha.training.ws.common.service.HelloService;

import lombok.extern.slf4j.Slf4j;

/**
 * WSDL: http://127.0.0.1:8080/ws-server-as/HelloWebService?WSDL
 */
@WebService
@Addressing
@Slf4j
public class HelloWebService extends AbstractSoapWebService {

    @Autowired
    private HelloService service;

    public String sayHello(String name) {
		log.info("Starting hello WS method ...");
        return service.sayHello(name);
    }

}
