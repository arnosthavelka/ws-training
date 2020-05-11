package com.github.aha.training.ws.server.service;

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

	@Override
	public String generateText(int paraCount) {
		return loremIpsum.getParagraphs(paraCount);
	}

}
