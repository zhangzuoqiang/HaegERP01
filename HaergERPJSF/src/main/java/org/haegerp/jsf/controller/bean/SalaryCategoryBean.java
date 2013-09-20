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
import org.haegerp.service.SalaryCategoryService;

import org.haegerp.entity.SalaryCategory;
import org.haegerp.jsf.controller.form.FormSalaryCategory;
import org.haegerp.jsf.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * Bean für die Seiten "SalaryCategoryManagement" und "Details"
 * 
 * @author Fábio Codinha
 */
@ManagedBean
@Controller
@Scope(value = "session")
public class SalaryCategoryBean implements Serializable {

    @Autowired
    private SalaryCategoryService salaryCategoryController;
    //Injected Manually at ClientBean.SetUp().@PostConstruct
    private EmployeeBean employeeBean;
    //Gehaltkategorie, die in der Seite der Details zeigen wird
    private SalaryCategory salaryCategory;
    //Hilfsvariable für die Methode, die eine Gehaltkategorie löschen.
    private long salaryCategoryId;
    //Klasse, wo die Felder von dem Formular gespeichert werden
    private FormSalaryCategory formSalaryCategory;
    
    //Object, dass der Inhalt von der Tabelle hat
    private Object[][] salaryCategoryObjects;
    //Wie viel Gehaltkategorie wurde in einer Seite gezeigt
    private int pageSize;
    //Die Suche, die der Benutzer eingefügt hat
    private String search;
    
    //Löschen?
    private Validator validator = new Validator();

    /**
     * Defaultwert
     */
    public SalaryCategoryBean() {
        pageSize = 10;
    }

    /**
     * Diese Methode lädt die Daten und bereitet das Formular vor
     */
    @PostConstruct
    public void setUp() {
        salaryCategoryObjects = salaryCategoryController.loadTableRows(pageSize);
        salaryCategory = new SalaryCategory();
        formSalaryCategory = new FormSalaryCategory(false);
    }

    /**
     * Wenn der Benutzer die Suche benutzen möchte, wird diese Methode gerufen
     */
    public void setUpSearch() {
        salaryCategoryController.setSearch(search, pageSize);
        salaryCategoryObjects = salaryCategoryController.loadTableRows(pageSize);
    }

    /**
     * Die Seite der Details wird vorbereitet.
     * 
     * @param id ID der Gehaltkategorie
     * @param disabled True - Die Gehaltkategorie wird nur gezeigt; False - Die Gehaltkategorie kann geändert werden.
     * @return Wenn die ID gültig ist, dann die Seite der Details wird geladen
     */
    public String prepareView(long id, boolean disabled) {
        salaryCategory = salaryCategoryController.getSalaryCategoryById(id);
        if (salaryCategory != null) {
            formSalaryCategory = new FormSalaryCategory(salaryCategory, disabled);
            return "salaryCategoryDetails?faces-redirect=true";
        } else {
            FacesMessage fMessage = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Salary's Category was not found in the database", null);
            FacesContext.getCurrentInstance().addMessage(null, fMessage);
            setUp();
            setUpSearch();
            return "";
        }
    }

    /**
     * Die Seite der Details wird eine neue Gehaltkategorie zu erstellen vorbereitet
     * @return Seite Details
     */
    public String prepareNew() {
        salaryCategory = new SalaryCategory();
        formSalaryCategory = new FormSalaryCategory(false);
        return "salaryCategoryDetails?faces-redirect=true";
    }

    /**
     * Dieser Betrieb wird gemacht, wenn der Benutzer im Knopf "Cancel" gedrückt hat
     * @return Zu welcher Seite wird der Benutzer geführt
     */
    public String btnCancel_ActionPerformed() {
        if (formSalaryCategory.isDisabled() || salaryCategory.getIdSalaryCategory() == 0) {
            return "salaryCategoryManagement?faces-redirect=true";
        } else {
            formSalaryCategory = new FormSalaryCategory(salaryCategory, true);
            return "salaryCategoryDetails?faces-redirect=true";
        }
    }

