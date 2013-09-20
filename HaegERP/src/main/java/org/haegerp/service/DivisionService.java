package org.haegerp.service;

import java.util.List;

import org.haegerp.entity.Division;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public interface DivisionService {
	
	/**
	 * Bekommt alle Kategorien in einer Liste.
	 * @return Liste mit den Kategorien
	 */
	public List<Division> getAllDivisions();
	
	/**
	 * Holt die Seite
	 * @return Aktueller Stand der Seite
	 */
	public Page<Division> getPage();
	
	/**
	 * Lädt die nächste Seite
	 * @param size Wie viele Divisionen will der Benutzer in der Seite
	 * @return True - Es gibt die nächste Seite; False - Sonst
	 */
	public boolean getNextPage(int size);
	
	/**
	 * Lädt die vorherige Seite
	 * @param size Wie viele Divisionen will der Benutzer in der Seite
	 * @return True - Es gibt die nächste Seite; False - Sonst
	 */
	public boolean getPreviousPage(int size);
	
	/**
	 * Diese Methode gibt die erste Seite
	 * @param size Wie viele Divisionen will der Benutzer in der Seite
	 */
	public boolean getFirstPage(int size);
	
	/**
	 * Diese Methode vorbereitet die Suche
	 * @param value Wert, der der Benutzer geschrieben hat
	 * @param size Wie viele Divisionen will der Benutzer in der Seite
	 */
	public void setSearch(String value, int size);
	
	/**
	 * Die Division wird gelöscht
	 * @param articleCategory Die Artikelkategorie
	 */
	public void delete(Division division);
	
	/**
	 * Die Division wird gespeichert
	 * @param articleCategory Die Artikelkategorie
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public Division save(Division division);
	
	/**
	 * Eine neue Seite wird geholt
	 * 
	 * @param size Wie viele Divisionen will der Benutzer in der Seite
	 * @return Seite mit den Divisionen
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public Page<Division> loadPage(int size);
	
	/**
	 * Wickelt den Inhalt der Tabelle ab
	 * @param size Wie viele Divisionen will der Benutzer in der Seite
	 * @return Objekt mit dem Inhalt von der Tabelle
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public Object[][] loadTableRows(int size);
	
	/**
	 * Holt eine Division.
	 * @param id Id der Division
	 * @return Die Division
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public Division getDivisionById(long id);
        
        /**
         * Hat die Division keine Mitarbeiter?
         * @param idDivision ID der Division.
         * @return True - Wenn die Division keinen Mitarbeiter hat; False - Sonst.
         */
        @Transactional(propagation=Propagation.REQUIRED)
        public boolean isDivisionEmpty(long idDivision);
}
