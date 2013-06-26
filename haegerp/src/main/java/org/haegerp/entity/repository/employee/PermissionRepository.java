package org.haegerp.entity.repository.employee;

import org.haegerp.entity.Permission;
import org.haegerp.entity.repository.MyRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dieses Interface wird bei Spring bearbeitet und SCRUB Operationen für die Erlaubnise bereitstellt.
 * 
 * @author Wolf
 *
 */
@Transactional(readOnly=true, propagation=Propagation.MANDATORY)
public interface PermissionRepository extends MyRepository<Permission, Long> {

	
}