    /**
     * Wenn die Seite in Zeigen Modus ist, dann wird sie zur Anderung geändert. 
     * Sonst versucht das System die Gehaltkategorie in der Datenbank zu speichern
     */
    public void btnEditSave_ActionPerformed() {
        if (formSalaryCategory.isDisabled()) {
            formSalaryCategory = new FormSalaryCategory(salaryCategory, false);
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
     * Eine Gehaltkategorie wird in der Datanbank gespeichern
     * @return ID der Gehaltkategorie
     * @throws Exception Wenn etwas nicht Normal geht, wird eine Ausnahme geworfen.
     */
    public long doSave() throws Exception {
        salaryCategory.setSalaryFrom(Float.valueOf(formSalaryCategory.getTxtSalaryFrom().replace(',', '.')));
        salaryCategory.setSalaryTo(Float.valueOf(formSalaryCategory.getTxtSalaryTo().replace(',', '.')));
        salaryCategory.setDescription(formSalaryCategory.getTxtDescription());

        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
        Long idEmployee = (Long) session.getAttribute("idemployee");

        salaryCategory.setIdEmployeeModify(idEmployee);
        salaryCategory.setLastModifiedDate(new Date());

        SalaryCategory newSalaryCategory = salaryCategoryController.save(salaryCategory);

        updateDependencies();

        return newSalaryCategory.getIdSalaryCategory();
    }

    /**
     * Das System versuche eine Gehaltkategorie zu löschen.
     */
    public void delete() {
        SalaryCategory deleteSalaryCategory = salaryCategoryController.getSalaryCategoryById(salaryCategoryId);
        FacesMessage fMessage;
        FacesMessage.Severity severity;
        String msg;
        if (deleteSalaryCategory != null) {
            try {
                if (salaryCategoryController.isSalaryCategoryEmpty(salaryCategoryId)) {
                    salaryCategoryController.delete(deleteSalaryCategory);
                    severity = FacesMessage.SEVERITY_INFO;
                    msg = "Salary's Category was deleted.";
                } else {
                    severity = FacesMessage.SEVERITY_WARN;
                    msg = "Salary's Category still has Employees";
                }
            } catch (Exception e) {
                Logger.getGlobal().log(Level.SEVERE, e.getMessage());
                severity = FacesMessage.SEVERITY_FATAL;
                msg = "Error ocurred: " + e.getMessage();
            }
        } else {
            severity = FacesMessage.SEVERITY_ERROR;
            msg = "Salary's Category was not found in the Database.";
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
        if (salaryCategoryController.getPreviousPage(getPageSize())) {
            salaryCategoryObjects = salaryCategoryController.loadTableRows(pageSize);
        }
    }

    /**
     * Die nächste Seite wird geladen
     */
    public void nextPage() {
        if (salaryCategoryController.getNextPage(getPageSize())) {
            salaryCategoryObjects = salaryCategoryController.loadTableRows(pageSize);
        }
    }

    /**
     * Die Nummer der aktuele Seite und der Seitentotal wird gezeigt
     * @return Der Wert zu zeigen
     */
    public String getPageNumber() {
        return (salaryCategoryController.getPage().getNumber() + 1) + " / " + salaryCategoryController.getPage().getTotalPages();
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
     * @return the salaryCategoryController
     */
    public SalaryCategoryService getSalaryCategoryController() {
        return salaryCategoryController;
    }

    /**
     * @return the salaryCategory
     */
    public SalaryCategory getSalaryCategory() {
        return salaryCategory;
    }

    /**
     * @param salaryCategory the salaryCategory to set
     */
    public void setSalaryCategory(SalaryCategory salaryCategory) {
        this.salaryCategory = salaryCategory;
    }

    /**
     * @return the salaryCategoryId
     */
    public long getSalaryCategoryId() {
        return salaryCategoryId;
    }

    /**
     * @param salaryCategoryId the salaryCategoryId to set
     */
    public void setSalaryCategoryId(long salaryCategoryId) {
        this.salaryCategoryId = salaryCategoryId;
    }

    /**
     * @return the salaryCategoryObjects
     */
    public Object[][] getSalaryCategoryObjects() {
        return salaryCategoryObjects;
    }

    /**
     * @param salaryCategoryObjects the salaryCategoryObjects to set
     */
    public void setSalaryCategoryObjects(Object[][] salaryCategoryObjects) {
        this.salaryCategoryObjects = salaryCategoryObjects;
    }

    /**
     * @return the formSalaryCategory
     */
    public FormSalaryCategory getFormSalaryCategory() {
        return formSalaryCategory;
    }

    /**
     * @param formSalaryCategory the formSalaryCategory to set
     */
    public void setFormSalaryCategory(FormSalaryCategory formSalaryCategory) {
        this.formSalaryCategory = formSalaryCategory;
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
     * @return the validator
     */
    public Validator getValidator() {
        return validator;
    }

    /**
     * @param validator the validator to set
     */
    public void setValidator(Validator validator) {
        this.validator = validator;
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