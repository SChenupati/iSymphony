/*
 * Copyright (c) 2020-2021 Innominds inc. All Rights Reserved. This software is
 * confidential and proprietary information of Innominds inc. You shall not disclose
 * Confidential Information and shall use it only in accordance with the terms
 *
 */
package com.myhr.features.platform.web.service;

import com.myhr.commons.data.utils.PageUtils;
import com.myhr.commons.exception.ServiceException;
import com.myhr.commons.instrumentation.Instrument;
import com.myhr.error.Errors;
import com.myhr.features.platform.data.mapper.EmployeeMapper;
import com.myhr.features.platform.data.model.experience.employee.CreateEmployeeRequest;
import com.myhr.features.platform.data.model.experience.employee.Employee;
import com.myhr.features.platform.data.model.experience.employee.UpdateEmployeeRequest;
import com.myhr.features.platform.data.model.persistence.DeptEntity;
import com.myhr.features.platform.data.model.persistence.EmployeeEntity;
import com.myhr.features.platform.data.repository.DeptRepository;
import com.myhr.features.platform.data.repository.EmployeeRepository;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

/**
 * Service implementation that provides CRUD (Create, Read, Update, Delete) capabilities for
 * entities of type {@link EmployeeEntity}.
 *
 * @author John Smith
 */
@Slf4j
@Validated
@Service
public class EmployeeService {

    /** Repository implementation of type {@link EmployeeRepository}. */
    private final EmployeeRepository employeeRepository;

    /**
     * Mapper implementation of type {@link EmployeeMapper} to transform between different types.
     */
    private final EmployeeMapper employeeMapper;

    /** Repository implementation of type {@link DeptRepository}. */
    private final DeptRepository deptRepository;

