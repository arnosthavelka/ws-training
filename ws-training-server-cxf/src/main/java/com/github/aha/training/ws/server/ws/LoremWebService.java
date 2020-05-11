package com.github.aha.training.ws.server.ws;

import javax.activation.DataHandler;
import javax.jws.WebMethod;
import javax.mail.util.ByteArrayDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.aha.training.ws.server.service.LoremService;

import lombok.extern.slf4j.Slf4j;

@Component("loremBean")
@Slf4j
public class LoremWebService {

    @Autowired
    private LoremService service;

    @WebMethod
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
