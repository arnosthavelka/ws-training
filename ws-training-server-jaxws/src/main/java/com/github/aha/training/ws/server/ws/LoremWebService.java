package com.github.aha.training.ws.server.ws;

import javax.activation.DataHandler;
import javax.jws.WebService;
import javax.mail.util.ByteArrayDataSource;
import javax.xml.ws.soap.MTOM;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.aha.training.ws.common.service.LoremService;

import lombok.extern.slf4j.Slf4j;

/**
 * WSDL: http://127.0.0.1:8080/ws-server-as/LoremWebService?WSDL
 */
@WebService
@MTOM(threshold = 1024)
@Slf4j
public class LoremWebService extends AbstractSoapWebService {

	@Autowired
	private LoremService service;

	public byte[] generate(int paraCount) {
		log.info("Starting lorem ipsum WS method ...");
		String text = service.generateText(paraCount);

		return text.getBytes();
	}

	public DataHandler generateAsText(int paraCount) {
		log.info("Starting lorem ipsum WS method ...");
		String text = service.generateText(paraCount);

		return new DataHandler(new ByteArrayDataSource(text.getBytes(), "text/plain"));
	}

}
