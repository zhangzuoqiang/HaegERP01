package org.haegerp.entity;

import java.io.Serializable;

public class Client extends BusinessPartner implements Serializable {

	private static final long serialVersionUID = -2499902175023899805L;
	
	private ClientCategory clientCategory;

	/**
	 * Konstruktor mit keinen Parametern
	 * Ideal für einen neuen Kunde
	 */
	public Client() {
		super();
	}

	/**
	 * Konstruktor, dass benutzt wird vom Hibernate.
	 * 
	 * @param idBusinessPartner Primary Key (Erforderlich - Automatisch)
	 * @param name Geschäftspartners Name (Erforderlich)
	 * @param taxId Geschäftspartners Steuernummer (Erforderlich)
	 * @param address Geschäftspartners Adresse (Erforderlich)
	 * @param zipCode Geschäftspartners Postleitzahl (Erforderlich)
	 * @param city Geschäftspartners Stadt (Erforderlich)
	 * @param region Geschäftspartners Bundesland
	 * @param country Geschäftspartners Land (Erforderlich)
	 * @param phoneNumber Geschäftspartners Telefonnummer
	 * @param mobileNumber Geschäftspartners Handynummer
	 * @param faxNumber Geschäftspartners Faxnummer
	 * @param description Geschäftspartners Beschreibung
	 * @param clientCategory Kategorie, dass Kunde gehört (Erforderlich)
	 */
	public Client(int idBusinessPartner, String name, long taxId,
			String address, String zipCode, String city, String region,
			String country, String phoneNumber, String mobileNumber,
			String faxNumber, String description, ClientCategory clientCategory) {
		super(idBusinessPartner, name, taxId, address, zipCode, city, region,
				country, phoneNumber, mobileNumber, faxNumber, description);
		this.clientCategory = clientCategory;
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
