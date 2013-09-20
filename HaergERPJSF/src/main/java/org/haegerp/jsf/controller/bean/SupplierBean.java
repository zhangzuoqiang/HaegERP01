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
import org.haegerp.service.SupplierService;
import org.haegerp.entity.Supplier;
import org.haegerp.jsf.controller.form.FormSupplier;
import org.haegerp.jsf.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * Bean für die Seiten "SupplierManagement" und "Details"
 * 
 * @author Fábio Codinha
 */
@ManagedBean
@Controller
@Scope(value = "session")
public class SupplierBean implements Serializable{
    
    @Autowired
    private SupplierService supplierController;
    
    //Lieferant, der in der Seite der Details zeigen wird
    private Supplier supplier;
    //Hilfsvariable für die Methode, die einen Lieferanten löschen.
    private long supplierId;
    //Klasse, wo die Felder von dem Formular gespeichert werden
    private FormSupplier formSupplier;
    
    //Object, dass der Inhalt von der Tabelle hat
    private Object[][] supplierObjects;
    
    //Wie viel Lieferant wurde in einer Seite gezeigt
    private int pageSize;
    //Die Suche, die der Benutzer eingefügt hat
    private String search;

    //Löschen?
    private Validator validator = new Validator();
    
    /**
     * Defaultwert
     */
    public SupplierBean() {
        pageSize = 10;
    }
    
    /**
     * Diese Methode lädt die Daten und bereitet das Formular vor
     */
    @PostConstruct
    public void setUp(){
        supplierObjects = supplierController.loadTableRows(pageSize);
        supplier = new Supplier();
        formSupplier = new FormSupplier(false);
    }

    /**
     * Wenn der Benutzer die Suche benutzen möchte, wird diese Methode gerufen
     */
    public void setUpSearch(){
        supplierController.setSearch(search, pageSize);
        supplierObjects = supplierController.loadTableRows(pageSize);
    }
    
    /**
     * Die Seite der Details wird vorbereitet.
     * 
     * @param id ID des Lieferanten
     * @param disabled True - Der Lieferant wird nur gezeigt; False - Der Lieferant kann geändert werden.
     * @return Wenn die ID gültig ist, dann die Seite der Details wird geladen
     */
    public String prepareView(long id, boolean disabled) {
        supplier = supplierController.getSupplierById(id);
        if (supplier != null) {
            formSupplier = new FormSupplier(supplier, disabled);
            return "supplierDetails?faces-redirect=true";
        } else {
            FacesMessage fMessage = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Supplier was not found in the database", null);
            FacesContext.getCurrentInstance().addMessage(null, fMessage);
            setUp();
            setUpSearch();
            return "";
        }
    }
    
    /**
     * Die Seite der Details wird einen neuen Lieferanten zu erstellen vorbereitet
     * @return Seite Details
     */
    public String prepareNew(){
        supplier = new Supplier();
        formSupplier = new FormSupplier(false);
        return "supplierDetails?faces-redirect=true";
    }
    
    /**
     * Dieser Betrieb wird gemacht, wenn der Benutzer im Knopf "Cancel" gedrückt hat
     * @return Zu welcher Seite wird der Benutzer geführt
     */
    public String btnCancel_ActionPerformed(){
        if (formSupplier.isDisabled() || supplier.getIdBusinessPartner() == 0) {
            return "supplierManagement?faces-redirect=true";
        } else {
            formSupplier = new FormSupplier(supplier, true);
            return "supplierDetails?faces-redirect=true";
        }
    }
    
    /**
     * Wenn die Seite in Zeigen Modus ist, dann wird sie zur Anderung geändert. 
     * Sonst versucht das System den Lieferanten in der Datenbank zu speichern
     */
    public void btnEditSave_ActionPerformed(){
        if (formSupplier.isDisabled()) {
            formSupplier = new FormSupplier(supplier, false);
        } else {
            long id = 0;
            try {
                id = doSave();
                setUpSearch();
            } catch(Exception ex) {
                ex.printStackTrace(System.err);
            }
            
            prepareView(id, true);
        }
    }
    
