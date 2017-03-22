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
@Table(name = "Car")
@NamedQueries({
    @NamedQuery(name = "Car.findAll", query = "SELECT c FROM Car c")
//    @NamedQuery(name = "Car.findByModelNoAndModelNameAndMakerAndType", query = "SELECT c FROM Car c WHERE c.modelNo=:modelNo AND "
//            + "c.modelName=:modelName AND c.maker=:maker AND c.type=:type")
})
public class Car implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CarId")
    private Long carId;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "ModelNo")
    private int modelNo;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "VIN")
    private String VIN;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "ModelName")
    private String modelName;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "Maker")
    private String maker;
    
    @Basic(optional = false)
    @NotNull @Pattern(regexp="Sedan|4 wheel drive|Truck")
    @Column(name = "Type")
    private String type;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "Thumbnail")
    private String thumbnail;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "Description")
    private String description;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "PreviewUrl")
    private String previewUrl;
    
    @Basic(optional = false)
    @NotNull @Pattern(regexp="sold|unsold")
    @Column(name = "SellState")
    private String sellState;

    public Car() {
       
    }
    
    public Car(Long carId) {
        this.carId = carId;
        
    }

    public Car(Long carId, int modelNo, String VIN, String modelName, String maker, String type, String thumbnail, String description, String previewUrl, String sellState) {
        this.carId = carId;
        this.modelNo = modelNo;
        this.VIN = VIN;
        this.modelName = modelName;
        this.maker = maker;
        this.type = type;
        this.thumbnail = thumbnail;
        this.description = description;
        this.previewUrl = previewUrl;
        this.sellState = sellState;
    }    

    public Car(Car car) {
        this.carId = car.carId;
        this.modelNo = car.modelNo;
        this.VIN = car.VIN;
        this.modelName = car.modelName;
        this.maker = car.maker;
        this.type = car.type;
        this.thumbnail = car.thumbnail;
        this.description = car.description;
        this.previewUrl = car.previewUrl;
        
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public int getModelNo() {
        return modelNo;
    }

    public void setModelNo(int modelNo) {
        this.modelNo = modelNo;
    }

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    public String getSellState() {
        return sellState;
    }

    public void setSellState(String sellState) {
        this.sellState = sellState;
    }

    @Override
    public String toString() {
        return "Car{" + "carId=" + carId + ", modelNo=" + modelNo + ", VIN=" + VIN + ", modelName=" + modelName + ", maker=" + maker + ", type=" + type + ", thumbnail=" + thumbnail + ", description=" + description + ", previewUrl=" + previewUrl + ", sellState=" + sellState + '}';
    }
    

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Car)) {
            return false;
        }
        Car other = (Car) object;
        if ((this.carId == null && other.carId != null) || (this.carId != null && !this.carId.equals(other.carId))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (carId != null ? carId.hashCode() : 0);
        return hash;
    }
}
