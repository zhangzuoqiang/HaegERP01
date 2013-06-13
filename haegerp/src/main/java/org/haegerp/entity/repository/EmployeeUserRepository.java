package org.haegerp.entity.repository;

import org.haegerp.entity.EmployeeUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=true, propagation=Propagation.MANDATORY)
public interface EmployeeUserRepository extends JpaRepository<EmployeeUser, Integer> {

	@Modifying
	@Transactional
	public <S extends EmployeeUser> S save(S entity);
}
