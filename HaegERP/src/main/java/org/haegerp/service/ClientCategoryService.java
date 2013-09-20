package org.haegerp.service;

import java.util.List;

import org.haegerp.entity.ClientCategory;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface ClientCategoryService {

	/**
	 * Bekommt alle Kategorien in einer Liste.
	 * @return Liste mit den Kategorien
	 */
	public List<ClientCategory> getAllCategories();
	
	/**
	 * Holt die Seite
	 * @return Aktueller Stand der Seite
	 */
	public Page<ClientCategory> getPage();
	
	/**
	 * Lädt die nächste Seite
	 * @param size Wie viele Kundenkategorien will der Benutzer in der Seite
	 * @return True - Es gibt die nächste Seite; False - Sonst
	 */
	public boolean getNextPage(int size);
	
	/**
	 * Lädt die vorherige Seite
	 * @param size Wie viele Kundenkategorien will der Benutzer in der Seite
	 * @return True - Es gibt die nächste Seite; False - Sonst
	 */
	public boolean getPreviousPage(int size);
	
	/**
	 * Diese Methode gibt die erste Seite
	 * @param size Wie viele Kundenkategorien will der Benutzer in der Seite
	 */
	public boolean getFirstPage(int size);
	
	/**
	 * Diese Methode vorbereitet die Suche
	 * @param value Wert, der der Benutzer geschrieben hat
	 * @param size Wie viele Kundenkategorien will der Benutzer in der Seite
	 */
	public void setSearch(String value, int size);
	
	/**
	 * Die Kundenkategorie wird gelöscht
	 * @param clientCategory Die Kundenkategorie
	 */
	public void delete(ClientCategory clientCategory);
	
	/**
	 * Die Kundenkategorie wird gespeichert
	 * @param clientCategory Die Kundenkategorie
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public ClientCategory save(ClientCategory clientCategory);
	
	/**
	 * Eine neue Seite wird geholt
	 * 
	 * @param size Wie viele Kundenkategorien will der Benutzer in der Seite
	 * @return Seite mit den Kundenkategorien
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public Page<ClientCategory> loadPage(int size);
	
	/**
	 * Wickelt den Inhalt der Tabelle ab
	 * @param size Wie viele Kundenkategorien will der Benutzer in der Seite
	 * @return Objekt mit dem Inhalt von der Tabelle
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public Object[][] loadTableRows(int size);
	
	/**
	 * Holt eine Kundenkategorie.
	 * @param id ID der Kategorie
	 * @return Kategorie
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public ClientCategory getClientCategoryId(long id);
        
        /**
         * Hat die Kundenkategorie keine Kunden?
         * @param idCategory ID der Kundenkategorie.
         * @return True - Wenn die Kategorie keinen Kunden hat; False - Sonst.
         */
        @Transactional(propagation=Propagation.REQUIRED)
        public boolean isCategoryClientsEmpty(long idCategory);
}
