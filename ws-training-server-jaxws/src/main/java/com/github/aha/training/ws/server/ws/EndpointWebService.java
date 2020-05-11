package com.github.aha.training.ws.server.ws;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.ws.Provider;
import javax.xml.ws.WebServiceProvider;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.aha.training.ws.server.ws.util.XmlProcessor;

import lombok.extern.slf4j.Slf4j;

@javax.xml.ws.ServiceMode(value = javax.xml.ws.Service.Mode.MESSAGE)
@WebServiceProvider(wsdlLocation = "wsdl/ws-training-endpoint.wsdl", serviceName = "EndpointService", portName = "EndpointPort", targetNamespace = "urn:com:github:aha:training:ws:endpoint:srv:v1")
@Slf4j
public class EndpointWebService extends AbstractSoapWebService implements Provider<SOAPMessage> {

    @Autowired
    private XmlProcessor xp;

    @Override
    public SOAPMessage invoke(SOAPMessage request) {
		log.debug("Provider invoke started ...");
        try {
            SOAPPart soapPart = request.getSOAPPart();

            String xml = xp.convert2String(soapPart.getContent());
			log.info("Received message:\n*****************************************\n" + xml);
            return null;

        } catch (SOAPException e) {
			log.error("Error in processing request", e);
            throw new RuntimeException("Unexpected error ...", e);
        }
    }
}
