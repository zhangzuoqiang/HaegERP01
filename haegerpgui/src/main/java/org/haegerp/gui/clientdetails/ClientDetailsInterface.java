package org.haegerp.gui.clientdetails;

import org.haegerp.gui.ClientDetails;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface ClientDetailsInterface {
	
	/**
	 * Das Interface wird auf dem KundenDetailsmenü angewendet
	 * 
	 * @param clientDetailsMenu Das KundenDetailsmenü
	 */
	public void applyView(ClientDetails clientDetailsMenu);
	
	/**
	 * Betrieb, dass die Taste tut.
	 * 
	 * @param clientDetailsMenu Das KundenDetailsmenü
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void btnSaveEdit(ClientDetails clientDetailsMenu);
	
	/**
	 * Betrieb, dass die Taste tut
	 * 
	 * @param clientDetailsMenu Das KundenDetailsmenü
	 */
	public void btnCancel(ClientDetails clientDetailsMenu);
}
