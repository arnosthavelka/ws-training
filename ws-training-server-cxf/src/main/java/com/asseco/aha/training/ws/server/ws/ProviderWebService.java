package com.asseco.aha.training.ws.server.ws;

import javax.annotation.Resource;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.ws.Provider;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.WebServiceProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.asseco.aha.training.ws.server.ws.util.XmlProcessor;

@Component("providerBean")
@javax.xml.ws.ServiceMode(value = javax.xml.ws.Service.Mode.PAYLOAD)
@WebServiceProvider
public class ProviderWebService implements Provider<Source> {

    /**
     * Class logger
     */
    private static final Logger LOG = LoggerFactory.getLogger(ProviderWebService.class);

    @Autowired
    private XmlProcessor xp;

    @Resource
    private WebServiceContext context;

    @Override
    public Source invoke(Source request) {
        LOG.debug("Provider invoke started ...");

        String response = prepareResponse();

        LOG.debug(response);
        return new DOMSource(xp.convert2dom(response));
    }

    private String prepareResponse() {
        StringBuilder sb = new StringBuilder();
        sb.append("<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">")
                .append("<SOAP-ENV:Header xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\"/>").append("<soap:Body>")
                .append("<ProviderResponse xmlns=\"urn:com:asseco:aha:training:types:v1\">");
        sb.append("<result>CXF custom value</result>");
        sb.append("</ProviderResponse>").append("</soap:Body>").append("</soap:Envelope>");

        return sb.toString();
    }

}
