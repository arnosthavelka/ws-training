package com.asseco.aha.training.ws.server.service;

import java.util.ArrayList;
import java.util.List;

import javax.xml.soap.SOAPHeader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.asseco.aha.training.ws.server.ws.ProviderWebService;
import com.asseco.aha.training.ws.server.ws.util.XmlProcessor;

@Service
public class ProviderServiceImpl implements ProviderService {

	/**
	 * Class logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(ProviderWebService.class);

	@Autowired
	private XmlProcessor xp;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.asseco.aha.training.ws.server.service.ProviderService#parseContent(org.w3c.dom.Document,
	 * javax.xml.soap.SOAPHeader)
	 */
	@Override
	public List<String> parseContent(Document doc, SOAPHeader headers) {
		List<String> response = new ArrayList<String>();

		//

		processHeaders(doc, headers, response);
		processValues(doc, response);

		return response;
	}

	private void processHeaders(Document doc, SOAPHeader headers, List<String> response) {

		List<Element> nodes = xp.applyXpath("//*[local-name(.)='ExtendedProviderRequest']", doc.getDocumentElement());
		if (nodes == null || nodes.size() == 0) {
			LOG.debug("provider echo called ...");
			return;
		}
		LOG.debug("provider echoExtended called ...");
		Element parent = nodes.get(0);
		String attValue = parent.getAttribute("showHeaders");
		boolean showHeaders = attValue == null ? false : Boolean.parseBoolean(attValue);
		if (!showHeaders) {
			return;
		}
		LOG.debug("processing soap headers ...");
		List<Element> headerNodes = xp.applyXpath("//*[local-name(.)='Header']/*", doc.getDocumentElement());
		for (Element element : headerNodes) {
			response.add(String.format("header: %s=%s", element.getNodeName(), element.getTextContent()));
		}
	}

	private void processValues(Document doc, List<String> response) {
		List<Element> nodes = xp.applyXpath("//*[local-name(.)='value']", doc.getDocumentElement());
		for (Element element : nodes) {
			response.add(String.format("value=%s", element.getTextContent()));
		}
	}
}
