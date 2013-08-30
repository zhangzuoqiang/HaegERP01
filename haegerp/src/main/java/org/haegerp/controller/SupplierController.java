package org.haegerp.controller;

import java.util.List;

import org.haegerp.entity.Supplier;
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
public interface SupplierController {

	/**
	 * Bekommt alle Lieferanten in einer Liste.
	 * @return Liste mit den Lieferanten.
	 */
	public List<Supplier> getAllSuppliers();
	
	/**
	 * Holt die Seite
	 * @return Aktueller Staat der Seite
	 */
	public Page<Supplier> getPage();

	/**
	 * Wickelt den Inhalt der Tabelle ab
	 * @param size Wie viele Linea will der Benutzer in der Seite
	 * @return Objekt mit dem Inhalt von der Tabelle
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Object[][] loadTableRows(int value);

	/**
	 * Diese Methode vorbereitet die Suche
	 * @param text Wert, der der Benutzer geschrieben hat
	 * @param size Wie viele Linea will der Benutzer in der Seite
	 */
	public void setSearch(String text, int size);

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
	public Page<Supplier> loadPage(int size);

	/**
	 * Der Lieferant wurde gespeichert
	 * @param supplier Der Lieferant
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public Supplier save(Supplier supplier);

	/**
	 * Der Liferant wurde gelöscht
	 * @param supplier Der Lieferant
	 */
	public void delete(Supplier supplier);
	
	/**
	 * Holt den Lieferanten mit dem passenden ID
	 * @param idSupplier ID des Lieferanten
	 * @return Lieferant
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public Supplier getSupplierById(long idSupplier);
	
	/**
	 * Wickelt den Inhalt der Tabelle ab
	 * @return Objekt mit dem Inhalt von der Tabelle
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public Object[][] loadAllTableRows();
}
