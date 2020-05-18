package com.github.aha.training.ws.common.service;

import static java.lang.String.format;

import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public String sayHello(String name) {
		return format("Spring says: Hello %s!", name);
    }

}
