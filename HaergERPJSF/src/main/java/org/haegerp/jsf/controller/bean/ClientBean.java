package org.haegerp.jsf.controller.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.haegerp.service.ClientCategoryService;
import org.haegerp.service.ClientService;
import org.haegerp.entity.Client;
import org.haegerp.entity.ClientCategory;
import org.haegerp.jsf.controller.form.FormClient;
import org.haegerp.jsf.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * Bean für die Seiten "ClientManagement" und "Details"
 * 
 * @author Fábio Codinha
 */
@ManagedBean
@Controller
@Scope(value = "session")
public class ClientBean implements Serializable{
    
    @Autowired
    private ClientService clientController;
    @Autowired
    private ClientCategoryService clientCategoryController;
    @Autowired
    private ClientCategoryBean clientCategoryBean;
    
    //Kunde, der in der Seite der Details zeigen wird
    private Client client;
    //Hilfsvariable für die Methode, die einen Kunden löschen.
    private long clientId;
    //Klasse, wo die Felder von dem Formular gespeichert werden
    private FormClient formClient;
    
    //Liste, die die Kundenkategorien haben
    private List<ClientCategory> categories;
    //Object, dass der Inhalt von der Tabelle hat
    private Object[][] clientObjects;
    
    //Variable, die die Ausgewählte ID enthalt
    private int cbSearchValue;
    
    //Wie viel Kunden wurde in einer Seite gezeigt
    private int pageSize;
    //Die Suche, die der Benutzer eingefügt hat
    private String search;
    
    //Löschen?
    private Validator validator = new Validator();
    
    /**
     * Defaultwert
     */
    public ClientBean() {
        pageSize = 10;
    }
    
    /**
     * Diese Methode lädt die Daten und bereitet das Formular vor
     */
    @PostConstruct
    public void setUp(){
        //Manual Dependency Injection
        clientCategoryBean.setClientBean(this);
        
        clientObjects = clientController.loadTableRows(pageSize);
        client = new Client();
        categories = clientCategoryController.getAllCategories();
        formClient = new FormClient(false);
    }

    /**
     * Wenn der Benutzer die Suche benutzen möchte, wird diese Methode gerufen
     */
    public void setUpSearch(){
        clientController.setSearch(search, pageSize);
        clientController.setCategory((long)cbSearchValue, pageSize);
        clientObjects = clientController.loadTableRows(pageSize);
    }
    
    /**
     * Die Seite der Details wird vorbereitet.
     * 
     * @param id ID des Kunden
     * @param disabled True - Der Kunde wird nur gezeigt; False - Der Kunde kann geändert werden.
     * @return Wenn die ID gültig ist, dann die Seite der Details wird geladen
     */
    public String prepareView(long id, boolean disabled) {
        client = clientController.getClientById(id);
        categories = clientCategoryController.getAllCategories();
        if (client != null) {
            formClient = new FormClient(client, disabled);
            return "clientDetails?faces-redirect=true";
        } else {
            FacesMessage fMessage = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Client was not found in the database", null);
            FacesContext.getCurrentInstance().addMessage(null, fMessage);
            setUp();
            setUpSearch();
            return "";
        }
    }
    
    /**
     * Die Seite der Details wird einen neuen Kunden zu erstellen vorbereitet
     * @return Seite Details
     */
    public String prepareNew(){
        client = new Client();
        formClient = new FormClient(false);
        return "clientDetails?faces-redirect=true";
    }
    
    /**
     * Dieser Betrieb wird gemacht, wenn der Benutzer im Knopf "Cancel" gedrückt hat
     * @return Zu welcher Seite wird der Benutzer geführt
     */
    public String btnCancel_ActionPerformed(){
        if (formClient.isDisabled() || client.getIdBusinessPartner() == 0) {
            return "clientManagement?faces-redirect=true";
        } else {
            formClient = new FormClient(client, true);
            return "clientDetails?faces-redirect=true";
        }
    }
    
