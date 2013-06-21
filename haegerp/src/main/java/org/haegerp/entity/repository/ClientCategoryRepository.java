package org.haegerp.entity.repository;

import org.haegerp.entity.ClientCategory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=true, propagation=Propagation.MANDATORY)
public interface ClientCategoryRepository extends MyRepository<ClientCategory, Long> {
	
}
