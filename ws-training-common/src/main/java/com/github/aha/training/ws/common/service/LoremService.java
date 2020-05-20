package com.github.aha.training.ws.common.service;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import de.svenjacobs.loremipsum.LoremIpsum;

@Service
public class LoremService {

	private LoremIpsum loremIpsum;

	@PostConstruct
	private void init() {
		loremIpsum = new LoremIpsum();
	}

	public String generateText(int paraCount) {
		return loremIpsum.getParagraphs(paraCount);
	}

}
