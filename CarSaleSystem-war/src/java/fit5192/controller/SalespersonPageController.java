/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5192.controller;

import fit5192.repository.entities.Users;
import fit5192.util.SuperBean;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Administrator
 */
@Named(value = "salespersonPageController")
@RequestScoped
public class SalespersonPageController extends SuperBean implements Serializable {

    /**
     * Creates a new instance of SalespersonPageController
     */
    private String pageTitle;
    private Users user;
    
    
    public SalespersonPageController() {
        user = (Users) getSessionValue("currentUser");
        pageTitle = "Welcome salesperson " + user.getFirstName() + " " + user.getLastName();
        
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

   public void logOutOnclick() throws IOException{
        Users user = (Users) deleteSessionValue("currentUser");
        System.out.println(user.toString());
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.invalidateSession();
        externalContext.redirect(externalContext.getRequestContextPath() + "/faces/index.xhtml");
    }
    
}
