package org.haegerp.entity;

import java.io.Serializable;

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
	 */
	public void setIdCard(long idCard) {
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
	 */
	public void setName(String name) {
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
	 */
	public void setAddress(String address) {
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
	 */
	public void setZipCode(String zipCode) {
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
	 */
	public void setCity(String city) {
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
	 */
	public void setRegion(String region) {
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
	 */
	public void setCountry(String country) {
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
	 */
	public void setEmail(String email) {
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
	 */
	public void setPhoneNumber(String phoneNumber) {
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
	 */
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
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
