package com.asseco.aha.training.ws.server.service;

import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {

	/* (non-Javadoc)
	 * @see com.asseco.aha.training.ws.server.service.HelloService#sayHello(java.lang.String)
	 */
	@Override
	public String sayHello(String name) {
		return String.format("Spring say: Hello %s!", name);
	}

}
