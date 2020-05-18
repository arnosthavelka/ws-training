package com.asseco.aha.training.ws.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.aha.training.ws.common.service.HelloService;

@SpringBootApplication
public class Application {

	public static void main(String[] args) { // NOSONAR (due to safe usage of argument)
        SpringApplication.run(Application.class, args);
    }

	@Configuration
	class ApplicationConfig {

		@Bean
		HelloService helloService() {
			return new HelloService();
		}

	}
}
