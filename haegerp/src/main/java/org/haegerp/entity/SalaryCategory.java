package org.haegerp.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.haegerp.exception.LengthOverflowException;

/**
 * Die Gehaltgruppen, dass Mitarbeiter gehören.<br/>
 * 
 * @author Fabio Codinha
 *
 */
public class SalaryCategory implements Serializable {

	private static final long serialVersionUID = 5718511347777083718L;
	
	//Primary Key (Erforderlich - Automatisch)
	private long idSalaryCategory;
	//Gehalt Minimum (Erforderlich)
	private float salaryFrom;
	//Gehalt Maximal (Erforderlich)
	private float salaryTo;
	//Gehaltkategorie Beschreibung
	private String description;
	//Mitarbeiter, dass hier gehört
	private Set<Employee> employees = new HashSet<Employee>(0);
	//ID des Mitarbeiter, der erstellt hat order geändert
	private Long idEmployeeModify;
	//Datum von der letzten Änderung
	private Date  lastModifiedDate;
	
	/**
	 * Konstruktor mit keinen Parametern
	 * Ideal für eine neue Gehaltkategorie
	 */
	public SalaryCategory() {
	}
	
	/**
	 * 
	 * @return idSalaryCategory - Primary Key (Erforderlich - Automatisch)
	 */
	public long getIdSalaryCategory() {
		return idSalaryCategory;
	}

	/**
	 * 
	 * @param idSalaryCategory Primary Key (Erforderlich - Automatisch)
	 */
	public void setIdSalaryCategory(long idSalaryCategory) {
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
	 * @throws LengthOverflowException 
	 */
	public void setSalaryFrom(float salaryFrom) throws LengthOverflowException {
		if (salaryFrom > 99999999999999999.99F || salaryFrom < 0.0F)
			throw new LengthOverflowException("SalaryTo");
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
	 * @throws LengthOverflowException 
	 */
	public void setSalaryTo(float salaryTo) throws LengthOverflowException {
		if (salaryTo > 99999999999999999.99F || salaryTo < 0.0F)
			throw new LengthOverflowException("SalaryTo");
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
		result = prime * result + (int) (idSalaryCategory ^ (idSalaryCategory >>> 32));
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
