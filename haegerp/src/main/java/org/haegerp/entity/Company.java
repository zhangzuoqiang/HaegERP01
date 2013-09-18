package org.haegerp.entity;

import java.io.Serializable;
import java.util.Date;

import org.haegerp.exception.LengthOverflowException;

/**
 * Hier bearbeitet man über die Information, die zu der Firma gehört.<br/>
 * 
 * @author Fabio Codinha
 *
 */
public class Company implements Serializable {

	private static final long serialVersionUID = 2462404964347485630L;

	//Primary Key (Erforderlich - Automatisch)
	private long idCompany;
	//Name der Unternehmen (Erforderlich)
	private String name;
	//Die Unternehmen Steuernummer (Erforderlich)
	private Long taxId;
	//Die Unternehmen Adresse (Erforderlich)
	private String address;
	//Fürher der Unternehmen
	private String owner;
	//Bereich der Unternehmen
	private String sector;
	//Die Unternehmen Postleitzahl (Erforderlich)
	private String zipCode;
	//Die Unternehmen Stadt (Erforderlich)
	private String city;
	//Die Unternehmen Bundesland
	private String region;
	//Die Unternehmen Land (Erforderlich)
	private String country;
	//Die Unternehmen Email
	private String email;
	//Die Unternehmen Telefonnummer
	private String phoneNumber;
	//Die Unternehmen Faxnummer
	private String faxNumber;
	//ID des Mitarbeiter, der erstellt hat order geändert
	private Long idEmployeeModify;
	//Datum von der letzten Änderung
	private Date  lastModifiedDate;
	
	/**
	 * Konstruktor mit keinen Parametern
	 * Ideal für einen neuen Geschäftspartner
	 */
	public Company() {
	}

	/**
	 * 
	 * @return idCompany - Primary Key (Erforderlich - Automatisch)
	 */
	public long getIdCompany() {
		return idCompany;
	}

	/**
	 * 
	 * @param idCompany Primary Key (Erforderlich - Automatisch)
	 */
	public void setIdCompany(long idCompany) {
		this.idCompany = idCompany;
	}

	/**
	 * 
	 * @return name - Name der Unternehmen (Erforderlich)
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name Name der Unternehmen (Erforderlich)
	 * @throws LengthOverflowException 
	 */
	public void setName(String name) throws LengthOverflowException {
		if (name != null && name.length() > 50)
			throw new LengthOverflowException("Name");
		this.name = name;
	}

	/**
	 * 
	 * @return taxId - Die Unternehmen Steuernummer (Erforderlich)
	 */
	public Long getTaxId() {
		return taxId;
	}

	/**
	 * 
	 * @param taxId Die Unternehmen Steuernummer (Erforderlich)
	 * @throws LengthOverflowException 
	 */
	public void setTaxId(Long taxId) throws LengthOverflowException {
		if (taxId != null){
                    if (taxId < 0 || taxId > Long.MAX_VALUE)
                        throw new LengthOverflowException("TaxID");
                    this.taxId = taxId;
		} else {
			this.taxId = null;
		}
	}

	/**
	 * 
	 * @return address - Die Unternehmen Adresse (Erforderlich)
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * 
	 * @param address Die Unternehmen Adresse (Erforderlich)
	 * @throws LengthOverflowException 
	 */
	public void setAddress(String address) throws LengthOverflowException {
		if (address != null && address.length() > 100)
			throw new LengthOverflowException("Address");
		this.address = address;
	}

	/**
	 * 
	 * @return owner - Fürher der Unternehmen
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * 
	 * @param owner Fürher der Unternehmen
	 * @throws LengthOverflowException 
	 */
	public void setOwner(String owner) throws LengthOverflowException {
		if (owner != null && owner.length() > 100)
			throw new LengthOverflowException("Owner");
		this.owner = owner;
	}

	/**
	 * 
	 * @return sector - Bereich der Unternehmen
	 */
	public String getSector() {
		return sector;
	}

	/**
	 * 
	 * @param sector Bereich der Unternehmen
	 * @throws LengthOverflowException 
	 */
	public void setSector(String sector) throws LengthOverflowException {
		if (sector != null && sector.length() > 100)
			throw new LengthOverflowException("Sector");
		this.sector = sector;
	}

	/**
	 * 
	 * @return zipCode - Die Unternehmen Postleitzahl (Erforderlich)
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * 
	 * @param zipCode Die Unternehmen Postleitzahl (Erforderlich)
	 * @throws LengthOverflowException 
	 */
	public void setZipCode(String zipCode) throws LengthOverflowException {
		if (zipCode != null && zipCode.length() > 15)
			throw new LengthOverflowException("ZipCode");
		this.zipCode = zipCode;
	}

	/**
	 * 
	 * @return city - Die Unternehmen Stadt (Erforderlich)
	 */
	public String getCity() {
		return city;
	}

	/**
	 * 
	 * @param city Die Unternehmen Stadt (Erforderlich)
	 * @throws LengthOverflowException 
	 */
	public void setCity(String city) throws LengthOverflowException {
		if (city != null && city.length() > 30)
			throw new LengthOverflowException("City");
		this.city = city;
	}

	/**
	 * 
	 * @return region - Die Unternehmen Bundesland
	 */
	public String getRegion() {
		return region;
	}

	/**
	 * 
	 * @param region Die Unternehmen Bundesland
	 * @throws LengthOverflowException 
	 */
	public void setRegion(String region) throws LengthOverflowException {
		if (region != null && region.length() > 50)
			throw new LengthOverflowException("Region");
		this.region = region;
	}

	/**
	 * 
	 * @return country - Die Unternehmen Land (Erforderlich)
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * 
	 * @param country Die Unternehmen Land (Erforderlich)
	 * @throws LengthOverflowException 
	 */
	public void setCountry(String country) throws LengthOverflowException {
		if (country != null && country.length() > 30)
			throw new LengthOverflowException("Country");
		this.country = country;
	}

	/**
	 * 
	 * @return Email - Die Unternehmen Email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 
	 * @param email Die Unternehmen Email
	 * @throws LengthOverflowException 
	 */
	public void setEmail(String email) throws LengthOverflowException {
		if (email != null && email.length() > 50)
			throw new LengthOverflowException("Email");
		this.email = email;
	}

	/**
	 * 
	 * @return phoneNumber - Die Unternehmen Telefonnummer
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * 
	 * @param phoneNumber Die Unternehmen Telefonnummer
	 * @throws LengthOverflowException 
	 */
	public void setPhoneNumber(String phoneNumber) throws LengthOverflowException {
		if (phoneNumber != null && phoneNumber.length() > 20)
			throw new LengthOverflowException("PhoneNumber");
		this.phoneNumber = phoneNumber;
	}

	/**
	 * 
	 * @return faxNumber - Die Unternehmen Faxnummer
	 */
	public String getFaxNumber() {
		return faxNumber;
	}

	/**
	 * 
	 * @param faxNumber Die Unternehmen Faxnummer
	 * @throws LengthOverflowException 
	 */
	public void setFaxNumber(String faxNumber) throws LengthOverflowException {
		if (faxNumber != null && faxNumber.length() > 20)
			throw new LengthOverflowException("FaxNumber");
		this.faxNumber = faxNumber;
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
		result = prime * result + (int) (idCompany ^ (idCompany >>> 32));
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
		Company other = (Company) obj;
		if (idCompany != other.idCompany)
			return false;
		return true;
	}
	
}
