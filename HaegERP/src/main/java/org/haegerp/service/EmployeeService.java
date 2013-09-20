package org.haegerp.service;

import org.haegerp.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public interface EmployeeService {
	
	/**
	 * Diese Methode pruft die Anmeldung.
	 * @param username Benutzer
	 * @param password Kennwort als MD5
	 * @return Der Mitarbeiter, der angemeldet hat. Null - Wenn die Anmeldung nicht richtig ist.
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public Employee isLoginCorrect(String username, String password);
	
	/**
	 * Holt die Seite
	 * @return Aktueller Stand der Seite
	 */
	public Page<Employee> getPage();
	
	/**
	 * Lädt die nächste Seite
	 * @param size Wie viele Mitarbeiter will der Benutzer in der Seite
	 * @return True - Es gibt die nächste Seite; False - Sonst
	 */
	public boolean getNextPage(int size);
	
	/**
	 * Lädt die vorherige Seite
	 * @param size Wie viele Mitarbeiter will der Benutzer in der Seite
	 * @return True - Es gibt die nächste Seite; False - Sonst
	 */
	public boolean getPreviousPage(int size);
	
	/**
	 * Diese Methode gibt die erste Seite
	 * @param size Wie viele Mitarbeiter will der Benutzer in der Seite
	 */
	public boolean getFirstPage(int size);
	
	/**
	 * Diese Methode vorbereitet die Suche
	 * @param idSalaryCategory Welche Gehaltkategorie, die der Benutzer gewählt hat
	 * @param size Wie viele Mitarbeiter will der Benutzer in der Seite
	 */
	public void setSalaryCategory(Long idSalaryCategory, int size);
	
	/**
	 * Diese Methode vorbereitet die Suche
	 * @param idUserGroup Welche Benutzergruppe, die der Benutzer gewählt hat
	 * @param size Wie viele Mitarbeiter will der Benutzer in der Seite
	 */
	public void setUserGroup(Long idUserGroup, int size);
	
	/**
	 * Diese Methode vorbereitet die Suche
	 * @param idDivision Welche division, die der Benutzer gewählt hat
	 * @param size Wie viele Mitarbeiter will der Benutzer in der Seite
	 */
	public void setDivision(Long idDivision, int size);
	
	/**
	 * Diese Methode vorbereitet die Suche
	 * @param value Wert, der der Benutzer geschrieben hat
	 * @param size Wie viele Mitarbeiter will der Benutzer in der Seite
	 */
	public void setSearch(String value, int size);
	
	/**
	 * Der Mitarbeiter wird gelöscht
	 * @param employee Der Mitarbeiter
	 */
	public void delete(Employee employee);
	
	/**
	 * Der Mitarbeiter wird gespeichert
	 * @param employee Der Mitarbeiter
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public Employee save(Employee employee);
	
	/**
	 * Eine neue Seite wird geholt
	 * 
	 * @param size Wie viele Mitarbeiter will der Benutzer in der Seite
	 * @return Seite mit den Mitarbeitern
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public Page<Employee> loadPage(int size);
	
	/**
	 * Wickelt den Inhalt der Tabelle ab
	 * @param size Wie viele Linea will der Benutzer in der Seite
	 * @return Objekt mit dem Inhalt von der Tabelle
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public Object[][] loadTableRows(int size);
	
	/**
	 * Holt den Namen vom Mitarbeiter
	 * @param id ID des Mitarbeiter
	 * @return Name des Mitarbeiter
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public String getEmployeeName(long id);
        
        /**
	 * Holt den Mitarbeiter mit dem passenden ID
	 * @param idEmployee ID des Mitarbeiter
	 * @return Mitarbeiter
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public Employee getEmployeeById(long idEmployee);
        
        /**
         * Hat der Mitarbeiter keine Angebote?
         * @param idEmployee ID des Mitarbeiter.
         * @return True - Wenn der Mitarbeiter kein Angebot hat; False - Sonst.
         */
        @Transactional(propagation=Propagation.REQUIRED)
        public boolean isEmployeeOffersEmpty(long idEmployee);
        
        /**
         * Hat der Mitarbeiter kein Angebot?
         * @param idEmployee ID des Mitarbeiters.
         * @return True - Wenn der Mitarbeiter keine Bestellung hat; False - Sonst.
         */
        @Transactional(propagation=Propagation.REQUIRED)
        public boolean isEmployeeOrdersEmpty(long idEmployee);
}
