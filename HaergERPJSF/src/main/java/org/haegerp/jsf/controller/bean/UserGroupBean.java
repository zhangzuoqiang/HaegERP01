package org.haegerp.jsf.controller.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.haegerp.controller.PermissionController;
import org.haegerp.controller.UserGroupController;
import org.haegerp.entity.Permission;

import org.haegerp.entity.UserGroup;
import org.haegerp.jsf.controller.form.FormUserGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * Bean für die Seiten "UserGroupManagement" und "Details"
 * 
 * @author Fábio Codinha
 */
@ManagedBean
@Controller
@Scope(value = "session")
public class UserGroupBean implements Serializable {

    @Autowired
    private UserGroupController userGroupController;
    @Autowired
    private PermissionController permissionController;
    //Injected Manually at @PostConstruct EmployeeBean.SetUp()
    private EmployeeBean employeeBean;
    //Benutzergruppe, die in der Seite der Details zeigen wird
    private UserGroup userGroup;
    //Hilfsvariable für die Methode, die eine Benutzergruppe löschen.
    private long userGroupId;
    //Klasse, wo die Felder von dem Formular gespeichert werden
    private FormUserGroup formUserGroup;
    
    //Object, dass der Inhalt von der Tabelle hat
    private Object[][] userGroupObjects;
    
    //Wie viel Benutzergruppe wurde in einer Seite gezeigt
    private int pageSize;
    //Die Suche, die die Benutzergruppe eingefügt hat
    private String search;

    /**
     * Defaultwert
     */
    public UserGroupBean() {
        pageSize = 10;
    }

    /**
     * Diese Methode lädt die Daten und bereitet das Formular vor
     */
    @PostConstruct
    public void setUp() {
        userGroupObjects = userGroupController.loadTableRows(pageSize);
        userGroup = new UserGroup();
        formUserGroup = new FormUserGroup(false, permissionController.getAllPermissions());
    }

    /**
     * Wenn der Benutzer die Suche benutzen möchte, wird diese Methode gerufen
     */
    public void setUpSearch() {
        userGroupController.setSearch(search, pageSize);
        userGroupObjects = userGroupController.loadTableRows(pageSize);
    }

    /**
     * Die Seite der Details wird vorbereitet.
     * 
     * @param id ID der Benutzergruppe
     * @param disabled True - Die Benutzergruppe wird nur gezeigt; False - Die Benutzergruppe kann geändert werden.
     * @return Wenn die ID gültig ist, dann die Seite der Details wird geladen
     */
    public String prepareView(long id, boolean disabled) {
        userGroup = userGroupController.getUserGroupById(id);
        if (userGroup != null) {
            formUserGroup = new FormUserGroup(userGroup, disabled, permissionController.getAllPermissions());
            return "userGroupDetails?faces-redirect=true";
        } else {
            FacesMessage fMessage = new FacesMessage(FacesMessage.SEVERITY_FATAL, "User Group was not found in the database", null);
            FacesContext.getCurrentInstance().addMessage(null, fMessage);
            setUp();
            setUpSearch();
            return "";
        }
    }

    /**
     * Die Seite der Details wird eine neue Benutzergruppe zu erstellen vorbereitet
     * @return Seite Details
     */
    public String prepareNew() {
        userGroup = new UserGroup();
        formUserGroup = new FormUserGroup(false, permissionController.getAllPermissions());
        return "userGroupDetails?faces-redirect=true";
    }

    /**
     * Dieser Betrieb wird gemacht, wenn der Benutzer im Knopf "Cancel" gedrückt hat
     * @return Zu welcher Seite wird der Benutzer geführt
     */
    public String btnCancel_ActionPerformed() {
        if (formUserGroup.isDisabled() || userGroup.getIdUserGroup() == 0) {
            return "userGroupManagement?faces-redirect=true";
        } else {
            formUserGroup = new FormUserGroup(userGroup, true, permissionController.getAllPermissions());
            return "userGroupDetails?faces-redirect=true";
        }
    }

    /**
     * Wenn die Seite in Zeigen Modus ist, dann wird sie zur Anderung geändert. 
     * Sonst versucht das System die Benutzergruppe in der Datenbank zu speichern
     */
    public void btnEditSave_ActionPerformed() {
        if (formUserGroup.isDisabled()) {
            formUserGroup = new FormUserGroup(userGroup, false, permissionController.getAllPermissions());
        } else {
            long id = 0;
            try {
                id = doSave();
                setUpSearch();
            } catch (Exception ex) {
                ex.printStackTrace(System.err);
            }

            prepareView(id, true);
        }
    }

