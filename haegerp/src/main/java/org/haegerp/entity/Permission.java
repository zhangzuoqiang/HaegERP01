package org.haegerp.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Permission implements Serializable {

	private static final long serialVersionUID = 5008365204082645635L;
	
	//Primary Key (Erforderlich)
	private int idPermission;
	
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
	public int getIdPermission() {
		return idPermission;
	}

	/**
	 * 
	 * @param idPermission Primary Key (Erforderlich)
	 */
	public void setIdPermission(int idPermission) {
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
}
