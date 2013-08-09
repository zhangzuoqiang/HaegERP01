package org.haegerp.entity.repository.supplier;

import org.haegerp.entity.SupplierOrder;
import org.haegerp.entity.repository.MyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dieses Interface wird bei Spring bearbeitet und SCRUB Operationen für die Lieferantbestellungen bereitstellt.
 * 
 * @author Wolf
 *
 */
@Repository
@Transactional
public interface SupplierOrderRepository extends MyRepository<SupplierOrder, Long> {
	
	@Query(countQuery="SELECT COUNT(*) " +
			"FROM SupplierOrder " +
			"WHERE " +
				"( " +
					"(1 = ?1 AND UPPER(supplier.name) LIKE '%' || UPPER(?2) || '%') " +
					"OR (1 = ?1 AND (UPPER(employee.name) LIKE '%' || UPPER(?2) || '%')) " +
					"OR (1 = ?1 AND (TO_CHAR(orderDate, 'DD-MM-YYYY HH24:MI') LIKE '%' || UPPER(?2) || '%')) " +
					"OR (1 = ?1 AND (TO_CHAR(total) LIKE '%' || UPPER(?2) || '%')) " +
					"OR (1 = ?1 AND (TO_CHAR(sendDate, 'DD-MM-YYYY HH24:MI') LIKE '%' || UPPER(?2) || '%')) " +
					"OR (1 = ?1 AND (UPPER(description) LIKE '%' || UPPER(?2) || '%')) " +
				") " +
				"OR (1 = ?3)",
		value="FROM SupplierOrder " +
			"WHERE " +
				"( " +
					"(1 = ?1 AND UPPER(supplier.name) LIKE '%' || UPPER(?2) || '%') " +
					"OR (1 = ?1 AND (UPPER(employee.name) LIKE '%' || UPPER(?2) || '%')) " +
					"OR (1 = ?1 AND (TO_CHAR(orderDate, 'DD-MM-YYYY HH24:MI') LIKE '%' || UPPER(?2) || '%')) " +
					"OR (1 = ?1 AND (TO_CHAR(total) LIKE '%' || UPPER(?2) || '%')) " +
					"OR (1 = ?1 AND (TO_CHAR(sendDate, 'DD-MM-YYYY HH24:MI') LIKE '%' || UPPER(?2) || '%')) " +
					"OR (1 = ?1 AND (UPPER(description) LIKE '%' || UPPER(?2) || '%')) " +
				") " +
				"OR (1 = ?3)")
	/**
	 * Diese Methode macht eine Rückfrage zur Datenbank mit den Filtern
	 * 
	 * @param enableSearch 1 - True; 0 - False
	 * @param search Suchen, dass der Benutzer eingefügt.
	 * @param enableAll Wenn andere "enable" Variablen 0 sind, dann muss diese Variable 1 sein.
	 * @param pageable PageRequest Klasse.
	 * @return Eine Seite mit den Lieferantbestellungen.
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	Page<SupplierOrder> findWithFilters(int enableSearch, String search,
								int enableAll, Pageable pageable);
}
