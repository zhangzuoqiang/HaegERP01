package org.haegerp.gui.supplierdetails;

import org.haegerp.gui.SupplierDetails;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface SupplierDetailsInterface {
	
	/**
	 * Das Interface wird auf dem KundenDetailsmenü angewendet
	 * 
	 * @param articleDetailsMenu Das ArtikelDetailsmenü
	 */
	public void applyView(SupplierDetails supplierDetailsMenu);
	
	/**
	 * Betrieb, dass die Taste tut.
	 * 
	 * @param articleDetailsMenu Das KundenDetailsmenü
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void btnSaveEdit(SupplierDetails supplierDetailsMenu);
	
	/**
	 * Betrieb, dass die Taste tut
	 * 
	 * @param articleDetailsMenu Das KundenDetailsmenü
	 */
	public void btnCancel(SupplierDetails supplierDetailsMenu);
}
