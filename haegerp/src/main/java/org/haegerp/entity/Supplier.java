package org.haegerp.entity;

import java.io.Serializable;

public class Supplier extends BusinessPartner implements Serializable {

	private static final long serialVersionUID = -7383190921739350244L;
	
	/**
	 * Konstruktor mit keinen Parametern
	 * Ideal für einen neuen Lieferant
	 */
	public Supplier() {
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
	 */
	public Supplier(int idBusinessPartner, String name, long taxId,
			String address, String zipCode, String city, String region,
			String country, String phoneNumber, String mobileNumber,
			String faxNumber, String description) {
		super(idBusinessPartner, name, taxId, address, zipCode, city, region, country,
				phoneNumber, mobileNumber, faxNumber, description);
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
