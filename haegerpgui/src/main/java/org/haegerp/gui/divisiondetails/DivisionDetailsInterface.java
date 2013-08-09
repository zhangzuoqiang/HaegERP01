package org.haegerp.gui.divisiondetails;

import org.haegerp.gui.DivisionDetails;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface DivisionDetailsInterface {
	
	/**
	 * Das Interface wird auf dem Divisiondetailsmenü angewendet
	 * 
	 * @param divisionDetailsMenu Das DivisionDetailsmenü
	 */
	public void applyView(DivisionDetails divisionDetailsMenu);
	
	/**
	 * Betrieb, dass die Taste tut.
	 * 
	 * @param divisionDetailsMenu Das Divisiondetailsmenü
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void btnSaveEdit(DivisionDetails divisionDetailsMenu);
	
	/**
	 * Betrieb, dass die Taste tut
	 * 
	 * @param divisionDetailsMenu Das Divisiondetailsmenü
	 */
	public void btnCancel(DivisionDetails divisionDetailsMenu);
}