    /**
     * Ein Lieferant wird in der Datanbank gespeichern
     * @return ID des Artikels
     * @throws Exception Wenn etwas nicht Normal geht, wird eine Ausnahme geworfen.
     */
    public long doSave() throws Exception{
        supplier.setTaxId(Long.valueOf(formSupplier.getTxtTaxId()));
        supplier.setName(formSupplier.getTxtName());
        supplier.setAddress(formSupplier.getTxtAddress());
        supplier.setZipCode(formSupplier.getTxtZipCode());
        supplier.setCity(formSupplier.getTxtCity());
        supplier.setRegion(formSupplier.getTxtRegion());
        supplier.setCountry(formSupplier.getTxtCountry());
        supplier.setEmail(formSupplier.getTxtEmail());
        supplier.setPhoneNumber(formSupplier.getTxtPhoneNumber());
        supplier.setMobileNumber(formSupplier.getTxtMobileNumber());
        supplier.setFaxNumber(formSupplier.getTxtFaxNumber());
        supplier.setDescription(formSupplier.getTxtDescription());
        
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession)context.getExternalContext().getSession(true);
        Long idEmployee = (Long)session.getAttribute("idemployee");
        
        supplier.setIdEmployeeModify(idEmployee);
        supplier.setLastModifiedDate(new Date());
        
        Supplier newSupplier = supplierController.save(supplier);
        
        return newSupplier.getIdBusinessPartner();
    }
    
    /**
     * Das System versuche einen Lieferanten zu löschen.
     */
    public void delete(){
        Supplier deleteSupplier = supplierController.getSupplierById(supplierId);
        FacesMessage fMessage;
        FacesMessage.Severity severity;
        String msg;
        if (deleteSupplier != null){
            try {
                if (supplierController.isSupplierOrdersEmpty(supplierId)) {
                supplierController.delete(deleteSupplier);
                severity = FacesMessage.SEVERITY_INFO;
                msg = "Supplier " + deleteSupplier.getName() + " was deleted.";
                } else {
                    severity = FacesMessage.SEVERITY_WARN;
                    msg = "Supplier " + deleteSupplier.getName() + " has already orders inserted.";
                }
            } catch (Exception e) {
                Logger.getGlobal().log(Level.SEVERE, e.getMessage());
                severity = FacesMessage.SEVERITY_FATAL;
                msg = "Error ocurred: " + e.getMessage();
            }
        } else {
            severity = FacesMessage.SEVERITY_ERROR;
            msg = "Supplier was not found in the Database.";
        }
        
        fMessage = new FacesMessage(severity, msg, null);

        FacesContext.getCurrentInstance().addMessage(null, fMessage);
        setUp();
        setUpSearch();
    }
    
    /**
     * Die vorige Seite wird geladen
     */
    public void previousPage(){
        if (supplierController.getPreviousPage(pageSize))
            supplierObjects = supplierController.loadTableRows(pageSize);
    }
    
    /**
     * Die nächste Seite wird geladen
     */
    public void nextPage(){
        if (supplierController.getNextPage(pageSize))
            supplierObjects = supplierController.loadTableRows(pageSize);
    }
    
    /**
     * Die Nummer der aktuele Seite und der Seitentotal wird gezeigt
     * @return Der Wert zu zeigen
     */
    public String getPageNumber(){
        return (supplierController.getPage().getNumber()+1) + " / " + supplierController.getPage().getTotalPages();
    }

    /**
     * @return the supplierController
     */
    public SupplierService getSupplierController() {
        return supplierController;
    }

    /**
     * @return the supplier
     */
    public Supplier getClient() {
        return supplier;
    }

    /**
     * @param supplier the supplier to set
     */
    public void setClient(Supplier supplier) {
        this.supplier = supplier;
    }

    /**
     * @return the supplierId
     */
    public long getSupplierId() {
        return supplierId;
    }

    /**
     * @param supplierId the supplierId to set
     */
    public void setSupplierId(long supplierId) {
        this.supplierId = supplierId;
    }

    /**
     * @return the formSupplier
     */
    public FormSupplier getFormSupplier() {
        return formSupplier;
    }

    /**
     * @param formSupplier the formSupplier to set
     */
    public void setFormSupplier(FormSupplier formSupplier) {
        this.formSupplier = formSupplier;
    }

    /**
     * @return the supplierObjects
     */
    public Object[][] getSupplierObjects() {
        return supplierObjects;
    }

    /**
     * @param supplierObjects the supplierObjects to set
     */
    public void setSupplierObjects(Object[][] supplierObjects) {
        this.supplierObjects = supplierObjects;
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
