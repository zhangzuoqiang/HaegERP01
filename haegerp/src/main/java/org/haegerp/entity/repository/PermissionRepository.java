package org.haegerp.entity.repository;

import org.haegerp.entity.Permission;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=true, propagation=Propagation.MANDATORY)
public interface PermissionRepository extends MyRepository<Permission, Long> {

	
}
