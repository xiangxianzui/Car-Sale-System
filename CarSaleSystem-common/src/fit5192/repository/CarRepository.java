/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5192.repository;

import fit5192.repository.entities.Car;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Administrator
 */
@Remote
public interface CarRepository {
    
    public void addCar(Car car) throws Exception;
    
    public void deleteCar(Long carId) throws Exception;
    
    public void updateCar(Car car) throws Exception;
    
    public Car searchCarById(Long id) throws Exception;
    
    public List<Car> getAllCars() throws Exception;
    
    public List<Car> getCarsByModelNoAndModelNameAndMakerAndType(int modelNo, String modelName, String maker, String type) throws Exception;
    
    public List<Car> getCarsByModelNo(int modelNo) throws Exception;
    
    public List<Car> getCarsByModelName(String modelName) throws Exception;
    
    public List<Car> getCarsByMaker(String maker) throws Exception;
    
    public List<Car> getCarsByType(String type) throws Exception;
    
    public List<Car> getCarsBySellState(String sellState) throws Exception;
    
    public void updateSellStateById(Long id, String sellState) throws Exception;
}
