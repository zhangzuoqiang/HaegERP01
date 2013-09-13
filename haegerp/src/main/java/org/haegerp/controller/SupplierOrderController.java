package org.haegerp.controller;

import org.haegerp.entity.SupplierBill;
import org.haegerp.entity.SupplierOrder;
import org.haegerp.exception.LengthOverflowException;
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
public interface SupplierOrderController {

	/**
	 * Holt die Seite
	 * @return Aktueller Staat der Seite
	 */
	public Page<SupplierOrder> getPage();

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
	 * @return Seite mit den Lieferantbestellungen
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public Page<SupplierOrder> loadPage(int size);

	/**
	 * Die Lieferantenbestellung wurde gespeichert
	 * @param supplier Die Lieferantbestellung
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public SupplierOrder save(SupplierOrder supplierOrder);

	/**
	 * Die Lieferantenbestellung wurde gelöscht
	 * @param supplierOrder Die Lieferantbestellung
	 */
	public void delete(SupplierOrder supplierOrder);
	
	/**
	 * Die Lieferantenbestellung wird aktualisiert
	 * 
	 * @param supplierOrder Die Lieferantenbestellung
	 * @return Die Lieferantenbestellung danach die Aktualisierung
	 * @throws LengthOverflowException Wenn ein Wert falsh ist
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public SupplierOrder updateSupplierOrder(SupplierOrder supplierOrder) throws LengthOverflowException;
	
	/**
	 * Die Lieferantenbestellung wird erstellt
	 * 
	 * @param supplierOrder
	 * @return Die Lieferantenbestellung danach die erstellung
	 * @throws LengthOverflowException Wenn ein Wert falsh ist
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public SupplierOrder newSupplierOrder(SupplierOrder supplierOrder) throws LengthOverflowException;
	
	/**
	 * Holt die Rechnung der Bestellung
	 * @param idSupplierBill Id der Rechnung
	 * @return Rechnung
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public SupplierBill getSupplierBillById(long idSupplierBill);
	
	/**
	 * Rechnung der Lieferantenbestellung wurde gespeichert
	 * @param supplier Die Rechnung
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public SupplierBill saveBill(SupplierBill supplierBill);
        
        /**
	 * Holt den LieferantenBestellung mit dem passenden ID
	 * @param idSupplierOrder ID des Lieferantenbestellung
	 * @return Lieferantenbestellung
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public SupplierOrder getSupplierOrderById(long idSupplierOrder);
}