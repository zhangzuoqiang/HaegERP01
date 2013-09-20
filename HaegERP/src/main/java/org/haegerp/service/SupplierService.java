package org.haegerp.service;

import java.util.List;

import org.haegerp.entity.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Diese Klasse besitzt die Geschäftslogik von dem Lieferanten.
 *
 * @author Fabio Codinha
 *
 */
@Service
@Transactional
public interface SupplierService {

    /**
     * Bekommt alle Lieferanten in einer Liste.
     *
     * @return Liste mit den Lieferanten.
     */
    public List<Supplier> getAllSuppliers();

    /**
     * Holt die Seite
     *
     * @return Aktueller Stand der Seite
     */
    public Page<Supplier> getPage();

    /**
     * Wickelt den Inhalt der Tabelle ab
     *
     * @param size Wie viele Lieferanten will der Benutzer in der Seite
     * @return Objekt mit dem Inhalt von der Tabelle
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Object[][] loadTableRows(int value);

    /**
     * Diese Methode vorbereitet die Suche
     *
     * @param text Wert, der der Benutzer geschrieben hat
     * @param size Wie viele Lieferanten will der Benutzer in der Seite
     */
    public void setSearch(String text, int size);

    /**
     * Lädt die nächste Seite
     *
     * @param size Wie viele Lieferanten will der Benutzer in der Seite
     * @return True - Es gibt die nächste Seite; False - Sonst
     */
    public boolean getNextPage(int size);

    /**
     * Lädt die vorherige Seite
     *
     * @param size Wie viele Lieferanten will der Benutzer in der Seite
     * @return True - Es gibt die nächste Seite; False - Sonst
     */
    public boolean getPreviousPage(int size);

    /**
     * Diese Methode gibt die erste Seite
     *
     * @param size Wie viele Lieferanten will der Benutzer in der Seite
     */
    public boolean getFirstPage(int size);

    /**
     * Eine neue Seite wird erhalt
     *
     * @param size Grösse der Seite
     * @return Seite mit den Lieferanten
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Page<Supplier> loadPage(int size);

    /**
     * Der Lieferant wird gespeichert
     *
     * @param supplier Der Lieferant
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Supplier save(Supplier supplier);

    /**
     * Der Liferant wird gelöscht
     *
     * @param supplier Der Lieferant
     */
    public void delete(Supplier supplier);

    /**
     * Holt den Lieferanten mit dem passenden ID
     *
     * @param idSupplier ID des Lieferanten
     * @return Lieferant
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Supplier getSupplierById(long idSupplier);

    /**
     * Wickelt den Inhalt der Tabelle ab
     *
     * @param enableSearch 1 - Wenn das Feld 'Search' etwas hat; 0 - Sonst
     * @param search Suchen, die der Benutzer eingefügt hat
     * @param enableAll 1 - Wenn das Feld 'enableSearch' 0 ist; 0 - Sonst
     * @return Objekt mit dem Inhalt von der Tabelle
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Object[][] loadAllTableRows(int enableSearch, String search, int enableAll);

    /**
     * Hat der Lieferant keine Bestellungen?
     *
     * @param idSupplier ID des Lieferanten.
     * @return True - Wenn der Lieferant keine Bestellung hat; False - Sonst.
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean isSupplierOrdersEmpty(long idSupplier);
}
