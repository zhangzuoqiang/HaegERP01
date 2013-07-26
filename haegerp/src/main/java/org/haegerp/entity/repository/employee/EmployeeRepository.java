package org.haegerp.entity.repository.employee;

import org.haegerp.entity.Employee;
import org.haegerp.entity.repository.MyRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dieses Interface wird bei Spring bearbeitet und SCRUB Operationen für die Mitarberter bereitstellt
 * 
 * @author Wolf
 *
 */
@Repository
@Transactional
public interface EmployeeRepository extends MyRepository<Employee, Long> {

	@Query("FROM Employee " +
			"WHERE Username = ?1 AND Password = ?2")
	/**
	 * Diese Methode pruft die Anmeldung.
	 * @param username Benutzername, der der Benutzer geschrieben hat
	 * @param passwordMD5 Kennwort in MD5, das der Benutzer geschrieben hat
	 * @return Employee - Benutzername und Kennwort sind richtig, sonst Null
	 */
	public Employee login(String username, String passwordMD5);
	
}
