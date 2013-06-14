package org.haegerp.entity.repository;

import org.haegerp.entity.SupplierBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=true, propagation=Propagation.MANDATORY)
public interface SupplierBillRepository extends JpaRepository<SupplierBill, Long> {

	@Modifying
	@Transactional
	public <S extends SupplierBill> S save(S entity);
}
