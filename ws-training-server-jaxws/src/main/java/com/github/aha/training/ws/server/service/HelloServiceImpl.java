package com.github.aha.training.ws.server.service;

import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {

    public String sayHello(String name) {
        return String.format("Spring says: Hello %s!", name);
    }

}
