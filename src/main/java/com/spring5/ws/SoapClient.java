/*
 * Copyright (C) 2019 Strategic Information Systems, LLC.
 *
 */
package com.spring5.ws;

import java.util.Iterator;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 * @author javaugi
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class SoapClient extends HttpServlet {

    private static final long serialVersionUID = 111211;
    private static final Logger log = LoggerFactory.getLogger(SoapClient.class);

    static MessageFactory messageFactory = null;

    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
        try {
            messageFactory = MessageFactory.newInstance();
        } catch (Exception ex) {
        }
    }

    public SOAPMessage onMessage(SOAPMessage msg) {
        try {
            SOAPPart soappart = msg.getSOAPPart();
            SOAPEnvelope incomingEnvelope = soappart.getEnvelope();
            SOAPBody body = incomingEnvelope.getBody();

            Iterator iterator = body.getChildElements(
                    incomingEnvelope.createName("numberAvailable", "laptops",
                            "http://www.XMLPowerCorp.com"));

            SOAPElement element;
            element = (SOAPElement) iterator.next();

            SOAPMessage message = messageFactory.createMessage();
            SOAPEnvelope envelope = message.getSOAPPart().getEnvelope();

            envelope.getBody().addChildElement(envelope
                    .createName("Response")).addTextNode(
                    "Got the SOAP message indicating there are "
                    + element.getValue()
                    + " laptops available."
            );

            return message;
        } catch (Exception e) {
            return null;
        }
    }
}
