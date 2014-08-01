package com.asseco.aha.training.ws.server.ws.util;

import java.util.List;

import javax.xml.transform.Source;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public interface XmlProcessor {

	/**
	 * Transform XML source to string (retrieve XML from the source).
	 * 
	 * @param source
	 *            instance of <code>Source</code>
	 * @return XML as string
	 */
	String convert2String(Source source);

	/**
	 * Transform XML source to DOM.
	 * 
	 * @param source
	 *            instance of <code>Source</code>
	 * @return XML as instance of <code>Document</code>
	 */
	Document convert2dom(Source source);

	List<Element> applyXpath(String xpath, Element element);

}