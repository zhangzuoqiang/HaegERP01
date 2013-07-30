package org.haegerp.controller;

import java.util.List;

import org.haegerp.entity.ClientCategory;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface ClientCategoryController {

	/**
	 * Bekommt alle Kategorien in einer Liste.
	 * @return Liste mit den Kategorien
	 */
	public List<ClientCategory> getAllCategories();
	
	/**
	 * Holt die Seite
	 * @return Aktueller Staat der Seite
	 */
	public Page<ClientCategory> getPage();
	
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
	 * Die Kundenkategorie wurde gelöscht
	 * @param clientCategory Die Kundenkategorie
	 */
	public void delete(ClientCategory clientCategory);
	
	/**
	 * Die Kundenkategorie wurde gespeichert
	 * @param clientCategory Die Kundenkategorie
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public ClientCategory save(ClientCategory clientCategory);
	
	/**
	 * Eine neue Seite wird erhalt
	 * 
	 * @param size Wie viele Linea will der Benutzer in der Seite
	 * @return Seite mit den Kundenkategorien
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public Page<ClientCategory> loadPage(int size);
	
	/**
	 * Wickelt den Inhalt der Tabelle ab
	 * @param size Wie viele Linea will der Benutzer in der Seite
	 * @return Objekt mit dem Inhalt von der Tabelle
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public Object[][] loadTableRows(int size);
	
}
