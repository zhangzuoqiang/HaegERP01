package org.haegerp.controller;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public interface EmployeeController {
	
	/**
	 * Diese Methode pruft die Anmeldung.
	 * @param username Benutzer
	 * @param password Kennwort als MD5
	 * @return True - Richtige Anmeldung; False - Sonst.
	 */
	public boolean isLoginCorrect(String username, String password);
}
