package com.github.aha.training.ws.common;

import static java.util.Collections.emptySet;
import static javax.xml.ws.handler.MessageContext.MESSAGE_OUTBOUND_PROPERTY;

import java.util.Set;

import javax.annotation.PostConstruct;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WsLoggingHandler implements SOAPHandler<SOAPMessageContext> {

	private static String requestString = "\"\n******************************** WS payload ( REQUEST ) ************************************ \n"
			+ "{}\n******************************** WS payload END ( REQEUST ) ********************************";
	private static String responseString = "\"\n******************************** WS payload ( RESPONSE ) ************************************ \n"
			+ "{}\n******************************** WS payload END ( RESPONSE ) ********************************";
	private static Logger loggerRequest = null;
	private static Logger loggerResponse = null;

	@Autowired
	private XmlProcessor xp;

	@PostConstruct
	private void init() {
		log.info("Spring initialization ...");
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	@Override
	public boolean handleMessage(SOAPMessageContext context) {
		log(context);

		// continue other handler chain
		return true;
	}

	private void log(SOAPMessageContext messageContext) {
		log.trace("Server : handleMessage()......");

		try {
			createLoggers();
			boolean isResponse = ((Boolean) messageContext.get(MESSAGE_OUTBOUND_PROPERTY)).booleanValue();

			Logger logger = isResponse ? loggerResponse : loggerRequest;
			if (!logger.isDebugEnabled()) {
				return;
			}

			SOAPMessage msg = messageContext.getMessage();

			logger.debug(isResponse ? responseString : requestString, xp.convert2String(msg.getSOAPPart().getContent()));

		} catch (Exception ex) {
			log.error("WS content logging error", ex);
		}
	}

	@Override
	public boolean handleFault(SOAPMessageContext context) {
		log.trace("Server : handleFault()......");
		return true;
	}

	@Override
	public void close(MessageContext context) {
		log.trace("Server : close()......");
	}

	@Override
	public Set<QName> getHeaders() {
		log.trace("Server : getHeaders()......");
		return emptySet();
	}

	private static final String LOGGER_PREFIX = "com.asseco.aha.wslogger.abc";

	private static void createLoggers() {
		if (loggerRequest == null || loggerResponse == null) {
			log.info("Creating loggers: " + LOGGER_PREFIX + ".request and " + LOGGER_PREFIX + ".response");
			loggerRequest = LoggerFactory.getLogger(LOGGER_PREFIX + ".request");
			loggerResponse = LoggerFactory.getLogger(LOGGER_PREFIX + ".response");
		}
	}

}
