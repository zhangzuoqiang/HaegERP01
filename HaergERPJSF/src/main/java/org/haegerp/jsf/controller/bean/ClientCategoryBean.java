package org.haegerp.jsf.controller.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.haegerp.controller.ClientCategoryController;

import org.haegerp.entity.ClientCategory;
import org.haegerp.jsf.controller.form.FormClientCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * Bean für die Seiten "ClientCategoryManagement" und "Details"
 * 
 * @author Fábio Codinha
 */
@ManagedBean
@Controller
@Scope(value = "session")
public class ClientCategoryBean implements Serializable {

    @Autowired
    private ClientCategoryController clientCategoryController;
    //Injected Manually at @PostConstruct ClientBean.SetUp()
    private ClientBean clientBean;
    //Kundenkategorie, die in der Seite der Details zeigen wird
    private ClientCategory clientCategory;
    //Hilfsvariable für die Methode, die eine Kundenkategorie löschen.
    private long clientCategoryId;
    //Klasse, wo die Felder von dem Formular gespeichert werden
    private FormClientCategory formClientCategory;
    
    //Object, dass der Inhalt von der Tabelle hat
    private Object[][] clientCategoryObjects;
    
    //Wie viel Kundenkategorie wurde in einer Seite gezeigt
    private int pageSize;
    //Die Suche, die der Benutzer eingefügt hat
    private String search;

    /**
     * Defaultwert
     */
    public ClientCategoryBean() {
        pageSize = 10;
    }

    /**
     * Diese Methode lädt die Daten und bereitet das Formular vor
     */
    @PostConstruct
    public void setUp() {
        clientCategoryObjects = clientCategoryController.loadTableRows(pageSize);
        clientCategory = new ClientCategory();
        formClientCategory = new FormClientCategory(false);
    }

    /**
     * Wenn der Benutzer die Suche benutzen möchte, wird diese Methode gerufen
     */
    public void setUpSearch() {
        clientCategoryController.setSearch(search, pageSize);
        clientCategoryObjects = clientCategoryController.loadTableRows(pageSize);
    }

    /**
     * Die Seite der Details wird vorbereitet.
     * 
     * @param id ID der Kundenkategorie
     * @param disabled True - Die Kundenkategorie wird nur gezeigt; False - Die Kundenkategorie kann geändert werden.
     * @return Wenn die ID gültig ist, dann die Seite der Details wird geladen
     */
    public String prepareView(long id, boolean disabled) {
        clientCategory = clientCategoryController.getClientCategoryId(id);
        if (clientCategory != null) {
            formClientCategory = new FormClientCategory(clientCategory, disabled);
            return "clientCategoryDetails?faces-redirect=true";
        } else {
            FacesMessage fMessage = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Client's Category was not found in the database", null);
            FacesContext.getCurrentInstance().addMessage(null, fMessage);
            setUp();
            setUpSearch();
            return "";
        }
    }

    /**
     * Die Seite der Details wird eine neue Kundenkategorie zu erstellen vorbereitet
     * @return Seite Details
     */
    public String prepareNew() {
        clientCategory = new ClientCategory();
        formClientCategory = new FormClientCategory(false);
        return "clientCategoryDetails?faces-redirect=true";
    }

    /**
     * Dieser Betrieb wird gemacht, wenn der Benutzer im Knopf "Cancel" gedrückt hat
     * @return Zu welcher Seite wird der Benutzer geführt
     */
    public String btnCancel_ActionPerformed() {
        if (formClientCategory.isDisabled() || clientCategory.getIdClientCategory() == 0) {
            return "clientCategoryManagement?faces-redirect=true";
        } else {
            formClientCategory = new FormClientCategory(clientCategory, true);
            return "clientCategoryDetails?faces-redirect=true";
        }
    }

