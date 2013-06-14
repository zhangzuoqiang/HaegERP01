package org.haegerp.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Division implements Serializable {

	private static final long serialVersionUID = 423295763797533848L;

	//Primary Key (Erforderlich - Automatisch)
	private long idDivision;
	
	//Name des Divisions
	private String name;
	
	//Division Beschreibung
	private String description;
	
	//(One-To-Many) Mitarbeiter, dass hier gehört
	private Set<Employee> employees = new HashSet<Employee>(0);
	
	/**
	 * Konstruktor mit keinen Parametern
	 * Ideal für ein neues Division
	 */
	public Division() {
	}

	/**
	 * 
	 * @param idDivision 
	 * @param name 
	 * @param description 
	 * @param employees 
	 */
	public Division(int idDivision, String name, String description,
			Set<Employee> employees) {
		this.idDivision = idDivision;
		this.name = name;
		this.description = description;
		this.employees = employees;
	}

	/**
	 * 
	 * @return idDivision - Primary Key (Erforderlich - Automatisch)
	 */
	public long getIdDivision() {
		return idDivision;
	}

	/**
	 * 
	 * @param idDivision Primary Key (Erforderlich - Automatisch)
	 */
	public void setIdDivision(long idDivision) {
		this.idDivision = idDivision;
	}

	/**
	 * 
	 * @return name - Name des Division
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name Name des Division
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return description - Division Beschreibung
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 
	 * @param description Division Beschreibung
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * 
	 * @return employees - Mitarbeiter, dass hier gehört
	 */
	public Set<Employee> getEmployees() {
		return employees;
	}

	/**
	 * 
	 * @param employees Mitarbeiter, dass hier gehört
	 */
	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idDivision ^ (idDivision >>> 32));
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
		Division other = (Division) obj;
		if (idDivision != other.idDivision)
			return false;
		return true;
	}
}
