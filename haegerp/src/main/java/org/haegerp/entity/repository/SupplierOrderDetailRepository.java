package org.haegerp.entity.repository;

import org.haegerp.entity.IdSupplierOrderDetail;
import org.haegerp.entity.SupplierOrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=true, propagation=Propagation.MANDATORY)
public interface SupplierOrderDetailRepository extends JpaRepository<SupplierOrderDetail, IdSupplierOrderDetail> {

	@Modifying
	@Transactional
	public <S extends SupplierOrderDetail> S save(S entity);
}
