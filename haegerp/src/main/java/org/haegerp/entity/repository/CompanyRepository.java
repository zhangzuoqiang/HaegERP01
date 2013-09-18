package org.haegerp.entity.repository;

import org.haegerp.entity.Company;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dieses Interface wird bei Spring bearbeitet und SCRUB Operationen für die Unternehmen bereitstellt
 * 
 * @author Fabio Codinha
 *
 */
@Repository
@Transactional
public interface CompanyRepository extends MyRepository<Company, Long> {

}
