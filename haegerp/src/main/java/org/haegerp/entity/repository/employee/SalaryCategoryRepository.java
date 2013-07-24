package org.haegerp.entity.repository.employee;

import org.haegerp.entity.SalaryCategory;
import org.haegerp.entity.repository.MyRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dieses Interface wird bei Spring bearbeitet und SCRUB Operationen für die Gehaltkaregorien bereitstellt.
 * 
 * @author Wolf
 *
 */
@Repository
@Transactional
public interface SalaryCategoryRepository extends MyRepository<SalaryCategory, Long> {

}
