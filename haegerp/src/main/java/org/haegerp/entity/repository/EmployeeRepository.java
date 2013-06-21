package org.haegerp.entity.repository;

import org.haegerp.entity.Employee;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=true, propagation=Propagation.MANDATORY)
public interface EmployeeRepository extends MyRepository<Employee, Long> {

}
