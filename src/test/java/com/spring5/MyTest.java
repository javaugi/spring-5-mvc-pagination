/*
 * Copyright (C) 2019 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.spring5;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.util.StringUtils;

/**
 *
 *
 * @author david
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class MyTest {

    private static final Logger log = LoggerFactory.getLogger(MyTest.class);

    @Test
    public void contextLoads() {
        List<String> list = new ArrayList();
        list.add("Test");
        list.add("Test2");
        list.add("Test3");

        String inClause = "(" + StringUtils.join(list, ",").replaceAll("(\\w+)", "'$1'") + ")";
        log.debug("inClause {}", inClause);
    }
}
