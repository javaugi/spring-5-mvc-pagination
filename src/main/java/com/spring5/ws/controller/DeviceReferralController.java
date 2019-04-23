/*
 * Copyright (C) 2019 Strategic Information Systems, LLC.
 *
 */
package com.spring5.ws.controller;

import com.spring5.ws.WebServiceBase;
import com.spring5.ws.beans.DeviceReferralJsonParam;
import com.spring5.ws.beans.DeviceReferralValueObject;
import com.spring5.ws.beans.ReturnValueObject;
import java.io.StringWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

/**
 *
 *
 * @author javaugi
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
@RequestMapping("/devicereferral")
public class DeviceReferralController extends WebServiceBase {

    private static final Logger log = LoggerFactory.getLogger(DeviceReferralController.class);

    @RequestMapping("test")
    public String test() {
        ReturnValueObject returnValue = new ReturnValueObject();
        return returnValue.toString();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String iPhoneReferral(String json) {
        try {
            log.error("DeviceReferralController json=" + json);
            DeviceReferralJsonParam jsonParam = new DeviceReferralJsonParam(json);
            return processDeviceReferral(jsonParam.getDrvo(), json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ReturnValueObject returnValue = new ReturnValueObject();
        returnValue.setReturnValue("false");
        return returnValue.toString();
    }

    @RequestMapping("referralgetj")
    public String iPhoneReferralGet(@RequestParam("queryParam") DeviceReferralJsonParam jsonParam) {
        try {
            log.error("DeviceReferralController.iPhoneReferralGet jsonParam=" + jsonParam);
            return processDeviceReferral(jsonParam.getDrvo(), jsonParam.getJson());
        } catch (Exception e) {
            e.printStackTrace();
        }
        ReturnValueObject returnValue = new ReturnValueObject();
        returnValue.setReturnValue("false");
        return returnValue.toString();
    }

    @PostMapping("referralpostj")
    public String iPhoneReferralPost(String json) {
        try {
            log.error("DeviceReferralController.iPhoneReferralPost json=" + json);
            DeviceReferralJsonParam jsonParam = new DeviceReferralJsonParam(json);
            return processDeviceReferral(jsonParam.getDrvo(), json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ReturnValueObject returnValue = new ReturnValueObject();
        returnValue.setReturnValue("false");
        return returnValue.toString();
    }

    private String processDeviceReferral(DeviceReferralValueObject drvo, String json) {
        /*
		 * https://spancmost.spancorp.com/span/
		 * String url = BaseSBean.getServerContext() + "services/devicereg/iphonerefpostj"
         */

        ReturnValueObject returnValue = new ReturnValueObject();
        try {
            String filename = "filename.txt";
            //saveToFile(filename, json);

            if ((drvo == null) || StringUtils.isEmpty(json)) {
                returnValue.setReturnValue("false");
                log.error("processDeviceReferral rdvo is null");
                return returnValue.toString();
            }

            if (StringUtils.isEmpty(drvo.getReferralSpanUid()) || StringUtils.isEmpty(drvo.getReferralSpanPwd())) {
                returnValue.setReturnValue("false");
                log.error("processDeviceReferral invalid uid or pwd uid=" + drvo.getReferralSpanUid() + "-pwd=" + drvo.getReferralSpanPwd());
                return returnValue.toString();
            }
            if (!"formFOX!Pwd4uid@2013".equals(drvo.getReferralSpanUid())
                    || !"formFOX!Pwd4uid@2013".equals(drvo.getReferralSpanPwd())) {
                returnValue.setReturnValue("false");
                log.error("processDeviceReferral invalid uid or pwd uid=" + drvo.getReferralSpanUid() + "-pwd=" + drvo.getReferralSpanPwd());
                return returnValue.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnValue.setReturnValue("false");
            log.error("processDeviceReferral exception=" + e.getMessage());
            return returnValue.toString();
        }

        return returnValue.toString();
    }

    @RequestMapping("producereferral")
    public String produceReferral() {

        StringBuffer sb = new StringBuffer();
        try {
            DeviceReferralValueObject drvo = new DeviceReferralValueObject();
            drvo.setReferralSpanUid("uid");
            drvo.setReferralSpanPwd("pwd");

            drvo.setClientName("John Doe");
            drvo.setClientEmail("john.doe@yahoo.com");
            drvo.setDateOfBirth("01/10/1990");
            drvo.setLast4SSN("1234");
            drvo.setRecipientID("RecipientId123");
            drvo.setCaseNumber("Case1111");
            drvo.setLoadNumber("load1234");
            drvo.setLogNumber("log1234");
            drvo.setStartDate("05/01/2012");
            drvo.setEndDate("01/31/2013");
            drvo.setPhone("734-222-2222");

            drvo.setStreetAddress("1 White St");
            drvo.setCity("Ann Arbor");
            drvo.setState("MI");
            drvo.setZip("48224");

            drvo.setSelectedTests("Span Ultra 18 Panel (Stand Alone)");
            drvo.setNumberOfScreenings("2");
            drvo.setTimesPeriod("7");
            drvo.setPeriodUnit("Days");

            drvo.setCaseWorkerEmail("javaugi@yahoo.com");
            drvo.setCaseWorkerFax("800-266-2666");
            drvo.setCaseWorkerName("Joe Doe");
            drvo.setCaseWorkerPhone("800-266-2667");
            drvo.setAgencyName("Span Corporation");
            drvo.setReferringCounty("Washtenaw");
            drvo.setNotes("Referral from Joe Doe");
            drvo.setManageType("Random Ongoing Testing");

            StringWriter sw = new StringWriter();
            jg = jf.createJsonGenerator(sw);
            mapper.writeValue(jg, drvo);
            sb.append(sw.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    @RequestMapping("producepartref")
    public String producePartReferral() {

        StringBuffer sb = new StringBuffer();
        try {
            DeviceReferralValueObject drvo = new DeviceReferralValueObject();
            drvo.setReferralSpanUid("uid");
            drvo.setReferralSpanPwd("pwd");

            drvo.setClientName("");
            drvo.setSelectedTests("EtG Urine Test (Stand Alone)");
            drvo.setPeriodUnit("Days");

            drvo.setCaseWorkerFax("800-266-2666");
            drvo.setCaseWorkerName("Dale Kozio");
            drvo.setCaseWorkerName("koziol@arbormoon.com");

            StringWriter sw = new StringWriter();
            jg = jf.createJsonGenerator(sw);
            mapper.writeValue(jg, drvo);
            sb.append(sw.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sb.toString();
    }
}
