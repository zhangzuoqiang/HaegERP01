package org.haegerp.entity.repository.client;

import org.haegerp.entity.ClientBill;
import org.haegerp.entity.repository.MyRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dieses Interface wird bei Spring bearbeitet und SCRUB Operationen für die Kundenrechnungen bereitstellt
 * 
 * @author Fabio Codinha
 *
 */
@Repository
@Transactional
public interface ClientBillRepository extends MyRepository<ClientBill, Long> {

}
