package org.haegerp.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class SalaryCategory implements Serializable {

	private static final long serialVersionUID = 5718511347777083718L;
	
	private int idSalaryCategory;
	private float salaryFrom;
	private float salaryTo;
	private String description;
	private Set<Employee> employees = new HashSet<Employee>(0);
	
	/**
	 * Konstruktor mit keinen Parametern
	 * Ideal für eine neue Gehaltkategorie
	 */
	public SalaryCategory() {
	}
	
	/**
	 * 
	 * 
	 * @param idSalaryCategory Primary Key (Erforderlich - Automatisch)
	 * @param salaryFrom Gehalt Minimum
	 * @param salaryTo Gehalt Maximal
	 * @param employees Mitarbeiter, dass hier gehört
	 * @param description Gehaltkategorie Beschreibung
	 */
	public SalaryCategory(int idSalaryCategory, float salaryFrom,
			float salaryTo, Set<Employee> employees, String description) {
		this.idSalaryCategory = idSalaryCategory;
		this.salaryFrom = salaryFrom;
		this.salaryTo = salaryTo;
		this.setEmployees(employees);
		this.description = description;
	}

	/**
	 * 
	 * @return idSalaryCategory - Primary Key (Erforderlich - Automatisch)
	 */
	public int getIdSalaryCategory() {
		return idSalaryCategory;
	}

	/**
	 * 
	 * @param idSalaryCategory Primary Key (Erforderlich - Automatisch)
	 */
	public void setIdSalaryCategory(int idSalaryCategory) {
		this.idSalaryCategory = idSalaryCategory;
	}

	/**
	 * 
	 * @return salaryFrom - Gehalt Minimum
	 */
	public float getSalaryFrom() {
		return salaryFrom;
	}

	/**
	 * 
	 * @param salaryFrom Gehalt Minimum
	 */
	public void setSalaryFrom(float salaryFrom) {
		this.salaryFrom = salaryFrom;
	}

	/**
	 * 
	 * @return salaryTo - Gehalt Maximal
	 */
	public float getSalaryTo() {
		return salaryTo;
	}

	/**
	 * 
	 * @param salaryTo Gehalt Maximal
	 */
	public void setSalaryTo(float salaryTo) {
		this.salaryTo = salaryTo;
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

	/**
	 * 
	 * @return description - Gehaltkategorie Beschreibung
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 
	 * @param description Gehaltkategorie Beschreibung
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
		result = prime * result + idSalaryCategory;
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
		SalaryCategory other = (SalaryCategory) obj;
		if (idSalaryCategory != other.idSalaryCategory)
			return false;
		return true;
	}
}
