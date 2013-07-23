package org.haegerp.entity.repository.client;

import org.haegerp.entity.Client;
import org.haegerp.entity.repository.MyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dieses Interface wird bei Spring bearbeitet und SCRUB Operationen für die Kundenbestellungen bereitstellt
 * 
 * @author Wolf
 *
 */
@Transactional(readOnly=true, propagation=Propagation.MANDATORY)
public interface ClientRepository extends MyRepository<Client, Long> {
	
	@Query(countQuery="SELECT COUNT(*) " +
			"FROM Client " +
			"WHERE ((1 = ?1 AND idClientCategory = ?2) OR 1=?3) " +
			"AND " +
			"( " +
				"(1 = ?4) " +
				"OR " +
				"( " +
					"(1 = ?5 AND taxID >= ?6 AND taxID <= ?7) " +
					"OR (1 = ?8 AND UPPER(name) LIKE '%' || UPPER(?9) || '%') " +
					"OR (1 = ?10 AND UPPER(email) LIKE '%' || UPPER(?11) || '%') " +
					"OR (1 = ?12 AND UPPER(city) LIKE '%' || UPPER(?13) || '%') " +
					"OR (1 = ?14 AND UPPER(country) LIKE '%' || UPPER(?15) || '%') " +
				") " +
			") " +
			"OR (1 = ?16)",
value="FROM Client " +
		"WHERE ((1 = ?1 AND idClientCategory = ?2) OR 1=?3) " +
		"AND " +
		"( " +
			"(1 = ?4) " +
			"OR " +
			"( " +
				"(1 = ?5 AND taxID >= ?6 AND taxID <= ?7) " +
				"OR (1 = ?8 AND UPPER(name) LIKE '%' || UPPER(?9) || '%') " +
				"OR (1 = ?10 AND UPPER(email) LIKE '%' || UPPER(?11) || '%') " +
				"OR (1 = ?12 AND UPPER(city) LIKE '%' || UPPER(?13) || '%') " +
				"OR (1 = ?14 AND UPPER(country) LIKE '%' || UPPER(?15) || '%') " +
			") " +
		") " +
		"OR (1 = ?16)")
	/**
	 * Diese Methode macht eine Rückfrage zur Datenbank mit den Filtern
	 * 
	 * @param enableCategory 1 - True; 0 - False.
	 * @param idClientCategory ID der Kategorie, dass der Benutzer gewählt hat.
	 * @param disableSearchCategory Keine suchen Kategorie? := 1; Sonst := 2
	 * @param disableSearchFilters Keine suchen Filter? := 1; Sonst := 2
	 * @param enableTaxID 1 - True; 0 - False.
	 * @param taxIdMin ID des Kunden, dass der Benutzer eingefügt. z.B. (Benutzer einfügt: 560, eanMax: 560)
	 * @param taxIdMax ID des Kunden, dass der Benutzer eingefügt. z.B. (Benutzer einfügt: 560, eanMax: 56099999999)
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
	public Page<Client> findWithFilters(int enableCategory, long idClientCategory,
										int disableSearchCategory, int disableSearchFilters,
										int enableTaxID, long taxIdMin, long taxIdMax,
										int enableName, String name,
										int enableEmail, String email,
										int enableCity, String city,
										int enableCountry, String country,
										int enableAll, Pageable pageable);
	
}
