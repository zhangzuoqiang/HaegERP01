package org.haegerp.entity.repository;

import org.haegerp.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=true, propagation=Propagation.MANDATORY)
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

	@Modifying
	@Transactional
	public <S extends Supplier> S save(S entity);
}
