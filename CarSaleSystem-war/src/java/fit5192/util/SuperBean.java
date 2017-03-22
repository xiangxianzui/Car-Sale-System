/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5192.util;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Administrator
 */
@Named(value = "superBean")
@SessionScoped
public class SuperBean implements Serializable{

    /**
     * Creates a new instance of UserSuperBean
     */
    public SuperBean() {
    }
    
    protected static Object getSessionValue(Object myKey){
        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(myKey);
    }
    
    protected static void setSessionValue(String myKey, Object myValue){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(myKey, myValue); 
    }
    
    protected static Object deleteSessionValue(String myKey){
        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(myKey);
    }
    
}
