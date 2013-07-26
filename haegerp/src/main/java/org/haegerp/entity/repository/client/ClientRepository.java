package org.haegerp.entity.repository.client;

import org.haegerp.entity.Client;
import org.haegerp.entity.repository.MyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dieses Interface wird bei Spring bearbeitet und SCRUB Operationen für die Kundenbestellungen bereitstellt
 * 
 * @author Wolf
 *
 */
@Repository
@Transactional
public interface ClientRepository extends MyRepository<Client, Long> {
	
	@Query(countQuery="SELECT COUNT(*) " +
			"FROM Client " +
			"WHERE ((1 = ?1 AND idClientCategory = ?2) OR 1=?3) " +
			"AND " +
			"( " +
				"(1 = ?4) " +
				"OR " +
				"( " +
					"(1 = ?5 AND TO_CHAR(taxID) LIKE '%' || ?6 || '%') " +
					"OR (1 = ?7 AND UPPER(name) LIKE '%' || UPPER(?8) || '%') " +
					"OR (1 = ?9 AND UPPER(email) LIKE '%' || UPPER(?10) || '%') " +
					"OR (1 = ?11 AND UPPER(city) LIKE '%' || UPPER(?12) || '%') " +
					"OR (1 = ?13 AND UPPER(country) LIKE '%' || UPPER(?14) || '%') " +
				") " +
			") " +
			"OR (1 = ?15)",
value="FROM Client " +
		"WHERE ((1 = ?1 AND idClientCategory = ?2) OR 1=?3) " +
		"AND " +
		"( " +
			"(1 = ?4) " +
			"OR " +
			"( " +
				"(1 = ?5 AND TO_CHAR(taxID) LIKE '%' || ?6 || '%') " +
				"OR (1 = ?7 AND UPPER(name) LIKE '%' || UPPER(?8) || '%') " +
				"OR (1 = ?9 AND UPPER(email) LIKE '%' || UPPER(?10) || '%') " +
				"OR (1 = ?11 AND UPPER(city) LIKE '%' || UPPER(?12) || '%') " +
				"OR (1 = ?13 AND UPPER(country) LIKE '%' || UPPER(?14) || '%') " +
			") " +
		") " +
		"OR (1 = ?15)")
	/**
	 * Diese Methode macht eine Rückfrage zur Datenbank mit den Filtern
	 * 
	 * @param enableCategory 1 - True; 0 - False.
	 * @param idClientCategory ID der Kategorie, dass der Benutzer gewählt hat.
	 * @param disableSearchCategory Keine suchen Kategorie? := 1; Sonst := 2
	 * @param disableSearchFilters Keine suchen Filter? := 1; Sonst := 2
	 * @param enableTaxID 1 - True; 0 - False.
	 * @param taxId ID des Kunden, dass der Benutzer eingefügt.
	 * @param enableName 1 - True; 0 - False.
	 * @param name Namme des Kunden, dass der Benutzer eingefügt.
	 * @param enableEmail 1 - True; 0 - False.
	 * @param email E-Mail des Kunden, dass der Benutzer eingefügt.
	 * @param enableCity 1 - True; 0 - False.
	 * @param city Stadt, dass der Benutzer eingefügt.
	 * @param enableCountry 1 - True; 0 - False.
	 * @param country Land, dass der Benutzer eingefügt.
	 * @param enableAll Wenn andere "enable" Variablen 0 sind, dann muss diese Variable 1 sein.
	 * @param pageRequest PageRequest Klasse.
	 * @return Eine Seite mit den Kunden.
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public Page<Client> findWithFilters(int enableCategory, long idClientCategory,
										int disableSearchCategory, int disableSearchFilters,
										int enableTaxID, String taxId,
										int enableName, String name,
										int enableEmail, String email,
										int enableCity, String city,
										int enableCountry, String country,
										int enableAll, Pageable pageable);
	
}
