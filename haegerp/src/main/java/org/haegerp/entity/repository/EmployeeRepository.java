package org.haegerp.entity.repository;

import org.haegerp.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=true, propagation=Propagation.MANDATORY)
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	@Modifying
	@Transactional
	public <S extends Employee> S save(S entity);
}