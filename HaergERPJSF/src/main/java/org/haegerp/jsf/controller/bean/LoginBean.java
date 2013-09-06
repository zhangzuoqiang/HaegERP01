/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.haegerp.jsf.controller.bean;

import java.io.Serializable;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.haegerp.controller.EmployeeController;
import org.haegerp.controller.UserGroupController;
import org.haegerp.entity.Employee;
import org.haegerp.entity.Permission;
import org.haegerp.tools.MD5Digest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@ManagedBean
@Controller
@Scope(value = "session")
public class LoginBean implements Serializable {

    @Autowired
    private EmployeeController employeeController;
    @Autowired
    private UserGroupController userGroupController;
    @Autowired
    private TemplateBean templateBean;
    
    //Wert des Feldes
    //Benutzer
    private String username;
    //Kennwort
    private String password;
    //Kennwort als MD5
    private String passwordMD5;

    /**
     * Wenn der Benutzer die richtige Anmeldung einf�gt hat, dann ist er zum Hauptmen� zu f�hren
     * 
     * @return Seite, die der Benutzer gef�hrt wird.
     */
    public String login() {
        passwordMD5 = MD5Digest.toMD5(getPassword());

        Employee employee = employeeController.isLoginCorrect(getUsername(), passwordMD5);

        if (employee != null) {
            FacesContext context = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
            session.setAttribute("idemployee", employee.getIdEmployee());
            verifyPermissions(employee);
            return "mainMenu?faces-redirect=true";
        }

        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wrong Login", "Username and Password does not match");
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);

        return "";
    }

    /**
     * Welche Erlaubnis hat der Benutzer
     * @param employee Mitarbeiter
     */
    private void verifyPermissions(Employee employee) {
        Set<Permission> permissions = userGroupController.getUserGroupById(employee.getUserGroup().getIdUserGroup()).getPermissions();

        for (Permission permission : permissions) {
            long x = permission.getIdPermission();

            if (x == 1) {
                templateBean.setPermissionArticle(true);
            } else {
                if (x == 2) {
                    templateBean.setPermissionBusinessPartners(true);
                } else {
                    if (x == 3) {
                        templateBean.setPermissionHumanResources(true);
                    } else {
                        if (x == 4) {
                            templateBean.setPermissionClientOffers(true);
                        } else {
                            if (x == 5) {
                                templateBean.setPermissionSupplierOrders(true);
                            } else {
                                if (x == 6) {
                                    templateBean.setPermissionCompany(true);
                                }
                            }
                        }
                    }
                }
            }
        }

        templateBean.setLogin("Logout");
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
