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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "sale")
@NamedQueries({
    @NamedQuery(name = "Sale.findAll", query = "SELECT s FROM Sale s")
//    @NamedQuery(name = "Car.findByModelNoAndModelNameAndMakerAndType", query = "SELECT c FROM Car c WHERE c.modelNo=:modelNo AND "
//            + "c.modelName=:modelName AND c.maker=:maker AND c.type=:type")
})
public class Sale implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SaleId")
    private Long saleId;
    
//    @OneToOne(optional = false)
//    @JoinColumn(name = "CustomerId", referencedColumnName = "UserId")
    @JoinColumn(name = "Customer")
    @ManyToOne(optional = false)
    private Users customer;
    
//    @OneToOne(optional = false)
//    @JoinColumn(name = "SalespersonId", referencedColumnName = "UserId")
    @JoinColumn(name = "Salesperson")
    @ManyToOne(optional = false)
    private Users salesperson;
    
    @Basic(optional = false)
    @NotNull @Pattern(regexp="(\\d{1,4})(-|\\/)(\\d{1,2})\\2(\\d{1,2}) (\\d{1,2}):(\\d{1,2}):(\\d{1,2})") //matches 2016-06-30 23:18:59 etc
    @Column(name = "SaleDate")
    private String saleDate;
    
    @JoinColumn(name = "Car")
    @ManyToOne(optional = false)
    private Car car;
    
    @Basic(optional = false)
    @NotNull @Pattern(regexp="unpaid|paid")
    @Column(name = "Status")
    private String status;

    public Sale() {
    }

    public Sale(Long saleId) {
        this.saleId = saleId;
    }

    public Sale(Long saleId, Users customer, Users salesperson, String saleDate, Car car, String status) {
        this.saleId = saleId;
        this.customer = customer;
        this.salesperson = salesperson;
        this.saleDate = saleDate;
        this.car = car;
        this.status = status;
    }

    public Long getSaleId() {
        return saleId;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }

    public Users getCustomer() {
        return customer;
    }

    public void setCustomer(Users customer) {
        this.customer = customer;
    }

    public Users getSalesperson() {
        return salesperson;
    }

    public void setSalesperson(Users salesperson) {
        this.salesperson = salesperson;
    }

    public String getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(String saleDate) {
        this.saleDate = saleDate;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Sale{" + "saleId=" + saleId + ", customer=" + customer + ", salesperson=" + salesperson + ", saleDate=" + saleDate + ", car=" + car + ", status=" + status + '}';
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sale)) {
            return false;
        }
        Sale other = (Sale) object;
        if ((this.saleId == null && other.saleId != null) || (this.saleId != null && !this.saleId.equals(other.saleId))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (saleId != null ? saleId.hashCode() : 0);
        return hash;
    }
}
