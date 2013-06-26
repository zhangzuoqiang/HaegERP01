package org.haegerp.entity.repository.supplier;

import org.haegerp.entity.SupplierBill;
import org.haegerp.entity.repository.MyRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dieses Interface wird bei Spring bearbeitet und SCRUB Operationen für die Lieferantrechnungen bereitstellt.
 * 
 * @author Wolf
 *
 */
@Transactional(readOnly=true, propagation=Propagation.MANDATORY)
public interface SupplierBillRepository extends MyRepository<SupplierBill, Long> {

}
