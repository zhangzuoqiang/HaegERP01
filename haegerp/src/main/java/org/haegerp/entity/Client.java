package org.haegerp.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Der Kunde ist ein Geschäftspartner und muss "Extend" ihn.<br/>
 * Er muss zu einer Kundenkategorie gehören.<br/>
 * Mit ihm kann man neue Kundenbestellungen.<br/>
 * 
 * @author Wolf
 *
 */
public class Client extends BusinessPartner implements Serializable {

	private static final long serialVersionUID = -2499902175023899805L;
	
	//(Many-To-One) Kategorie, dass Kunde gehört (Erforderlich)
	private ClientCategory clientCategory;
	//ID des Mitarbeiter, der erstellt hat order geändert
	private Long idEmployeeModify;
	//Datum von der letzten Änderung
	private Date  lastModifiedDate;

	/**
	 * Konstruktor mit keinen Parametern
	 * Ideal für einen neuen Kunde
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
	 * @return idEmployeeModify - ID des Mitarbeiter, der erstellt hat order geändert
	 */
	public Long getIdEmployeeModify() {
		return idEmployeeModify;
	}

	/**
	 * 
	 * @param idEmployeeModify ID des Mitarbeiter, der erstellt hat order geändert
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
}
