/*
 * Copyright (c) 2021 REPLACE_CUSTOMER_NAME. All rights reserved.
 *
 * This file is part of MyHR.
 *
 * MyHR project and associated code cannot be copied
 * and/or distributed without a written permission of REPLACE_CUSTOMER_NAME,
 * and/or its subsidiaries.
 */
package com.myhr.commons.data.jpa.persistence;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.myhr.commons.data.persistence.ISoftDeletable;

/**
 * Abstract implementation of an entity that supports soft-deletion functionality for an entity whose primary key is
 * auto-generated.
 *
 * @author John Smith
 */
@ToString(callSuper = true, of = {})
@EqualsAndHashCode(callSuper = true, of = {})
@Getter
@Setter
@MappedSuperclass
public abstract class AbstractSoftDeletableUUIDGeneratedEntity extends AbstractUUIDGeneratedEntity implements ISoftDeletable {
    /** Boolean to indicate if this record is deleted or not. */
    @Column(name = "deleted")
    private boolean deleted;

    /** Timestamp in epoch format indicating when the record was deleted. */
    @Column(name = "deleted_ts")
    private Long deletedTimestamp;
}