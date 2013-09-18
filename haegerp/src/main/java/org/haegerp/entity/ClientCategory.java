package org.haegerp.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.haegerp.exception.LengthOverflowException;

/**
 * Die Kundenkategorie gruppiert die Kunden.<br/>
 * 
 * @author Fabio Codinha
 *
 */
public class ClientCategory implements Serializable {

	private static final long serialVersionUID = -4809347561860652303L;

	//Primary Key (Erforderlich - Automatisch)
	private long idClientCategory;
	//Name von der Artikelkategorie (Erforderlich)
	private String name;
	//Kundenkategorie Beschreibung
	private String description;
	//(One-To-Many) Kunden, dass angeschlossen werden mit der Kundenkategorie
	private Set<Client> clients = new HashSet<Client>(0);
	//ID des Mitarbeiter, der erstellt hat order geändert
	private Long idEmployeeModify;
	//Datum von der letzten Änderung
	private Date  lastModifiedDate;
		
	/**
	 * Konstruktor mit keinen Parametern
	 * Ideal für eine neue Kundenkategorie
	 */
	public ClientCategory() {
	}
	
	/**
	 * 
	 * @return idClientCategory - Primary Key (Erforderlich - Automatisch)
	 */
	public long getIdClientCategory() {
		return idClientCategory;
	}

	/**
	 * 
	 * @param idClientCategory Primary Key (Erforderlich - Automatisch)
	 */
	public void setIdClientCategory(long idClientCategory) {
		this.idClientCategory = idClientCategory;
	}

	/**
	 * 
	 * @return name - Kundenkategorie Name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name Kundenkategorie Name (Erforderlich)
	 * @throws LengthOverflowException 
	 */
	public void setName(String name) throws LengthOverflowException {
		if (name.length() > 50)
			throw new LengthOverflowException("Name");
		this.name = name;
	}

	/**
	 * 
	 * @return description - Kundenkategorie Beschreibung
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 
	 * @param description Kundenkategorie Beschreibung
	 * @throws LengthOverflowException 
	 */
	public void setDescription(String description) throws LengthOverflowException {
		if (description != null && description.length() > 256)
			throw new LengthOverflowException("Description");
		this.description = description;
	}

	/**
	 * 
	 * @return articles - Kunden, dass angeschlossen werden mit der Kundenkategorie
	 */
	public Set<Client> getClients() {
		return clients;
	}

	/**
	 * 
	 * @param articles Kunden, dass angeschlossen werden mit der Kundenkategorie
	 */
	public void setClients(Set<Client> clients) {
		this.clients = clients;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idClientCategory ^ (idClientCategory >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClientCategory other = (ClientCategory) obj;
		if (idClientCategory != other.idClientCategory)
			return false;
		return true;
	}
}
