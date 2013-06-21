package org.haegerp.entity.repository;

import org.haegerp.entity.ClientBill;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=true, propagation=Propagation.MANDATORY)
public interface ClientBillRepository extends MyRepository<ClientBill, Long> {

}
