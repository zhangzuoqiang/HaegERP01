package org.haegerp.entity.repository;

import org.haegerp.entity.SupplierBill;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=true, propagation=Propagation.MANDATORY)
public interface SupplierBillRepository extends MyRepository<SupplierBill, Long> {

}
