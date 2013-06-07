package org.haegerp.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Permission implements Serializable {

	private static final long serialVersionUID = 5008365204082645635L;
	
	private int idPermission;
	private String moduleName;
	private Set<UserGroup> userGroups = new HashSet<UserGroup>(0);
	
	/**
	 * 
	 */
	public Permission() {
	}

	/**
	 * 
	 * @param idPermission idPermission Primary Key (Erforderlich)
	 * @param moduleName moduleName Name der Erlaubnis (Erforderlich)
	 * @param userGroups BenutzerGruppen, dass angeschlossen werden mit der Erlaubnis
	 */
	public Permission(int idPermission, String moduleName,
			Set<UserGroup> userGroups) {
		this.idPermission = idPermission;
		this.moduleName = moduleName;
		this.userGroups = userGroups;
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
