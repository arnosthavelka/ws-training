package com.asseco.aha.training.ws.server.ws.util;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class XmlProcessorImpl implements XmlProcessor {

    private TransformerFactory tf = TransformerFactory.newInstance();
    private XPathFactory xf;
    private DocumentBuilderFactory tbf;

    @PostConstruct
    private void init() {
        tf = TransformerFactory.newInstance();
        xf = XPathFactory.newInstance();
        tbf = DocumentBuilderFactory.newInstance();
    }

    private Transformer createTransformer() throws TransformerConfigurationException {
        Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        return transformer;
    }

    @Override
    public String convert2String(Source source) {
        try {
            StringWriter sw = new StringWriter();
            Transformer transformer = createTransformer();

            transformer.transform(source, new StreamResult(sw));
            return sw.toString();
        } catch (Exception ex) {
			log.error("Error reading XML (from SOAP message)!", ex);
            return "N/A";
        }
    }

    @Override
    public Document convert2dom(Source source) {
        try {
            DOMResult dom = new DOMResult();
            Transformer transformer = createTransformer();

            transformer.transform(source, dom);
            return (Document) dom.getNode();
        } catch (Exception ex) {
			log.error("Error in converting XML (from SOAP message) to to DOM!", ex);
            return null;
        }
    }

    @Override
    public Document convert2dom(String source) {
        try {
            DocumentBuilder builder = tbf.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(source)));
            return doc;
        } catch (Exception e) {
			log.error("Error in converting XML (from SOAP message) to to DOM!", e);
			return null;
        }
    }

    @Override
    public List<Element> applyXpath(String xpath, Element element) {
        List<Element> response = new ArrayList<Element>();

        XPath xPath = xf.newXPath();
        try {
            NodeList nodes = (NodeList) xPath.evaluate(xpath, element, XPathConstants.NODESET);
            for (int i = 0; i < nodes.getLength(); ++i) {
                response.add((Element) nodes.item(i));
            }
			return response;
        } catch (XPathExpressionException e) {
            throw new RuntimeException("Unexpected Xpath processing error!", e);
        }
    }
}
