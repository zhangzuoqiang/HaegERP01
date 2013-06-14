package org.haegerp.entity.repository;

import org.haegerp.entity.SupplierOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=true, propagation=Propagation.MANDATORY)
public interface SupplierOrderRepository extends JpaRepository<SupplierOrder, Long> {

	@Modifying
	@Transactional
	public <S extends SupplierOrder> S save(S entity);
}
