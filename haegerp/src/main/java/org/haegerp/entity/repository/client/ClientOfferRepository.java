package org.haegerp.entity.repository.client;

import org.haegerp.entity.ClientOffer;
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
public interface ClientOfferRepository extends MyRepository<ClientOffer, Long> {
	
	@Query(countQuery="SELECT COUNT(*) " +
			"FROM ClientOffer " +
			"WHERE " +
				"( " +
					"(1 = ?1 AND UPPER(client.name) LIKE '%' || UPPER(?2) || '%') " +
					"OR (1 = ?1 AND (UPPER(employee.name) LIKE '%' || UPPER(?2) || '%')) " +
					"OR (1 = ?1 AND (TO_CHAR(offerDate, 'DD-MM-YYYY HH24:MI') LIKE '%' || UPPER(?2) || '%')) " +
					"OR (1 = ?1 AND (TO_CHAR(total) LIKE '%' || UPPER(?2) || '%')) " +
					"OR (1 = ?1 AND (TO_CHAR(sendDate, 'DD-MM-YYYY HH24:MI') LIKE '%' || UPPER(?2) || '%')) " +
					"OR (1 = ?1 AND (UPPER(description) LIKE '%' || UPPER(?2) || '%')) " +
				") " +
				"OR (1 = ?3)",
		value="FROM ClientOffer " +
			"WHERE " +
				"( " +
					"(1 = ?1 AND UPPER(client.name) LIKE '%' || UPPER(?2) || '%') " +
					"OR (1 = ?1 AND (UPPER(employee.name) LIKE '%' || UPPER(?2) || '%')) " +
					"OR (1 = ?1 AND (TO_CHAR(offerDate, 'DD-MM-YYYY HH24:MI') LIKE '%' || UPPER(?2) || '%')) " +
					"OR (1 = ?1 AND (TO_CHAR(total) LIKE '%' || UPPER(?2) || '%')) " +
					"OR (1 = ?1 AND (TO_CHAR(sendDate, 'DD-MM-YYYY HH24:MI') LIKE '%' || UPPER(?2) || '%')) " +
					"OR (1 = ?1 AND (UPPER(description) LIKE '%' || UPPER(?2) || '%')) " +
				") " +
				"OR (1 = ?3)")
	@Transactional(propagation=Propagation.REQUIRED)
	Page<ClientOffer> findWithFilters(int enableSearch, String search,
			int enableAll, Pageable pageable);

}
