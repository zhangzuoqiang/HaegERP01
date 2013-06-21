package org.haegerp.entity.repository;

import org.haegerp.entity.Outstanding;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=true, propagation=Propagation.MANDATORY)
public interface OutstandingRepository extends MyRepository<Outstanding, Long> {

}
