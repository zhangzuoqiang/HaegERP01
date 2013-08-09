package org.haegerp.entity.repository.supplier;

import org.haegerp.entity.Supplier;
import org.haegerp.entity.repository.MyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dieses Interface wird bei Spring bearbeitet und SCRUB Operationen für die Lieferanten bereitstellt.
 * 
 * @author Wolf
 *
 */
@Repository
@Transactional
public interface SupplierRepository extends MyRepository<Supplier, Long> {

	
	@Query(countQuery="SELECT COUNT(*) " +
			"FROM Supplier " +
			"WHERE " +
				"( " +
					"(1 = ?1 AND TO_CHAR(taxId) LIKE '%' || UPPER(?2) || '%') " +
					"OR (1 = ?3 AND (UPPER(name) LIKE '%' || UPPER(?4) || '%')) " +
					"OR (1 = ?5 AND (UPPER(email) LIKE '%' || UPPER(?6) || '%')) " +
					"OR (1 = ?7 AND (UPPER(city) LIKE '%' || UPPER(?8) || '%')) " +
					"OR (1 = ?9 AND (UPPER(country) LIKE '%' || UPPER(?10) || '%')) " +
				") " +
				"OR (1 = ?11)",
		value="FROM Supplier " +
			"WHERE " +
				"( " +
					"(1 = ?1 AND TO_CHAR(taxId) LIKE '%' || UPPER(?2) || '%') " +
					"OR (1 = ?3 AND (UPPER(name) LIKE '%' || UPPER(?4) || '%')) " +
					"OR (1 = ?5 AND (UPPER(email) LIKE '%' || UPPER(?6) || '%')) " +
					"OR (1 = ?7 AND (UPPER(city) LIKE '%' || UPPER(?8) || '%')) " +
					"OR (1 = ?9 AND (UPPER(country) LIKE '%' || UPPER(?10) || '%')) " +
				") " +
				"OR (1 = ?11)")
	/**
	 * Diese Methode macht eine Rückfrage zur Datenbank mit den Filtern
	 * 
	 * @param enableTaxID 1 - True; 0 - False.
	 * @param taxId ID des Lieferanten, dass der Benutzer eingefügt.
	 * @param enableName 1 - True; 0 - False.
	 * @param name Name des Artikel, dass der Benutzer eingefügt.
	 * @param enableEmail 1 - True; 0 - False.
	 * @param email E-Mail des Artikel, dass der Benutzer eingefügt.
	 * @param enableCity 1 - True; 0 - False.
	 * @param city Stadt des Artikel, dass der Benutzer eingefügt.
	 * @param enableCountry 1 - True; 0 - False.
	 * @param country Land des Artikel, dass der Benutzer eingefügt.
	 * @param enableAll Wenn andere "enable" Variablen 0 sind, dann muss diese Variable 1 sein.
	 * @param pageable PageRequest Klasse.
	 * @return Eine Seite mit den Lieferanten.
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	Page<Supplier> findWithFilters(int enableTaxID, String taxId,
								int enableName, String name,
								int enableEmail, String email,
								int enableCity, String city,
								int enableCountry, String country,
								int enableAll, Pageable pageable);

}
