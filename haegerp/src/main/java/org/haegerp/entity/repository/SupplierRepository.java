package org.haegerp.entity.repository;

import org.haegerp.entity.Supplier;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=true, propagation=Propagation.MANDATORY)
public interface SupplierRepository extends MyRepository<Supplier, Long> {

}
