package com.asseco.aha.training.ws.server.ws;

import javax.annotation.Resource;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.ws.Provider;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.WebServiceProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.asseco.aha.training.ws.server.ws.util.XmlProcessor;

@javax.xml.ws.ServiceMode(value = javax.xml.ws.Service.Mode.MESSAGE)
@WebServiceProvider(wsdlLocation = "wsdl/ws-training-endpoint.wsdl", serviceName = "EndpointService", portName = "EndpointPort", targetNamespace = "urn:com:asseco:aha:training:ws:endpoint:srv:v1")
public class EndpointWebService extends AbstractSoapWebService implements Provider<SOAPMessage> {

    /**
     * Class logger
     */
    private static final Logger LOG = LoggerFactory.getLogger(EndpointWebService.class);

    @Autowired
    private XmlProcessor xp;

    @Resource
    private WebServiceContext context;

    @Override
    public SOAPMessage invoke(SOAPMessage request) {
        LOG.debug("Provider invoke started ...");
        try {
            SOAPPart soapPart = request.getSOAPPart();

            String xml = xp.convert2String(soapPart.getContent());
            LOG.info("Received message:\n*****************************************\n" + xml);
            return null;

        } catch (SOAPException e) {
            LOG.error("Error in processing request", e);
            throw new RuntimeException("Unexpected error ...", e);
        }
    }
}
