package org.haegerp.entity;

import java.io.Serializable;
import java.util.Date;

import org.haegerp.exception.LengthOverflowException;

/**
 * Der Geschäftspartner ist eine Abstract Klasse.<br/>
 * Er muss erbt werden.<br/>
 * 
 * @author Wolf
 *
 */
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
	//ID des Mitarbeiter, der erstellt hat order geändert
	private Long idEmployeeModify;
	//Datum von der letzten Änderung
	private Date  lastModifiedDate;
	
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
	 * @throws LengthOverflowException 
	 */
	public void setName(String name) throws LengthOverflowException {
		if (name.length() > 100)
			throw new LengthOverflowException("Name");
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
	 * @throws LengthOverflowException 
	 */
	public void setTaxId(long taxId) throws LengthOverflowException {
		if (taxId > Long.MAX_VALUE || taxId < 0)
			throw new LengthOverflowException("TaxId");
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
	 * @throws LengthOverflowException 
	 */
	public void setAddress(String address) throws LengthOverflowException {
		if (address.length() > 100)
			throw new LengthOverflowException("TaxId");
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
	 * @throws LengthOverflowException 
	 */
	public void setZipCode(String zipCode) throws LengthOverflowException {
		if (zipCode.length() > 12)
			throw new LengthOverflowException("TaxId");
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
	 * @throws LengthOverflowException 
	 */
	public void setCity(String city) throws LengthOverflowException {
		if (city.length() > 50)
			throw new LengthOverflowException("City");
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
	 * @throws LengthOverflowException 
	 */
	public void setRegion(String region) throws LengthOverflowException {
		if (region != null && region.length() > 50)
			throw new LengthOverflowException("Region");
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
	 * @throws LengthOverflowException 
	 */
	public void setCountry(String country) throws LengthOverflowException {
		if (country.length() > 50)
			throw new LengthOverflowException("Country");
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
	 * @throws LengthOverflowException 
	 */
	public void setEmail(String email) throws LengthOverflowException {
		if (email.length() > 50)
			throw new LengthOverflowException("E-Mail");
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
	 * @throws LengthOverflowException 
	 */
	public void setPhoneNumber(String phoneNumber) throws LengthOverflowException {
		if (phoneNumber != null && phoneNumber.length() > 20)
			throw new LengthOverflowException("PhoneNumber");
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
	 * @throws LengthOverflowException 
	 */
	public void setMobileNumber(String mobileNumber) throws LengthOverflowException {
		if (mobileNumber != null && mobileNumber.length() > 20)
			throw new LengthOverflowException("MobileNumber");
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
	 * @throws LengthOverflowException 
	 */
	public void setFaxNumber(String faxNumber) throws LengthOverflowException {
		if (faxNumber != null && faxNumber.length() > 20)
			throw new LengthOverflowException("FaxNumber");
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
	 * @throws LengthOverflowException 
	 */
	public void setDescription(String description) throws LengthOverflowException {
		if (description != null && description.length() > 256)
			throw new LengthOverflowException("Description");
		this.description = description;
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
