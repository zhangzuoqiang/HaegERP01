package org.haegerp.entity.repository.supplier;

import java.util.List;

import org.haegerp.entity.SupplierOrderDetail;
import org.haegerp.entity.SupplierOrderDetailPK;
import org.haegerp.entity.repository.MyRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dieses Interface wird bei Spring bearbeitet und SCRUB Operationen für die Linien den Lieferantrechnungen bereitstellt.
 * 
 * @author Wolf
 *
 */
@Repository
@Transactional
public interface SupplierOrderDetailRepository extends MyRepository<SupplierOrderDetail, SupplierOrderDetailPK> {

	@Query(value="FROM SupplierOrderDetail " +
				"WHERE supplierOrderDetailPK.supplierOrder.idSupplierOrder = ?1")
	public List<SupplierOrderDetail> findAllByIdSupplierOrder(long idSupplierOrder);

}
