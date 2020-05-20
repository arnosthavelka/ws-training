package com.github.aha.training.ws.server.ws;

import javax.activation.DataHandler;
import javax.jws.WebMethod;
import javax.mail.util.ByteArrayDataSource;

import org.springframework.stereotype.Component;

import com.github.aha.training.ws.common.service.LoremService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component("loremBean")
@Slf4j
@RequiredArgsConstructor
public class LoremWebService {

	@NonNull
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
