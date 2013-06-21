package org.haegerp.entity.repository;

import org.haegerp.entity.ClientOffer;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=true, propagation=Propagation.MANDATORY)
public interface ClientOfferRepository extends MyRepository<ClientOffer, Long> {

}
