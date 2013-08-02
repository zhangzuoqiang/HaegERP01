package org.haegerp.gui.employeedetails;

import org.haegerp.gui.EmployeeDetails;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface EmployeeDetailsInterface {
	
	/**
	 * Das Interface wird auf dem MitarbeiterDetailsmenü angewendet
	 * 
	 * @param employeeDetailsMenu Das MitarbeiterDetailsmenü
	 */
	public void applyView(EmployeeDetails employeeDetailsMenu);
	
	/**
	 * Betrieb, dass die Taste tut.
	 * 
	 * @param employeeDetailsMenu Das MitarbeiterDetailsmenü
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void btnSaveEdit(EmployeeDetails employeeDetailsMenu);
	
	/**
	 * Betrieb, dass die Taste tut
	 * 
	 * @param employeeDetailsMenu Das MitarbeiterDetailsmenü
	 */
	public void btnCancel(EmployeeDetails employeeDetailsMenu);
}
