package org.haegerp.entity.repository.employee;

import org.haegerp.entity.SalaryCategory;
import org.haegerp.entity.repository.MyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
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

	@Query(countQuery="SELECT COUNT (*) FROM SalaryCategory " +
			"WHERE (TO_CHAR(SalaryFrom) LIKE '%' || UPPER(?1) || '%') " +
				"OR (TO_CHAR(SalaryTo) LIKE '%' || UPPER(?1) || '%') " +
				"OR (UPPER(Description) LIKE '%' || UPPER(?1) || '%') " +
				"OR (1 = ?2)",
		value="FROM SalaryCategory " +
				"WHERE (TO_CHAR(SalaryFrom) LIKE '%' || UPPER(?1) || '%') " +
					"OR (TO_CHAR(SalaryTo) LIKE '%' || UPPER(?1) || '%') " +
					"OR (UPPER(Description) LIKE '%' || UPPER(?1) || '%') " +
					"OR (1 = ?2)")
	/**
	* Diese Methode macht eine Rückfrage zur Datenbank mit den Filtern
	* 
	* @param textToFilter Text, dass der Benutzer einge
	* @param enableAll Wenn TextToFilter keinen Text hat, dann muss diese Variable 0 sein, sonst ist 1.
	* @param pageable PageRequest Klasse.
	* @return Eine Seite mit den Gehaltkategorien.
	*/
	@Transactional(propagation=Propagation.REQUIRED, readOnly = true)
	public Page<SalaryCategory> findWithFilters(String textToFilter, int enableAll, Pageable pageable);
}
