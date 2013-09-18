package org.haegerp.entity.repository.client;

import org.haegerp.entity.Outstanding;
import org.haegerp.entity.repository.MyRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dieses Interface wird bei Spring bearbeitet und SCRUB Operationen für
 * Outstanding den Kundenbestellungen bereitstellt
 *
 * @author Fabio Codinha
 *
 */
@Repository
@Transactional
public interface OutstandingRepository extends MyRepository<Outstanding, Long> {
}