    /**
     * Wenn die Seite in Zeigen Modus ist, dann wird sie zur Anderung geändert. 
     * Sonst versucht das System den Kunden in der Datenbank zu speichern
     */
    public void btnEditSave_ActionPerformed(){
        if (formClient.isDisabled()) {
            formClient = new FormClient(client, false);
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
     * Ein Artikel wird in der Datanbank gespeichern
     * @return ID des Kunden
     * @throws Exception Wenn etwas nicht Normal geht, wird eine Ausnahme geworfen.
     */
    public long doSave() throws Exception{
        ClientCategory clientCategory = clientCategoryController.getClientCategoryId(formClient.getCbClientCategory());
        
        client.setTaxId(Long.valueOf(formClient.getTxtTaxId()));
        client.setName(formClient.getTxtName());
        client.setClientCategory(clientCategory);
        client.setAddress(formClient.getTxtAddress());
        client.setZipCode(formClient.getTxtZipCode());
        client.setCity(formClient.getTxtCity());
        client.setRegion(formClient.getTxtRegion());
        client.setCountry(formClient.getTxtCountry());
        client.setEmail(formClient.getTxtEmail());
        client.setPhoneNumber(formClient.getTxtPhoneNumber());
        client.setMobileNumber(formClient.getTxtMobileNumber());
        client.setFaxNumber(formClient.getTxtFaxNumber());
        client.setDescription(formClient.getTxtDescription());
        
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession)context.getExternalContext().getSession(true);
        Long idEmployee = (Long)session.getAttribute("idemployee");
        
        client.setIdEmployeeModify(idEmployee);
        client.setLastModifiedDate(new Date());
        
        Client newClient = clientController.save(client);
        
        clientCategoryBean.setUp();
        
        return newClient.getIdBusinessPartner();
    }
    
    /**
     * Das System versuche einen Kunden zu löschen.
     */
    public void delete(){
        Client deleteClient = clientController.getClientById(clientId);
        FacesMessage fMessage;
        FacesMessage.Severity severity;
        String msg;
        if (deleteClient != null){
            try {
                if (clientController.isClientOffersEmpty(clientId)) {
                clientController.delete(deleteClient);
                severity = FacesMessage.SEVERITY_INFO;
                msg = "Client " + deleteClient.getName() + " was deleted.";
                } else {
                    severity = FacesMessage.SEVERITY_WARN;
                    msg = "Client " + deleteClient.getName() + " has already offers inserted.";
                }
            } catch (Exception e) {
                Logger.getGlobal().log(Level.SEVERE, e.getMessage());
                severity = FacesMessage.SEVERITY_FATAL;
                msg = "Error ocurred: " + e.getMessage();
            }
        } else {
            severity = FacesMessage.SEVERITY_ERROR;
            msg = "Client was not found in the Database.";
        }
        
        fMessage = new FacesMessage(severity, msg, null);

        FacesContext.getCurrentInstance().addMessage(null, fMessage);
        setUp();
        setUpSearch();
        clientCategoryBean.setUp();
    }
    
    /**
     * Die vorige Seite wird geladen
     */
    public void previousPage(){
        if (clientController.getPreviousPage(pageSize))
            clientObjects = clientController.loadTableRows(pageSize);
    }
    
    /**
     * Die nächste Seite wird geladen
     */
    public void nextPage(){
        if (clientController.getNextPage(pageSize))
            clientObjects = clientController.loadTableRows(pageSize);
    }
    
    /**
     * Die Nummer der aktuele Seite und der Seitentotal wird gezeigt
     * @return Der Wert zu zeigen
     */
    public String getPageNumber(){
        return (clientController.getPage().getNumber()+1) + " / " + clientController.getPage().getTotalPages();
    }

    /**
     * @return the clientController
     */
    public ClientService getClientController() {
        return clientController;
    }

    /**
     * @return the clientCategoryController
     */
    public ClientCategoryService getClientCategoryController() {
        return clientCategoryController;
    }

    /**
     * @return the client
     */
    public Client getClient() {
        return client;
    }

    /**
     * @param client the client to set
     */
    public void setClient(Client client) {
        this.client = client;
    }

    /**
     * @return the clientId
     */
    public long getClientId() {
        return clientId;
    }

    /**
     * @param clientId the clientId to set
     */
    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    /**
     * @return the formClient
     */
    public FormClient getFormClient() {
        return formClient;
    }

    /**
     * @param formClient the formClient to set
     */
    public void setFormClient(FormClient formClient) {
        this.formClient = formClient;
    }

    /**
     * @return the categories
     */
    public List<ClientCategory> getCategories() {
        return categories;
    }

    /**
     * @param categories the categories to set
     */
    public void setCategories(List<ClientCategory> categories) {
        this.categories = categories;
    }

    /**
     * @return the clientObjects
     */
    public Object[][] getClientObjects() {
        return clientObjects;
    }

    /**
     * @param clientObjects the clientObjects to set
     */
    public void setClientObjects(Object[][] clientObjects) {
        this.clientObjects = clientObjects;
    }

    /**
     * @return the cbSearchValue
     */
    public int getCbSearchValue() {
        return cbSearchValue;
    }

    /**
     * @param cbSearchValue the cbSearchValue to set
     */
    public void setCbSearchValue(int cbSearchValue) {
        this.cbSearchValue = cbSearchValue;
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
     * @return the clientCategoryBean
     */
    public ClientCategoryBean getClientCategoryBean() {
        return clientCategoryBean;
    }

    /**
     * @param clientCategoryBean the clientCategoryBean to set
     */
    public void setClientCategoryBean(ClientCategoryBean clientCategoryBean) {
        this.clientCategoryBean = clientCategoryBean;
    }
    
}
