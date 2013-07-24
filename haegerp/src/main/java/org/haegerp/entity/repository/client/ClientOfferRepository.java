package org.haegerp.entity.repository.client;

import org.haegerp.entity.ClientOffer;
import org.haegerp.entity.repository.MyRepository;
import org.springframework.stereotype.Repository;
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

}
