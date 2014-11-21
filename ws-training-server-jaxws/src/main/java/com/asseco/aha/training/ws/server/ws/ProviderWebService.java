package com.asseco.aha.training.ws.server.ws;

import java.io.StringReader;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.Provider;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.WebServiceProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Document;

import com.asseco.aha.training.ws.server.service.ProviderService;
import com.asseco.aha.training.ws.server.ws.util.XmlProcessor;

@javax.xml.ws.ServiceMode(value = javax.xml.ws.Service.Mode.MESSAGE)
@WebServiceProvider(wsdlLocation = "wsdl/ws-training-provider.wsdl", serviceName = "ProviderService", portName = "ProviderPort", targetNamespace = "urn:com:asseco:aha:training:ws:provider:srv:v1")
public class ProviderWebService extends AbstractSoapWebService implements Provider<SOAPMessage> {

    /**
     * Class logger
     */
    private static final Logger LOG = LoggerFactory.getLogger(ProviderWebService.class);

    @Autowired
    private XmlProcessor xp;

    @Autowired
    private ProviderService ps;

    private MessageFactory mf;

    @Resource
    private WebServiceContext context;

    @PostConstruct
    private void init() {
        try {
            mf = MessageFactory.newInstance();
        } catch (SOAPException e) {
            LOG.error("Initialization error ...", e);
        }
    }

    @Override
    public SOAPMessage invoke(SOAPMessage request) {
        LOG.debug("Provider invoke started ...");
        try {
            SOAPPart soapPart = request.getSOAPPart();
            Document doc = xp.convert2dom(soapPart.getContent());

            List<String> values = ps.parseContent(doc, request.getSOAPHeader());
            String response = prepareResponse(values);

            LOG.debug(response);
            return makeSOAPMessage(response);

        } catch (SOAPException e) {
            LOG.error("Error in processing request", e);
            throw new RuntimeException("Unexpected error ...", e);
        }
    }

    private String prepareResponse(List<String> values) {
        StringBuilder sb = new StringBuilder();
        sb.append("<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">")
                .append("<SOAP-ENV:Header xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\"/>").append("<soap:Body>")
                .append("<ProviderResponse xmlns=\"urn:com:asseco:aha:training:types:v1\">");
        for (String value : values) {
            sb.append("<result>").append(value).append("</result>");
        }

        sb.append("</ProviderResponse>").append("</soap:Body>").append("</soap:Envelope>");

        return sb.toString();
    }

    private SOAPMessage makeSOAPMessage(String msg) {
        try {
            SOAPMessage message = mf.createMessage();
            message.getSOAPPart().setContent((Source) new StreamSource(new StringReader(msg)));
            message.saveChanges();
            return message;
        } catch (Exception e) {
            return null;
        }
    }
}
