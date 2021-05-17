/*
 * Copyright (c) 2020-2021 Innominds inc. All Rights Reserved. This software is
 * confidential and proprietary information of Innominds inc. You shall not disclose
 * Confidential Information and shall use it only in accordance with the terms
 *
 */
package com.myhr.features.platform.data.model.experience.employee;

import com.myhr.features.platform.data.model.experience.dept.Dept;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Implementation of an experience model that is meant to be used by the API Layer for communication
 * either with the front-end or to the service-layer.
 *
 * @author John Smith
 */
@Data
@SuperBuilder
@NoArgsConstructor
public class Employee {
    /** Reference to the id. */
    private Integer id;

    /** Reference to the first_name. */
    private String firstName;

    /** Reference to the last_name. */
    private String lastName;

    /** Reference to the dob. */
    private Date dob;

    /** Reference to the joining_date. */
    private Date joiningDate;

    /** Reference to the dept. */
    private Dept dept;
}
