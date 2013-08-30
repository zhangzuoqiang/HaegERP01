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
import org.haegerp.controller.ClientCategoryController;
import org.haegerp.controller.ClientController;
import org.haegerp.entity.Client;
import org.haegerp.entity.ClientCategory;
import org.haegerp.jsf.controller.form.FormClient;
import org.haegerp.jsf.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@ManagedBean
@Controller
@Scope(value = "session")
public class ClientBean implements Serializable{
    
    @Autowired
    private ClientController clientController;
    @Autowired
    private ClientCategoryController clientCategoryController;
    @Autowired
    private ClientCategoryBean clientCategoryBean;
    
    private Client client;
    private long clientId;
    
    private FormClient formClient;
    
    private List<ClientCategory> categories;
    private Object[][] clientObjects;
    
    private int cbSearchValue;
    
    private int pageSize;
    private String search;

    private Validator validator = new Validator();
    
    public ClientBean() {
        pageSize = 10;
    }
    
    @PostConstruct
    public void setUp(){
        //Manual Dependency Injection
        clientCategoryBean.setClientBean(this);
        
        clientObjects = clientController.loadTableRows(pageSize);
        client = new Client();
        categories = clientCategoryController.getAllCategories();
        formClient = new FormClient(false);
    }

    public void setUpSearch(){
        clientController.setSearch(search, pageSize);
        clientController.setCategory((long)cbSearchValue, pageSize);
        clientObjects = clientController.loadTableRows(pageSize);
    }
    
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
    
    public String prepareNew(){
        client = new Client();
        formClient = new FormClient(false);
        return "clientDetails?faces-redirect=true";
    }
    
    public String btnCancel_ActionPerformed(){
        if (formClient.isDisabled() || client.getIdBusinessPartner() == 0) {
            return "clientManagement?faces-redirect=true";
        } else {
            formClient = new FormClient(client, true);
            return "clientDetails?faces-redirect=true";
        }
    }
    
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
    
    public void delete(){
        Client deleteClient = clientController.getClientById(clientId);
        FacesMessage fMessage;
        FacesMessage.Severity severity;
        String msg;
        if (deleteClient != null){
            try {
                clientController.delete(deleteClient);
                severity = FacesMessage.SEVERITY_INFO;
                msg = "Client " + deleteClient.getName() + " was deleted.";
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
    
    public void previousPage(){
        if (clientController.getPreviousPage(pageSize))
            clientObjects = clientController.loadTableRows(pageSize);
    }
    
    public void nextPage(){
        if (clientController.getNextPage(pageSize))
            clientObjects = clientController.loadTableRows(pageSize);
    }
    
    public String getPageNumber(){
        return (clientController.getPage().getNumber()+1) + " / " + clientController.getPage().getTotalPages();
    }

    /**
     * @return the clientController
     */
    public ClientController getClientController() {
        return clientController;
    }

    /**
     * @return the clientCategoryController
     */
    public ClientCategoryController getClientCategoryController() {
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
