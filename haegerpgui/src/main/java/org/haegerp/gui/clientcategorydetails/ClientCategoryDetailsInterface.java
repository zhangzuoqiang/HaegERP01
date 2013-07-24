package org.haegerp.gui.clientcategorydetails;

import org.haegerp.gui.ClientCategoryDetails;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface ClientCategoryDetailsInterface {
	
	/**
	 * Das Interface wird auf dem ArtikelkategorieDetailsmenü angewendet
	 * 
	 * @param articleDetailsMenu Das KundenKategorieDetailsMenü
	 */
	public void applyView(ClientCategoryDetails clientCategoryDetailsMenu);
	
	/**
	 * Betrieb, dass die Taste tut.
	 * 
	 * @param articleDetailsMenu Das KundenKategorieDetailsMenü
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void btnSaveEdit(ClientCategoryDetails clientCategoryDetailsMenu);
	
	/**
	 * Betrieb, dass die Taste tut
	 * 
	 * @param articleDetailsMenu Das KundenKategorieDetailsMenü
	 */
	public void btnCancel(ClientCategoryDetails clientCategoryDetailsMenu);
}
