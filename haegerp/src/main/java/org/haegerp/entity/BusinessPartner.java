package org.haegerp.entity;

import java.io.Serializable;

public abstract class BusinessPartner implements Serializable {

	private static final long serialVersionUID = 1526098575049425791L;

	//Primary Key (Erforderlich - Automatisch)
	private long idBusinessPartner;
	
	//Geschäftspartners Name (Erforderlich)
	private String name;
	
	//Geschäftspartners Steuernummer (Erforderlich)
	private long taxId;
	
	//Geschäftspartners Adresse (Erforderlich)
	private String address;
	
	//Geschäftspartners Postleitzahl (Erforderlich)
	private String zipCode;
	
	//Geschäftspartners Stadt (Erforderlich)
	private String city;
	
	//Geschäftspartners Bundesland
	private String region;
	
	//Geschäftspartners Land (Erforderlich)
	private String country;
	
	//Geschäftspartners Email (Erforderlich)
	private String email;
	
	//Geschäftspartners Telefonnummer
	private String phoneNumber;
	
	//Geschäftspartners Handynummer
	private String mobileNumber;
	
	//Geschäftspartners Faxnummer
	private String faxNumber;
	
	//Geschäftspartners Beschreibung
	private String description;
	
	/**
	 * Konstruktor mit keinen Parametern
	 * Ideal für einen neuen Geschäftspartner
	 */
	public BusinessPartner() {
	}
	
	/**
	 * 
	 * @return idBusinessPartners - Primary Key (Erforderlich - Automatisch)
	 */
	public long getIdBusinessPartner() {
		return idBusinessPartner;
	}

	/**
	 * 
	 * @param idBusinessPartner Primary Key (Erforderlich - Automatisch)
	 */
	public void setIdBusinessPartner(long idBusinessPartner) {
		this.idBusinessPartner = idBusinessPartner;
	}

	/**
	 * 
	 * @return name - Geschäftspartners Name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name Geschäftspartners Name (Erforderlich)
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return taxId - Geschäftspartners Steuernummer
	 */
	public long getTaxId() {
		return taxId;
	}

	/**
	 * 
	 * @param taxId Geschäftspartners Steuernummer (Erforderlich)
	 */
	public void setTaxId(long taxId) {
		this.taxId = taxId;
	}

	/**
	 * 
	 * @return Address - Geschäftspartners Adresse
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * 
	 * @param address Geschäftspartners Adresse (Erforderlich)
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 
	 * @return zipCode - Geschäftspartners Postleitzahl
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * 
	 * @param zipCode Geschäftspartners Postleitzahl (Erforderlich)
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * 
	 * @return cite - Geschäftspartners Stadt
	 */
	public String getCity() {
		return city;
	}

	/**
	 * 
	 * @param city Geschäftspartners Stadt (Erforderlich)
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * 
	 * @return region - Geschäftspartners Bundesland
	 */
	public String getRegion() {
		return region;
	}

	/**
	 * 
	 * @param region Geschäftspartners Bundesland
	 */
	public void setRegion(String region) {
		this.region = region;
	}

	/**
	 * 
	 * @return country - Geschäftspartners Land (Erforderlich)
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * 
	 * @param country Geschäftspartners Land (Erforderlich)
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * 
	 * @return email - Geschäftspartners Email (Erforderlich)
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 
	 * @param email Geschäftspartners Email (Erforderlich)
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 
	 * @return phoneNumber - Geschäftspartners Telefonnummer
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * 
	 * @param phoneNumber Geschäftspartners Telefonnummer
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * 
	 * @return mobileNumber - Geschäftspartners Handynummer
	 */
	public String getMobileNumber() {
		return mobileNumber;
	}

	/**
	 * 
	 * @param mobileNumber Geschäftspartners Handynummer
	 */
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	/**
	 * 
	 * @return faxNumber - Geschäftspartners Faxnummer
	 */
	public String getFaxNumber() {
		return faxNumber;
	}

	/**
	 * 
	 * @param faxNumber Geschäftspartners Faxnummer
	 */
	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	/**
	 * 
	 * @return description - Geschäftspartners Beschreibung
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 
	 * @param description Geschäftspartners Beschreibung
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idBusinessPartner ^ (idBusinessPartner >>> 32));
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
		BusinessPartner other = (BusinessPartner) obj;
		if (idBusinessPartner != other.idBusinessPartner)
			return false;
		return true;
	}
}
