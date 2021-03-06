/*
 * Copyright (c) 2020-2021 Innominds inc. All Rights Reserved. This software is
 * confidential and proprietary information of Innominds inc. You shall not disclose
 * Confidential Information and shall use it only in accordance with the terms
 *
 */
package com.myhr.features.platform.data.model.experience.user;

import com.myhr.features.platform.data.model.experience.role.Role;
import java.util.Collection;
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
public class User {
    /** Unique identifier of the user. */
    private Integer id;

    /** User name of the user. */
    private String username;

    /** Password of the user. */
    private String password;

    /** Reference to the roles. */
    private Collection<Role> roles;
}
