package com.asseco.aha.training.ws.server.ws;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.ws.Provider;
import javax.xml.ws.WebServiceProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Node;

import com.asseco.aha.training.ws.server.ws.util.XmlProcessor;

@javax.xml.ws.ServiceMode(value = javax.xml.ws.Service.Mode.MESSAGE)
@WebServiceProvider(wsdlLocation = "wsdl/ws-training-provider.wsdl", serviceName = "WsTrainingProviderService", portName = "WsTrainingProviderPort", targetNamespace = "urn:com:asseco:aha:training:ws:v1")
public class ProviderWebService extends AbstractSoapWebService implements Provider<SOAPMessage> {

	/**
	 * Class logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(ProviderWebService.class);

	@Autowired
	private XmlProcessor xp;

	@Override
	public SOAPMessage invoke(SOAPMessage request) {
		LOG.debug("Provider invoke started ...");
		try {
			SOAPPart soapPart = request.getSOAPPart();
			Node node = xp.convert2dom(soapPart.getContent());
			// node.getParentNode()
			LOG.debug(node.toString());
		} catch (SOAPException e) {
			LOG.error("Error in processing request", e);
		}

		// TODO Auto-generated method stub
		return null;
	}
}
