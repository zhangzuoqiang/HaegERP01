package org.haegerp.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Permission implements Serializable {

	private static final long serialVersionUID = 5008365204082645635L;
	
	//Primary Key (Erforderlich)
	private long idPermission;
	
	//Name der Erlaubnis (Erforderlich)
	private String moduleName;
	
	//(Many-To-Many) BenutzerGruppen, dass angeschlossen werden mit der Erlaubnis
	private Set<UserGroup> userGroups = new HashSet<UserGroup>(0);
	
	/**
	 * Konstruktor mit keinen Parametern
	 * Ideal für eine neue Erlaubnis
	 */
	public Permission() {
	}

	/**
	 * 
	 * @return idPermission - Primary Key (Erforderlich)
	 */
	public long getIdPermission() {
		return idPermission;
	}

	/**
	 * 
	 * @param idPermission Primary Key (Erforderlich)
	 */
	public void setIdPermission(long idPermission) {
		this.idPermission = idPermission;
	}

	/**
	 * 
	 * @return moduleName - Name der Erlaubnis (Erforderlich)
	 */
	public String getModuleName() {
		return moduleName;
	}

	/**
	 * 
	 * @param moduleName Name der Erlaubnis (Erforderlich)
	 */
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	/**
	 * 
	 * @return
	 */
	public Set<UserGroup> getUserGroups() {
		return userGroups;
	}

	/**
	 * 
	 * @param userGroups
	 */
	public void setUserGroups(Set<UserGroup> userGroups) {
		this.userGroups = userGroups;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idPermission ^ (idPermission >>> 32));
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
		Permission other = (Permission) obj;
		if (idPermission != other.idPermission)
			return false;
		return true;
	}
	
}
