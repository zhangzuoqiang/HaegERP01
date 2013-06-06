package org.haegerp.entity;

import java.io.Serializable;

public class BusinessPartner implements Serializable {

	private static final long serialVersionUID = 1526098575049425791L;

	private int idBusinessPartner;
	private String name;
	private long taxId;
	private String address;
	private String zipCode;
	private String city;
	private String region;
	private String country;
	private String phoneNumber;
	private String mobileNumber;
	private String faxNumber;
	private String description;
	
	/**
	 * Konstruktor mit keinen Parametern
	 * Ideal für einen neuen Geschäftspartner
	 */
	public BusinessPartner() {
	}
	
	/**
	 * Konstruktor, dass benutzt wird vom Hibernate
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
	public BusinessPartner(int idBusinessPartner, String name, long taxId,
			String address, String zipCode, String city, String region,
			String country, String phoneNumber, String mobileNumber,
			String faxNumber, String description) {
		this.idBusinessPartner = idBusinessPartner;
		this.name = name;
		this.taxId = taxId;
		this.address = address;
		this.zipCode = zipCode;
		this.city = city;
		this.region = region;
		this.country = country;
		this.phoneNumber = phoneNumber;
		this.mobileNumber = mobileNumber;
		this.faxNumber = faxNumber;
		this.description = description;
	}

	public int getIdBusinessPartner() {
		return idBusinessPartner;
	}

	public void setIdBusinessPartner(int idBusinessPartner) {
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
}
