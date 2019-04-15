/*
 * Copyright (C) 2019 Strategic Information Systems, LLC.
 *
 */
package com.spring5.ws.beans;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 * @author javaugi
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
@Data
public class DeviceReferralValueObject extends ReferralBaseValueObject {

    private static final long serialVersionUID = 111333;
    private static final Logger log = LoggerFactory.getLogger(DeviceReferralValueObject.class);

    private String referralSpanUid = ""; //referralUid13
    private String referralSpanPwd = ""; //devRef!uid@2013
}
