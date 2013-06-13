package org.haegerp.entity.repository;

import org.haegerp.entity.Division;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=true, propagation=Propagation.MANDATORY)
public interface DivisionRepository extends JpaRepository<Division, Integer> {

	@Modifying
	@Transactional
	public <S extends Division> S save(S entity);
}
