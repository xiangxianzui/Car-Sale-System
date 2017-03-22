/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5192.mbeans;

import fit5192.repository.GroupsRepository;
import fit5192.repository.UsersRepository;
import fit5192.repository.entities.Groups;
import fit5192.repository.entities.Users;
import fit5192.util.MD5;
import fit5192.util.SHA256;
import fit5192.util.SuperBean;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Administrator
 */
@Named(value = "userBean")
@SessionScoped
public class UserBean extends SuperBean implements Serializable {

    /**
     * Creates a new instance of UserBean
     */
    
    
    @EJB
    private UsersRepository usersRepository;
    @EJB
    private GroupsRepository groupsRepository;
    private Users user;
    private String errMsg;
    
    private String userSearchCriterionSelectedType;
    private String userSearchCriterionSelectedValue;
    private String userSearchErrMsg;
    
    public UserBean() {
        this.user = new Users();
        this.errMsg = "";
        
        userSearchCriterionSelectedType = "";
        userSearchCriterionSelectedValue = "";
        userSearchErrMsg = "";
    }

    public UsersRepository getUsersRepository() {
        return usersRepository;
    }

    public void setUsersRepository(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getUserSearchCriterionSelectedType() {
        return userSearchCriterionSelectedType;
    }

    public void setUserSearchCriterionSelectedType(String userSearchCriterionSelectedType) {
        this.userSearchCriterionSelectedType = userSearchCriterionSelectedType;
    }

    public String getUserSearchCriterionSelectedValue() {
        return userSearchCriterionSelectedValue;
    }

    public void setUserSearchCriterionSelectedValue(String userSearchCriterionSelectedValue) {
        this.userSearchCriterionSelectedValue = userSearchCriterionSelectedValue;
    }

    public String getUserSearchErrMsg() {
        return userSearchErrMsg;
    }

    public void setUserSearchErrMsg(String userSearchErrMsg) {
        this.userSearchErrMsg = userSearchErrMsg;
    }

    public GroupsRepository getGroupsRepository() {
        return groupsRepository;
    }

    public void setGroupsRepository(GroupsRepository groupsRepository) {
        this.groupsRepository = groupsRepository;
    }
    
    
//    public String loginOnclick() throws Exception{
//        if(user.getEmail()!=null && user.getType()!=null && user.getPassword()!=null){
//            if(!user.getEmail().equals("") && !user.getType().equals("") && !user.getPassword().equals("")){
//                String email = user.getEmail();
//                String type = user.getType();
//                String password = user.getPassword();
//                MD5 md5 = new MD5();
//                String encodedPassword = md5.md5Encode(password);
//                Users thisUser = usersRepository.getUserByEmail(email);
//                if(thisUser == null){
//                    errMsg = "You have not registered yet! Register first!";
//                    return null;
//                }
//                else{
//                    if(thisUser.getPassword().equals(encodedPassword)){
//                        if(thisUser.getType().equals(type)){
//                            errMsg = "Log in success!";
//                            setSessionValue("currentUser", thisUser);
//                            //FacesContext.getCurrentInstance().getExternalContext().redirect("/CarSaleSystem-war/faces/customerPage.xhtml");
//                            switch (thisUser.getType()) {
//                                case "Customer":
//                                    return "customerPage";
//                                case "SalesPerson":
//                                    return "salespersonPage";
//                                default:
//                                    return null;
//                            }
//                        } else{
//                            errMsg = "You are attempting to log in a different type!";
//                            return null;
//                        }   
//                    } else{
//                        errMsg = "Password is wrong!";
//                        return null;
//                    }
//                }
//            }
//            else{
//                errMsg = "Please fill all blanks!";
//                return null;
//            }
//        }
//        else{
//            errMsg = "One or more user attributes are null!";
//            return null;
//        }
////        if(!user.getFirstName().equals("") && !user.getLastName().equals("") && !user.getType().equals("") && !user.getPassword().equals("")){
////            System.out.println("hiohoioihohoihoiho");
////        }
//    }
    
    public String loginOnclick() throws Exception{
        if(user.getEmail()!=null && user.getPassword()!=null){
            if(!user.getEmail().equals("") && !user.getPassword().equals("")){
                String email = user.getEmail();
                //String type = user.getType();
                String password = user.getPassword();
                System.out.println(email + ", " + password);
                Users thisUser = usersRepository.getUserByEmail(email);
                if(thisUser == null){
                    errMsg = "You have not registered yet! Register first!";
                    return null;
                } else{
                    HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
                    try {
                        request.login(email, password);

                        if (request.isUserInRole("Customer")) {
                            System.out.println("**********log in as customer***************");
                            setSessionValue("currentUser", thisUser);
                            errMsg = "Log in as customer!";
                            return "/customerPage?faces-redirect=true";
                        } else if (request.isUserInRole("SalesPerson")) {
                            System.out.println("**********log in as salesperson***************");
                            setSessionValue("currentUser", thisUser);
                            errMsg = "Log in as salesperson!";
                            return "/salespersonPage?faces-redirect=true";
                        }else{
                            System.out.println("System error. Try again later.");
                            errMsg = "System error. Try again later.";
                        }
                    } catch (Exception e) {
                        System.out.println("password is wrong");
                        errMsg = "password is wrong!";
                    }
                }
            } else{
                errMsg = "Please fill all blanks!";
                return null;
                }
        } else{
            errMsg = "One or more user attributes are null!";
            return null;
        }
        return null;
    }
    
    public String registerOnclick() throws Exception{
        if(user.getFirstName()!=null && user.getLastName()!=null && user.getEmail()!=null && user.getPassword()!=null && user.getType()!=null && user.getAddress()!=null
                && user.getPhone()!=null){
            if(!user.getFirstName().equals("") && !user.getLastName().equals("") && !user.getEmail().equals("") && !user.getPassword().equals("") && !user.getType().equals("") && !user.getAddress().equals("")
                && !user.getPhone().equals("")){
                if(user.getPassword().matches("\\w{4,30}")){//password length should be >=4 and <=30
                    String firstName = user.getFirstName();
                    String lastName = user.getLastName();
                    String email = user.getEmail();
                    String password = user.getPassword();
                    SHA256 sha256 = new SHA256();
                    String encodedPassword = sha256.SHA256Encode(password);
                    String type = user.getType();
                    String address = user.getAddress();
                    String phone = user.getPhone();
                    Users thisUser = usersRepository.getUserByEmail(email);
                    if(thisUser != null){
                        errMsg = email + " has already been registered!";
                        return null;
                    } else{
                        String carList = " ";
                        Users newUser = new Users();
                        newUser.setLastName(lastName);
                        newUser.setFirstName(firstName);
                        newUser.setEmail(email);
                        newUser.setPassword(encodedPassword);
                        newUser.setType(type);
                        newUser.setAddress(address);
                        newUser.setPhone(phone);
                        newUser.setCarList(carList);
                        usersRepository.addUser(newUser);
                        
                        Groups group = new Groups();
                        group.setEmail(email);
                        group.setGroupname(type);
                        groupsRepository.addGroup(group);
                        
                        setSessionValue("currentUser", newUser);
                        errMsg = "Register success!";
                        switch (newUser.getType()) {
                            case "Customer":
                                return "/customerPage?faces-redirect=true";
                            case "SalesPerson":
                                return "/salespersonPage?faces-redirect=true";
                            default:
                                return null;
                        }
                    }
                } else{
                    errMsg = "Password length should be >=4 and <=30!";
                    return null;
                }
                
                
            } else{
                errMsg = "Please fill all blanks!";
                return null;
            }
        } else{
            errMsg = "One or more user attributes are null!";
            return null;
        }
       
    }
    
    public List<Users> searchOnclick() throws Exception{// deal with search user button in salesperson page
        List<Users> resultUsers = null;
        switch (userSearchCriterionSelectedType) {
            case "All Users":
                resultUsers = usersRepository.getAllUsers();
                if(resultUsers == null || resultUsers.isEmpty())  {userSearchErrMsg = "No users found!";}
                else    {userSearchErrMsg = "";}
                return resultUsers;
            case "User Id":
                if(userSearchCriterionSelectedValue.matches("[0-9]+")){
                    resultUsers = new ArrayList<>();
                    resultUsers.add(usersRepository.searchUserById(Long.parseLong(userSearchCriterionSelectedValue)));
                    if(resultUsers.isEmpty())  {userSearchErrMsg = "No users found!";}
                    else    {userSearchErrMsg = "";}
                    return resultUsers;
                } else{
                    userSearchErrMsg = "Invalid input for User Id! Input a number!";
                    return null;
                }
            case "Last Name":
                if(userSearchCriterionSelectedValue.matches("[a-zA-Z]+")){
                    resultUsers = usersRepository.getUsersByLastName(userSearchCriterionSelectedValue);
                    if(resultUsers == null || resultUsers.isEmpty())  {userSearchErrMsg = "No users found!";}
                    else    {userSearchErrMsg = "";}
                    return resultUsers;
                } else{
                    userSearchErrMsg = "Invalid input for Last Name! Input an English word!";
                    return null;
                }
            case "First Name":
                if(userSearchCriterionSelectedValue.matches("[a-zA-Z]+")){
                    resultUsers = usersRepository.getUsersByFirstName(userSearchCriterionSelectedValue);
                    if(resultUsers == null || resultUsers.isEmpty())  {userSearchErrMsg = "No users found!";}
                    else    {userSearchErrMsg = "";}
                    return resultUsers;
                } else{
                    userSearchErrMsg = "Invalid input for First Name! Input an English word!";
                    return null;
                }
            case "Email":
                if(userSearchCriterionSelectedValue.matches("(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)")){
                    resultUsers = new ArrayList<>();
                    resultUsers.add(usersRepository.getUserByEmail(userSearchCriterionSelectedValue));
                    if(resultUsers.isEmpty())  {userSearchErrMsg = "No users found!";}
                    else    {userSearchErrMsg = "";}
                    return resultUsers;
                } else{
                    userSearchErrMsg = "Invalid input for Email!";
                    return null;
                }
            default:
                return null;
        }
    }
    
    public List<String> getAllSalespersonNames() throws Exception{
        List<Users> salespersons = usersRepository.getAllSalespersons();
        List<String> names = new ArrayList<String>();
        for (Users salesperson : salespersons) {
            names.add(salesperson.getFirstName() + " " + salesperson.getLastName());
        }
        return names;
    }
    
    
    
}
