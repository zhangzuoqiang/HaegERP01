package org.haegerp.entity.repository;

import org.haegerp.entity.SupplierOrder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=true, propagation=Propagation.MANDATORY)
public interface SupplierOrderRepository extends MyRepository<SupplierOrder, Long> {

}
