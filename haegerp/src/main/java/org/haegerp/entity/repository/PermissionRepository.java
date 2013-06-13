package org.haegerp.entity.repository;

import org.haegerp.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=true, propagation=Propagation.MANDATORY)
public interface PermissionRepository extends JpaRepository<Permission, Integer> {

	@Modifying
	@Transactional
	public <S extends Permission> S save(S entity);
	
}
