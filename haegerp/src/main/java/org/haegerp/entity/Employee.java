package org.haegerp.entity;

import java.io.Serializable;

public class Employee implements Serializable {

	private static final long serialVersionUID = 925767297886028673L;

	private int idEmployee;
	private SalaryCategory salaryCategory;
	private Division division;
	private UserGroup userGroup;
	private EmployeeUser employeeUser;
	private long idCard;
	private String name;
	private String address;
	private String zipCode;
	private String city;
	private String region;
	private String country;
	private String email;
	private String phoneNumber;
	private String mobileNumber;
	
	/**
	 * Konstruktor mit keinen Parametern
	 * Ideal für einen neuen Mitarbeiter
	 */
	public Employee() {
	}

	/**
	 * 
	 * @param idEmployee Primary Key (Erforderlich - Automatisch)
	 * @param salaryCategory Gehaltkategorie, dass Mitarbeiter gehört (Erforderlich)
	 * @param division Division, dass Mitarbeiter gehört (Erforderlich)
	 * @param userGroup Benutzergruppe, dass Mitarbeiter gehört (Erforderlich)
	 * @param employeeUser Benutzers Angaben (Erforderlich)
	 * @param idCard Ausweisnummer vom Mitarbeiter (Erforderlich)
	 * @param name Name vom Mitarbeiter (Erforderlich)
	 * @param address Adresse vom Mitarbeiter (Erforderlich)
	 * @param zipCode Postleitzahl von der Adresse des Mitarbeiter (Erforderlich)
	 * @param city Stadt von der Adresse des Mitarbeiter (Erforderlich)
	 * @param region Bundesland von der Adresse des Mitarbeiter
	 * @param country Land vom Mitarbeiter (Erforderlich)
	 * @param email Email vom Mitarbeiter (Erforderlich)
	 * @param phoneNumber Telefonnummer vom Mitarbeiter
	 * @param mobileNumber Handynummer vom Mitarbeiter
	 */
	public Employee(int idEmployee, SalaryCategory salaryCategory,
			Division division, UserGroup userGroup, EmployeeUser employeeUser,
			long idCard, String name, String address, String zipCode, String city,
			String region, String country, String email, String phoneNumber,
			String mobileNumber) {
		this.idEmployee = idEmployee;
		this.salaryCategory = salaryCategory;
		this.division = division;
		this.userGroup = userGroup;
		this.employeeUser = employeeUser;
		this.idCard = idCard;
		this.name = name;
		this.address = address;
		this.zipCode = zipCode;
		this.city = city;
		this.region = region;
		this.country = country;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.mobileNumber = mobileNumber;
	}

	/**
	 * 
	 * @return idEmployee - Primary Key (Erforderlich - Automatisch)
	 */
	public int getIdEmployee() {
		return idEmployee;
	}

	/**
	 * 
	 * @param idEmployee Primary Key (Erforderlich - Automatisch)
	 */
	public void setIdEmployee(int idEmployee) {
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
	 * @return employeeUser - Benutzers Angaben (Erforderlich)
	 */
	public EmployeeUser getEmployeeUser() {
		return employeeUser;
	}

	/**
	 * 
	 * @param employeeUser Benutzers Angaben (Erforderlich)
	 */
	public void setEmployeeUser(EmployeeUser employeeUser) {
		this.employeeUser = employeeUser;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idEmployee;
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
