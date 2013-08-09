package org.haegerp.gui.clientcategorydetails;

import org.haegerp.gui.ClientCategoryDetails;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface ClientCategoryDetailsInterface {
	
	/**
	 * Das Interface wird auf dem Kundenkategoriedetailsmenü angewendet
	 * 
	 * @param clientCategoryDetailsMenu Das KundenKategoriedetailsMenü
	 */
	public void applyView(ClientCategoryDetails clientCategoryDetailsMenu);
	
	/**
	 * Betrieb, dass die Taste tut.
	 * 
	 * @param clientCategoryDetailsMenu Das KundenKategoriedetailsMenü
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void btnSaveEdit(ClientCategoryDetails clientCategoryDetailsMenu);
	
	/**
	 * Betrieb, dass die Taste tut
	 * 
	 * @param clientCategoryDetailsMenu Das KundenKategoriedetailsMenü
	 */
	public void btnCancel(ClientCategoryDetails clientCategoryDetailsMenu);
}
