package org.haegerp.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class UserGroup implements Serializable {

	private static final long serialVersionUID = -2312054737177420461L;

	private int idUserGroup;
	private String name;
	private String description;
	private Set<Employee> employees = new HashSet<Employee>(0);
	private Set<Permission> permissions = new HashSet<Permission>(0);
	
	/**
	 * Konstruktor mit keinen Parametern
	 * Ideal für eine neue Benutzergruppe
	 */
	public UserGroup() {
	}
	
	/**
	 * 
	 * @param idUserGroup Primary Key (Erforderlich - Automatisch)
	 * @param name Name der Benutzergruppe
	 * @param description Benutzergruppe Beschreibung
	 * @param employees Mitarbeiter, dass hier gehört
	 * @param permissions Erlaubnisse, dass angeschlossen werden mit der Benutzergruppe
	 */
	public UserGroup(int idUserGroup, String name, String description,
			Set<Permission> permissions) {
		this.idUserGroup = idUserGroup;
		this.name = name;
		this.description = description;
		this.permissions = permissions;
	}

	/**
	 * 
	 * @return idUserGroup - Primary Key (Erforderlich - Automatisch)
	 */
	public int getIdUserGroup() {
		return idUserGroup;
	}

	/**
	 * 
	 * @param idUserGroup Primary Key (Erforderlich - Automatisch)
	 */
	public void setIdUserGroup(int idUserGroup) {
		this.idUserGroup = idUserGroup;
	}

	/**
	 * 
	 * @return name - Name der Benutzergruppe
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name Name der Benutzergruppe
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return description - Benutzergruppe Beschreibung
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 
	 * @param description Benutzergruppe Beschreibung
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

	/**
	 * 
	 * @return permissions - Erlaubnisse, dass angeschlossen werden mit der Benutzergruppe
	 */
	public Set<Permission> getPermissions() {
		return permissions;
	}
	
	/**
	 * 
	 * @param permissions Erlaubnisse, dass angeschlossen werden mit der Benutzergruppe
	 */
	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idUserGroup;
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
		UserGroup other = (UserGroup) obj;
		if (idUserGroup != other.idUserGroup)
			return false;
		return true;
	}
}
