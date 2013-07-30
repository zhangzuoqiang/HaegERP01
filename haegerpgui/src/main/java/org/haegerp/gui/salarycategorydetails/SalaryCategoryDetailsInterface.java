package org.haegerp.gui.salarycategorydetails;

import org.haegerp.gui.SalaryCategoryDetails;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface SalaryCategoryDetailsInterface {
	
	/**
	 * Das Interface wird auf dem ArtikelkategorieDetailsmenü angewendet
	 * 
	 * @param salaryCategoryDetailsMenu Das ArtikelDetailsmenü
	 */
	public void applyView(SalaryCategoryDetails salaryCategoryDetailsMenu);
	
	/**
	 * Betrieb, dass die Taste tut.
	 * 
	 * @param salaryCategoryDetailsMenu Das ArtikelDetailsmenü
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void btnSaveEdit(SalaryCategoryDetails salaryCategoryDetailsMenu);
	
	/**
	 * Betrieb, dass die Taste tut
	 * 
	 * @param salaryCategoryDetailsMenu Das ArtikelDetailsmenü
	 */
	public void btnCancel(SalaryCategoryDetails salaryCategoryDetailsMenu);
}
