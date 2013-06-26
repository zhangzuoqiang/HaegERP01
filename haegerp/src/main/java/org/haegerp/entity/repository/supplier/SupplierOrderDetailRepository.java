package org.haegerp.entity.repository.supplier;

import org.haegerp.entity.SupplierOrderDetail;
import org.haegerp.entity.SupplierOrderDetailPK;
import org.haegerp.entity.repository.MyRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dieses Interface wird bei Spring bearbeitet und SCRUB Operationen für die Linien den Lieferantrechnungen bereitstellt.
 * 
 * @author Wolf
 *
 */
@Transactional(readOnly=true, propagation=Propagation.MANDATORY)
public interface SupplierOrderDetailRepository extends MyRepository<SupplierOrderDetail, SupplierOrderDetailPK> {

}
