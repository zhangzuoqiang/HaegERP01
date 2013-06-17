package org.haegerp.entity;

import java.io.Serializable;

import org.haegerp.exception.LengthOverflowException;

/**
 * Die Mitarbeiter sind die Benutzer von dem System, sie ertellen neue Kunden, Bestellungen, usw.<br/>
 * 
 * @author Wolf
 *
 */
public class Employee implements Serializable {

	private static final long serialVersionUID = 925767297886028673L;

	//Primary Key (Erforderlich - Automatisch)
	private long idEmployee;
	//(Many-To-One) Gehaltkategorie, dass Mitarbeiter gehört (Erforderlich)
	private SalaryCategory salaryCategory;
	//(Many-To-One) Division, dass Mitarbeiter gehört (Erforderlich)
	private Division division;
	//(Many-To-One) Benutzergruppe, dass Mitarbeiter gehört (Erforderlich)
	private UserGroup userGroup;
	//Ausweisnummer vom Mitarbeiter (Erforderlich)
	private long idCard;
	//Name vom Mitarbeiter (Erforderlich)
	private String name;
	//Adresse vom Mitarbeiter (Erforderlich)
	private String address;
	//Postleitzahl von der Adresse des Mitarbeiter (Erforderlich)
	private String zipCode;
	//Stadt von der Adresse des Mitarbeiter (Erforderlich)
	private String city;
	//Bundesland von der Adresse des Mitarbeiter
	private String region;
	//Land vom Mitarbeiter (Erforderlich)
	private String country;
	//Email vom Mitarbeiter (Erforderlich)
	private String email;
	//Telefonnummer vom Mitarbeiter
	private String phoneNumber;
	//Handynummer vom Mitarbeiter
	private String mobileNumber;
	//Benutzername (Erforderlich)
	private String username;
	//Kenntwort (Erforderlich)
	private String password;
	
	/**
	 * Konstruktor mit keinen Parametern
	 * Ideal für einen neuen Mitarbeiter
	 */
	public Employee() {
	}

	/**
	 * 
	 * @return idEmployee - Primary Key (Erforderlich - Automatisch)
	 */
	public long getIdEmployee() {
		return idEmployee;
	}

	/**
	 * 
	 * @param idEmployee Primary Key (Erforderlich - Automatisch)
	 */
	public void setIdEmployee(long idEmployee) {
		this.idEmployee = idEmployee;
	}

	/**
	 * 
	 * @return salaryCategory - Gehaltkategorie, dass Mitarbeiter gehört (Erforderlich)
	 */
	public SalaryCategory getSalaryCategory() {
		return salaryCategory;
	}

	/**
	 * 
	 * @param salaryCategory Gehaltkategorie, dass Mitarbeiter gehört (Erforderlich)
	 */
	public void setSalaryCategory(SalaryCategory salaryCategory) {
		this.salaryCategory = salaryCategory;
	}

	/**
	 * 
	 * @return division - Division, dass Mitarbeiter gehört (Erforderlich)
	 */
	public Division getDivision() {
		return division;
	}

	/**
	 * 
	 * @param division Division, dass Mitarbeiter gehört (Erforderlich)
	 */
	public void setDivision(Division division) {
		this.division = division;
	}

	/**
	 * 
	 * @return userGroup - Benutzergruppe, dass Mitarbeiter gehört (Erforderlich)
	 */
	public UserGroup getUserGroup() {
		return userGroup;
	}

