package org.haegerp.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.haegerp.exception.LengthOverflowException;

/**
 * Der Erlaubnis, dass Benuztergruppen haben können.<br/>
 *
 * @author Wolf
 *
 */
public class Permission implements Serializable {

    private static final long serialVersionUID = 5008365204082645635L;
    //Primary Key (Erforderlich)
    private long idPermission;
    //Name der Erlaubnis (Erforderlich)
    private String moduleName;
    //(Many-To-Many) BenutzerGruppen, dass angeschlossen werden mit der Erlaubnis
    private Set<UserGroup> userGroups = new HashSet<UserGroup>(0);
    //ID des Mitarbeiter, der erstellt hat order geändert
    private Long idEmployeeModify;
    //Datum von der letzten Änderung
    private Date lastModifiedDate;

    /**
     * Konstruktor mit keinen Parametern Ideal für eine neue Erlaubnis
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
     * @throws LengthOverflowException
     */
    public void setModuleName(String moduleName) throws LengthOverflowException {
        if (moduleName.length() > 50) {
            throw new LengthOverflowException("ModuleName");
        }
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

    /**
     *
     * @return idEmployeeModify - ID des Mitarbeiter, der erstellt hat order
     * geändert
     */
    public Long getIdEmployeeModify() {
        return idEmployeeModify;
    }

    /**
     *
     * @param idEmployeeModify ID des Mitarbeiter, der erstellt hat order
     * geändert
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
        result = prime * result + (int) (idPermission ^ (idPermission >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Permission other = (Permission) obj;
        if (idPermission != other.idPermission) {
            return false;
        }
        return true;
    }
}
