package org.haegerp.entity.repository;

import org.haegerp.entity.Client;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=true, propagation=Propagation.MANDATORY)
public interface ClientRepository extends MyRepository<Client, Long> {

}