	/**
	 * 
	 * @param userGroup Benutzergruppe, dass Mitarbeiter gehört (Erforderlich)
	 */
	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}

	/**
	 * 
	 * @return idCard - Ausweisnummer vom Mitarbeiter (Erforderlich)
	 */
	public long getIdCard() {
		return idCard;
	}

	/**
	 * 
	 * @param idCard Ausweisnummer vom Mitarbeiter (Erforderlich)
	 * @throws LengthOverflowException 
	 */
	public void setIdCard(long idCard) throws LengthOverflowException {
		if (idCard > Long.MAX_VALUE || idCard < 0L)
			throw new LengthOverflowException("IdCard");
		this.idCard = idCard;
	}

	/**
	 * 
	 * @return name - Name vom Mitarbeiter (Erforderlich)
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name Name vom Mitarbeiter (Erforderlich)
	 * @throws LengthOverflowException 
	 */
	public void setName(String name) throws LengthOverflowException {
		if (name.length() > 100)
			throw new LengthOverflowException("Name");
		this.name = name;
	}

	/**
	 * 
	 * @return address - Adresse vom Mitarbeiter (Erforderlich)
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * 
	 * @param address Adresse vom Mitarbeiter (Erforderlich)
	 * @throws LengthOverflowException 
	 */
	public void setAddress(String address) throws LengthOverflowException {
		if (address.length() > 100)
			throw new LengthOverflowException("Address");
		this.address = address;
	}

	/**
	 * 
	 * @return zipCode - Postleitzahl von der Adresse des Mitarbeiter (Erforderlich)
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * 
	 * @param zipCode Postleitzahl von der Adresse des Mitarbeiter (Erforderlich)
	 * @throws LengthOverflowException 
	 */
	public void setZipCode(String zipCode) throws LengthOverflowException {
		if (zipCode.length() > 15)
			throw new LengthOverflowException("ZipCode");
		this.zipCode = zipCode;
	}

	/**
	 * 
	 * @return city - Stadt von der Adresse des Mitarbeiter (Erforderlich)
	 */
	public String getCity() {
		return city;
	}

	/**
	 * 
	 * @param city Stadt von der Adresse des Mitarbeiter (Erforderlich)
	 * @throws LengthOverflowException 
	 */
	public void setCity(String city) throws LengthOverflowException {
		if (city.length() > 30)
			throw new LengthOverflowException("City");
		this.city = city;
	}

	/**
	 * 
	 * @return region - Bundesland von der Adresse des Mitarbeiter
	 */
	public String getRegion() {
		return region;
	}

	/**
	 * 
	 * @param region Bundesland von der Adresse des Mitarbeiter
	 * @throws LengthOverflowException 
	 */
	public void setRegion(String region) throws LengthOverflowException {
		if (region.length() > 30)
			throw new LengthOverflowException("Region");
		this.region = region;
	}

	/**
	 * 
	 * @return country - Land vom Mitarbeiter (Erforderlich)
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * 
	 * @param country Land vom Mitarbeiter (Erforderlich)
	 * @throws LengthOverflowException 
	 */
	public void setCountry(String country) throws LengthOverflowException {
		if (country.length() > 30)
			throw new LengthOverflowException("Country");
		this.country = country;
	}

	/**
	 * 
	 * @return email - Email vom Mitarbeiter (Erforderlich)
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 
	 * @param email Email vom Mitarbeiter (Erforderlich)
	 * @throws LengthOverflowException 
	 */
	public void setEmail(String email) throws LengthOverflowException {
		if (email.length() > 50)
			throw new LengthOverflowException("E-Mail");
		this.email = email;
	}

	/**
	 * 
	 * @return phoneNumber - Telefonnummer vom Mitarbeiter
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * 
	 * @param phoneNumber Telefonnummer vom Mitarbeiter
	 * @throws LengthOverflowException 
	 */
	public void setPhoneNumber(String phoneNumber) throws LengthOverflowException {
		if (phoneNumber.length() > 20)
			throw new LengthOverflowException("PhoneNumber");
		this.phoneNumber = phoneNumber;
	}

	/**
	 * 
	 * @return mobileNumber - Handynummer vom Mitarbeiter
	 */
	public String getMobileNumber() {
		return mobileNumber;
	}

	/**
	 * 
	 * @param mobileNumber Handynummer vom Mitarbeiter
	 * @throws LengthOverflowException 
	 */
	public void setMobileNumber(String mobileNumber) throws LengthOverflowException {
		if (mobileNumber.length() > 20)
			throw new LengthOverflowException("MobileNumber");
		this.mobileNumber = mobileNumber;
	}

	/**
	 * 
	 * @return username - Benutzername (Erforderlich)
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * 
	 * @param username Benutzername (Erforderlich)
	 * @throws LengthOverflowException
	 */
	public void setUsername(String username) throws LengthOverflowException {
		if (username.length() > 50)
			throw new LengthOverflowException("Username");
		this.username = username;
	}

	/**
	 * 
	 * @return password - Kenntwort (Erforderlich)
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 
	 * @param password Kenntwort (Erforderlich)
	 * @throws LengthOverflowException 
	 */
	public void setPassword(String password) throws LengthOverflowException {
		if (password.length() > 32)
			throw new LengthOverflowException("Password");
		this.password = password;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idEmployee ^ (idEmployee >>> 32));
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
		Employee other = (Employee) obj;
		if (idEmployee != other.idEmployee)
			return false;
		return true;
	}
	
}
