package org.haegerp.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Der Kunde ist ein Geschäftspartner und muss "Extend" ihn.<br/>
 * Er muss zu einer Kundenkategorie gehören.<br/>
 * Mit ihm kann man neue Kundenbestellungen.<br/>
 *
 * @author Fabio Codinha
 *
 */
public class Client extends BusinessPartner implements Serializable {

    private static final long serialVersionUID = -2499902175023899805L;
    //(Many-To-One) Kategorie, dass Kunde gehört (Erforderlich)
    private ClientCategory clientCategory;
    //ID des Mitarbeiter, der erstellt hat order geändert
    private Long idEmployeeModify;
    //Datum von der letzten Änderung
    private Date lastModifiedDate;
    //Angeboten, dass der Benutzer zu diesen Kunden gemacht hat.
    private Set<ClientOffer> offers = new HashSet<ClientOffer>(0);

    /**
     * Konstruktor mit keinen Parametern Ideal für einen neuen Kunde
     */
    public Client() {
        super();
    }

    /**
     *
     * @return clientCategory - Kategorie, dass Kunde gehört (Erforderlich)
     */
    public ClientCategory getClientCategory() {
        return clientCategory;
    }

    /**
     *
     * @param clientCategory Kategorie, dass Kunde gehört (Erforderlich)
     */
    public void setClientCategory(ClientCategory clientCategory) {
        this.clientCategory = clientCategory;
    }

    /**
     *
     * @return idEmployeeModify - ID des Mitarbeiter, der erstellt hat order
     * geändert
     */
    public Long getIdEmployeeModify() {
        return idEmployeeModify;
    }

    /**
     *
     * @param idEmployeeModify ID des Mitarbeiter, der erstellt hat order
     * geändert
     */
    public void setIdEmployeeModify(Long idEmployeeModify) {
        this.idEmployeeModify = idEmployeeModify;
    }

    /**
     *
     * @return lastModifiedDate - Datum von der letzten Änderung
     */
    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    /**
     *
     * @param lastModifiedDate Datum von der letzten Änderung
     */
    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    /**
     * @return offers - Angeboten, dass der Benutzer zu diesen Kunden gemacht
     * hat.
     */
    public Set<ClientOffer> getOffers() {
        return offers;
    }

    /**
     * @param offers Angeboten, dass der Benutzer zu diesen Kunden gemacht hat.
     */
    public void setOffers(Set<ClientOffer> offers) {
        this.offers = offers;
    }
}
