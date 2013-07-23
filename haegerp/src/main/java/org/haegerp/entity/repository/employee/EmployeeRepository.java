package org.haegerp.entity.repository.employee;

import org.haegerp.entity.Employee;
import org.haegerp.entity.repository.MyRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dieses Interface wird bei Spring bearbeitet und SCRUB Operationen für die Mitarberter bereitstellt
 * 
 * @author Wolf
 *
 */
@Repository
@Transactional
public interface EmployeeRepository extends MyRepository<Employee, Long> {
	
}
