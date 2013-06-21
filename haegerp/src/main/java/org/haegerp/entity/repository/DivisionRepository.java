package org.haegerp.entity.repository;

import org.haegerp.entity.Division;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=true, propagation=Propagation.MANDATORY)
public interface DivisionRepository extends MyRepository<Division, Long> {

}
