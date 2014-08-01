package com.asseco.aha.training.ws.server.ws.util;

import javax.xml.transform.Source;

import org.w3c.dom.Node;

public interface XmlProcessor {

	public abstract String convert2String(Source source);

	public abstract Node convert2dom(Source source);

}