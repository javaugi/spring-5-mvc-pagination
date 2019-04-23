/*
 * Copyright (C) 2019 Strategic Information Systems, LLC.
 *
 */
package com.spring5.ws.rest;

import com.spring5.payroll.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 *
 * @author javaugi
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public interface RestEmployeeRepository extends JpaRepository<Employee, Long> {

}
