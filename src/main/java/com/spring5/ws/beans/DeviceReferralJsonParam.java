/*
 * Copyright (C) 2019 Strategic Information Systems, LLC.
 *
 */
package com.spring5.ws.beans;

import com.spring5.ws.WebServiceBase;
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
public class DeviceReferralJsonParam extends WebServiceBase implements Serializable {

    private static final Logger log = LoggerFactory.getLogger(DeviceReferralJsonParam.class);
    private static final long serialVersionUID = 111;

    private DeviceReferralValueObject drvo;
    private String json;

    public DeviceReferralJsonParam(String json) {

        log.debug("DeviceReferralJsonParam json=" + json);
        //JSONObject obj = null;
        try {
            this.json = json;
            drvo = new DeviceReferralValueObject();
            drvo = mapper.readValue(json, DeviceReferralValueObject.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        log.debug("DeviceReferralJsonParam DeviceReferralValueObject=");
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public DeviceReferralValueObject getDrvo() {
        return drvo;
    }

    public void setDrvo(DeviceReferralValueObject drvo) {
        this.drvo = drvo;
    }
}
