package org.haegerp.controller;

import java.util.List;

import org.haegerp.entity.UserGroup;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public interface UserGroupController {
	
	/**
	 * Bekommt alle Gruppen in einer Liste.
	 * @return Liste mit den Kategorien
	 */
	public List<UserGroup> getAllGroups();
	
	/**
	 * Holt die Seite
	 * @return Aktueller Stand der Seite
	 */
	public Page<UserGroup> getPage();
	
	/**
	 * Lädt die nächste Seite
	 * @param size Wie viele Benutzergruppen will der Benutzer in der Seite
	 * @return True - Es gibt die nächste Seite; False - Sonst
	 */
	public boolean getNextPage(int size);
	
	/**
	 * Lädt die vorherige Seite
	 * @param size Wie viele Benutzergruppen will der Benutzer in der Seite
	 * @return True - Es gibt die nächste Seite; False - Sonst
	 */
	public boolean getPreviousPage(int size);
	
	/**
	 * Diese Methode gibt die erste Seite
	 * @param size Wie viele Benutzergruppe will der Benutzer in der Seite
	 */
	public boolean getFirstPage(int size);
	
	/**
	 * Diese Methode vorbereitet die Suche
	 * @param value Wert, der der Benutzer geschrieben hat
	 * @param size Wie viele Benutzergruppen will der Benutzer in der Seite
	 */
	public void setSearch(String value, int size);
	
	/**
	 * Die Benutzergruppe wird gelöscht
	 * @param articleCategory Die Artikelkategorie
	 */
	public void delete(UserGroup articleCategory);
	
	/**
	 * Die Benutzergruppe wird gespeichert
	 * @param articleCategory Die Artikelkategorie
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public UserGroup save(UserGroup articleCategory);
	
	/**
	 * Eine neue Seite wird geholt
	 * 
	 * @param size Wie viele Benutzergruppen will der Benutzer in der Seite
	 * @return Seite mit den Benutzergruppen
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public Page<UserGroup> loadPage(int size);
	
	/**
	 * Wickelt den Inhalt der Tabelle ab
	 * @param size Wie viele Benutzergruppen will der Benutzer in der Seite
	 * @return Objekt mit dem Inhalt von der Tabelle
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public Object[][] loadTableRows(int size);
	
	/**
	 * Diese Methode macht eine Abfrage an die Datenbank
	 * @param id ID der Benutzergruppe
	 * @return Benutzergruppe
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public UserGroup getUserGroupById(long id);
        
        /**
         * Hat die Benutzergruppe keine Mitarbeiter?
         * @param idUserGroup ID der Benutzergruppe.
         * @return True - Wenn die Gruppe keinen Mitarbeiter hat; False - Sonst.
         */
        @Transactional(propagation=Propagation.REQUIRED)
        public boolean isUserGroupEmpty(long idUserGroup);
}
