package org.haegerp.entity;

import java.io.Serializable;

public class Client extends BusinessPartner implements Serializable {

	private static final long serialVersionUID = -2499902175023899805L;
	
	//(Many-To-One) Kategorie, dass Kunde gehört (Erforderlich)
	private ClientCategory clientCategory;

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
}
