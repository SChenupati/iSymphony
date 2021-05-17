/*
 * Copyright (c) 2020-2021 Innominds inc. All Rights Reserved. This software is
 * confidential and proprietary information of Innominds inc. You shall not disclose
 * Confidential Information and shall use it only in accordance with the terms
 *
 */
package com.myhr.features.platform.data.model.experience.employee;

import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
public class UpdateEmployeeRequest {
    /** Reference to the id. */
    @NotNull(message = "employee.id.not.null.message")
    private Integer id;

    /** Reference to the first_name. */
    @NotBlank(message = "employee.firstName.not.blank.message")
    @Size(max = 20, message = "employee.firstName.size.message")
    private String firstName;

    /** Reference to the last_name. */
    @NotBlank(message = "employee.lastName.not.blank.message")
    @Size(max = 20, message = "employee.lastName.size.message")
    private String lastName;

    /** Reference to the dob. */
    @NotNull(message = "employee.dob.not.null.message")
    private Date dob;

    /** Reference to the joining_date. */
    @NotNull(message = "employee.joiningDate.not.null.message")
    private Date joiningDate;

    /** Reference to the dept. */
    @NotNull(message = "employee.dept.not.null.message")
    private Integer dept;
}
