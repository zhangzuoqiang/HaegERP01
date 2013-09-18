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
 * Diese Klasse besitzt die Geschäftslogik von den Artikeln des Kundenangebots.
 * 
 * @author Fabio Codinha
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
	 * Eine neue Seite wird geholt
	 * 
	 * @param idClientOffer ID des Kundenangebots
	 * @return Seite mit den Kundenangebotdetails
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public List<ClientOfferDetail> loadPage(long idClientOffer);
        
	/**
	 * Diese Methode aktualisiert die ausgewählte Artikeln der Bestellung.
         * 
	 * @param table Tabelle mit den Artikeln der Kundenbestellung.
	 * @param idClientOffer ID des Angebots
	 * @return Colletion mit Lieferantbestellungdetails
	 * @throws LengthOverflowException Wenn ein Wert Falsch ist.
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Set<ClientOfferDetail> updateOrderArticle(JTable table, long idClientOffer) throws LengthOverflowException;
	
	/**
	 * Löscht alle Artikeln vom Angebot.
	 * 
	 * @param idClientOffer ID des Angebots
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteAllArticles(long idClientOffer);
}
