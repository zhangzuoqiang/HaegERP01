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
	 * Bekommt alle Kategorien in einer Liste.
	 * @return Liste mit den Kategorien
	 */
	public List<UserGroup> getAllGroups();
	
	/**
	 * Holt die Seite
	 * @return Aktueller Staat der Seite
	 */
	public Page<UserGroup> getPage();
	
	/**
	 * Lädt die nächste Seite
	 * @param size Wie viele Linea will der Benutzer in der Seite
	 * @return True - Es gibt die nächste Seite; False - Sonst
	 */
	public boolean getNextPage(int size);
	
	/**
	 * Lädt die vorherige Seite
	 * @param size Wie viele Linea will der Benutzer in der Seite
	 * @return True - Es gibt die nächste Seite; False - Sonst
	 */
	public boolean getPreviousPage(int size);
	
	/**
	 * Diese Methode gibt die erste Seite
	 * @param size Wie viele Linea will der Benutzer in der Seite
	 */
	public boolean getFirstPage(int size);
	
	/**
	 * Diese Methode vorbereitet die Suche
	 * @param value Wert, der der Benutzer geschrieben hat
	 * @param size Wie viele Linea will der Benutzer in der Seite
	 */
	public void setSearch(String value, int size);
	
	/**
	 * Die Artikelkategorie wurde gelöscht
	 * @param articleCategory Die Artikelkategorie
	 */
	public void delete(UserGroup articleCategory);
	
	/**
	 * Die Artikelkategorie wurde gespeichert
	 * @param articleCategory Die Artikelkategorie
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public UserGroup save(UserGroup articleCategory);
	
	/**
	 * Eine neue Seite wird erhalt
	 * 
	 * @param size Wie viele Linea will der Benutzer in der Seite
	 * @return Seite mit den Artikelkategorien
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public Page<UserGroup> loadPage(int size);
	
	/**
	 * Wickelt den Inhalt der Tabelle ab
	 * @param size Wie viele Linea will der Benutzer in der Seite
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
}
