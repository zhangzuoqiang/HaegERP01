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
	 * Löscht alle Artikeln vom Angebot.
	 * 
	 * @param idClientOffer ID des Angebots
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteAllArticles(long idClientOffer);
        
        /**
         * Diese Methode aktualisiert die Sammlung der Artikel.
         * <br/>
         * [0] - ArticleHistoryPK<br/>
         * [1] - Quantity<br/>
         * [2] - Discount
         * 
         * @param values Objekt mit den Informationen der Artikel
         * @param idSupplierOrder Welche Kundenbestellung.
         * @throws LengthOverflowException Wenn ein Wert Falsch ist
         * @return Sammlung
         */
        @Transactional(propagation = Propagation.REQUIRED)
        public Set<ClientOfferDetail> doUpdateOfferArticle(Object[][] values, long idClientOffer) throws LengthOverflowException ;
}
