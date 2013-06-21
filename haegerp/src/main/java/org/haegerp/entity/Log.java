package org.haegerp.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Diese Entität bearbeitet alle Logs, was die Mitarbeiter in dem System machen.
 * 
 * @author Wolf
 *
 */
public class Log implements Serializable {
	
	private static final long serialVersionUID = -3450950968484609280L;
	
	//Primary Key (Erforderlich)
	private long idLog;
	//Welche Entität wird geändert (Erforderlich)
	private String entity;
	//Welche Operation wird durchgeführt (Erforderlich)
	private String operation;
	//Welcher Mitarbeiter hat die Operation durchgeführt (Erforderlich)
	private Employee employee;
	//Datum, dass die Operation durchgeführt wird (Erforderlich)
	private Date operationDate;
	
	/**
	 * Standardkonstruktor
	 */
	public Log() {
	}
	
	/**
	 * 
	 * @param entity Welche Entität wird geändert (Erforderlich)
	 * @param operation Welche Operation wird durchgeführt (Erforderlich)
	 * @param employee Welcher Mitarbeiter hat die Operation durchgeführt (Erforderlich)
	 */
	public Log(String entity, String operation, Employee employee) {
		this.entity = entity;
		this.operation = operation;
		this.employee = employee;
		this.operationDate = new Date();
	}
	
	/**
	 * 
	 * @return idLog - Primary Key (Erforderlich)
	 */
	public long getIdLog() {
		return idLog;
	}
	
	/**
	 * 
	 * @param idLog Primary Key (Erforderlich)
	 */
	public void setIdLog(long idLog) {
		this.idLog = idLog;
	}
	
	/**
	 * 
	 * @return entity - Welche Entität wird geändert (Erforderlich)
	 */
	public String getEntity() {
		return entity;
	}
	
	/**
	 * 
	 * @param entity Welche Entität wird geändert (Erforderlich)
	 */
	public void setEntity(String entity) {
		this.entity = entity;
	}
	
	/**
	 * 
	 * @return operation - Welche Operation wird durchgeführt (Erforderlich)
	 */
	public String getOperation() {
		return operation;
	}
	
	/**
	 * 
	 * @param operation Welche Operation wird durchgeführt (Erforderlich)
	 */
	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	/**
	 * 
	 * @return employee Welcher Mitarbeiter hat die Operation durchgeführt (Erforderlich)
	 */
	public Employee getEmployee() {
		return employee;
	}
	
	/**
	 * 
	 * @param employee Welcher Mitarbeiter hat die Operation durchgeführt (Erforderlich)
	 */
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	/**
	 * 
	 * @return operationDate - Datum, dass die Operation durchgeführt wird (Erforderlich)
	 */
	public Date getOperationDate() {
		return operationDate;
	}
	
	/**
	 * 
	 * @param operationDate Datum, dass die Operation durchgeführt wird (Erforderlich)
	 */
	public void setOperationDate(Date operationDate) {
		this.operationDate = operationDate;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idLog ^ (idLog >>> 32));
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
		Log other = (Log) obj;
		if (idLog != other.idLog)
			return false;
		return true;
	}
	
	
}
