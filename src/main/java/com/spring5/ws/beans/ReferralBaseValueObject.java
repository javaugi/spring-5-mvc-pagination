/*
 * Copyright (C) 2019 Strategic Information Systems, LLC.
 *
 */
package com.spring5.ws.beans;

import java.io.Serializable;
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
public class ReferralBaseValueObject implements Serializable {

    private static final long serialVersionUID = 111;
    private static final Logger log = LoggerFactory.getLogger(ReferralBaseValueObject.class);

    private String clientName = ""; //: 	Jack J Jones
    private String streetAddress = ""; //: 	1234 Main
    private String city = "";
    private String state = "";
    private String zip = "";
    //Ann Arbo, Mi 48105
    private String dateOfBirth = ""; //: 	2/10/93
    private String last4SSN = ""; //: 	5522
    private String phone = ""; //: 	7345551212
    private String clientPhone = ""; //:
    private String clientEmail = ""; //: 	joe@aol.com
    private String caseNumber = ""; //: 	giitdccxvj
    private String logNumber = ""; //: 	ugxcjhfr
    private String recipientID = ""; //: 	Fcbjgss
    private String selectedTests = ""; //:Span Ultra 18 Panel (Stand Alone)
    private String numberOfScreenings = "";
    private String timesPeriod = "";  //2 Tests Per 7 Period, 2 Tests Per 7 Period,7 Days Period
    private String periodUnit = "";
    private String startDate = ""; //: 	2/10/13
    private String endDate = ""; //: 	2/10/13
    private String manageType = "";

    //Case Worker:
    private String caseWorkerName = ""; //: 	Mike Grosh
    private String caseWorkerPhone = ""; //: 	7346237726
    private String caseWorkerFax = ""; //: 	7347696268
    private String agencyName = ""; //: 	Span Corporation
    private String referringCounty = ""; //: 	Washtenaw
    private String loadNumber = ""; //: 	12345678
    private String caseWorkerEmail = "";
    private String notes = "";

    private String addressContinued = "";
    private String clientNameFirst = "";
    private String clientNameMiddle = "";
    private String clientNameLast = "";

}
