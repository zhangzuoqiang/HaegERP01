package org.haegerp.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.haegerp.exception.LengthOverflowException;

/**
 * Die Division gruppiert die Mitarbeiter.<br/>
 * 
 * @author Fabio Codinha
 *
 */
public class Division implements Serializable {

	private static final long serialVersionUID = 423295763797533848L;

	//Primary Key (Erforderlich - Automatisch)
	private long idDivision;
	//Name des Divisions (Erforderlich)
	private String name;
	//Division Beschreibung
	private String description;
	//(One-To-Many) Mitarbeiter, dass hier gehört
	private Set<Employee> employees = new HashSet<Employee>(0);
	//ID des Mitarbeiter, der erstellt hat order geändert
	private Long idEmployeeModify;
	//Datum von der letzten Änderung
	private Date  lastModifiedDate;
	
	/**
	 * Konstruktor mit keinen Parametern
	 * Ideal für ein neues Division
	 */
	public Division() {
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
	 * @throws LengthOverflowException 
	 */
	public void setName(String name) throws LengthOverflowException {
		if (name.length() > 50)
			throw new LengthOverflowException("Name");
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
	 * @throws LengthOverflowException 
	 */
	public void setDescription(String description) throws LengthOverflowException {
		if (description != null && description.length() > 256)
			throw new LengthOverflowException("Description");
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
