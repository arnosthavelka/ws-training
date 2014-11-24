package com.asseco.aha.training.ws.server.ws.util;

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

public class WsLoggingHandler implements SOAPHandler<SOAPMessageContext> {

	/**
	 * Basic class logger.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(WsLoggingHandler.class);
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
		LOG.info("Spring initialization ...");
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	@Override
	public boolean handleMessage(SOAPMessageContext context) {
		log(context);

		// continue other handler chain
		return true;
	}

	private void log(SOAPMessageContext messageContext) {
		if (LOG.isTraceEnabled()) {
			LOG.trace("Server : handleMessage()......");
		}

		try {
			createLoggers();
			boolean isResponse = ((Boolean) messageContext.get(SOAPMessageContext.MESSAGE_OUTBOUND_PROPERTY))
					.booleanValue();

			Logger logger = isResponse ? loggerResponse : loggerRequest;
			if (!logger.isDebugEnabled()) {
				return;
			}

			SOAPMessage msg = messageContext.getMessage();

			logger.debug(isResponse ? responseString : requestString, xp.convert2String(msg.getSOAPPart().getContent()));

		} catch (Exception ex) {
			LOG.error("WS content logging error", ex);
		}
	}

	@Override
	public boolean handleFault(SOAPMessageContext context) {
		LOG.trace("Server : handleFault()......");
		return true;
	}

	@Override
	public void close(MessageContext context) {
		LOG.trace("Server : close()......");

	}

	@Override
	public Set<QName> getHeaders() {
		LOG.trace("Server : getHeaders()......");
		return null;
	}

	private static final String LOGGER_PREFIX = "com.asseco.aha.wslogger.abc";

	private void createLoggers() {
		if (loggerRequest == null || loggerResponse == null) {
			LOG.info("Creating loggers: " + LOGGER_PREFIX + ".request and " + LOGGER_PREFIX + ".response");
			loggerRequest = LoggerFactory.getLogger(LOGGER_PREFIX + ".request");
			loggerResponse = LoggerFactory.getLogger(LOGGER_PREFIX + ".response");
		}
	}

}
