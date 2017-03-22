/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5192.controller;

import fit5192.repository.entities.Users;
import fit5192.util.SuperBean;
import java.io.IOException;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Administrator
 */
@Named(value = "customerPageController")
@RequestScoped
public class CustomerPageController extends SuperBean implements Serializable{

    /**
     * Creates a new instance of CustomerPageController
     */
    private String pageTitle;
    private Users user;
    
    
    public CustomerPageController() {
        user = (Users) getSessionValue("currentUser");
        pageTitle = "Welcome customer " + user.getFirstName() + " " + user.getLastName();

        
//        setSessionValue("carSearchCriterionSelectedType", carSearchCriterionSelectedType);
//        setSessionValue("carSearchCriterionSelectedValue", carSearchCriterionSelectedValue);
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

 
    
    public boolean canBuyCar(String sellState){
        //System.out.println(sellState);
        if(sellState != null){
            if(sellState.equals("sold")){
                return false;
            }
            if(sellState.equals("unsold")){
                return true;
            }
        }
        return false;
    }
    
    public void logOutOnclick() throws IOException{
        Users user = (Users) deleteSessionValue("currentUser");
        System.out.println(user.toString());
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.invalidateSession();
        externalContext.redirect(externalContext.getRequestContextPath() + "/faces/index.xhtml");
    }
}
