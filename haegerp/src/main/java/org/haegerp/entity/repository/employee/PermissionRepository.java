package org.haegerp.entity.repository.employee;

import org.haegerp.entity.Permission;
import org.haegerp.entity.repository.MyRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dieses Interface wird bei Spring bearbeitet und SCRUB Operationen für die
 * Erlaubnise bereitstellt.
 *
 * @author Fabio Codinha
 *
 */
@Repository
@Transactional
public interface PermissionRepository extends MyRepository<Permission, Long> {
}
