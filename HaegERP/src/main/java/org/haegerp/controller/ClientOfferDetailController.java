package org.haegerp.controller;

import java.util.List;
import java.util.Set;

import javax.swing.JTable;

import org.haegerp.entity.ClientOfferDetail;
import org.haegerp.exception.LengthOverflowException;
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
public interface ClientOfferDetailController {

	/**
	 * Wickelt den Inhalt der Tabelle ab
	 * @param idClientOffer ID des Angebot
	 * @return Objekt mit dem Inhalt von der Tabelle
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Object[][] loadTableRows(long idClientOffer);

	/**
	 * Eine neue Seite wird erhalt
	 * 
	 * @param idClientOffer ID des Angebot
	 * @return Seite mit den Kundenangebotdetails
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public List<ClientOfferDetail> loadPage(long idClientOffer);

	/**
	 * Die Lieferantbestellung wurde gespeichert
	 * @param clientOfferDetail Die Kundenangebotdetails
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public ClientOfferDetail save(ClientOfferDetail clientOfferDetail);

	/**
	 * Der Liferant wurde gelöscht
	 * @param clientOfferDetail Die Kundenangebotdetails
	 */
	public void delete(ClientOfferDetail clientOfferDetail);
	
	/**
	 * Diese Methode aktualisiert einen Artikel der Bestellung.
	 * @param table Tabelle mit den Artikeln der Lieferantsbestellung.
	 * @param idClientOffer ID des Angebot
	 * @return Colletion mit Lieferantbestellungdetails
	 * @throws LengthOverflowException
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Set<ClientOfferDetail> updateOrderArticle(JTable table, long idClientOffer) throws LengthOverflowException;
	
	/**
	 * Löcht alle Artikeln vom Angebot.
	 * 
	 * @param idClientOffer ID des Angebot
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteAllArticles(long idClientOffer);
}