    /**
     * Constructor.
     *
     * @param employeeRepository Repository instance of type {@link EmployeeRepository}.
     * @param employeeMapper Mapper instance of type {@link EmployeeMapper}.
     * @param deptRepository Repository instance of type {@link DeptRepository}.
     */
    public EmployeeService(
            final EmployeeRepository employeeRepository,
            final EmployeeMapper employeeMapper,
            final DeptRepository deptRepository) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
        this.deptRepository = deptRepository;
    }

    /**
     * This method attempts to create an instance of type {@link EmployeeEntity} in the system based
     * on the provided payload.
     *
     * @param payload Payload containing the details required to create an instance of type {@link
     *     EmployeeEntity}.
     * @return An experience model of type {@link Employee} that represents the newly created entity
     *     of type {@link EmployeeEntity}.
     */
    @Instrument
    @Transactional
    public Employee createEmployee(
            final Integer deptId, @Valid final CreateEmployeeRequest payload) {
        // 1. Validate the dependencies.

        final DeptEntity matchingDept =
                deptRepository
                        .findById(deptId)
                        .orElseThrow(
                                () ->
                                        ServiceException.instance(
                                                Errors.RESOURCE_NOT_FOUND_WITH_ID, deptId));

        // 2.Transform the experience model to a persistence model.
        final EmployeeEntity employeeEntity = employeeMapper.transform(payload);

        // 3.Set the  parent persistence model to current persistence model.
        employeeEntity.setDept(matchingDept);

        // 4. Save the entity.
        final EmployeeEntity savedInstance = employeeRepository.save(employeeEntity);

        // 5. Transform and return.
        return employeeMapper.transform(savedInstance);
    }

    /**
     * This method attempts to update an existing instance of type {@link EmployeeEntity} using the
     * details from the provided input, which is an instance of type {@link UpdateEmployeeRequest}.
     *
     * @param employeeId Unique identifier of Employee in the system, which needs to be updated.
     * @param payload Request payload containing the details of an existing Employee, which needs to
     *     be updated in the system.
     * @return A instance of type {@link Employee} containing the updated details.
     */
    @Instrument
    @Transactional
    public Employee updateEmployee(
            final Integer deptId,
            final Integer employeeId,
            @Valid final UpdateEmployeeRequest payload) {
        // 1. Validate the dependencies.

        final DeptEntity matchingDept =
                deptRepository
                        .findById(deptId)
                        .orElseThrow(
                                () ->
                                        ServiceException.instance(
                                                Errors.RESOURCE_NOT_FOUND_WITH_ID, deptId));

        final EmployeeEntity matchingEmployee =
                employeeRepository
                        .findById(employeeId)
                        .orElseThrow(
                                () ->
                                        ServiceException.instance(
                                                Errors.RESOURCE_NOT_FOUND_WITH_ID, employeeId));

        if (!matchingEmployee.getDept().equals(matchingDept)) {
            throw ServiceException.instance(Errors.RESOURCE_NOT_FOUND);
        }

        // 2. Transform the experience model to a persistence model.
        employeeMapper.transform(payload, matchingEmployee);

        // 3. Save the entity.
        final EmployeeEntity updatedInstance = employeeRepository.save(matchingEmployee);

        // 4. Transform and return.
        return employeeMapper.transform(updatedInstance);
    }

    /**
     * This method attempts to find a {@link EmployeeEntity} whose unique identifier matches the
     * provided identifier.
     *
     * @param employeeId Unique identifier of Employee in the system, whose details have to be
     *     retrieved.
     * @return Matching entity of type {@link Employee} if found, else returns null.
     */
    @Instrument
    @Transactional(readOnly = true)
    public Employee findEmployee(final Integer deptId, final Integer employeeId) {

        // 1. Validate the dependencies.

        final DeptEntity matchingDept =
                deptRepository
                        .findById(deptId)
                        .orElseThrow(
                                () ->
                                        ServiceException.instance(
                                                Errors.RESOURCE_NOT_FOUND_WITH_ID, deptId));

        final EmployeeEntity matchingEmployee =
                employeeRepository
                        .findById(employeeId)
                        .orElseThrow(
                                () ->
                                        ServiceException.instance(
                                                Errors.RESOURCE_NOT_FOUND_WITH_ID, employeeId));

        if (!matchingEmployee.getDept().equals(matchingDept)) {
            throw ServiceException.instance(Errors.RESOURCE_NOT_FOUND);
        }

        // 2. Transform and return.
        return employeeMapper.transform(matchingEmployee);
    }

    /**
     * This method attempts to find instances of type EmployeeEntity based on the provided page
     * definition. If the page definition is null or contains invalid values, this method attempts
     * to return the data for the first page (i.e. page index is 0) with a default page size as 20.
     *
     * @return Returns a page of objects based on the provided page definition. Each object in the
     *     returned page is an instance of type {@link Employee}.
     */
    @Instrument
    @Transactional(readOnly = true)
    public Page<Employee> findAllEmployees(final Integer deptId, final Pageable page) {
        // 0. Validate the dependencies.

        final DeptEntity matchingDept =
                deptRepository
                        .findById(deptId)
                        .orElseThrow(
                                () ->
                                        ServiceException.instance(
                                                Errors.RESOURCE_NOT_FOUND_WITH_ID, deptId));

        // 1. Validate the provided pagination settings.
        final Pageable pageSettings = PageUtils.validateAndUpdatePaginationConfiguration(page);

        EmployeeService.LOGGER.debug(
                "Page settings: page number {}, page size {}",
                pageSettings.getPageNumber(),
                pageSettings.getPageSize());

        // 2. Delegate to the super class method to find the data (page settings are verified in
        // that method).
        final Page<EmployeeEntity> pageData = employeeRepository.findAll(pageSettings);

        // 3. If the page has data, transform each element into target type.
        if (pageData.hasContent()) {
            final List<Employee> dataToReturn =
                    pageData.getContent().stream()
                            .map(employeeMapper::transform)
                            .collect(Collectors.toList());

            return PageUtils.createPage(dataToReturn, pageSettings, pageData.getTotalElements());
        }

        // 4. Return empty page.
        return PageUtils.emptyPage(pageSettings);
    }
    /**
     * This method attempts to delete an existing instance of type {@link EmployeeEntity} whose
     * unique identifier matches the provided identifier.
     *
     * @param employeeId Unique identifier of Employee in the system, which needs to be deleted.
     * @return Unique identifier of the instance of type EmployeeEntity that was deleted.
     */
    @Instrument
    @Transactional
    public Integer deleteEmployee(final Integer deptId, final Integer employeeId) {
        // 1. Validate the dependencies.

        final DeptEntity matchingDept =
                deptRepository
                        .findById(deptId)
                        .orElseThrow(
                                () ->
                                        ServiceException.instance(
                                                Errors.RESOURCE_NOT_FOUND_WITH_ID, deptId));

        final EmployeeEntity matchingEmployee =
                employeeRepository
                        .findById(employeeId)
                        .orElseThrow(
                                () ->
                                        ServiceException.instance(
                                                Errors.RESOURCE_NOT_FOUND_WITH_ID, employeeId));

        if (!matchingEmployee.getDept().equals(matchingDept)) {
            throw ServiceException.instance(Errors.RESOURCE_NOT_FOUND);
        }

        // 2.Delete Persistence Entity
        employeeRepository.deleteById(employeeId);

        // 3. Return the deleted identifier
        return employeeId;
    }
}
