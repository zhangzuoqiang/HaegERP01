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

import org.haegerp.entity.ClientCategory;
import org.haegerp.jsf.controller.form.FormClientCategory;
import org.haegerp.jsf.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@ManagedBean
@Controller
@Scope(value = "session")
public class ClientCategoryBean implements Serializable{
    
    @Autowired
    private ClientCategoryController clientCategoryController;

    private ClientCategory clientCategory;
    private long clientCategoryId;

    private List<ClientCategory> clientCategories;
    private Object[][] clientCategoryObjects;
    
    private FormClientCategory formClientCategory;
    
    private int pageSize;
    private String search;

    public ClientCategoryBean() {
        pageSize = 10;
    }
    
    @PostConstruct
    public void setUp(){
        clientCategoryObjects = clientCategoryController.loadTableRows(pageSize);
        clientCategory = new ClientCategory();
        formClientCategory = new FormClientCategory(false);
    }

    public void setUpSearch(){
        clientCategoryController.setSearch(search, pageSize);
        clientCategoryObjects = clientCategoryController.loadTableRows(pageSize);
    }
    
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
    
    public String prepareNew(){
        clientCategory = new ClientCategory();
        formClientCategory = new FormClientCategory(false);
        return "clientCategoryDetails?faces-redirect=true";
    }
    
    public String btnCancel_ActionPerformed(){
        if (formClientCategory.isDisabled() || clientCategory.getIdClientCategory()== 0) {
            return "clientCategoryManagement?faces-redirect=true";
        } else {
            formClientCategory = new FormClientCategory(clientCategory, true);
            return "clientCategoryDetails?faces-redirect=true";
        }
    }
    
    public void btnEditSave_ActionPerformed(){
        if (formClientCategory.isDisabled()) {
            formClientCategory = new FormClientCategory(clientCategory, false);
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
        clientCategory.setName(formClientCategory.getTxtName());
        clientCategory.setDescription(formClientCategory.getTxtDescription());
        
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession)context.getExternalContext().getSession(true);
        Long idEmployee = (Long)session.getAttribute("idemployee");
        
        clientCategory.setIdEmployeeModify(idEmployee);
        clientCategory.setLastModifiedDate(new Date());
        
        return clientCategoryController.save(clientCategory).getIdClientCategory();
    }
    
    public void delete(){
        ClientCategory deleteClientCategory = clientCategoryController.getClientCategoryId(clientCategoryId);
        FacesMessage fMessage;
        FacesMessage.Severity severity;
        String msg;
        if (deleteClientCategory != null){
            try {
                clientCategoryController.delete(deleteClientCategory);
                severity = FacesMessage.SEVERITY_INFO;
                msg = "Client's Category " + deleteClientCategory.getName() + " was deleted.";
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
    }
    
    public void previousPage(){
        if (clientCategoryController.getPreviousPage(getPageSize()))
            clientCategoryObjects = clientCategoryController.loadTableRows(pageSize);
    }
    
    public void nextPage(){
        if (clientCategoryController.getNextPage(getPageSize()))
            clientCategoryObjects = clientCategoryController.loadTableRows(pageSize);
    }
    
    public String getPageNumber(){
        return (clientCategoryController.getPage().getNumber()+1) + " / " + clientCategoryController.getPage().getTotalPages();
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
}