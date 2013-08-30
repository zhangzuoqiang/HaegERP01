package org.haegerp.controller;

import org.haegerp.entity.Client;
import org.haegerp.entity.ClientCategory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Diese Klasse besitzt die Geschäftslogik von dem Kunden.
 * 
 * @author Wolf
 *
 */
@Service
@Transactional
public interface ClientController {

	/**
	 * Holt die Seite
	 * @return Aktueller Staat der Seite
	 */
	public Page<Client> getPage();

	/**
	 * Wickelt den Inhalt der Tabelle ab
	 * @param size Wie viele Linea will der Benutzer in der Seite
	 * @return Objekt mit dem Inhalt von der Tabelle
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Object[][] loadTableRows(int value);

	/**
	 * Diese Methode vorbereitet die Suche
	 * @param value Wert, der der Benutzer geschrieben hat
	 * @param size Wie viele Linea will der Benutzer in der Seite
	 */
	public void setSearch(String value, int size);

	/**
	 * Diese Methode vorbereitet die Suche
	 * @param cbbIndex Welche Kategorie, die der Benutzer gewählt hat
	 * @param size Wie viele Linea will der Benutzer in der Seite
	 */
	public void setCategory(long cbbIndex, int value);

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
	 * Eine neue Seite wird erhalt
	 * 
	 * @param size Grösse der Seite
	 * @return Seite mit den Kunden
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public Page<Client> loadPage(int size);

	/**
	 * Der Kunde wurde gespeichert
	 * @param client Der Kunde
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public Client save(Client client);

	/**
	 * Der Kunde wurde gelöscht
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
	 * @return Objekt mit dem Inhalt von der Tabelle
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public Object[][] loadAllTableRows();
}
