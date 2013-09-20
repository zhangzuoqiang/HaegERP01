package org.haegerp.service;

import org.haegerp.entity.Client;
import org.haegerp.entity.ClientCategory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Diese Klasse besitzt die Geschäftslogik von dem Kunden.
 * 
 * @author Fabio Codinha
 *
 */
@Service
@Transactional
public interface ClientService {

	/**
	 * Holt die Seite
	 * @return Aktueller Stand der Seite
	 */
	public Page<Client> getPage();

	/**
	 * Wickelt den Inhalt der Tabelle ab
	 * @param size Wie viele Kunden will der Benutzer in der Seite
	 * @return Objekt mit dem Inhalt von der Tabelle
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Object[][] loadTableRows(int value);

	/**
	 * Diese Methode vorbereitet die Suche
	 * @param value Wert, der der Benutzer geschrieben hat
	 * @param size Wie viele Kunden will der Benutzer in der Seite
	 */
	public void setSearch(String value, int size);

	/**
	 * Diese Methode vorbereitet die Suche
	 * @param cbbIndex Welche Kategorie, die der Benutzer gewählt hat
	 * @param size Wie viele Kunden will der Benutzer in der Seite
	 */
	public void setCategory(long cbbIndex, int value);

	/**
	 * Lädt die nächste Seite
	 * @param size Wie viele Kunden will der Benutzer in der Seite
	 * @return True - Es gibt die nächste Seite; False - Sonst
	 */
	public boolean getNextPage(int size);

	/**
	 * Lädt die vorherige Seite
	 * @param size Wie viele Kunden will der Benutzer in der Seite
	 * @return True - Es gibt die nächste Seite; False - Sonst
	 */
	public boolean getPreviousPage(int size);

	/**
	 * Diese Methode gibt die erste Seite
	 * @param size Wie viele Kunden will der Benutzer in der Seite
	 */
	public boolean getFirstPage(int size);

	/**
	 * Eine neue Seite wird geholt
	 * 
	 * @param size Grösse der Seite
	 * @return Seite mit den Kunden
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public Page<Client> loadPage(int size);

	/**
	 * Der Kunde wird gespeichert
	 * @param client Der Kunde
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public Client save(Client client);

	/**
	 * Der Kunde wird gelöscht
	 * @param client Der Kunde
	 */
	public void delete(Client client);

	/**
	 * Diese Methode löscht alle Kunden von einer Kategorie
	 * @param clientCategory Kundenkategorie
	 */
	public void deleteAllArticleFromCategory(ClientCategory clientCategory);
	
	/**
	 * Holt den Kunden mit dem passenden ID
	 * @param idClient ID des Kunden
	 * @return Kunde
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public Client getClientById(long idClient);
	
	/**
	 * Wickelt den Inhalt der Tabelle ab
         * 
	 * @param enableSearch 1 - Wenn das Feld 'Search' etwas hat; 0 - Sonst
         * @param search Suchen, die der Benutzer eingefügt hat
         * @param enableAll 1 - Wenn das Feld 'enableSearch' 0 ist; 0 - Sonst
         * @return Objekt mit dem Inhalt von der Tabelle
         */
	@Transactional(propagation=Propagation.REQUIRED)
	public Object[][] loadAllTableRows(int enableSearch, String search, int enableAll);
        
        /**
         * Hat der Kunde keine Angebote?
         * @param idClient ID des Kunden.
         * @return True - Wenn der Kunde kein Angebot hat; False - Sonst.
         */
        @Transactional(propagation=Propagation.REQUIRED)
        public boolean isClientOffersEmpty(long idClient);
}