    /**
     * Eine Benutzergruppe wird in der Datanbank gespeichern
     * @return ID der Benutzergruppe
     * @throws Exception Wenn etwas nicht Normal geht, wird eine Ausnahme geworfen.
     */
    public long doSave() throws Exception {
        userGroup.setName(formUserGroup.getTxtName());
        userGroup.setDescription(formUserGroup.getTxtDescription());

        userGroup.setPermissions(new HashSet<Permission>(0));
        for (Permission permission : formUserGroup.getPermissions().getTarget()) {
            userGroup.getPermissions().add(permission);
        }
        
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
        Long idEmployee = (Long) session.getAttribute("idemployee");

        userGroup.setIdEmployeeModify(idEmployee);
        userGroup.setLastModifiedDate(new Date());

        UserGroup newUserGroup = userGroupController.save(userGroup);

        updateDependencies();

        return newUserGroup.getIdUserGroup();
    }

    /**
     * Das System versuche eine Benutzergruppe zu löschen.
     */
    public void delete() {
        UserGroup deleteUserGroup = userGroupController.getUserGroupById(userGroupId);
        FacesMessage fMessage;
        FacesMessage.Severity severity;
        String msg;
        if (deleteUserGroup != null) {
            try {
                if (userGroupController.isUserGroupEmpty(userGroupId)) {
                    userGroupController.delete(deleteUserGroup);
                    severity = FacesMessage.SEVERITY_INFO;
                    msg = "User Group " + deleteUserGroup.getName() + " was deleted.";
                } else {
                    severity = FacesMessage.SEVERITY_WARN;
                    msg = "User Group " + deleteUserGroup.getName() + " still has Employees";
                }
            } catch (Exception e) {
                Logger.getGlobal().log(Level.SEVERE, e.getMessage());
                severity = FacesMessage.SEVERITY_FATAL;
                msg = "Error ocurred: " + e.getMessage();
            }
        } else {
            severity = FacesMessage.SEVERITY_ERROR;
            msg = "User Group was not found in the Database.";
        }

        fMessage = new FacesMessage(severity, msg, null);

        FacesContext.getCurrentInstance().addMessage(null, fMessage);
        setUp();
        setUpSearch();
        updateDependencies();
    }

    /**
     * Die vorige Seite wird geladen
     */
    public void previousPage() {
        if (userGroupController.getPreviousPage(pageSize)) {
            userGroupObjects = userGroupController.loadTableRows(pageSize);
        }
    }

    /**
     * Die nächste Seite wird geladen
     */
    public void nextPage() {
        if (userGroupController.getNextPage(pageSize)) {
            userGroupObjects = userGroupController.loadTableRows(pageSize);
        }
    }

    /**
     * Die Nummer der aktuele Seite und der Seitentotal wird gezeigt
     * @return Der Wert zu zeigen
     */
    public String getPageNumber() {
        return (userGroupController.getPage().getNumber() + 1) + " / " + userGroupController.getPage().getTotalPages();
    }

    /**
     * Wenn die Kategorien geändert wird und das Formular des Kunden schon geladen wurde, dann muss das System das KundenBean aktualisieren
     */
    public void updateDependencies() {
        if (employeeBean != null) {
            employeeBean.setUp();
        }
    }
    
    /**
     * @return the userGroupId
     */
    public long getUserGroupId() {
        return userGroupId;
    }

    /**
     * @param userGroupId the userGroupId to set
     */
    public void setUserGroupId(long userGroupId) {
        this.userGroupId = userGroupId;
    }

    /**
     * @return the userGroupObjects
     */
    public Object[][] getUserGroupObjects() {
        return userGroupObjects;
    }

    /**
     * @param userGroupObjects the userGroupObjects to set
     */
    public void setUserGroupObjects(Object[][] userGroupObjects) {
        this.userGroupObjects = userGroupObjects;
    }

    /**
     * @return the formUserGroup
     */
    public FormUserGroup getFormUserGroup() {
        return formUserGroup;
    }

    /**
     * @param formUserGroup the formUserGroup to set
     */
    public void setFormUserGroup(FormUserGroup formUserGroup) {
        this.formUserGroup = formUserGroup;
    }

    /**
     * @return the pageSize
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * @param pageSize the pageSize to set
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * @return the search
     */
    public String getSearch() {
        return search;
    }

    /**
     * @param search the search to set
     */
    public void setSearch(String search) {
        this.search = search;
    }

    /**
     * @return the employeeBean
     */
    public EmployeeBean getEmployeeBean() {
        return employeeBean;
    }

    /**
     * @param employeeBean the employeeBean to set
     */
    public void setEmployeeBean(EmployeeBean employeeBean) {
        this.employeeBean = employeeBean;
    }
}