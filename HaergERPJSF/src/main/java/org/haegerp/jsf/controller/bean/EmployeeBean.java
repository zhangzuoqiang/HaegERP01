package org.haegerp.jsf.controller.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpSession;
import org.haegerp.controller.DivisionController;
import org.haegerp.controller.EmployeeController;
import org.haegerp.controller.SalaryCategoryController;
import org.haegerp.controller.UserGroupController;
import org.haegerp.entity.Division;
import org.haegerp.entity.Employee;
import org.haegerp.entity.SalaryCategory;
import org.haegerp.entity.UserGroup;
import org.haegerp.jsf.controller.form.FormEmployee;
import org.haegerp.jsf.validation.Validator;
import org.haegerp.tools.MD5Digest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * Bean für die Seiten "EmployeeManagement" und "Details"
 *
 * @author Fabio Codinha
 */
@ManagedBean
@Controller
@Scope(value = "session")
public class EmployeeBean implements Serializable {

    @Autowired
    private EmployeeController employeeController;
    @Autowired
    private SalaryCategoryController salaryCategoryController;
    @Autowired
    private DivisionController divisionController;
    @Autowired
    private UserGroupController userGroupController;
    @Autowired
    private SalaryCategoryBean salaryCategoryBean;
    @Autowired
    private DivisionBean divisionBean;
    @Autowired
    private UserGroupBean userGroupBean;
    //Mitarbeiter, der in der Seite der Details zeigen wird
    private Employee employee;
    //Hilfsvariable für die Methode, die einen Mitarbeiter löschen.
    private long employeeId;
    //Klasse, wo die Felder von dem Formular gespeichert werden
    private FormEmployee formEmployee;
    //Liste, die die Gehaltkategorien haben
    private List<SalaryCategory> salaryCategories;
    //Liste, die die Division haben
    private List<Division> divisions;
    //Liste, die die Benutzergruppe haben
    private List<UserGroup> userGroups;
    //Object, dass der Inhalt von der Tabelle hat
    private Object[][] employeeObjects;
    //Variable, die die Ausgewählte ID enthalt
    private int cbSalaryCategory;
    //Variable, die die Ausgewählte ID enthalt
    private int cbDivision;
    //Variable, die die Ausgewählte ID enthalt
    private int cbUserGroup;
    //Wie viel Mitarbeiter wurde in einer Seite gezeigt
    private int pageSize;
    //Die Suche, die der Benutzer eingefügt hat
    private String search;
    //Löschen?
    private Validator validator = new Validator();

    /**
     * Defaultwert
     */
    public EmployeeBean() {
        pageSize = 10;
    }

    /**
     * Diese Methode lädt die Daten und bereitet das Formular vor
     */
    @PostConstruct
    public void setUp() {
        //Manual Dependency Injection
        salaryCategoryBean.setEmployeeBean(this);
        divisionBean.setEmployeeBean(this);
        userGroupBean.setEmployeeBean(this);

        employeeObjects = employeeController.loadTableRows(pageSize);
        employee = new Employee();
        salaryCategories = salaryCategoryController.getAllCategories();
        userGroups = userGroupController.getAllGroups();
        divisions = divisionController.getAllDivisions();
        formEmployee = new FormEmployee(false, true);
    }

    /**
     * Wenn der Benutzer die Suche benutzen möchte, wird diese Methode gerufen
     */
    public void setUpSearch() {
        employeeController.setSearch(search, pageSize);
        employeeController.setSalaryCategory((long) cbSalaryCategory, pageSize);
        employeeController.setUserGroup((long) cbUserGroup, pageSize);
        employeeController.setDivision((long) cbDivision, pageSize);
        employeeObjects = employeeController.loadTableRows(pageSize);
    }

    /**
     * Die Seite der Details wird vorbereitet.
     *
     * @param id ID des Mitarbeiters
     * @param disabled True - Der Mitarbeiter wird nur gezeigt; False - Der
     * mitarbeiter kann geändert werden.
     * @return Wenn die ID gültig ist, dann die Seite der Details wird geladen
     */
    public String prepareView(long id, boolean disabled) {
        employee = employeeController.getEmployeeById(id);
        salaryCategories = salaryCategoryController.getAllCategories();
        userGroups = userGroupController.getAllGroups();
        divisions = divisionController.getAllDivisions();
        if (employee != null) {
            formEmployee = new FormEmployee(employee, disabled, false);
            return "employeeDetails?faces-redirect=true";
        } else {
            FacesMessage fMessage = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Employee was not found in the database", null);
            FacesContext.getCurrentInstance().addMessage(null, fMessage);
            setUp();
            setUpSearch();
            return "";
        }
    }

