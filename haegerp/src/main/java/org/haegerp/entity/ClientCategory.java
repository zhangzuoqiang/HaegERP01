package org.haegerp.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class ClientCategory implements Serializable {

	private static final long serialVersionUID = -4809347561860652303L;

	private int idClientCategory;
	private String name;
	private String description;
	private Set<Client> clients = new HashSet<Client>(0);
	
	/**
	 * Konstruktor mit keinen Parametern
	 * Ideal für eine neue Kundenkategorie
	 */
	public ClientCategory() {
	}
	
	/**
	 * 
	 * @param idClientCategory Primary Key (Erforderlich - Automatisch)
	 * @param name Name von der Artikelkategorie (Erforderlich)
	 * @param description Kundenkategorie Beschreibung
	 * @param clients Kunden, dass angeschlossen werden mit der Kundenkategorie
	 */
	public ClientCategory(int idClientCategory, String name,
			String description, Set<Client> clients) {
		super();
		this.idClientCategory = idClientCategory;
		this.name = name;
		this.description = description;
		this.clients = clients;
	}
	
	public int getIdClientCategory() {
		return idClientCategory;
	}

	public void setIdClientCategory(int idClientCategory) {
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
}
