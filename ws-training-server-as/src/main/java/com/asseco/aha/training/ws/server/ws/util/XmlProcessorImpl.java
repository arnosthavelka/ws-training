package com.asseco.aha.training.ws.server.ws.util;

import java.io.StringWriter;

import javax.annotation.PostConstruct;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.stream.StreamResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.w3c.dom.Node;

@Service
public class XmlProcessorImpl implements XmlProcessor {

	/**
	 * Class logger.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(XmlProcessorImpl.class);

	private static TransformerFactory tf = TransformerFactory.newInstance();

	@PostConstruct
	private void init() {
		tf = TransformerFactory.newInstance();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.asseco.aha.training.ws.server.ws.util.XmlProcessor#source2String(javax.xml.transform.Source)
	 */
	@Override
	public String convert2String(Source source) {
		try {
			StringWriter sw = new StringWriter();
			Transformer transformer = createTransformer();

			transformer.transform(source, new StreamResult(sw));
			return sw.toString();
		} catch (Exception ex) {
			LOG.error("Error reading XML (from SOAP message)!", ex);
			return "N/A";
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.asseco.aha.training.ws.server.ws.util.XmlProcessor#source2dom(javax.xml.transform.Source)
	 */
	@Override
	public Node convert2dom(Source source) {
		try {
			DOMResult dom = new DOMResult();
			Transformer transformer = createTransformer();

			transformer.transform(source, dom);
			Node node = dom.getNode();
			return node;
		} catch (Exception ex) {
			LOG.error("Error in converting XML (from SOAP message) to to DOM!", ex);
			return null;
		}
	}

	private Transformer createTransformer() throws TransformerConfigurationException {
		Transformer transformer = tf.newTransformer();
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
		transformer.setOutputProperty(OutputKeys.METHOD, "xml");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		return transformer;
	}

}
