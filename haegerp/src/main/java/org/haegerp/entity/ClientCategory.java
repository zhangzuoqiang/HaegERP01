package org.haegerp.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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
	 */
	public void setName(String name) {
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
	 */
	public void setDescription(String description) {
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
