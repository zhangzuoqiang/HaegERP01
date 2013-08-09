package org.haegerp.gui.supplierdetails;

import org.haegerp.gui.SupplierDetails;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface SupplierDetailsInterface {
	
	/**
	 * Das Interface wird auf dem Lieferantdetailsmenü angewendet
	 * 
	 * @param supplierDetailsMenu Das Lieferantdetailsmenü
	 */
	public void applyView(SupplierDetails supplierDetailsMenu);
	
	/**
	 * Betrieb, dass die Taste tut.
	 * 
	 * @param supplierDetailsMenu Das Lieferantdetailsmenü
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void btnSaveEdit(SupplierDetails supplierDetailsMenu);
	
	/**
	 * Betrieb, dass die Taste tut
	 * 
	 * @param supplierDetailsMenu Das Lieferantdetailsmenü
	 */
	public void btnCancel(SupplierDetails supplierDetailsMenu);
}
