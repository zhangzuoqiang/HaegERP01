package org.haegerp.jsf.controller.bean;

import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.haegerp.service.CompanyService;
import org.haegerp.entity.Company;
import org.haegerp.jsf.controller.form.FormCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * Bean für die Seiten "CompanyDetails"
 * 
 * @author Fábio Codinha
 */
@ManagedBean
@Controller
@Scope(value = "session")
public class CompanyBean implements Serializable{
    
    @Autowired
    private CompanyService companyController;
    
    //Firma, die die Details zeigen wird
    private Company company;
    //Klasse, wo die Felder von dem Formular gespeichert werden
    private FormCompany formCompany;
    
    public CompanyBean() {
    }
    
    /**
     * Diese Methode lädt die Daten und bereitet das Formular vor
     */
    @PostConstruct
    public void setUp(){
        company = companyController.getCompany();
        formCompany = new FormCompany(company, true);
    }

    /**
     * Die Seite der Details wird vorbereitet.
     * 
     * @param disabled True - Die Firma wird nur gezeigt; False - Die Firma kann geändert werden.
     * @return Wenn die ID gültig ist, dann die Seite der Details wird geladen
     */
    public String prepareView(boolean disabled) {
        company = companyController.getCompany();
        if (company != null) {
            formCompany = new FormCompany(company, disabled);
            return "companyDetails?faces-redirect=true";
        } else {
            FacesMessage fMessage = new FacesMessage(FacesMessage.SEVERITY_FATAL, "An error ocurred in the database", null);
            FacesContext.getCurrentInstance().addMessage(null, fMessage);
            setUp();
            return "";
        }
    }
    
    /**
     * Dieser Betrieb wird gemacht, wenn der Benutzer im Knopf "Cancel" gedrückt hat
     * @return Zu welcher Seite wird der Benutzer geführt
     */
    public String btnCancel_ActionPerformed(){
        if (formCompany.isDisabled()) {
            return "";
        } else {
            formCompany = new FormCompany(company, true);
            return "companyDetails?faces-redirect=true";
        }
    }
    
    /**
     * Wenn die Seite in Zeigen Modus ist, dann wird sie zur Anderung geändert. 
     * Sonst versucht das System dir Firma in der Datenbank zu speichern
     */
    public void btnEditSave_ActionPerformed(){
        if (formCompany.isDisabled()) {
            formCompany = new FormCompany(company, false);
        } else {
            long id = 0;
            try {
                id = doSave();
            } catch(Exception ex) {
                ex.printStackTrace(System.err);
            }
            
            prepareView(true);
        }
    }
    
    /**
     * Die Firma wird in der Datanbank gespeichern
     * @return ID der Firma
     * @throws Exception Wenn etwas nicht Normal geht, wird eine Ausnahme geworfen.
     */
    public long doSave() throws Exception{
        Long taxId;
        if (formCompany.getTxtTaxId().equals(""))
            taxId = null;
        else
            taxId = Long.valueOf(formCompany.getTxtTaxId());
        
        company.setTaxId(taxId);
        company.setName(formCompany.getTxtName());
        company.setAddress(formCompany.getTxtAddress());
        company.setZipCode(formCompany.getTxtZipCode());
        company.setCity(formCompany.getTxtCity());
        company.setRegion(formCompany.getTxtRegion());
        company.setCountry(formCompany.getTxtCountry());
        company.setEmail(formCompany.getTxtEmail());
        company.setPhoneNumber(formCompany.getTxtPhoneNumber());
        company.setFaxNumber(formCompany.getTxtFaxNumber());
        
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession)context.getExternalContext().getSession(true);
        Long idEmployee = (Long)session.getAttribute("idemployee");
        
        company.setIdEmployeeModify(idEmployee);
        company.setLastModifiedDate(new Date());
        
        Company newCompany = companyController.save(company);
        
        return newCompany.getIdCompany();
    }

    /**
     * @return the formCompany
     */
    public FormCompany getFormCompany() {
        return formCompany;
    }

    /**
     * @param formCompany the formCompany to set
     */
    public void setFormCompany(FormCompany formCompany) {
        this.formCompany = formCompany;
    }
}
