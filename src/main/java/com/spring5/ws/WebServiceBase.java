/*
 * Copyright (C) 2019 Strategic Information Systems, LLC.
 *
 */
package com.spring5.ws;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FilenameFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 * @author javaugi
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class WebServiceBase {

    private static final Logger log = LoggerFactory.getLogger(WebServiceBase.class);

    public static final ObjectMapper mapper = new ObjectMapper();
    public static final JsonFactory jf = new JsonFactory();
    public JsonGenerator jg;

    public static class UploadFilenameFilter implements FilenameFilter {

        public boolean accept(File dir, String name) {
            return name.endsWith(".xml") || name.endsWith(".XML");
        }
    }

}
