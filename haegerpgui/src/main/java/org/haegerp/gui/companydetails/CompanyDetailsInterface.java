package org.haegerp.gui.companydetails;

import org.haegerp.gui.CompanyDetails;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface CompanyDetailsInterface {
	
	/**
	 * Das Interface wird auf dem Firmadetailsmenü angewendet
	 * 
	 * @param supplierDetailsMenu Das ArtikelDetailsmenü
	 */
	public void applyView(CompanyDetails supplierDetailsMenu);
	
	/**
	 * Betrieb, dass die Taste tut.
	 * 
	 * @param supplierDetailsMenu Das Firmadetailsmenü
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void btnSaveEdit(CompanyDetails supplierDetailsMenu);
	
	/**
	 * Betrieb, dass die Taste tut
	 * 
	 * @param supplierDetailsMenu Das Firmadetailsmenü
	 */
	public void btnCancel(CompanyDetails companyDetailsMenu);
}
