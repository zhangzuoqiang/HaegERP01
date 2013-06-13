package org.haegerp.entity.repository;

import org.haegerp.entity.ClientCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=true, propagation=Propagation.MANDATORY)
public interface ClientCategoryRepository extends JpaRepository<ClientCategory, Integer> {

	@Modifying
	@Transactional
	public <S extends ClientCategory> S save(S entity);
}
