/*
 * Copyright (c) 2021 REPLACE_CUSTOMER_NAME. All rights reserved.
 *
 * This file is part of MyHR.
 *
 * MyHR project and associated code cannot be copied
 * and/or distributed without a written permission of REPLACE_CUSTOMER_NAME,
 * and/or its subsidiaries.
 */
package com.myhr.security;

import com.myhr.security.db.EnableDBAuthentication;
import com.myhr.security.properties.ApplicationSecurityProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
/**
 * Main application class that is responsible to start the db auth service and exposes the functionality over the specified
 * port.
 *
 * @author John Smith
 */
@EnableConfigurationProperties(value ={ApplicationSecurityProperties.class})
@EnableDBAuthentication
@SpringBootApplication
public class ExampleSecurityAuthDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExampleSecurityAuthDbApplication.class, args);
	}

}