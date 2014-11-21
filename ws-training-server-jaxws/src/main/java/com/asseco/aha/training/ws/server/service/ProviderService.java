package com.asseco.aha.training.ws.server.service;

import java.util.List;

import javax.xml.soap.SOAPHeader;

import org.w3c.dom.Document;

public interface ProviderService {

	public abstract List<String> parseContent(Document doc, SOAPHeader headers);

}