package com.github.aha.training.ws.common;

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

	/**
	 * Apply Xpath expression on DOM element to retrieve particular elements.
	 * 
	 * @param xpath
	 *            Xpatch expression (as string)
	 * @param element
	 *            instance of <code>Element</code> (to apply to)
	 * @return list of found elements (instance of <code>Element</code>)
	 */
	List<Element> applyXpath(String xpath, Element element);

}