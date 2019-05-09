/*
 * Copyright (C) 2019 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.spring5;

import com.example.springsocial.config.AppProperties;
import static com.spring5.MockAutoConfiguration.DEFAULT_PROFILE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 *
 *
 * @author david
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
@Profile(DEFAULT_PROFILE)
@Configuration
public class MockAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(MockAutoConfiguration.class);
    public static final String DEFAULT_PROFILE = "default";

//    @Bean
//    public AppProperties getAppProperties() {
//        return new AppProperties();
//    }
}