    /**
     * Die Seite der Details wird einen neuen mitarbeiter zu erstellen
     * vorbereitet
     *
     * @return Seite Details
     */
    public String prepareNew() {
        employee = new Employee();
        formEmployee = new FormEmployee(false, true);
        return "employeeDetails?faces-redirect=true";
    }

    /**
     * Dieser Betrieb wird gemacht, wenn der Benutzer im Knopf "Cancel" gedrückt
     * hat
     *
     * @return Zu welcher Seite wird der Benutzer geführt
     */
    public String btnCancel_ActionPerformed() {
        if (formEmployee.isDisabled() || employee.getIdEmployee() == 0) {
            return "employeeManagement?faces-redirect=true";
        } else {
            formEmployee = new FormEmployee(employee, true, false);
            return "employeeDetails?faces-redirect=true";
        }
    }

    /**
     * Wenn die Seite in Zeigen Modus ist, dann wird sie zur Anderung geändert.
     * Sonst versucht das System den Mitarbeiter in der Datenbank zu speichern
     */
    public void btnEditSave_ActionPerformed() {
        if (formEmployee.isDisabled()) {
            formEmployee = new FormEmployee(employee, false, false);
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
     * Ein Mitarbeiter wird in der Datanbank gespeichern
     *
     * @return ID des Mitarbeiters
     * @throws Exception Wenn etwas nicht Normal geht, wird eine Ausnahme
     * geworfen.
     */
    public long doSave() throws Exception {
        SalaryCategory salaryCategory = salaryCategoryController.getSalaryCategoryById(formEmployee.getCbSalaryCategory());
        UserGroup userGroup = userGroupController.getUserGroupById(formEmployee.getCbUserGroup());
        Division division = divisionController.getDivisionById(formEmployee.getCbDivision());

        employee.setIdCard(Long.valueOf(formEmployee.getTxtIdCard()));
        employee.setName(formEmployee.getTxtName());
        employee.setSalaryCategory(salaryCategory);
        employee.setUserGroup(userGroup);
        employee.setDivision(division);
        employee.setAddress(formEmployee.getTxtAddress());
        employee.setZipCode(formEmployee.getTxtZipCode());
        employee.setCity(formEmployee.getTxtCity());
        employee.setRegion(formEmployee.getTxtRegion());
        employee.setCountry(formEmployee.getTxtCountry());
        employee.setEmail(formEmployee.getTxtEmail());
        employee.setPhoneNumber(formEmployee.getTxtPhoneNumber());
        employee.setMobileNumber(formEmployee.getTxtMobileNumber());
        employee.setUsername(formEmployee.getTxtUsername());
        if (!formEmployee.getTxtPassword().isEmpty()) {
            employee.setPassword(MD5Digest.toMD5(formEmployee.getTxtPassword()));
        }

        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
        Long idEmployee = (Long) session.getAttribute("idemployee");

        employee.setIdEmployeeModify(idEmployee);
        employee.setLastModifiedDate(new Date());

        Employee newEmployee = employeeController.save(employee);

        salaryCategoryBean.setUp();
        userGroupBean.setUp();
        divisionBean.setUp();

        return newEmployee.getIdEmployee();
    }

    /**
     * Das System versuche einen Mitarbeiter zu löschen.
     */
    public void delete() {
        Employee deleteEmployee = employeeController.getEmployeeById(employeeId);
        FacesMessage fMessage;
        FacesMessage.Severity severity;
        String msg;
        if (deleteEmployee != null) {
            try {
                if (employeeController.isEmployeeOffersEmpty(employeeId)
                        && employeeController.isEmployeeOrdersEmpty(employeeId)) {
                    employeeController.delete(deleteEmployee);
                    severity = FacesMessage.SEVERITY_INFO;
                    msg = "Employee " + deleteEmployee.getName() + " was deleted.";
                } else {
                    severity = FacesMessage.SEVERITY_WARN;
                    msg = "Employee " + deleteEmployee.getName() + " has already orders and/or offers inserted.";
                }
            } catch (Exception e) {
                Logger.getGlobal().log(Level.SEVERE, e.getMessage());
                severity = FacesMessage.SEVERITY_FATAL;
                msg = "Error ocurred: " + e.getMessage();
            }
        } else {
            severity = FacesMessage.SEVERITY_ERROR;
            msg = "Employee was not found in the Database.";
        }

        fMessage = new FacesMessage(severity, msg, null);

        FacesContext.getCurrentInstance().addMessage(null, fMessage);
        setUp();
        setUpSearch();
        salaryCategoryBean.setUp();
        userGroupBean.setUp();
        divisionBean.setUp();
    }

    /**
     * Die vorige Seite wird geladen
     */
    public void previousPage() {
        if (employeeController.getPreviousPage(pageSize)) {
            employeeObjects = employeeController.loadTableRows(pageSize);
        }
    }

    /**
     * Die nächste Seite wird geladen
     */
    public void nextPage() {
        if (employeeController.getNextPage(pageSize)) {
            employeeObjects = employeeController.loadTableRows(pageSize);
        }
    }

    /**
     * Die Nummer der aktuele Seite und der Seitentotal wird gezeigt
     * @return Der Wert zu zeigen
     */
    public String getPageNumber() {
        return (employeeController.getPage().getNumber() + 1) + " / " + employeeController.getPage().getTotalPages();
    }

    /**
     * Validierer von dem Feld Kennwort und Konfirmation des Kennworts
     * 
     * @param context FacesContext
     * @param component UIComponent
     * @param value Wert des Feldes
     */
    public void validateConfirmPassword(FacesContext context, UIComponent component, Object value) {
        String confirmPassword = (String) value;
        if (!confirmPassword.isEmpty()) {
            if (!confirmPassword.equals(formEmployee.getTxtPassword())) {
                throw new ValidatorException(
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Password Confirmation must match with the Password",
                        "Password Confirmation must match with the Password"));
            }

        }
    }

    /**
     * @return the employee
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * @param employee the employee to set
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    /**
     * @return the employeeId
     */
    public long getEmployeeId() {
        return employeeId;
    }

    /**
     * @param employeeId the employeeId to set
     */
    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * @return the formEmployee
     */
    public FormEmployee getFormEmployee() {
        return formEmployee;
    }

    /**
     * @param formEmployee the formEmployee to set
     */
    public void setFormEmployee(FormEmployee formEmployee) {
        this.formEmployee = formEmployee;
    }

    /**
     * @return the salaryCategories
     */
    public List<SalaryCategory> getSalaryCategories() {
        return salaryCategories;
    }

    /**
     * @param salaryCategories the salaryCategories to set
     */
    public void setSalaryCategories(List<SalaryCategory> salaryCategories) {
        this.salaryCategories = salaryCategories;
    }

    /**
     * @return the divisions
     */
    public List<Division> getDivisions() {
        return divisions;
    }

    /**
     * @param divisions the divisions to set
     */
    public void setDivisions(List<Division> divisions) {
        this.divisions = divisions;
    }

    /**
     * @return the userGroups
     */
    public List<UserGroup> getUserGroups() {
        return userGroups;
    }

    /**
     * @param userGroups the userGroups to set
     */
    public void setUserGroups(List<UserGroup> userGroups) {
        this.userGroups = userGroups;
    }

    /**
     * @return the employeeObjects
     */
    public Object[][] getEmployeeObjects() {
        return employeeObjects;
    }

    /**
     * @param employeeObjects the employeeObjects to set
     */
    public void setEmployeeObjects(Object[][] employeeObjects) {
        this.employeeObjects = employeeObjects;
    }

    /**
     * @return the cbSalaryCategory
     */
    public int getCbSalaryCategory() {
        return cbSalaryCategory;
    }

    /**
     * @param cbSalaryCategory the cbSalaryCategory to set
     */
    public void setCbSalaryCategory(int cbSalaryCategory) {
        this.cbSalaryCategory = cbSalaryCategory;
    }

    /**
     * @return the cbDivision
     */
    public int getCbDivision() {
        return cbDivision;
    }

    /**
     * @param cbDivision the cbDivision to set
     */
    public void setCbDivision(int cbDivision) {
        this.cbDivision = cbDivision;
    }

    /**
     * @return the cbUserGroup
     */
    public int getCbUserGroup() {
        return cbUserGroup;
    }

    /**
     * @param cbUserGroup the cbUserGroup to set
     */
    public void setCbUserGroup(int cbUserGroup) {
        this.cbUserGroup = cbUserGroup;
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
}
