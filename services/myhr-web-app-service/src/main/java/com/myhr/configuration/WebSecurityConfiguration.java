/*
 * Copyright (c) 2020-2021 Innominds inc. All Rights Reserved. This software is
 * confidential and proprietary information of Innominds inc. You shall not disclose
 * Confidential Information and shall use it only in accordance with the terms
 *
 */
package com.myhr.features.platform.web.client;

import com.myhr.commons.web.filter.JwtAuthenticationFilter;
import com.myhr.security.db.configuration.AbstractDbAuthWebSecurityConfigurerAdapter;
import com.myhr.security.properties.ApplicationSecurityProperties;
import com.myhr.security.token.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * Configuration class responsible to configure the security aspects for the microservice /
 * application in consideration.
 *
 * @author John Smith
 */
@Slf4j
@Order(SecurityProperties.BASIC_AUTH_ORDER - 2)
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Configuration
public class WebSecurityConfiguration extends AbstractDbAuthWebSecurityConfigurerAdapter {
    /**
     * Constructor.
     *
     * @param applicationSecurityProperties Configurable properties instance of type {@link
     *     ApplicationSecurityProperties}.
     * @param jwtAuthenticationFilter Filter instance of type {@link JwtAuthenticationFilter}.
     */
    public WebSecurityConfiguration(
            final ApplicationSecurityProperties applicationSecurityProperties,
            final JwtAuthenticationFilter jwtAuthenticationFilter,
            final JwtTokenProvider jwtTokenProvider) {
        super(applicationSecurityProperties, jwtAuthenticationFilter, jwtTokenProvider);
    }
}
