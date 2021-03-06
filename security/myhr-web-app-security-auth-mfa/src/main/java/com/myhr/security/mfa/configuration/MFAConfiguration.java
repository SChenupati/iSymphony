/*
 * Copyright (c) 2021 REPLACE_CUSTOMER_NAME. All rights reserved.
 *
 * This file is part of MyHR.
 *
 * MyHR project and associated code cannot be copied
 * and/or distributed without a written permission of REPLACE_CUSTOMER_NAME,
 * and/or its subsidiaries.
 */
package com.myhr.security.mfa.configuration;


import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.myhr.security.mfa.EnableMFA;
import com.myhr.security.mfa.configuration.properties.TotpProperties;

/**
 * Configuration class that is responsible for configuration of the beans required for Multi-Factor Authentication (i.e.
 * MFA).
 * <p>
 * There are two ways in which an application can leverage MFA functionality.
 * <p>
 * First approach involves using the @{@link org.springframework.context.annotation.Import} annotation and provide this
 * configuration class to initialize the beans required for MFA functionality.
 * <p>
 * Second approach is to use the @{@link EnableMFA} annotation, which takes care of initializing the necessary beans.
 *
 * @author John Smith
 */
@EnableConfigurationProperties(TotpProperties.class)
@ComponentScan(basePackageClasses = {EnableMFA.class})
@Configuration
public class MFAConfiguration {
}
