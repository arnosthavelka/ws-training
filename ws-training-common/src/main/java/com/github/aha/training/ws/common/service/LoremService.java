package com.github.aha.training.ws.common.service;

import org.springframework.stereotype.Service;

import de.svenjacobs.loremipsum.LoremIpsum;

@Service
public class LoremService {

	private LoremIpsum loremIpsum = new LoremIpsum();

	public String generateText(int paraCount) {
		return loremIpsum.getParagraphs(paraCount);
	}

}
