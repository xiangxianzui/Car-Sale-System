/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5192.mbeans;

import fit5192.repository.BingImageSearch;
import fit5192.repository.BingWebSearch;
import fit5192.repository.CarRepository;
import fit5192.repository.entities.Car;
import fit5192.util.RandomGenerator;
import fit5192.util.SuperBean;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Administrator
 */
@Named(value = "carBean")
@SessionScoped
public class CarBean extends SuperBean implements Serializable {

    /**
     * Creates a new instance of CarBean
     */
    @EJB
    private CarRepository carRepository;
    @EJB
    private BingImageSearch bingImageSearch;
    @EJB
    private BingWebSearch bingWebSearch;
    private Car car;
    
    private String errMsg;
    
    private String carSearchCriterionSelectedType;
    private String carSearchCriterionSelectedValue;
    
    private Car addCar;
    private String addCarErrMsg;
    
    public CarBean() {
        this.car = new Car();
        
        errMsg = "";
        
        carSearchCriterionSelectedType = "";
        carSearchCriterionSelectedValue = "";
        
        addCar = new Car();
        addCarErrMsg = "";
    }

    public CarRepository getCarRepository() {
        return carRepository;
    }

    public void setCarRepository(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getCarSearchCriterionSelectedType() {
        return carSearchCriterionSelectedType;
    }

    public void setCarSearchCriterionSelectedType(String carSearchCriterionSelectedType) {
        this.carSearchCriterionSelectedType = carSearchCriterionSelectedType;
    }

    public String getCarSearchCriterionSelectedValue() {
        return carSearchCriterionSelectedValue;
    }

    public void setCarSearchCriterionSelectedValue(String carSearchCriterionSelectedValue) {
        this.carSearchCriterionSelectedValue = carSearchCriterionSelectedValue;
    }

    public Car getAddCar() {
        return addCar;
    }

    public void setAddCar(Car addCar) {
        this.addCar = addCar;
    }

    public String getAddCarErrMsg() {
        return addCarErrMsg;
    }

    public void setAddCarErrMsg(String addCarErrMsg) {
        this.addCarErrMsg = addCarErrMsg;
    }

    public BingImageSearch getBingImageSearch() {
        return bingImageSearch;
    }

    public void setBingImageSearch(BingImageSearch bingImageSearch) {
        this.bingImageSearch = bingImageSearch;
    }

    public BingWebSearch getBingWebSearch() {
        return bingWebSearch;
    }

    public void setBingWebSearch(BingWebSearch bingWebSearch) {
        this.bingWebSearch = bingWebSearch;
    }
    
    public List<Car> searchOnclick() throws Exception{// deal with search car button in salesperson page
        //System.out.println(carSearchCriterionSelectedType);
        //System.out.println(carSearchCriterionSelectedValue);
        List<Car> resultCars = null;
        switch (carSearchCriterionSelectedType) {
            case "All Cars":
                resultCars = carRepository.getAllCars();
                if(resultCars == null || resultCars.isEmpty())  {errMsg = "No cars found!";}
                else    {errMsg = "";}
                return resultCars;
            case "Model No":
                if(carSearchCriterionSelectedValue.matches("[0-9]+")){
                    resultCars = carRepository.getCarsByModelNo(Integer.parseInt(carSearchCriterionSelectedValue));
                    if(resultCars == null || resultCars.isEmpty())  {errMsg = "No cars found!";}
                    else    {errMsg = "";}
                    return resultCars;
                } else{
                    errMsg = "Invalid input for Model No! Input a number!";
                    return null;
                }
            case "Model Name":
                resultCars = carRepository.getCarsByModelName(carSearchCriterionSelectedValue);
                if(resultCars == null || resultCars.isEmpty())  {errMsg = "No cars found!";}
                else    {errMsg = "";}
                return resultCars;
            case "Maker":
                resultCars = carRepository.getCarsByMaker(carSearchCriterionSelectedValue);
                if(resultCars == null || resultCars.isEmpty())  {errMsg = "No cars found!";}
                else    {errMsg = "";}
                return resultCars;
            case "Type":
                if(carSearchCriterionSelectedValue.equals("Sedan") || carSearchCriterionSelectedValue.equals("4 wheel drive") || carSearchCriterionSelectedValue.equals("Truck")){
                    resultCars = carRepository.getCarsByType(carSearchCriterionSelectedValue);
                    if(resultCars == null || resultCars.isEmpty())  {errMsg = "No cars found!";}
                    else    {errMsg = "";}
                    return resultCars;
                } else{
                    errMsg = "Invalid input for Type! Input Sedan, 4 wheel drive, or Truck!";
                    return null;
                }
            case "State":
                if(carSearchCriterionSelectedValue.equals("sold") || carSearchCriterionSelectedValue.equals("unsold")){
                    resultCars = carRepository.getCarsBySellState(carSearchCriterionSelectedValue);
                    if(resultCars == null || resultCars.isEmpty())  {errMsg = "No cars found!";}
                    else    {errMsg = "";}
                    return resultCars;
                } else{
                    errMsg = "Invalid input for State! Input sold or unsold!";
                    return null;
                }
            default:
                return null;
        }
    }
    
    public String updateCar(Long carId) throws Exception{
//        System.out.println(car.toString());
        
        //carRepository.updateCar(car);
        Car updateCar = carRepository.searchCarById(carId);
        if(updateCar != null){
            setSessionValue("updateCar", updateCar);
            return "/updateCarPage?faces-redirect=true"; 
        }
        return null;  
    }
    
//    public void addNewCar() throws Exception{
//        if(String.valueOf(addCar.getModelNo())!=null && addCar.getModelName()!=null && addCar.getVIN()!=null && addCar.getMaker()!=null && addCar.getType()!=null && addCar.getThumbnail()!=null && addCar.getDescription()!=null && addCar.getPreviewUrl()!=null && addCar.getSellState()!=null){
//            if(!String.valueOf(addCar.getModelNo()).equals("") && !addCar.getModelName().equals("") && !addCar.getVIN().equals("") && !addCar.getMaker().equals("") && !addCar.getType().equals("") && !addCar.getThumbnail().equals("")
//                && !addCar.getDescription().equals("") && !addCar.getPreviewUrl().equals("") && !addCar.getSellState().equals("")){
//                int modelNo = addCar.getModelNo();
//                String modelName = addCar.getModelName();
//                String vin = addCar.getVIN();
//                String maker = addCar.getMaker();
//                String type = addCar.getType();
//                String thumbnail = addCar.getThumbnail();
//                String description = addCar.getDescription();
//                String previewUrl = addCar.getPreviewUrl();
//                String sellState = addCar.getSellState();
//                Car newCar = new Car();
//                newCar.setModelNo(modelNo);
//                newCar.setModelName(modelName);
//                newCar.setVIN(vin);
//                newCar.setMaker(maker);
//                newCar.setType(type);
//                newCar.setThumbnail(thumbnail);
//                newCar.setDescription(description);
//                newCar.setPreviewUrl(previewUrl);
//                newCar.setSellState(sellState);
//                System.out.println(newCar.toString());
//                carRepository.addCar(newCar);
//                addCarErrMsg = "";
//            } else{
//                addCarErrMsg = "Please fill all blanks!";
//            }
//        } else{
//            addCarErrMsg = "One or more user attributes are null!";
//        }
//    }
    
    
    public void addNewCar() throws Exception{
        if(addCar.getMaker()!=null && addCar.getType()!=null && addCar.getSellState()!=null && addCar.getModelName()!=null){
            if(!addCar.getMaker().equals("") && !addCar.getType().equals("") && !addCar.getSellState().equals("") && !addCar.getModelName().equals("")){
                RandomGenerator randomGenerator = new RandomGenerator();
                int modelNo = randomGenerator.getRandomModelNo();
                String VIN = randomGenerator.getRandomVIN();
                String modelName = addCar.getModelName();
                String maker = addCar.getMaker();
                String type = addCar.getType();
                String thumbnail = bingImageSearch.getImageUrl(maker + " " + type);
                String description = bingWebSearch.getDescriptionAndPreviewUrl(maker + " " + type)[0];
                String previewUrl = bingWebSearch.getDescriptionAndPreviewUrl(maker + " " + type)[1];
                String sellState = addCar.getSellState();
                Car newCar = new Car();
                newCar.setModelNo(modelNo);
                newCar.setModelName(modelName);
                newCar.setVIN(VIN);
                newCar.setMaker(maker);
                newCar.setType(type);
                newCar.setThumbnail(thumbnail);
                newCar.setDescription(description);
                newCar.setPreviewUrl(previewUrl);
                newCar.setSellState(sellState);
                System.out.println(newCar.toString());
                carRepository.addCar(newCar);
                addCarErrMsg = "";
                       
            } else{
                addCarErrMsg = "Please fill all blanks!";
            }
        } else{
            addCarErrMsg = "One or more user attributes are null!";
        }
    }
    
    
    public void deleteCar(Car car) throws Exception{
        Long carId = car.getCarId();
        carRepository.deleteCar(carId);
    
    }
    
    
}
