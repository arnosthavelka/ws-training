package com.asseco.aha.training.ws.server.ws;

import javax.activation.DataHandler;
import javax.mail.util.ByteArrayDataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.asseco.aha.training.ws.server.service.LoremService;

@Component
public class LoremWebService {

    /**
     * Class logger
     */
    private static final Logger LOG = LoggerFactory.getLogger(LoremWebService.class);

    @Autowired
    private LoremService service;

    public byte[] generate(int paraCount) {
        LOG.info("Starting lorem ipsum WS method ...");
        String text = service.generate(paraCount);

        return text.getBytes();
    }

    public DataHandler generateAsText(int paraCount) {
        LOG.info("Starting lorem ipsum WS method ...");
        String text = service.generate(paraCount);

        return new DataHandler(new ByteArrayDataSource(text.getBytes(), "text/plain"));
    }

}
