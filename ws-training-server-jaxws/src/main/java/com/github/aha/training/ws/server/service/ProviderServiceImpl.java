package com.github.aha.training.ws.server.service;

import java.util.ArrayList;
import java.util.List;

import javax.xml.soap.SOAPHeader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.github.aha.training.ws.server.ws.util.XmlProcessor;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProviderServiceImpl implements ProviderService {

	@Autowired
	private XmlProcessor xp;

	@Override
	public List<String> parseContent(Document doc, SOAPHeader headers) {
		List<String> response = new ArrayList<>();

		processHeaders(doc, response);
		processValues(doc, response);

		return response;
	}

	private void processHeaders(Document doc, List<String> response) {

		List<Element> nodes = xp.applyXpath("//*[local-name(.)='ExtendedProviderRequest']", doc.getDocumentElement());
		if (nodes == null || nodes.isEmpty()) {
			log.debug("provider echo called ...");
			return;
		}
		log.debug("provider echoExtended called ...");
		Element parent = nodes.get(0);
		String attValue = parent.getAttribute("showHeaders");
		boolean showHeaders = attValue == null ? false : Boolean.parseBoolean(attValue);
		if (!showHeaders) {
			return;
		}
		log.debug("processing soap headers ...");
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
