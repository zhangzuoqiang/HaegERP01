package org.haegerp.controller;

import org.haegerp.entity.ClientBill;
import org.haegerp.entity.ClientOffer;
import org.haegerp.exception.LengthOverflowException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Diese Klasse besitzt die Geschäftslogik von dem Kundenangebot.
 * 
 * @author Fabio Codinha
 *
 */
@Service
@Transactional
public interface ClientOfferController {

	/**
	 * Holt die Seite
	 * @return Aktueller Stand der Seite
	 */
	public Page<ClientOffer> getPage();

	/**
	 * Wickelt den Inhalt der Tabelle ab
	 * @param size Wie viele KundenAngeboten will der Benutzer in der Seite
	 * @return Objekt mit dem Inhalt von der Tabelle
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Object[][] loadTableRows(int value);

	/**
	 * Diese Methode vorbereitet die Suche
	 * @param text Wert, der der Benutzer geschrieben hat
	 * @param size Wie viele KundenAngeboten will der Benutzer in der Seite
	 */
	public void setSearch(String text, int size);

	/**
	 * Lädt die nächste Seite
	 * @param size Wie viele KundenAngeboten will der Benutzer in der Seite
	 * @return True - Es gibt die nächste Seite; False - Sonst
	 */
	public boolean getNextPage(int size);

	/**
	 * Lädt die vorherige Seite
	 * @param size Wie viele KundenAngeboten will der Benutzer in der Seite
	 * @return True - Es gibt die nächste Seite; False - Sonst
	 */
	public boolean getPreviousPage(int size);

	/**
	 * Diese Methode gibt die erste Seite
	 * @param size Wie viele KundenAngeboten will der Benutzer in der Seite
	 */
	public boolean getFirstPage(int size);

	/**
	 * Eine neue Seite wird geholt
	 * 
	 * @param size Grösse der Seite
	 * @return Seite mit den Kundenangeboten
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public Page<ClientOffer> loadPage(int size);

	/**
	 * Das Kundenangeboten wird gespeichert
	 * @param clientOffer Das Kundenangebot
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public ClientOffer save(ClientOffer clientOffer);

	/**
	 * Das Kundenangebot wird gelöscht
	 * @param clientOffer Das Kundenangebot
	 */
	public void delete(ClientOffer clientOffer);
	
	/**
	 * Das Kundenangebot wird aktualisiert
	 * 
	 * @param clientOffer Das Kundenangebot
	 * @return Das Kundenangebot danach die Aktualisierung
	 * @throws LengthOverflowException Wenn ein Wert falsh ist
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public ClientOffer updateClientOffer(ClientOffer clientOffer) throws LengthOverflowException;
	
	/**
	 * Das Kundenangebot wird erstellt
	 * 
	 * @param clientOrder Das Kundenangebot
	 * @return Das Kundenangebot danach die erstellung
	 * @throws LengthOverflowException Wenn ein Wert falsh ist
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public ClientOffer newClientOffer(ClientOffer clientOffer) throws LengthOverflowException;
	
	/**
	 * Holt die Rechnung des Angebots
	 * @param idSupplierBill Id der Rechnung
	 * @return Rechnung
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public ClientBill getClientBillById(long idClientBill);
	
	/**
	 * Rechnung des Kundenangebots wird gespeichert
	 * @param clientBill Die Rechnung
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public ClientBill saveBill(ClientBill clientBill);
        
        /**
         * Holt das Kundenangebot von der Datenbank
         * @param idClientOffer ID des Kundenangebots
         * @return Kundenangebot
         */
        @Transactional(propagation = Propagation.REQUIRED)
        public ClientOffer getClientOfferById(long idClientOffer);
}