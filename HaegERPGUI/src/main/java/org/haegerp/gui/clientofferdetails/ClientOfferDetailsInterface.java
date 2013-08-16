package org.haegerp.gui.clientofferdetails;

import org.haegerp.gui.ClientOfferDetails;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface ClientOfferDetailsInterface {
	
	/**
	 * Das Interface wird auf dem Kundenangebotdetailsmenü angewendet
	 * 
	 * @param supplierOrderDetailsMenu Das Kundenangebotdetailsmenü
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void applyView(ClientOfferDetails clientOfferDetailsMenu);
	
	/**
	 * Betrieb, dass die Taste tut.
	 * 
	 * @param supplierOrderDetailsMenu Das Kundenangebotdetailsmenü
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void btnSaveEdit(ClientOfferDetails clientOfferDetailsMenu);
	
	/**
	 * Betrieb, dass die Taste tut
	 * 
	 * @param supplierOrderDetailsMenu Das Kundenangebotdetailsmenü
	 */
	public void btnCancel(ClientOfferDetails clientOfferDetailsMenu);
}
