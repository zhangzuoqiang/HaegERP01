package org.haegerp.entity.repository.client;

import org.haegerp.entity.ClientOfferDetail;
import org.haegerp.entity.ClientOfferDetailPK;
import org.haegerp.entity.repository.MyRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dieses Interface wird bei Spring bearbeitet und SCRUB Operationen für die linien den Kundenbestellungen bereitstellt
 * 
 * @author Wolf
 *
 */
@Repository
@Transactional
public interface ClientOfferDetailRepository extends MyRepository<ClientOfferDetail, ClientOfferDetailPK> {

}
