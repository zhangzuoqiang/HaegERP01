package org.haegerp.gui.usergroupdetails;

import org.haegerp.gui.UserGroupDetails;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation=Propagation.REQUIRED)
public interface UserGroupDetailsInterface {
	
	/**
	 * Das Interface wird auf dem Benutzergruppedetailsmenü angewendet
	 * 
	 * @param userGroupDetailsMenu Das Benutzergruppedetailsmenü
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void applyView(UserGroupDetails userGroupDetailsMenu);
	
	/**
	 * Betrieb, dass die Taste tut.
	 * 
	 * @param userGroupDetailsMenu Das Benutzergruppedetailsmenü
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void btnSaveEdit(UserGroupDetails userGroupDetailsMenu);
	
	/**
	 * Betrieb, dass die Taste tut
	 * 
	 * @param userGroupDetailsMenu Das Benutzergruppedetailsmenü
	 */
	public void btnCancel(UserGroupDetails userGroupDetailsMenu);
}
