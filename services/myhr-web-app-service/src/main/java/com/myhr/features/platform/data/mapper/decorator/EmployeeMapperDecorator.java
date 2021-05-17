/*
 * Copyright (c) 2020-2021 Innominds inc. All Rights Reserved. This software is
 * confidential and proprietary information of Innominds inc. You shall not disclose
 * Confidential Information and shall use it only in accordance with the terms
 *
 */
package com.myhr.features.platform.data.mapper.decorator;

import com.myhr.features.platform.data.mapper.DeptMapper;
import com.myhr.features.platform.data.mapper.EmployeeMapper;
import com.myhr.features.platform.data.model.experience.employee.CreateEmployeeRequest;
import com.myhr.features.platform.data.model.experience.employee.Employee;
import com.myhr.features.platform.data.model.experience.employee.UpdateEmployeeRequest;
import com.myhr.features.platform.data.model.persistence.EmployeeEntity;
import com.myhr.features.platform.data.repository.DeptRepository;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Decorator implementation that maps / transforms data from an instance of type {@link EmployeeEntity to {@link Employee and vice-versa.
 *
 * @author John Smith
 */
@Slf4j
public abstract class EmployeeMapperDecorator implements EmployeeMapper {

    /** Mapper implementation of type {@link EmployeeMapper}. */
    @Autowired private EmployeeMapper employeeMapper;

    /** Repository implementation of type {@link DeptRepository}. */
    @Autowired private DeptRepository deptRepository;

    /** Mapper implementation of type {@link DeptMapper}. */
    @Autowired private DeptMapper deptMapper;

    @Override
    public EmployeeEntity transform(final CreateEmployeeRequest source) {
        // 1. Transform the CreateEmployeeRequest to EmployeeEntity object.
        final EmployeeEntity employee = employeeMapper.transform(source);

        if (Objects.nonNull(source.getDept())) {
            employee.setDept(deptRepository.findByIdOrThrow(source.getDept()));
        }
        // Return the transformed object.
        return employee;
    }

    @Override
    public Employee transform(final EmployeeEntity source) {
        // 1. Transform the EmployeeEntity to Employee object.
        final Employee employee = employeeMapper.transform(source);

        if (Objects.nonNull(source.getDept())) {
            employee.setDept(deptMapper.transform(source.getDept()));
        }
        // Return the transformed object.
        return employee;
    }

    @Override
    public void transform(
            final UpdateEmployeeRequest source, final @MappingTarget EmployeeEntity target) {

        // Transform from source to the target.
        employeeMapper.transform(source, target);

        if (Objects.nonNull(source.getDept())) {
            target.setDept(deptRepository.findByIdOrThrow(source.getDept()));
        }
    }

    @Override
    public EmployeeEntity transform(final UpdateEmployeeRequest source) {

        // Transform from source to the target.
        final EmployeeEntity employee = employeeMapper.transform(source);

        if (Objects.nonNull(source.getDept())) {
            employee.setDept(deptRepository.findByIdOrThrow(source.getDept()));
        }
        // Return the response.
        return employee;
    }
}
