package org.haegerp.jsf.controller.bean;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * Klasse, die die Erlaubnisse und das Modell führt
 * 
 * @author Fabio Codinha
 */
@ManagedBean
@Controller
@Scope(value = "session")
public class TemplateBean implements Serializable{
    
    //Artikel Erlaubnis
    private boolean permissionArticle;
    //Geschaftpartners Erlaubnis
    private boolean permissionBusinessPartners;
    //Personalabteilung Erlaubnis
    private boolean permissionHumanResources;
    //Firma Erlaubnis
    private boolean permissionCompany;
    //Lieferantenbestellung Erlaubnis
    private boolean permissionSupplierOrders;
    //Kundenangebot Erlaubnis
    private boolean permissionClientOffers;
    
    //Login oder Logout
    private String login;

    public TemplateBean() {
    }
    
    /**
     * Die Methode macht eine Rückstellung zu den Erlaubnissen
     */
    @PostConstruct
    public void resetPermissions(){
        login = "Login";
        permissionArticle = false;
        permissionBusinessPartners = false;
        permissionHumanResources = false;
        permissionCompany = false;
        permissionSupplierOrders = false;
        permissionClientOffers = false;
    }
    
    /**
     * Session wird gepützt
     * 
     * @return Der Benutzer wird zu der Login Seite geführt
     */
    public String loginAction(){
        if (!login.equals("Login")){
            FacesContext context = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession)context.getExternalContext().getSession(true);
            session.invalidate();
        }
        
        return "login.xhtml?faces-redirect=true";
    }
    
    /**
     * @return the permissionArticle
     */
    public boolean isPermissionArticle() {
        return permissionArticle;
    }

    /**
     * @param permissionArticle the permissionArticle to set
     */
    public void setPermissionArticle(boolean permissionArticle) {
        this.permissionArticle = permissionArticle;
    }

    /**
     * @return the permissionBusinessPartners
     */
    public boolean isPermissionBusinessPartners() {
        return permissionBusinessPartners;
    }

    /**
     * @param permissionBusinessPartners the permissionBusinessPartners to set
     */
    public void setPermissionBusinessPartners(boolean permissionBusinessPartners) {
        this.permissionBusinessPartners = permissionBusinessPartners;
    }

    /**
     * @return the permissionHumanResources
     */
    public boolean isPermissionHumanResources() {
        return permissionHumanResources;
    }

    /**
     * @param permissionHumanResources the permissionHumanResources to set
     */
    public void setPermissionHumanResources(boolean permissionHumanResources) {
        this.permissionHumanResources = permissionHumanResources;
    }

    /**
     * @return the permissionCompany
     */
    public boolean isPermissionCompany() {
        return permissionCompany;
    }

    /**
     * @param permissionCompany the permissionCompany to set
     */
    public void setPermissionCompany(boolean permissionCompany) {
        this.permissionCompany = permissionCompany;
    }

    /**
     * @return the permissionSupplierOrders
     */
    public boolean isPermissionSupplierOrders() {
        return permissionSupplierOrders;
    }

    /**
     * @param permissionSupplierOrders the permissionSupplierOrders to set
     */
    public void setPermissionSupplierOrders(boolean permissionSupplierOrders) {
        this.permissionSupplierOrders = permissionSupplierOrders;
    }

    /**
     * @return the permissionClientOffers
     */
    public boolean isPermissionClientOffers() {
        return permissionClientOffers;
    }

    /**
     * @param permissionClientOffers the permissionClientOffers to set
     */
    public void setPermissionClientOffers(boolean permissionClientOffers) {
        this.permissionClientOffers = permissionClientOffers;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }
}