    /**
     * Wenn die Seite in Zeigen Modus ist, dann wird sie zur Anderung geändert. 
     * Sonst versucht das System die Kundenkategorie in der Datenbank zu speichern
     */
    public void btnEditSave_ActionPerformed() {
        if (formClientCategory.isDisabled()) {
            formClientCategory = new FormClientCategory(clientCategory, false);
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
     * Eine Kundenkategorie wird in der Datanbank gespeichern
     * @return ID der Artikelkategorie
     * @throws Exception Wenn etwas nicht Normal geht, wird eine Ausnahme geworfen.
     */
    public long doSave() throws Exception {
        clientCategory.setName(formClientCategory.getTxtName());
        clientCategory.setDescription(formClientCategory.getTxtDescription());

        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
        Long idEmployee = (Long) session.getAttribute("idemployee");

        clientCategory.setIdEmployeeModify(idEmployee);
        clientCategory.setLastModifiedDate(new Date());

        ClientCategory newClientCategory = clientCategoryController.save(clientCategory);

        updateDependencies();

        return newClientCategory.getIdClientCategory();
    }

    /**
     * Das System versuche eine Kundenkategorie zu löschen.
     */
    public void delete() {
        ClientCategory deleteClientCategory = clientCategoryController.getClientCategoryId(clientCategoryId);
        FacesMessage fMessage;
        FacesMessage.Severity severity;
        String msg;
        if (deleteClientCategory != null) {
            try {
                if (clientCategoryController.isCategoryClientsEmpty(clientCategoryId)) {
                    clientCategoryController.delete(deleteClientCategory);
                    severity = FacesMessage.SEVERITY_INFO;
                    msg = "Client's Category " + deleteClientCategory.getName() + " was deleted.";
                } else {
                    severity = FacesMessage.SEVERITY_WARN;
                    msg = "Client's Category " + deleteClientCategory.getName() + " still has Clients";
                }
            } catch (Exception e) {
                Logger.getGlobal().log(Level.SEVERE, e.getMessage());
                severity = FacesMessage.SEVERITY_FATAL;
                msg = "Error ocurred: " + e.getMessage();
            }
        } else {
            severity = FacesMessage.SEVERITY_ERROR;
            msg = "Client's Category was not found in the Database.";
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
        if (clientCategoryController.getPreviousPage(getPageSize())) {
            clientCategoryObjects = clientCategoryController.loadTableRows(pageSize);
        }
    }

    /**
     * Die nächste Seite wird geladen
     */
    public void nextPage() {
        if (clientCategoryController.getNextPage(getPageSize())) {
            clientCategoryObjects = clientCategoryController.loadTableRows(pageSize);
        }
    }

    /**
     * Die Nummer der aktuele Seite und der Seitentotal wird gezeigt
     * @return Der Wert zu zeigen
     */
    public String getPageNumber() {
        return (clientCategoryController.getPage().getNumber() + 1) + " / " + clientCategoryController.getPage().getTotalPages();
    }

    /**
     * Wenn die Kategorien geändert wird und das Formular des Kunden schon geladen wurde, dann muss das System das KundenBean aktualisieren
     */
    public void updateDependencies() {
        if (clientBean != null) {
            clientBean.setUp();
        }
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
     * @return the clientCategoryObjects
     */
    public Object[][] getClientCategoryObjects() {
        return clientCategoryObjects;
    }

    /**
     * @param clientCategoryObjects the clientCategoryObjects to set
     */
    public void setClientCategoryObjects(Object[][] clientCategoryObjects) {
        this.clientCategoryObjects = clientCategoryObjects;
    }

    /**
     * @return the clientCategoryId
     */
    public long getClientCategoryId() {
        return clientCategoryId;
    }

    /**
     * @param clientCategoryId the clientCategoryId to set
     */
    public void setClientCategoryId(long clientCategoryId) {
        this.clientCategoryId = clientCategoryId;
    }

    /**
     * @return the formClientCategory
     */
    public FormClientCategory getFormClientCategory() {
        return formClientCategory;
    }

    /**
     * @param formClientCategory the formClientCategory to set
     */
    public void setFormClientCategory(FormClientCategory formClientCategory) {
        this.formClientCategory = formClientCategory;
    }

    /**
     * @return the clientBean
     */
    public ClientBean getClientBean() {
        return clientBean;
    }

    /**
     * @param clientBean the clientBean to set
     */
    public void setClientBean(ClientBean clientBean) {
        this.clientBean = clientBean;
    }
}