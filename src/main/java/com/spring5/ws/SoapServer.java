/*
 * Copyright (C) 2019 Strategic Information Systems, LLC.
 *
 */
package com.spring5.ws;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import javax.activation.DataHandler;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.soap.AttachmentPart;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPHeader;
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
public class SoapServer extends HttpServlet {

    private static final long serialVersionUID = 111211;
    private static final Logger log = LoggerFactory.getLogger(SoapServer.class);

    private SOAPConnection connection;

    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);

        try {
            SOAPConnectionFactory connectionFactory
                    = SOAPConnectionFactory.newInstance();
            connection = connectionFactory.createConnection();
        } catch (Exception e) {
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        String outString = "<HTML><H1>Sending and reading the SOAP Message</H1><P>";

        try {
            MessageFactory messageFactory = MessageFactory.newInstance();
            SOAPMessage outgoingMessage = messageFactory.createMessage();

            SOAPPart soappart = outgoingMessage.getSOAPPart();
            SOAPEnvelope envelope = soappart.getEnvelope();
            SOAPHeader header = envelope.getHeader();
            SOAPBody body = envelope.getBody();

            body.addBodyElement(envelope.createName("numberAvailable", "laptops",
                    "http://www.XMLPowerCorp.com")).addTextNode("216");

            StringBuffer serverUrl = new StringBuffer();
            serverUrl.append(request.getScheme()).append("://").
                    append(request.getServerName());
            serverUrl.append(":").append(request.getServerPort()).
                    append(request.getContextPath());
            String baseUrl = serverUrl.toString();
            URL url = new URL(baseUrl + "/ch18_06.html");

            AttachmentPart attachmentpart = outgoingMessage.
                    createAttachmentPart(new DataHandler(url));
            attachmentpart.setContentType("text/html");
            outgoingMessage.addAttachmentPart(attachmentpart);

            URL client = new URL(baseUrl + "/ch18_05");

            FileOutputStream outgoingFile = new FileOutputStream("out.msg");
            outgoingMessage.writeTo(outgoingFile);
            outgoingFile.close();

            outString += "SOAP outgoingMessage sent (see out.msg). <BR>";

            SOAPMessage incomingMessage = connection.
                    call(outgoingMessage, client);

            if (incomingMessage != null) {
                FileOutputStream incomingFile = new FileOutputStream("in.msg");
                incomingMessage.writeTo(incomingFile);
                incomingFile.close();
                outString
                        += "SOAP outgoingMessage received (see in.msg).</HTML>";
            }

        } catch (Throwable e) {
        }

        try {
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(outString.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
        }
    }
}
