package org.haegerp.entity.repository;

import org.haegerp.entity.SalaryCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=true, propagation=Propagation.MANDATORY)
public interface SalaryCategoryRepository extends JpaRepository<SalaryCategory, Long> {

	@Modifying
	@Transactional
	public <S extends SalaryCategory> S save(S entity);
}
