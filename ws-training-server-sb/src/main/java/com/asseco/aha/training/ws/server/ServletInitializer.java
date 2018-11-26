package com.asseco.aha.training.ws.server;

import javax.servlet.ServletContext;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.context.WebApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		log.info("Starting application ...");
		return application.sources(Application.class);
	}

	private static WebApplicationContext webApplicationContext;

	public static WebApplicationContext getCurrentWebApplicationContext() {
		return webApplicationContext;
	}
	@Override
	protected WebApplicationContext createRootApplicationContext(ServletContext servletContext) {
		// https://stackoverflow.com/questions/25177491/spring-boot-register-jax-ws-webservice-as-bean
		webApplicationContext = super.createRootApplicationContext(servletContext);
		return webApplicationContext;
	}

}
