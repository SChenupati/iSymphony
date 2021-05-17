/*
 * Copyright (c) 2020-2021 Innominds inc. All Rights Reserved. This software is
 * confidential and proprietary information of Innominds inc. You shall not disclose
 * Confidential Information and shall use it only in accordance with the terms
 *
 */
package com.myhr.features.platform.data.repository;

import com.myhr.commons.data.jpa.repository.ExtendedJpaRepository;
import com.myhr.features.platform.data.model.persistence.UserEntity;
import org.springframework.stereotype.Repository;

/**
 * Repository interface to handle the operations pertaining to domain models of type "UserEntity".
 *
 * @author John Smith
 */
@Repository
public interface UserRepository extends ExtendedJpaRepository<UserEntity, Integer> {
    // Any custom methods can be added here.
}
