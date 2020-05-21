package com.asseco.aha.training.ws.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) { // NOSONAR (due to safe usage of argument)
        SpringApplication.run(Application.class, args);
    }

}
