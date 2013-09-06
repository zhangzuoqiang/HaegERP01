package org.haegerp.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Der Lieferant ist ein Geschäftspartner.<br/>
 * Zu ihnen kann man die Artikel kaufen.<br/>
 *
 * @author Wolf
 *
 */
public class Supplier extends BusinessPartner implements Serializable {

    private static final long serialVersionUID = -7383190921739350244L;
    //ID des Mitarbeiter, der erstellt hat order geändert
    private Long idEmployeeModify;
    //Datum von der letzten Änderung
    private Date lastModifiedDate;
    //Bestellungen, dass der Benutzer zu diesen Lieferanten gemacht hat.
    private Set<SupplierOrder> orders = new HashSet<SupplierOrder>(0);

    /**
     * Konstruktor mit keinen Parametern Ideal für einen neuen Lieferant
     */
    public Supplier() {
        super();
    }

    /**
     *
     * @return idEmployeeModify - ID des Mitarbeiter, der erstellt hat order
     * geändert
     */
    @Override
    public Long getIdEmployeeModify() {
        return idEmployeeModify;
    }

    /**
     *
     * @param idEmployeeModify ID des Mitarbeiter, der erstellt hat order
     * geändert
     */
    @Override
    public void setIdEmployeeModify(Long idEmployeeModify) {
        this.idEmployeeModify = idEmployeeModify;
    }

    /**
     *
     * @return lastModifiedDate - Datum von der letzten Änderung
     */
    @Override
    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    /**
     *
     * @param lastModifiedDate Datum von der letzten Änderung
     */
    @Override
    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    /**
     * @return orders - Bestellungen, dass der Benutzer zu diesen Lieferanten
     * gemacht hat.
     */
    public Set<SupplierOrder> getOrders() {
        return orders;
    }

    /**
     * @param orders Bestellungen, dass der Benutzer zu diesen Lieferanten
     * gemacht hat.
     */
    public void setOrders(Set<SupplierOrder> orders) {
        this.orders = orders;
    }
}
