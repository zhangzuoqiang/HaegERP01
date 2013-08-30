package org.haegerp.entity.repository.client;

import org.haegerp.entity.Client;
import org.haegerp.entity.repository.MyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
                                    "OR (1 = ?5 AND UPPER(name) LIKE '%' || UPPER(?6) || '%') " +
                                    "OR (1 = ?5 AND UPPER(email) LIKE '%' || UPPER(?6) || '%') " +
                                    "OR (1 = ?5 AND UPPER(city) LIKE '%' || UPPER(?6) || '%') " +
                                    "OR (1 = ?5 AND UPPER(country) LIKE '%' || UPPER(?6) || '%') " +
                                ") " +
                            ") " +
                            "OR (1 = ?7)",
                value="FROM Client " +
            		"WHERE ((1 = ?1 AND idClientCategory = ?2) OR 1=?3) " +
                            "AND " +
                            "( " +
                                "(1 = ?4) " +
                                "OR " +
                                "( " +
                                    "(1 = ?5 AND TO_CHAR(taxID) LIKE '%' || ?6 || '%') " +
                                    "OR (1 = ?5 AND UPPER(name) LIKE '%' || UPPER(?6) || '%') " +
                                    "OR (1 = ?5 AND UPPER(email) LIKE '%' || UPPER(?6) || '%') " +
                                    "OR (1 = ?5 AND UPPER(city) LIKE '%' || UPPER(?6) || '%') " +
                                    "OR (1 = ?5 AND UPPER(country) LIKE '%' || UPPER(?6) || '%') " +
                                ") " +
                            ") " +
                            "OR (1 = ?7)")
    /**
     * Diese Methode macht eine Rückfrage zur Datenbank mit den Filtern
     * 
     * @param enableCategory 1 - True; 0 - False.
     * @param idClientCategory ID der Kategorie, dass der Benutzer gewählt hat.
     * @param disableSearchCategory Keine suchen Kategorie? := 1; Sonst := 2
     * @param disableSearchFilters Keine suchen Filter? := 1; Sonst := 2
     * @param enableSearch 1 - True; 0 - False.
     * @param taxId Wert des Kunden, dass der Benutzer eingefügt.
     * @param enableAll Wenn andere "enable" Variablen 0 sind, dann muss diese Variable 1 sein.
     * @param pageRequest PageRequest Klasse.
     * @return Eine Seite mit den Kunden.
     */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Page<Client> findWithFilters(int enableCategory, long idClientCategory,
                                        int disableSearchCategory, int disableSearchFilters,
                                        int enableSearch, String search, int enableAll,
                                        Pageable pageable);
        
}
