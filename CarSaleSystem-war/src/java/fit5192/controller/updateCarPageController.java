/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5192.controller;

import fit5192.repository.CarRepository;
import fit5192.repository.entities.Car;
import fit5192.util.SuperBean;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Administrator
 */
@Named(value = "updateCarPageController")
@RequestScoped
public class updateCarPageController extends SuperBean implements Serializable {

    /**
     * Creates a new instance of updateCarPageController
     */
    @EJB
    private CarRepository carRepository;
    private Car updateCar;
    
    private List<String> sellStateList;
    
    public updateCarPageController() {
        updateCar = (Car) getSessionValue("updateCar");
        sellStateList = new ArrayList<>();
        sellStateList.add("unsold");
        sellStateList.add("sold");
    }

    public Car getUpdateCar() {
        return updateCar;
    }

    public void setUpdateCar(Car updateCar) {
        this.updateCar = updateCar;
    }

    public CarRepository getCarRepository() {
        return carRepository;
    }

    public void setCarRepository(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<String> getSellStateList() {
        return sellStateList;
    }

    public void setSellStateList(List<String> sellStateList) {
        this.sellStateList = sellStateList;
    }
    
    public void updateCar() throws Exception{
        String description = updateCar.getDescription();
        String previewUrl = updateCar.getPreviewUrl();
        updateCar.setDescription(description);
        updateCar.setPreviewUrl(previewUrl);
        carRepository.updateCar(updateCar);
    }
    
}
