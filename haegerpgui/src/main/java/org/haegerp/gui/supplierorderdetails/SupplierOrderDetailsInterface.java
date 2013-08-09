package org.haegerp.gui.supplierorderdetails;

import org.haegerp.gui.SupplierOrderDetails;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface SupplierOrderDetailsInterface {
	
	/**
	 * Das Interface wird auf dem Lieferantbestellungdetailsmenü angewendet
	 * 
	 * @param supplierOrderDetailsMenu Das Lieferantdetailsmenü
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void applyView(SupplierOrderDetails supplierOrderDetailsMenu);
	
	/**
	 * Betrieb, dass die Taste tut.
	 * 
	 * @param supplierOrderDetailsMenu Das Lieferantbestellungdetailsmenü
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void btnSaveEdit(SupplierOrderDetails supplierOrderDetailsMenu);
	
	/**
	 * Betrieb, dass die Taste tut
	 * 
	 * @param supplierOrderDetailsMenu Das Lieferantbestellungdetailsmenü
	 */
	public void btnCancel(SupplierOrderDetails supplierOrderDetailsMenu);
}
