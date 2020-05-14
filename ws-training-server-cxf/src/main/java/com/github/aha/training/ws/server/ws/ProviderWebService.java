package com.github.aha.training.ws.server.ws;

import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.ws.Provider;
import javax.xml.ws.WebServiceProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.aha.training.ws.common.XmlProcessor;

import lombok.extern.slf4j.Slf4j;

@Component("providerBean")
@javax.xml.ws.ServiceMode(value = javax.xml.ws.Service.Mode.PAYLOAD)
@WebServiceProvider
@Slf4j
public class ProviderWebService implements Provider<Source> {

    @Autowired
    private XmlProcessor xp;

    @Override
    public Source invoke(Source request) {
		log.debug("Provider invoke started ...");

        String response = prepareResponse();
		log.debug(response);

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
