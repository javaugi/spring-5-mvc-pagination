/*
 * Copyright (C) 2019 Strategic Information Systems, LLC.
 *
 */
package com.spring5.ws.beans;

import java.io.Serializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 * @author javaugi
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class ReturnValueObject implements Serializable {

    private static final long serialVersionUID = 111211;
    private static final Logger log = LoggerFactory.getLogger(ReturnValueObject.class);

    private String returnValue = "True";

    public String getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(String returnValue) {
        this.returnValue = returnValue;
    }

    @Override
    public String toString() {
        return "{\"returnValue\":\"" + getReturnValue() + "\"}";
    }

    public String toXmlString() {
        return "<returnValue>" + returnValue + "</returnValue>";
    }
}
