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
import org.haegerp.controller.DivisionController;

import org.haegerp.entity.Division;
import org.haegerp.jsf.controller.form.FormDivision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * Bean f�r die Seiten "DivisionManagement" und "Details"
 * 
 * @author F�bio Codinha
 */
@ManagedBean
@Controller
@Scope(value = "session")
public class DivisionBean implements Serializable {

    @Autowired
    private DivisionController divisionController;
    //Injected Manually at ClientBean.SetUp().@PostConstruct
    private EmployeeBean employeeBean;
    //Division, die in der Seite der Details zeigen wird
    private Division division;
    //Hilfsvariable f�r die Methode, die eine Division l�schen.
    private long divisionId;
    //Klasse, wo die Felder von dem Formular gespeichert werden
    private FormDivision formDivision;
    
    //Object, dass der Inhalt von der Tabelle hat
    private Object[][] divisionObjects;
    
    //Wie viele Division wurde in einer Seite gezeigt
    private int pageSize;
    //Die Suche, die der Benutzer eingef�gt hat
    private String search;

    /**
     * Defaultwert
     */
    public DivisionBean() {
        pageSize = 10;
    }

    /**
     * Diese Methode l�dt die Daten und bereitet das Formular vor
     */
    @PostConstruct
    public void setUp() {
        divisionObjects = divisionController.loadTableRows(pageSize);
        division = new Division();
        formDivision = new FormDivision(false);
    }

    /**
     * Wenn der Benutzer die Suche benutzen m�chte, wird diese Methode gerufen
     */
    public void setUpSearch() {
        divisionController.setSearch(search, pageSize);
        divisionObjects = divisionController.loadTableRows(pageSize);
    }

    /**
     * Die Seite der Details wird vorbereitet.
     * 
     * @param id ID der Division
     * @param disabled True - Die Division wird nur gezeigt; False - Die Division kann ge�ndert werden.
     * @return Wenn die ID g�ltig ist, dann die Seite der Details wird geladen
     */
    public String prepareView(long id, boolean disabled) {
        division = divisionController.getDivisionById(id);
        if (division != null) {
            formDivision = new FormDivision(division, disabled);
            return "divisionDetails?faces-redirect=true";
        } else {
            FacesMessage fMessage = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Division was not found in the database", null);
            FacesContext.getCurrentInstance().addMessage(null, fMessage);
            setUp();
            setUpSearch();
            return "";
        }
    }

    /**
     * Die Seite der Details wird eine neue Division zu erstellen vorbereitet
     * @return Seite Details
     */
    public String prepareNew() {
        division = new Division();
        formDivision = new FormDivision(false);
        return "divisionDetails?faces-redirect=true";
    }

    /**
     * Dieser Betrieb wird gemacht, wenn der Benutzer im Knopf "Cancel" gedr�ckt hat
     * @return Zu welcher Seite wird der Benutzer gef�hrt
     */
    public String btnCancel_ActionPerformed() {
        if (formDivision.isDisabled() || division.getIdDivision() == 0) {
            return "divisionManagement?faces-redirect=true";
        } else {
            formDivision = new FormDivision(division, true);
            return "divisionDetails?faces-redirect=true";
        }
    }

    /**
     * Wenn die Seite in Zeigen Modus ist, dann wird sie zur Anderung ge�ndert. 
     * Sonst versucht das System die Division in der Datenbank zu speichern
     */
    public void btnEditSave_ActionPerformed() {
        if (formDivision.isDisabled()) {
            formDivision = new FormDivision(division, false);
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
     * Eine Division wird in der Datanbank gespeichern
     * @return ID des Artikels
     * @throws Exception Wenn etwas nicht Normal geht, wird eine Ausnahme geworfen.
     */
    public long doSave() throws Exception {
        division.setName(formDivision.getTxtName());
        division.setDescription(formDivision.getTxtDescription());

        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
        Long idEmployee = (Long) session.getAttribute("idemployee");

        division.setIdEmployeeModify(idEmployee);
        division.setLastModifiedDate(new Date());

        Division newDivision = divisionController.save(division);

        updateDependencies();

        return newDivision.getIdDivision();
    }

    /**
     * Das System versuche eine Division zu l�schen.
     */
    public void delete() {
        Division deleteDivision = divisionController.getDivisionById(divisionId);
        FacesMessage fMessage;
        FacesMessage.Severity severity;
        String msg;
        if (deleteDivision != null) {
            try {
                if (divisionController.isDivisionEmpty(divisionId)) {
                    divisionController.delete(deleteDivision);
                    severity = FacesMessage.SEVERITY_INFO;
                    msg = "Division " + deleteDivision.getName() + " was deleted.";
                } else {
                    severity = FacesMessage.SEVERITY_WARN;
                    msg = "Division " + deleteDivision.getName() + " still has Employees";
                }
            } catch (Exception e) {
                Logger.getGlobal().log(Level.SEVERE, e.getMessage());
                severity = FacesMessage.SEVERITY_FATAL;
                msg = "Error ocurred: " + e.getMessage();
            }
        } else {
            severity = FacesMessage.SEVERITY_ERROR;
            msg = "Division was not found in the Database.";
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
        if (divisionController.getPreviousPage(pageSize)) {
            divisionObjects = divisionController.loadTableRows(pageSize);
        }
    }
    
    /**
     * Die n�chste Seite wird geladen
     */
    public void nextPage() {
        if (divisionController.getNextPage(pageSize)) {
            divisionObjects = divisionController.loadTableRows(pageSize);
        }
    }
    
    /**
     * Die Nummer der aktuele Seite und der Seitentotal wird gezeigt
     * @return Der Wert zu zeigen
     */
    public String getPageNumber() {
        return (divisionController.getPage().getNumber() + 1) + " / " + divisionController.getPage().getTotalPages();
    }

    /**
     * Wenn die Division ge�ndert wird und das Formular des Kunden schon geladen wurde, dann muss das System das KundenBean aktualisieren
     */
    public void updateDependencies() {
        if (employeeBean != null) {
            employeeBean.setUp();
        }
    }

    /**
     * @return the division
     */
    public Division getDivision() {
        return division;
    }

    /**
     * @param division the division to set
     */
    public void setDivision(Division division) {
        this.division = division;
    }

    /**
     * @return the divisionId
     */
    public long getDivisionId() {
        return divisionId;
    }

    /**
     * @param divisionId the divisionId to set
     */
    public void setDivisionId(long divisionId) {
        this.divisionId = divisionId;
    }

    /**
     * @return the divisionObjects
     */
    public Object[][] getDivisionObjects() {
        return divisionObjects;
    }

    /**
     * @param divisionObjects the divisionObjects to set
     */
    public void setDivisionObjects(Object[][] divisionObjects) {
        this.divisionObjects = divisionObjects;
    }

    /**
     * @return the formDivision
     */
    public FormDivision getFormDivision() {
        return formDivision;
    }

    /**
     * @param formDivision the formDivision to set
     */
    public void setFormDivision(FormDivision formDivision) {
        this.formDivision = formDivision;
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