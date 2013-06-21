package org.haegerp.entity.repository;

import org.haegerp.entity.Log;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=true, propagation=Propagation.MANDATORY)
public interface LogRepository extends MyRepository<Log, Long> {

}
