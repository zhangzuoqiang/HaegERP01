package org.haegerp.controller;

import org.haegerp.entity.Employee;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public interface EmployeeController {
	
	/**
	 * Diese Methode pruft die Anmeldung.
	 * @param username Benutzer
	 * @param password Kennwort als MD5
	 * @return Der Mitarbeiter, der angemeldet hat. Null - Wenn die Anmeldung nicht richtig ist.
	 */
	public Employee isLoginCorrect(String username, String password);
}
