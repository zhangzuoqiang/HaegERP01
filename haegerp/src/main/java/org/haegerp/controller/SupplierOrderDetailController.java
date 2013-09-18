package org.haegerp.controller;

import java.util.List;
import java.util.Set;

import javax.swing.JTable;

import org.haegerp.entity.SupplierOrderDetail;
import org.haegerp.exception.LengthOverflowException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Diese Klasse besitzt die Geschäftslogik von den Lieferantenbestellungdetails.
 * 
 * @author Fabio Codinha
 *
 */
@Service
@Transactional
public interface SupplierOrderDetailController {

	/**
	 * Wickelt den Inhalt der Tabelle ab
	 * @param idSupplierOrder ID der Lieferantenbestellung
	 * @return Objekt mit dem Inhalt von der Tabelle
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Object[][] loadTableRows(long idSupplierOrder);

	/**
	 * Eine neue Seite wird erhalt
	 * 
	 * @param size Grösse der Seite
	 * @return Seite mit den Lieferantbestellungdetails
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public List<SupplierOrderDetail> loadPage(long idSupplierOrder);

	/**
	 * Die Lieferantbestellung wird gespeichert
	 * @param supplierOrderDetail Die Lieferantbestellungdetails
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public SupplierOrderDetail save(SupplierOrderDetail supplierOrderDetail);
	
	/**
	 * Diese Methode aktualisiert einen Artikel der Bestellung.
	 * @param table Tabelle mit den Artikeln der Lieferantsbestellung.
	 * @param idSupplierOrder ID der Bestellung
	 * @return Colletion mit Lieferantbestellungdetails
	 * @throws LengthOverflowException Wenn ein Wert Falsch ist
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Set<SupplierOrderDetail> updateOrderArticle(JTable table, long idSupplierOrder) throws LengthOverflowException;
	
	/**
	 * Löscht alle Artikeln von der Bestellung
	 * 
	 * @param idSupplierOrder ID der Lieferantsbestellung
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteAllArticles(long idSupplierOrder);
        
        /**
         * Diese Methode aktualisiert die Sammlung der Artikel.
         * <br/>
         * [0] - ArticleHistoryPK<br/>
         * [1] - Quantity<br/>
         * [2] - Discount
         * 
         * @param values Objekt mit den Informationen der Artikel
         * @param idSupplierOrder Welche Lieferantenbestellung.
         * @throws LengthOverflowException Wenn ein Wert Falsch ist
         * @return Sammlung
         */
        @Transactional(propagation = Propagation.REQUIRED)
        public Set<SupplierOrderDetail> doUpdateOrderArticle(Object[][] values, long idSupplierOrder) throws LengthOverflowException ;
}
