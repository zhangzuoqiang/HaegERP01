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
 * Diese Klasse besitzt die Geschäftslogik von dem Kunden.
 * 
 * @author Wolf
 *
 */
@Service
@Transactional
public interface SupplierOrderDetailController {

	/**
	 * Wickelt den Inhalt der Tabelle ab
	 * @param size Wie viele Linea will der Benutzer in der Seite
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
	 * Die Lieferantbestellung wurde gespeichert
	 * @param supplier Die Lieferantbestellungdetails
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public SupplierOrderDetail save(SupplierOrderDetail supplierOrderDetail);

	/**
	 * Der Liferant wurde gelöscht
	 * @param supplier Die Lieferantbestellungdetails
	 */
	public void delete(SupplierOrderDetail supplierOrderDetail);
	
	/**
	 * Diese Methode aktualisiert einen Artikel der Bestellung.
	 * @param table Tabelle mit den Artikeln der Lieferantsbestellung.
	 * @param idSupplierOrder ID der Bestellung
	 * @return Colletion mit Lieferantbestellungdetails
	 * @throws LengthOverflowException
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Set<SupplierOrderDetail> updateOrderArticle(JTable table, long idSupplierOrder) throws LengthOverflowException;
	
	/**
	 * löcht alle Artikeln von der Bestellung
	 * 
	 * @param entities Artikeln
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteAllArticles(long idSupplierOrder);
}
