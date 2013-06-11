package org.haegerp.entity;

import java.io.Serializable;

public class EmployeeUser implements Serializable {
	
	private static final long serialVersionUID = -7802167683974129599L;
	
	//Primary Key (Erforderlich - Automatisch)
	private int idEmployee;
	//Benutzername vom Mitarbeiter (Erforderlich)
	private String username;
	//Kennwort vom Mitarbeiter (Erforderlich)
	private String password;
	//(One-To-One) Mitarbeiter, dass angeschlossen wird mit der Benutzerangabe
	private Employee employee;
	
	/**
	 * Konstruktor mit keinen Parametern
	 * Ideal für eine neue Benutzerangabe
	 */
	public EmployeeUser() {
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
	 * @return username - Benutzername vom Mitarbeiter (Erforderlich)
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * 
	 * @param username Benutzername vom Mitarbeiter (Erforderlich)
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 
	 * @return password - Kennwort vom Mitarbeiter (Erforderlich)
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 
	 * @param password Kennwort vom Mitarbeiter (Erforderlich)
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 
	 * @return employee - Mitarbeiter, dass angeschlossen wird mit der Benutzerangabe
	 */
	public Employee getEmployee() {
		return employee;
	}

	/**
	 * 
	 * @param employee Mitarbeiter, dass angeschlossen wird mit der Benutzerangabe
	 */
	public void setEmployee(Employee employee) {
		this.employee = employee;
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
		EmployeeUser other = (EmployeeUser) obj;
		if (idEmployee != other.idEmployee)
			return false;
		return true;
	}
}
