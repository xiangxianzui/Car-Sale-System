/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5192.repository.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "users")
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u")
})
public class Users implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "UserId")
    private Long userId;
    
    @Basic(optional = false)
    @NotNull
    @Pattern(regexp="[a-zA-Z]+")
    @Column(name = "LastName")
    private String lastName;
    
    @Basic(optional = false)
    @NotNull
    @Pattern(regexp="[a-zA-Z]+")
    @Column(name = "FirstName")
    private String firstName;
    
    @Basic(optional = false)
    @NotNull
    @Pattern(regexp="(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)")
    @Column(name = "Email")
    private String email;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "Password")
    private String password;
    
    @Basic(optional = false)
    @NotNull
    @Pattern(regexp="SalesPerson|Customer")
    @Column(name = "Type")
    private String type;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "Address")
    private String address;
    
    @Basic(optional = false)
    @NotNull
    @Pattern(regexp="(6\\d{7})|(1\\d{10})")
    @Column(name = "Phone")
    private String phone;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "CarList")
    private String carList;

    public Users() {
    }
    
    public Users(Long userId){
        this.userId = userId;
    }

    public Users(Long userId, String lastName, String firstName, String email, String password, String type, String address, String phone, String carList) {
        this.userId = userId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.password = password;
        this.type = type;
        this.address = address;
        this.phone = phone;
        this.carList = carList;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCarList() {
        return carList;
    }

    public void setCarList(String carList) {
        this.carList = carList;
    }

    @Override
    public String toString() {
        return "Users{" + "lastName=" + lastName + ", firstName=" + firstName + ", email=" + email + ", password=" + password + ", type=" + type + ", address=" + address + ", phone=" + phone + ", carList=" + carList + '}';
    }


    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }
    
    
    
}