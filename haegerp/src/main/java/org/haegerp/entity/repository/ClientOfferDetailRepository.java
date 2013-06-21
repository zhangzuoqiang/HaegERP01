package org.haegerp.entity.repository;

import org.haegerp.entity.ClientOfferDetail;
import org.haegerp.entity.ClientOfferDetailPK;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=true, propagation=Propagation.MANDATORY)
public interface ClientOfferDetailRepository extends MyRepository<ClientOfferDetail, ClientOfferDetailPK> {

}
