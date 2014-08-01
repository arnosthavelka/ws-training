package com.asseco.aha.training.ws.server.service;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import de.svenjacobs.loremipsum.LoremIpsum;

@Service
public class LoremServiceImpl implements LoremService {

	private LoremIpsum loremIpsum;

	@PostConstruct
	private void init() {
		loremIpsum = new LoremIpsum();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.asseco.aha.training.ws.server.service.HelloService#sayHello(java. lang.String)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.asseco.aha.training.ws.server.service.LoremService#generate(int)
	 */
	@Override
	public String generate(int paraCount) {
		return loremIpsum.getParagraphs(paraCount);
	}

}
